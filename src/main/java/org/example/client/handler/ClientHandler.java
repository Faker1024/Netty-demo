package org.example.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.actuator.ActuatorEnum;
import org.example.protocol.Packet;
import org.example.util.LoginUtil;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (!LoginUtil.hasLogin(channel)) {
            ActuatorEnum.getActuatorByFlag((byte) 1).execute(ctx);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        ActuatorEnum.getActuatorByFlag(packet.getActuatorFlag()).execute(ctx, packet);
    }
}
