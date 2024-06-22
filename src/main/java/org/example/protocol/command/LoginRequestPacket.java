package org.example.protocol.command;

import static org.example.protocol.command.Command.LOGIN_REQUEST;

public class LoginRequestPacket extends Packet{
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
