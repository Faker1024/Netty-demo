package org.example.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protocol.response.LoginResponsePacket;
import org.example.session.Session;
import org.example.util.SessionUtil;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userId = loginResponsePacket.getUserId();
        String username = loginResponsePacket.getUsername();
        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + username + "] 登录成功，userId为：" + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        }else{
            System.out.println("[" + username + "]登录失败， 原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }
}
