package org.example.client.console;

import io.netty.channel.Channel;
import org.example.protocol.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，获取群成员列表：");
        channel.writeAndFlush(ListGroupMembersRequestPacket.builder().groupId(scanner.next()).build());
    }
}
