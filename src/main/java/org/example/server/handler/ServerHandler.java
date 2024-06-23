package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.protocol.Packet;
import org.example.protocol.PacketCodeC;
import org.example.protocol.request.LoginRequestPacket;
import org.example.protocol.response.LoginResponsePacket;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "receive your message !!!".getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        return buffer.writeBytes(bytes);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": 客户端开始登陆");
        ByteBuf requestByteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        requestByteBuf.release();
        LoginResponsePacket loginResponsePacket = null;
        if (packet instanceof LoginRequestPacket loginRequestPacket) {
            if (valid(loginRequestPacket)) {
                System.out.println(new Date() + ": 登录成功");
                loginResponsePacket = LoginResponsePacket.builder().success(true).build();
            }else {
                System.out.println(new Date() + ": 登录失败");
                loginResponsePacket = LoginResponsePacket.builder().success(false)
                        .reason("账号密码校验失败").build();
            }
        }
        ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket));
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
