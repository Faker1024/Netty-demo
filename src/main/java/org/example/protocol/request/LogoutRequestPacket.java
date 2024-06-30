package org.example.protocol.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.LOGOUT_REQUEST;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
