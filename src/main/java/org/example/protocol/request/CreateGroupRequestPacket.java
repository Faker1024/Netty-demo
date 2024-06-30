package org.example.protocol.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;

import java.util.List;

import static org.example.protocol.command.Command.CREATE_GROUP_REQUEST;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
