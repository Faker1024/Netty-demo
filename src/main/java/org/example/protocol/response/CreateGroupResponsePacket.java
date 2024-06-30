package org.example.protocol.response;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import java.util.List;

import static org.example.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
