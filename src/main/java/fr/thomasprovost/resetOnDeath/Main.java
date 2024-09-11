package fr.thomasprovost.resetOnDeath;

import fr.thomasprovost.resetOnDeath.game.GameTask;
import fr.thomasprovost.resetOnDeath.managers.ListenersManager;
import fr.thomasprovost.resetOnDeath.utils.WorldsUtils;
import org.bukkit.plugin.java.JavaPlugin;

import static fr.thomasprovost.resetOnDeath.utils.WorldsUtils.createTemporaryWorld;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        WorldsUtils.createWorlds();
        createTemporaryWorld();

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

        GameTask gameTask = new GameTask();
        gameTask.run();
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
