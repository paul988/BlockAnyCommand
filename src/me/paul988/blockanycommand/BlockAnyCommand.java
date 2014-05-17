package me.paul988.blockanycommand;

import java.util.logging.Logger;
import me.paul988.blockanycommand.Updater.ReleaseType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockAnyCommand extends JavaPlugin {

    public static boolean update = false;
    public static String name = "";
    public static ReleaseType type = null;
    public static String version = "";
    public static String link = "";

    @Override
    public void onEnable() {
        Logger logger = this.getLogger();
        logger.info("Has been enabled.");
        registerListeners();
        registerCommands();
        loadConfiguration();
        if (this.getConfig().getBoolean("Check-Update") == true) {
            updateCheck();
            if (this.getConfig().getBoolean("Notify-Update") == true) {
                if (this.update) {
                    logger.info("There is an update available.");
                } else {
                    logger.info("There is no update available.");
                }
            }
        }
    }

    @Override
    public void onDisable() {
        Logger logger = this.getLogger();
        logger.info("Has been disabled.");
    }

    public void loadConfiguration() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    public void updateCheck() {
        Updater updater = new Updater(this, 79927, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
        update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
        name = updater.getLatestName(); // Get the latest name
        version = updater.getLatestGameVersion(); // Get the latest game version
        type = updater.getLatestType(); // Get the latest file's type
        link = updater.getLatestFileLink(); // Get the latest link
        if (update && this.getConfig().getBoolean("Auto-Update") == true) {
            updateStart();
        }
    }

    public void updateStart() {
        Logger logger = this.getLogger();
        logger.info("Update has started.");
        Updater updater = new Updater(this, 79927, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BlockAnyCommandListener(this), this);
        pm.registerEvents(new PlayerJoinListener(this), this);
    }

    private void registerCommands() {
        getCommand("bac").setExecutor(new cmdHelp(this));
        getCommand("baclist").setExecutor(new cmdList(this));
        getCommand("bacupdate").setExecutor(new cmdUpdate(this));
        getCommand("bacreload").setExecutor(new cmdReload(this));
    }

}
