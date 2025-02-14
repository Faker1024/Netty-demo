package org.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.example.protocol.request.QuitGroupRequestPacket;
import org.example.protocol.response.QuitGroupResponsePacket;
import org.example.util.SessionUtil;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getGroupId());
        ctx.channel().writeAndFlush(QuitGroupResponsePacket.builder().groupId(msg.getGroupId()).success(true).build());
        channelGroup.remove(ctx.channel());
    }
}
