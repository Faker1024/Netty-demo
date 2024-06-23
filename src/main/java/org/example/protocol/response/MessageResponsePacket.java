package org.example.protocol.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.MESSAGE_RESPONSE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
