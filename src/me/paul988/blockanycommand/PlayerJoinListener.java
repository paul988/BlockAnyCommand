package me.paul988.blockanycommand;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    BlockAnyCommand plugin;

    public PlayerJoinListener(BlockAnyCommand passedPlugin) {
        this.plugin = passedPlugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("Notify-Update") == true && plugin.getConfig().getBoolean("Update-Check") == true) {
            if (player.hasPermission("BlockAnyCommand.update.notify") && plugin.update || player.isOp() && plugin.update) {
                player.sendMessage("An update is available: " + plugin.name + ", a " + plugin.type + " for " + plugin.version + " available at " + plugin.link);
                player.sendMessage("Type /update if you would like to automatically update.");
            }
        }
    }
}
