package me.paul988.blockanycommand;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdReload implements CommandExecutor {

    BlockAnyCommand plugin;

    public cmdReload(BlockAnyCommand passedPlugin) {
        this.plugin = passedPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (sender.hasPermission("BlockAnyCommand.reload") || sender.isOp()) {
            Logger logger = plugin.getLogger();
            logger.info("Configuration file has been reloaded.");
            plugin.reloadConfig();
            if (sender instanceof Player) {
                sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + " Configuration file has been reloaded.");
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("PermMessage").replace("&", "§"));
        }
        return true;
    }

}
