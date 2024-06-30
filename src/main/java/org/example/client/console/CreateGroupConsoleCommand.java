package org.example.client.console;

import io.netty.channel.Channel;
import org.example.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand{

    private static final String USER_ID_SPLIT = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入 userId 列表， userId之间英文逗号隔开：");
        String userIds = scanner.next();
        CreateGroupRequestPacket request = CreateGroupRequestPacket.builder().userIdList(Arrays.asList(userIds.split(USER_ID_SPLIT))).build();
        channel.writeAndFlush(request);
    }
}
