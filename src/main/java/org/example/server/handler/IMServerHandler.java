package org.example.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protocol.Packet;

import java.util.HashMap;
import java.util.Map;

import static org.example.protocol.command.Command.*;

@ChannelHandler.Sharable
public class IMServerHandler extends SimpleChannelInboundHandler<Packet> {

    public static IMServerHandler INSTANCE = new IMServerHandler();
    
    private Map<Byte, SimpleChannelInboundHandler> handlers = new HashMap<Byte, SimpleChannelInboundHandler>();

    {
        handlers.put(LOGIN_REQUEST, LoginRequestHandler.INSTANCE);
        handlers.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlers.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlers.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlers.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlers.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlers.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
