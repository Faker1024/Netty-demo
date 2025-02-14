package org.example.client.console;

import io.netty.channel.Channel;
import org.example.protocol.request.MessageRequestPacket;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户Id以及消息内容，以空格隔开：");
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
        System.out.println("已发送消息：" + message + " ->" + toUserId);
    }
}
