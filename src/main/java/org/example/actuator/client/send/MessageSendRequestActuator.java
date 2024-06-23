package org.example.actuator.client.send;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.example.actuator.Actuator;
import org.example.protocol.Packet;
import org.example.protocol.PacketCodeC;
import org.example.protocol.request.MessageRequestPacket;

import java.util.Scanner;

public class MessageSendRequestActuator implements Actuator {

    public static final MessageSendRequestActuator INSTANCE = new MessageSendRequestActuator();

    @Override
    public void execute(ChannelHandlerContext ctx, Packet... msg) {
        System.out.println("输入消息发送至服务端: ");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Channel channel = ctx.channel();
        MessageRequestPacket packet = MessageRequestPacket.builder().message(line).build();
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
        channel.writeAndFlush(byteBuf);
    }
}
