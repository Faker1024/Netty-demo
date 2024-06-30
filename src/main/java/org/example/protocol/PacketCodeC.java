package org.example.protocol;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import org.example.protocol.request.CreateGroupRequestPacket;
import org.example.protocol.request.LoginRequestPacket;
import org.example.protocol.request.LogoutRequestPacket;
import org.example.protocol.request.MessageRequestPacket;
import org.example.protocol.response.CreateGroupResponsePacket;
import org.example.protocol.response.LoginResponsePacket;
import org.example.protocol.response.LogoutResponsePacket;
import org.example.protocol.response.MessageResponsePacket;
import org.example.serialize.Serializer;
import org.example.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.example.protocol.command.Command.*;

@Data
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x1234;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;
    public static final PacketCodeC INSTANCE;

    private PacketCodeC(){}

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        INSTANCE = new PacketCodeC();
        serializerMap = new HashMap<>();
        JSONSerializer jsonSerializer = new JSONSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlgorithm(), jsonSerializer);
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }

    private Class<? extends Packet> getRequestType(Byte command) {
        return packetTypeMap.get(command);
    }


    public Packet decode(ByteBuf byteBuf) {
        int magicNumber = byteBuf.readInt();
        if (magicNumber!= MAGIC_NUMBER) {
            System.out.println("magic number is not match. magic number = " + magicNumber);
            return null;
        }
        // 跳过版本检测
        byteBuf.skipBytes(1);

        byte serializeAlgorithm = byteBuf.readByte();

        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte[] dst = new byte[length];

        byteBuf.readBytes(dst);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, dst);
        }
        return null;
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        return byteBuf.writeInt(MAGIC_NUMBER)
                .writeByte(packet.getVersion())
                .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
                .writeByte(packet.getCommand())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }

}
