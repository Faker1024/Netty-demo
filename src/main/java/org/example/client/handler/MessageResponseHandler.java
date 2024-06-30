package org.example.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protocol.response.MessageResponsePacket;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        String userId = messageResponsePacket.getFormUserId();
        String userName = messageResponsePacket.getFromUserName();
        System.out.println(userId + ":" + userName + " -> " + messageResponsePacket.getMessage());
    }
}
