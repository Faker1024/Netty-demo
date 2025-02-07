package org.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.example.protocol.request.ListGroupMembersRequestPacket;
import org.example.protocol.response.ListGroupMembersResponsePacket;
import org.example.session.Session;
import org.example.util.SessionUtil;

import java.util.List;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<Session> sessionList = channelGroup.stream().map(SessionUtil::getSession).toList();
        ctx.channel().writeAndFlush(ListGroupMembersResponsePacket.builder().groupId(groupId).sessionList(sessionList).build());
    }
}
