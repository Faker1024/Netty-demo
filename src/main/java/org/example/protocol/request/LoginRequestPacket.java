package org.example.protocol.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.LOGIN_REQUEST;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
