package com.github.cubealex.main;

import com.github.cubealex.commands.HomeCommand;
import com.github.cubealex.configs.HomesYML;
import com.github.cubealex.listener.JoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {

        plugin = this;

        this.getLogger().info(Utils.prefix + "§aDas Plugin wird gestartet!");

        try {

            this.getLogger().info(Utils.prefix + "§aDas Plugin wird geladen...");

            //Try register all Commands and Listener
            register();

            //Initialize Config
            this.saveDefaultConfig();

        }catch (Exception e) {

            e.printStackTrace();

        }

        //Load all Configs
        HomesYML.createConfig();

        this.getLogger().info(Utils.prefix + "§aDas Plugin wurde geladen!");

    }

    @Override
    public void onDisable() {

        this.getLogger().info(Utils.prefix + "§4Das Plugin wurde gestoppt!");

    }

    private void register() {

        //Registers Commands
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("sethome").setExecutor(new HomeCommand());

        //Registers Listeners
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new JoinListener(), this);

    }

    public static Main getPlugin() {
        return plugin;
    }
}
