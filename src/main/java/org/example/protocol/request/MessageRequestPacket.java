package org.example.protocol.request;

import lombok.*;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.MESSAGE_REQUEST;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageRequestPacket extends Packet {

    private String toId;

    private String type;

    private String message;

    public MessageRequestPacket(String toId, String message) {
        this.toId = toId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
