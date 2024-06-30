package org.example.protocol.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.LOGIN_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String username;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
