package com.github.cubealex.configs;

import com.github.cubealex.main.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CoordsYML {

    private static File configFile = new File(Main.getPlugin().getDataFolder(), "coords.yml");

    private static FileConfiguration config = new YamlConfiguration();

    public static void createConfig () {

        //Checks if ConfigFile doesnt exist
        if (!configFile.exists()) {

            configFile.getParentFile().mkdirs();
            Main.getPlugin().saveResource("coords.yml", false);

        }

        try {

            config.load(configFile);

        }catch (IOException | InvalidConfigurationException e ) {

            e.printStackTrace();

        }

    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static File getConfigFile() {
        return configFile;
    }

}
