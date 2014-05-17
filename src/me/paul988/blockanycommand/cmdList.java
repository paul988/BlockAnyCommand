package me.paul988.blockanycommand;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdList implements CommandExecutor {

    BlockAnyCommand plugin;

    int bc = 0;
    String bcstr = "";
    
    public cmdList(BlockAnyCommand passedPlugin) {
        this.plugin = passedPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (sender.hasPermission("BlockAnyCommand.list")) {
            sender.sendMessage(ChatColor.RED + "===== " + plugin.getConfig().getString("Prefix").replace("&", "§") + ChatColor.RED + " =====");
            List<String> command = plugin.getConfig().getStringList("BlockedCommands.global");
            for (String str : command) {
                bc++;
                bcstr = "" + bc;
                sender.sendMessage(ChatColor.RED + bcstr + ". " + ChatColor.GRAY + "/" + str);
                }
        } else {
            sender.sendMessage(plugin.getConfig().getString("PermMessage").replace("&", "§"));
        }
        return true;
    }

}
