package org.example.protocol.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import static org.example.protocol.command.Command.JOIN_GROUP_REQUEST;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JoinGroupRequestPacket extends Packet {

    private String groupId;


    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
