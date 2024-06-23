package org.example.protocol.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.LOGIN_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
