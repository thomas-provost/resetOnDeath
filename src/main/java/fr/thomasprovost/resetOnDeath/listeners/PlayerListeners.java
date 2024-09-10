package fr.thomasprovost.resetOnDeath.listeners;

import fr.thomasprovost.resetOnDeath.Main;
import fr.thomasprovost.resetOnDeath.utils.WorldsUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListeners implements Listener {

    private final Main main = Main.getInstance();
    private final FileConfiguration config = main.getConfig();
    private int timer = config.getInt("RESET-DELAY");

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        event.setDeathMessage(config.getString("MESSAGES.DEATH_ANNOUNCE")
                .replace("%player%", event.getEntity().getName()
                .replaceAll("&", "§")));

        for (Player onlinePlayers : Bukkit.getOnlinePlayers()){
            onlinePlayers.sendTitle(config.getString("MESSAGES.DEATH_TITLE.TITLE")
                            .replace("%time%", timer + "")
                            .replaceAll("&", "§"),
                    config.getString("MESSAGES.DEATH_TITLE.SUBTITLE")
                            .replace("%time%", timer + "")
                            .replaceAll("&", "§"));

            onlinePlayers.playSound(onlinePlayers.getLocation(), config.getString("DEATH_SOUND"), 1f, 1f);
        }

        //Runnable dans 10 sec:
        Bukkit.getScheduler().runTaskLater(main, () -> {
            WorldsUtils.resetWorlds();
            for(Player onlinePlayers : Bukkit.getOnlinePlayers()){
                onlinePlayers.sendMessage(config.getString("MESSAGES.WORLD_RESET")
                        .replace("%time%", String.valueOf(timer)
                        .replaceAll("&", "§")));
            }
            if(timer == 0){
                timer = config.getInt("RESET-DELAY");;
            } else {
                timer--;
            }
        }, 200L);
    }


}
