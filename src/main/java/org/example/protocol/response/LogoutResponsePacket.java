package org.example.protocol.response;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.LOGOUT_RESPONSE;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LogoutResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
