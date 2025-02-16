package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.example.client.console.ConsoleManager;
import org.example.client.console.LoginConsoleCommand;
import org.example.client.console.SendToUserConsoleCommand;
import org.example.client.handler.*;
import org.example.codec.PacketDecoder;
import org.example.codec.PacketEncoder;
import org.example.codec.Spliter;
import org.example.util.SessionUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        ConsoleManager consoleManager = new ConsoleManager();
        new Thread(() -> {
            while (!Thread.interrupted()){
                if (!SessionUtil.hasLogin(channel)) {
                    new LoginConsoleCommand().exec(sc, channel);
                }else{
                    consoleManager.exec(sc, channel);
                }
            }
        }).start();
    }


    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
                startConsoleThread(((ChannelFuture)future).channel());
            } else if (retry == 0) {
                System.out.println("已无重试次数， 连接失败");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }


    public static void main(String[] args) {
        Bootstrap clientBootstrap = new Bootstrap();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        clientBootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
                                .addLast(new ListGroupMembersResponseHandler())
                                .addLast(new QuitGroupResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
//                                .addLast(new ClientHandler())
                                .addLast(new PacketEncoder());
                    }
                });
        connect(clientBootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }
}
