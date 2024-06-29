package org.example.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.actuator.Actuator;
import org.example.actuator.ActuatorEnum;
import org.example.protocol.Packet;

public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        Actuator actuator = ActuatorEnum.getActuatorByFlag((byte) (msg.getActuatorFlag()+1));
        actuator.execute(ctx, msg);
    }
}
