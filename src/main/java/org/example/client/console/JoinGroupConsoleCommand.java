package org.example.client.console;

import io.netty.channel.Channel;
import org.example.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId， 加入群聊：");
        channel.writeAndFlush(JoinGroupRequestPacket.builder().groupId(scanner.next()).build());
    }
}
