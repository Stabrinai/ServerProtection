package sattera.iriscraft;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.charset.StandardCharsets;

public class PacketSerializer {

  private final byte[] result;
  
  public PacketSerializer(String string) {
    ByteBuf buf = Unpooled.buffer();
    writeString(string, buf);
    this.result = buf.array();
    buf.release();
  }
  
  private void writeString(String s, ByteBuf buf) {
    if (s.length() > 32767)
      throw new IllegalArgumentException(String.format("Cannot send string longer than Short.MAX_VALUE (got %s characters)", s.length()));
    byte[] b = s.getBytes(StandardCharsets.UTF_8);
    writeVarInt(b.length, buf);
    buf.writeBytes(b);
  }
  
  private void writeVarInt(int value, ByteBuf output) {
    do {
      int part = value & 0x7F;
      value >>>= 7;
      if (value != 0)
        part |= 0x80; 
      output.writeByte(part);
    } while (value != 0);
  }
  
  public byte[] toArray() {
    return this.result;
  }

}
