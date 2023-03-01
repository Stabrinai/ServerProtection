//package sattera.serverprotection;

//import java.util.ArrayList;
//import java.util.List;
//
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.Player;
//import org.bukkit.entity.Villager;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.player.*;

//public class PlayerListener implements org.bukkit.event.Listener {
//    @EventHandler
//    public void playerTeleport(PlayerTeleportEvent event) {
//        List<String> whiteList = new ArrayList<String>() {
//            {
//                add("world");
//            }
//        };
//        String worldName = event.getTo().getWorld().getName();
//        boolean status = whiteList.contains(worldName);
//        if (!status) {
//            Player player = event.getPlayer();
//            status = player.hasPermission("world." + worldName);
//            if (!status) {
//                event.setCancelled(true);
//            }
//        }
//    }

//    @EventHandler
//    public void playerPortal(PlayerPortalEvent event) {
//        List<String> whiteList = new ArrayList<String>() {
//            {
//                add("world");
//            }
//        };
//        String worldName = event.getTo().getWorld().getName();
//        boolean status = whiteList.contains(worldName);
//        if (!status) {
//            Player player = event.getPlayer();
//            status = player.hasPermission("world." + worldName);
//            if (!status) {
//                event.setCancelled(true);
//            }
//        }
//    }
//    @EventHandler
//    public void onVillagerTrade(PlayerInteractEntityEvent event) {
//        Entity entity = event.getRightClicked();
//        if (!(entity instanceof Villager)) {
//            return;
//        }
//        Player player = event.getPlayer();
//        if (player.hasPermission("novillagertrades.bypass")) {
//            return;
//        }
//        event.setCancelled(true);
//    }
//    @EventHandler(ignoreCancelled = true)
//    public void onShear(PlayerShearEntityEvent event) {
//        if (event.getEntity().getType() == EntityType.MUSHROOM_COW)
//            event.setCancelled(true);
//    }
//}

//public class PlayerListener implements Listener {
//    List<String> protectionWorlds = new ArrayList<String>() {
//        {
//            add("rpg");
//        }
//    };
//
//    List<String> preventTridentWorlds = new ArrayList<String>() {
//        {
//            add("Plot");
//        }
//    };

//    @EventHandler
//    public void onPlace(BlockPlaceEvent e) {
//        Player p = e.getPlayer();
//        if (this.protectionWorlds.contains(p.getWorld().getName()) && !p.isOp())
//            e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onBreak(BlockBreakEvent e) {
//        Player p = e.getPlayer();
//        if (this.protectionWorlds.contains(p.getWorld().getName()) && !p.isOp())
//            e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onInteract(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        if (this.protectionWorlds.contains(p.getWorld().getName()) && e.hasBlock() && !p.isOp())
//            e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onPlayerBucketFillEvent(PlayerBucketFillEvent e) {
//        Player p = e.getPlayer();
//        if (this.protectionWorlds.contains(p.getWorld().getName()))
//            e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
//        Player p = e.getPlayer();
//        if (this.protectionWorlds.contains(p.getWorld().getName()))
//            e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void PlayerInteractEvent(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && (e
//                .getPlayer().getInventory().getItemInMainHand().getType().equals(Material.TRIDENT)
//                || e.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.TRIDENT))
//                && this.preventTridentWorlds.contains(e.getPlayer().getWorld().getName())
//                && !p.isOp())
//            e.setCancelled(true);
//    }
//}