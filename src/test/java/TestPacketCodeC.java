import io.netty.buffer.ByteBuf;
import org.example.protocol.command.LoginRequestPacket;
import org.example.protocol.command.Packet;
import org.example.protocol.command.PacketCodeC;
import org.junit.Test;

public class TestPacketCodeC {

    @Test
    public void testDecode(){
        LoginRequestPacket request = LoginRequestPacket.builder().username("test").password("123").userId(1).build();
        PacketCodeC packetCodeC = new PacketCodeC();
        System.out.println(request.toString());
        ByteBuf bytes = packetCodeC.encode(request);
        System.out.println(bytes.toString());
        Packet result = packetCodeC.decode(bytes);
        System.out.println(result.toString());
    }
}
