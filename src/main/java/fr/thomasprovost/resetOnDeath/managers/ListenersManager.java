package fr.thomasprovost.resetOnDeath.managers;

import fr.thomasprovost.resetOnDeath.Main;
import fr.thomasprovost.resetOnDeath.listeners.PlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    private static Main main = Main.getInstance();
    private PluginManager pluginManager = Bukkit.getPluginManager();

    public void initListeners() {
        pluginManager.registerEvents(new PlayerListeners(), main);
    }
}
