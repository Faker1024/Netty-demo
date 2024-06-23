package org.example.actuator;

import io.netty.channel.ChannelHandlerContext;
import org.example.protocol.Packet;

public interface Actuator {
    public void execute(ChannelHandlerContext ctx, Packet... msg);
}
