package org.example.actuator.client.receive;

import io.netty.channel.ChannelHandlerContext;
import org.example.actuator.Actuator;
import org.example.protocol.Packet;
import org.example.protocol.response.LoginResponsePacket;
import org.example.util.LoginUtil;

import java.util.Date;

public class LoginReceiveResponseActuator implements Actuator {

    public static final LoginReceiveResponseActuator INSTANCE = new LoginReceiveResponseActuator();

    @Override
    public void execute(ChannelHandlerContext ctx, Packet... packets) {
        Packet packet = packets[0];
        if (packet instanceof LoginResponsePacket loginResponsePacket) {
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登陆成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else {
                System.out.println(new Date() + ": 客户端登陆失败, 原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
