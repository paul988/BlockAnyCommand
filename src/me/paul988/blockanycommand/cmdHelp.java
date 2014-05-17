package me.paul988.blockanycommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class cmdHelp implements CommandExecutor {

    BlockAnyCommand plugin;

    public cmdHelp(BlockAnyCommand passedPlugin) {
        this.plugin = passedPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (sender.hasPermission("BlockAnyCommand.help") || sender.isOp()) {
            PluginDescriptionFile pdfFile = plugin.getDescription();
            sender.sendMessage(ChatColor.RED + "===== " + plugin.getConfig().getString("Prefix").replace("&", "§") + ChatColor.RED + " =====");
            sender.sendMessage(ChatColor.RED + "1." + ChatColor.GRAY + " /bac" + ChatColor.WHITE +  " - Shows all commands.");
            sender.sendMessage(ChatColor.RED + "2." + ChatColor.GRAY + " /baclist" + ChatColor.WHITE +  " - Shows you a list of disabled commands.");
            sender.sendMessage(ChatColor.RED + "3." + ChatColor.GRAY + " /bacupdate" + ChatColor.WHITE + " - Updates the plugin.");
            sender.sendMessage(ChatColor.RED + "4." + ChatColor.GRAY + " /bacreload" + ChatColor.WHITE + " - Reloads the configuration file.");
        } else {
            sender.sendMessage(plugin.getConfig().getString("PermMessage").replace("&", "§"));
        }
        return true;
    }
}
