package club.therepo;

import club.therepo.command.ApproveRulesCommand;
import club.therepo.command.RulesCommand;
import club.therepo.listeners.ApproveRulesListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.Objects;

public class ApproveRulesPlugin {
    public final ApproveRules plugin;

    ApproveRulesPlugin(ApproveRules plugin) {
        this.plugin = plugin;
    }

    void enable() {
        plugin.getLogger().info("ApproveRules is starting up!");
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        Objects.requireNonNull(plugin.getCommand("acceptrules")).setExecutor(new ApproveRulesCommand());
        Objects.requireNonNull(plugin.getCommand("rules")).setExecutor(new RulesCommand());
        new ApproveRulesListener(this);
    }

    void disable() {
        plugin.getLogger().info("ApproveRules is shutting down!");
        HandlerList.unregisterAll(plugin);
        Bukkit.getScheduler().cancelTasks(plugin);
        plugin.getLogger().info("Goodbye!");
    }
}
