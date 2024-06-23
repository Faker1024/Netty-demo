package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.actuator.Actuator;
import org.example.actuator.ActuatorEnum;
import org.example.protocol.Packet;
import org.example.protocol.PacketCodeC;

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        requestByteBuf.release();
        Actuator actuator = ActuatorEnum.getActuatorByFlag((byte) (packet.getActuatorFlag()+1));
        actuator.execute(ctx, packet);
    }
}
