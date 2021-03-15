package com.github.cubealex.main;

import com.github.cubealex.commands.HomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {

        plugin = this;

        this.getLogger().info(Utils.PREFIX + "§aDas Plugin wird gestartet!");

        try {

            this.getLogger().info(Utils.PREFIX + "§aDas Plugin wird geladen...");

            //Try register all Commands and Listener
            register();

            //Initialize Config
            this.saveDefaultConfig();

        }catch (Exception e) {

            e.printStackTrace();

        }

        this.getLogger().info(Utils.PREFIX + "§aDas Plugin wurde geladen!");

    }

    @Override
    public void onDisable() {

        this.getLogger().info(Utils.PREFIX + "§4Das Plugin wurde gestoppt!");

    }

    private void register() {

        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("sethome").setExecutor(new HomeCommand());

    }

    public static Main getPlugin() {
        return plugin;
    }
}
