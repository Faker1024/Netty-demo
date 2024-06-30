package org.example.protocol.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.MESSAGE_REQUEST;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageRequestPacket extends Packet {

    private String toId;

    // user or group
    private String type;

    private String message;

    public MessageRequestPacket(String toId, String message) {
        this(toId, message,"user");
    }

    public MessageRequestPacket(String toId, String type, String message) {
        this.toId = toId;
        this.type = type;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
