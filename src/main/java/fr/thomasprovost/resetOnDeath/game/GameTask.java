package fr.thomasprovost.resetOnDeath.game;

import fr.thomasprovost.resetOnDeath.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameTask implements Runnable {

    private final Main main = Main.getInstance();
    private final FileConfiguration config = main.getConfig();

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 999999, 255, true);
            PotionEffect confusion = new PotionEffect(PotionEffectType.CONFUSION, 99999, 255, true);
            if (onlinePlayers.getLocation().getWorld().getName().equals(config.getString("TEMPORARY-WORLD.NAME"))) {
                onlinePlayers.addPotionEffect(blindness);
                onlinePlayers.addPotionEffect(confusion);
                if (onlinePlayers.getGameMode().equals(GameMode.SURVIVAL)) {
                    onlinePlayers.setGameMode(GameMode.ADVENTURE);
                }
            } else {
                onlinePlayers.removePotionEffect(blindness.getType());
                onlinePlayers.removePotionEffect(confusion.getType());
                if (onlinePlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                    onlinePlayers.setGameMode(GameMode.SURVIVAL);
                }
            }
        }
    }
}
