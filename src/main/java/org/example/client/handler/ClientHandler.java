package org.example.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.protocol.Packet;
import org.example.protocol.PacketCodeC;
import org.example.protocol.request.LoginRequestPacket;
import org.example.protocol.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登陆");
        LoginRequestPacket request = LoginRequestPacket.builder()
                .userId(UUID.randomUUID().toString())
                .username("test")
                .password("123").build();
        ByteBuf requestBuffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), request);
        ctx.channel().writeAndFlush(requestBuffer);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket loginResponsePacket) {
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登陆成功");
            }else {
                System.out.println(new Date() + ": 客户端登陆失败, 原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
