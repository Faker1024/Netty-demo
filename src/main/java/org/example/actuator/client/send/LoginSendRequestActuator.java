package org.example.actuator.client.send;

import io.netty.channel.ChannelHandlerContext;
import org.example.actuator.Actuator;
import org.example.protocol.Packet;
import org.example.protocol.request.LoginRequestPacket;

import java.util.Date;
import java.util.UUID;

public class LoginSendRequestActuator implements Actuator {

    public static final LoginSendRequestActuator INSTANCE = new LoginSendRequestActuator();

    @Override
    public void execute(ChannelHandlerContext ctx, Packet... packets) {
        System.out.println(new Date() + ": 客户端开始登陆");
        LoginRequestPacket request = LoginRequestPacket.builder()
                .userId(UUID.randomUUID().toString())
                .username("test")
                .password("123").build();
        ctx.channel().writeAndFlush(request);
    }
}
