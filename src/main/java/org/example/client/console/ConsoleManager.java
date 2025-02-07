package org.example.client.console;

import io.netty.channel.Channel;
import org.example.util.SessionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleManager implements ConsoleCommand{

    private Map<String, ConsoleCommand> consoleCommands;

    public ConsoleManager() {
        consoleCommands = new HashMap<>();
        consoleCommands.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommands.put("logout", new LogoutConsoleCommand());
        consoleCommands.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommands.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommands.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommands.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();
        if (!SessionUtil.hasLogin(channel)) return;
        ConsoleCommand consoleCommand = consoleCommands.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        }else{
            System.err.println("无法识别【" + command + "】指针，请重新输入以下指令之一！");
            System.out.println(consoleCommands.keySet());
        }
    }
}
