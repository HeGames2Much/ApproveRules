package club.therepo.approverules.listeners;

import club.therepo.approverules.ApproveRules;
import club.therepo.approverules.ApproveRulesPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ApproveRulesListener implements Listener {

    private final ApproveRulesPlugin plugin;

    public ApproveRulesListener(ApproveRulesPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin.plugin);
    }

    // This method checks for incoming players and sends them a message
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);

        if (ApproveRules.plugin.plugin.getConfig().getBoolean("options.enable")) {
            if(!player.hasPermission("approverules.read")) {
                for(String messages : ApproveRules.plugin.plugin.getConfig().getStringList("rules")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fType &a/acceptrules &fto accept the rules."));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer(); //Make player object.

        if (ApproveRules.plugin.plugin.getConfig().getBoolean("options.enable")) {
            if(!player.hasPermission("approverules.read")) {
                final Location from = event.getFrom();
                final Location to = event.getTo();
                if (from.getX() != to.getX() || from.getZ() != to.getZ() || from.getY() != to.getY()) {
                    event.setTo(from);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlaced(BlockPlaceEvent event) {
        Player player = event.getPlayer(); //Make player object.

        if (ApproveRules.plugin.plugin.getConfig().getBoolean("options.enable")) {
            if (!player.hasPermission("approverules.read")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer(); //Make player object.

        if (ApproveRules.plugin.plugin.getConfig().getBoolean("options.enable")) {
            if (!player.hasPermission("approverules.read")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity player = event.getDamager(); //Make player object.

        if (ApproveRules.plugin.plugin.getConfig().getBoolean("options.enable")) {
            if (!player.hasPermission("approverules.read")) {
                if (player instanceof Player) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onHit(EntityDamageEvent event) {
        Entity player = event.getEntity(); //Make player object.

        if (ApproveRules.plugin.plugin.getConfig().getBoolean("options.enable")) {
            if (!player.hasPermission("approverules.read")) {
                if (player instanceof Player) {
                    event.setCancelled(true);
                }
            }
        }
    }

}