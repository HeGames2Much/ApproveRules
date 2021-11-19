package club.therepo.approverules.commands;

import club.therepo.approverules.ApproveRules;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            for (String messages : ApproveRules.plugin.plugin.getConfig().getStringList("rules")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
            }
            String msg = "%sender% Ran the command %command%!"
                    .replaceAll("%sender%".toLowerCase(), sender.getName())
                    .replaceAll("%command%".toLowerCase(), command.getName());
            ApproveRules.plugin.plugin.getLogger().info(msg);
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
