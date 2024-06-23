package org.example.protocol.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.MESSAGE_REQUEST;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MessageRequestPacket extends Packet {

    private String message;

    {
        super.actuatorFlag = 4;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
