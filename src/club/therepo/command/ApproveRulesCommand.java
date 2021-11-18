package club.therepo.command;

import club.therepo.ApproveRules;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.DataType;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ApproveRulesCommand implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("approverules.read")) {
            for (String messages : ApproveRules.plugin.plugin.getConfig().getStringList("accept")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
            }
        } else {
            if (sender instanceof Player) {
                LuckPerms api = LuckPermsProvider.get();
                User user = api.getUserManager().getUser(((Player) sender).getUniqueId());
                PermissionNode node = PermissionNode.builder("approverules.read").build();
                user.getData(DataType.NORMAL).add(node);
                api.getUserManager().saveUser(user);

                for (String messages : ApproveRules.plugin.plugin.getConfig().getStringList("accept")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
                }
                ApproveRules.plugin.plugin.getLogger().info("{sender} Ran a command!");
            }
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
