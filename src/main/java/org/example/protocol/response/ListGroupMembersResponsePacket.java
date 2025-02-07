package org.example.protocol.response;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.protocol.Packet;
import org.example.session.Session;

import java.util.List;

import static org.example.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
