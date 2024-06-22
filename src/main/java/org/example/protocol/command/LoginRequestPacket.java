package org.example.protocol.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static org.example.protocol.command.Command.LOGIN_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class LoginRequestPacket extends Packet{
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
