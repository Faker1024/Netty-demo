package org.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protocol.request.LogoutRequestPacket;
import org.example.protocol.response.LogoutResponsePacket;
import org.example.util.SessionUtil;

public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        SessionUtil.unbBindSession(ctx.channel());
        ctx.channel().writeAndFlush(LogoutResponsePacket.builder().success(true).build());
    }
}
