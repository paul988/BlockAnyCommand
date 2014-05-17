package me.paul988.blockanycommand;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockAnyCommandListener implements Listener {

    public static BlockAnyCommand plugin;
    public BlockAnyCommandListener(BlockAnyCommand instance){plugin = instance;}

    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasPermission("BlockAnyCommand.bypass") || player.isOp())) {
            List<String> command = plugin.getConfig().getStringList("BlockedCommands.global");
            for (String cmd : command) {
                if (event.getMessage().toLowerCase().startsWith("/" + cmd)) {
                    if (!(player.hasPermission("BlockAnyCommand.bypass." + cmd) || player.isOp())) {
                        event.setCancelled(true);
                        player.sendMessage(plugin.getConfig().getString("Message").replace("&", "§"));
                    }
                }
            }
        }
    }
}
