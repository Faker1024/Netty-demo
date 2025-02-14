package org.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protocol.request.LoginRequestPacket;
import org.example.protocol.response.LoginResponsePacket;
import org.example.session.Session;
import org.example.util.SessionUtil;

import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static LoginRequestHandler INSTANCE = new LoginRequestHandler();


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket response = null;
        if (valid(msg)) {
            response = LoginResponsePacket.builder().success(true)
                    .userId(randomUserId()).build();
            System.out.println("[" + msg.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(response.getUserId(), msg.getUsername()), ctx.channel());
        }else {
            response = LoginResponsePacket.builder().success(false)
                   .reason("账号密码校验失败").build();
            System.out.println("[" + msg.getUsername() + "]登录失败");
        }
        ctx.channel().writeAndFlush(response.setUsername(msg.getUsername()).setVersion(msg.getVersion()));
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unbBindSession(ctx.channel());
    }
}
