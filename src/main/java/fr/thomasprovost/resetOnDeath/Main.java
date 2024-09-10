package fr.thomasprovost.resetOnDeath;

import fr.thomasprovost.resetOnDeath.managers.ListenersManager;
import fr.thomasprovost.resetOnDeath.utils.WorldsUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        WorldsUtils.createWorlds();

        ListenersManager listenersManager = new ListenersManager();
        listenersManager.initListeners();

        getLogger().info("*");
        getLogger().info("* Starting ResetOnDeath plugin...");
        getLogger().info("*");
        getLogger().info("* Version: " + getDescription().getVersion());
        getLogger().info("* Author: " + getDescription().getAuthors().get(0));
        if(getConfig().getBoolean("CHECK-UPDATE")) {
            getLogger().info("* Checking for updates... (TODO)");
        }
        getLogger().info("*");
        getLogger().info("* ResetOnDeath plugin started successfully !");
        getLogger().info("*");
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
