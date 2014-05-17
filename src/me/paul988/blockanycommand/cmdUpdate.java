package me.paul988.blockanycommand;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdUpdate implements CommandExecutor {

    BlockAnyCommand plugin;

    public cmdUpdate(BlockAnyCommand passedPlugin) {
        this.plugin = passedPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (sender.hasPermission("BlockAnyCommand.update") || sender.isOp()) {
            if (plugin.update) {
                plugin.updateStart();
                if (sender instanceof Player) {
                    sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + " Update has started.");
                }
            } else {
                Logger logger = plugin.getLogger();
                logger.info("There is no update available.");
                if (sender instanceof Player) {
                    sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + " There is no update available.");
                }
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("PermMessage").replace("&", "§"));
        }
        return true;
    }
}
