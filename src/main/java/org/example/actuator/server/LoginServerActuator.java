package org.example.actuator.server;

import io.netty.channel.ChannelHandlerContext;
import org.example.actuator.Actuator;
import org.example.protocol.Packet;
import org.example.protocol.PacketCodeC;
import org.example.protocol.request.LoginRequestPacket;
import org.example.protocol.response.LoginResponsePacket;

import java.util.Date;

public class LoginServerActuator implements Actuator {

    public static final LoginServerActuator INSTANCE = new LoginServerActuator();

    @Override
    public void execute(ChannelHandlerContext ctx, Packet... packets) {
        System.out.println(new Date() + ": 客户端开始登陆");
        Packet packet = packets[0];
        LoginResponsePacket loginResponsePacket = null;
        if (packet instanceof LoginRequestPacket loginRequestPacket) {
            if (valid(loginRequestPacket)) {
                System.out.println(new Date() + ": 登录成功");
                loginResponsePacket = LoginResponsePacket.builder().success(true).build();
            }else {
                System.out.println(new Date() + ": 登录失败");
                loginResponsePacket = LoginResponsePacket.builder().success(false)
                        .reason("账号密码校验失败").build();
            }
        }
        ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket));
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
