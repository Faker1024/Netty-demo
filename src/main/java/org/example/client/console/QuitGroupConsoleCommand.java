package org.example.client.console;

import io.netty.channel.Channel;
import org.example.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，退出群聊：");
        channel.writeAndFlush(QuitGroupRequestPacket.builder().groupId(scanner.next()).build());
    }
}
