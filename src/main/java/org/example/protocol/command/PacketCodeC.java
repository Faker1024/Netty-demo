package org.example.protocol.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import org.example.serialize.Serializer;
import org.example.serialize.impl.JSONSerializer;

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

    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        return byteBuf.writeInt(MAGIC_NUMBER)
                .writeByte(packet.getVersion())
                .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
                .writeByte(packet.getCommand())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }

}
