package org.example.protocol.command;

import lombok.Data;
import org.example.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

import static org.example.protocol.command.Command.LOGIN_REQUEST;

@Data
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x1234;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();

    }
}
