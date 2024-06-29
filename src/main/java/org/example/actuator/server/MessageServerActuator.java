package org.example.actuator.server;

import io.netty.channel.ChannelHandlerContext;
import org.example.actuator.Actuator;
import org.example.protocol.Packet;
import org.example.protocol.request.MessageRequestPacket;
import org.example.protocol.response.MessageResponsePacket;

import java.util.Date;

public class MessageServerActuator implements Actuator {

    public static final MessageServerActuator INSTANCE = new MessageServerActuator();

    @Override
    public void execute(ChannelHandlerContext ctx, Packet... msg) {
        if (msg[0] instanceof MessageRequestPacket request) {
            System.out.println(new Date() + ": 收到客户端消息：" + request.getMessage());
            MessageResponsePacket response = MessageResponsePacket.builder().message("服务端回复【" + request.getMessage() + "】").build();
            ctx.channel().writeAndFlush(response);
        }
    }
}
