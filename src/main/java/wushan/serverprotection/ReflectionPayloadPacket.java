package wushan.serverprotection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;

public class ReflectionPayloadPacket {
  private ServerProtection plugin;
  
  public ReflectionPayloadPacket(ServerProtection plugin) {
    this.plugin = plugin;
    Messenger messenger = Bukkit.getMessenger();
    try {
      Method method = messenger.getClass().getDeclaredMethod("addToOutgoing", new Class[] { Plugin.class, String.class });
      method.setAccessible(true);
      method.invoke(messenger, new Object[] { plugin, "minecraft:brand" });
    } catch (Exception exception) {}
  }
  
  public void send(Player player, String string) {
    sendRaw(player, string);
  }
  
  public void sendRaw(Player player, String brand) {
    checkPlayerChannels(player);
    player.sendPluginMessage((Plugin)this.plugin, "minecraft:brand", (new PacketSerializer(brand)).toArray());
  }
  
  private void checkPlayerChannels(Player player) {
    try {
      Field playerChannels = player.getClass().getDeclaredField("channels");
      playerChannels.setAccessible(true);
      Set<String> channels = (Set<String>)playerChannels.get(player);
      if (!channels.contains("minecraft:brand"))
        channels.add("minecraft:brand"); 
    } catch (Exception exception) {}
  }
}
