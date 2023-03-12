package sattera.iriscraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideExecutor implements CommandExecutor {
    private final IrisCraft plugin;

    public HideExecutor(IrisCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HideManager manager = HideManager.inst;
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("nickhider.self")) {
                    if (manager.isHided(player)) {
                        manager.unHidePlayer(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a昵称标签已显示."));
                    } else {
                        manager.hidePlayer(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a昵称标签已隐藏."));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你没有权限."));
                }
            } else {
                sender.sendMessage("非控制台命令.");
            }
            return true;
        } else if (args.length == 1) {
            if (sender.hasPermission("nickhider.other")) {
                Player otherPlayer = Bukkit.getPlayer(args[0]);
                if (otherPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7未找到玩家."));
                } else {
                    if (manager.isHided(otherPlayer)) {
                        manager.unHidePlayer(otherPlayer);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a该玩家的昵称标签已显示."));
                    } else {
                        manager.hidePlayer(otherPlayer);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a该玩家的昵称标签已隐藏."));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你没有权限."));
            }
        }
        return true;
    }
}
