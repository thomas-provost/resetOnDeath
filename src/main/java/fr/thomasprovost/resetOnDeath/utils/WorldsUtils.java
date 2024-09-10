package fr.thomasprovost.resetOnDeath.utils;

import fr.thomasprovost.resetOnDeath.Main;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class WorldsUtils {

    private static final Main main = Main.getInstance();
    private static final FileConfiguration config = main.getConfig();
    private static final String prefix = config.getString("MESSAGES.PREFIX");

    public static void resetWorlds() {
        if (Bukkit.getWorlds().contains(Bukkit.getWorld(config.getString("TEMPORARY-WORLD.NAME")))) {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.teleport(Bukkit.getWorld(config.getString("TEMPORARY-WORLD.NAME")).getSpawnLocation());
                onlinePlayers.playSound(onlinePlayers.getLocation(), config.getString("TELEPORT-SOUND"), 1f, 1f);
            }
        } else {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.kickPlayer(prefix + config.getString("MESSAGES.KICK-REASON"));
            }
            Bukkit.unloadWorld("world", false);
            Bukkit.unloadWorld("world_nether", false);
            Bukkit.unloadWorld("world_the_end", false);
            File worldFolder = new File("world");
            try {
                FileUtils.deleteDirectory(worldFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            File netherFolder = new File("world_nether");
            try {
                FileUtils.deleteDirectory(netherFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            File endFolder = new File("world_the_end");
            try {
                FileUtils.deleteDirectory(endFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            createWorlds();
        }
    }

    public static void createTemporaryWorld(){
        if(Bukkit.getWorld(config.getString("TEMPORARY-WORLD.NAME")) == null) {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.sendMessage(prefix + config.getString("MESSAGES.WORLD-RESET"));
            }
            WorldCreator creator = new WorldCreator(config.getString("TEMPORARY-WORLD.NAME"))
                    .environment(World.Environment.NORMAL);

            World temp = Bukkit.createWorld(creator);
        }+
    }

    public static void createWorlds() {
        if (Bukkit.getWorld("world") == null) {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.sendMessage(prefix + config.getString("MESSAGES.WORLD-RESET"));
            }
            WorldCreator creator = new WorldCreator("world")
                    .environment(World.Environment.NORMAL);
            WorldCreator creatorNether = new WorldCreator("world_nether")
                    .environment(World.Environment.NETHER);
            WorldCreator creatorEnd = new WorldCreator("world_the_end")
                    .environment(World.Environment.THE_END);

            World world = Bukkit.createWorld(creator);
            World nether = Bukkit.createWorld(creatorNether);
            World end = Bukkit.createWorld(creatorEnd);

            int difficulty = config.getInt("DIFFICULTY");

            switch (difficulty){
                case 0:
                    world.setDifficulty(org.bukkit.Difficulty.PEACEFUL);
                    nether.setDifficulty(org.bukkit.Difficulty.PEACEFUL);
                    end.setDifficulty(org.bukkit.Difficulty.PEACEFUL);
                    break;
                case 1:
                    world.setDifficulty(org.bukkit.Difficulty.EASY);
                    nether.setDifficulty(org.bukkit.Difficulty.EASY);
                    end.setDifficulty(org.bukkit.Difficulty.EASY);
                    break;
                case 2:
                    world.setDifficulty(org.bukkit.Difficulty.NORMAL);
                    nether.setDifficulty(org.bukkit.Difficulty.NORMAL);
                    end.setDifficulty(org.bukkit.Difficulty.NORMAL);
                    break;
                case 3:
                    world.setDifficulty(org.bukkit.Difficulty.HARD);
                    nether.setDifficulty(org.bukkit.Difficulty.HARD);
                    end.setDifficulty(org.bukkit.Difficulty.HARD);
                    break;
            }

        }
    }
}
