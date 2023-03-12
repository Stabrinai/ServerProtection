package sattera.iriscraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Handler implements Listener {
    private final IrisCraft plugin;

    public Handler(IrisCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        HideManager.inst.hidedPlayer.add(e.getPlayer());
        Stream<String> stream = Stream.concat(
                Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName),
                HideManager.inst.hidedPlayer.stream().map(HumanEntity::getName)
        );
        Set<String> list = stream.collect(Collectors.toSet());
        if (list.size() > 0) {
            for (String s : list) {
                HideManager.inst.hideNick(p, s);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        HideManager.inst.hidedPlayer.remove(e.getPlayer());
        HideManager.inst.unHidePlayer(e.getPlayer());
    }
}
