package org.example.client.console;

import io.netty.channel.Channel;
import org.example.protocol.request.LoginRequestPacket;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户名登录：");
        LoginRequestPacket request = LoginRequestPacket.builder()
                .username(scanner.nextLine())
                .password("pwd")
                .build();
        channel.writeAndFlush(request);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
