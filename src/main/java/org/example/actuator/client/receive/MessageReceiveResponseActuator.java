package org.example.actuator.client.receive;

import io.netty.channel.ChannelHandlerContext;
import org.example.actuator.Actuator;
import org.example.protocol.Packet;
import org.example.protocol.response.MessageResponsePacket;

import java.util.Date;

public class MessageReceiveResponseActuator implements Actuator {

    public static final MessageReceiveResponseActuator INSTANCE = new MessageReceiveResponseActuator();

    @Override
    public void execute(ChannelHandlerContext ctx, Packet... packets) {
        if (packets[0] instanceof MessageResponsePacket messageResponsePacket) {
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }
}
