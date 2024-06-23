package org.example.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.actuator.ActuatorEnum;
import org.example.protocol.Packet;
import org.example.protocol.PacketCodeC;
import org.example.util.LoginUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (!LoginUtil.hasLogin(channel)) {
            ActuatorEnum.getActuatorByFlag((byte) 1).execute(ctx);
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responseByteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(responseByteBuf);
        responseByteBuf.release();
        ActuatorEnum.getActuatorByFlag(packet.getActuatorFlag()).execute(ctx, packet);
    }
}
