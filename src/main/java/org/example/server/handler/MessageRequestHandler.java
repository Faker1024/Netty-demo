package org.example.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.example.protocol.request.MessageRequestPacket;
import org.example.protocol.response.MessageResponsePacket;
import org.example.session.Session;
import org.example.util.SessionUtil;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 获取会话信息
        Session session = SessionUtil.getSession(ctx.channel());
        // 通过消息发送方的会话信息构建要发送的消息
        MessageResponsePacket response = MessageResponsePacket.builder().formUserId(session.getUserId())
                .fromUserName(session.getUserName()).message(msg.getMessage()).build();
        if ("group".equals(msg.getType())) {
            ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getToId());
            response.setFromGroupId(msg.getToId());
            if (channelGroup != null) {channelGroup.writeAndFlush(response);}
        }else{
            Channel toChannel = SessionUtil.getChannel(msg.getToId());
            if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
                toChannel.writeAndFlush(response);
            }else{
                System.err.println("[" + session.getUserId() + "] 不在线发送失败");
            }
        }
    }
}
