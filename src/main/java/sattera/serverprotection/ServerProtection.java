package sattera.serverprotection;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerProtection extends JavaPlugin implements Listener, ServerBrand {
  private static ServerProtection plugin;

  public void onEnable() {
    plugin = this;
//    Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
//    getServer().getPluginManager().registerEvents(new PlayerListener(), (Plugin)this);
  }

  public void onDisable() {
    HandlerList.unregisterAll((Plugin)this);
//    getServer().getScheduler().cancelTasks((Plugin)this);
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerQuit(PlayerQuitEvent e) {
      e.setQuitMessage(null);
  }

@EventHandler
  public void onJoin(PlayerJoinEvent e) {
    sendRaw(e.getPlayer().getUniqueId(), "vanilla");
    e.setJoinMessage(null);
  }
  
  public void sendRaw(Player player, String brand) {
    if (player == null)
      return; 
    (new ReflectionPayloadPacket(plugin)).send(player, brand);
  }
  
  public void sendRaw(UUID uuid, String brand) {
    sendRaw(Bukkit.getPlayer(uuid), brand);
  }
}
