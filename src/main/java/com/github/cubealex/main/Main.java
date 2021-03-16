package com.github.cubealex.main;

import com.github.cubealex.commands.CoordsCommand;
import com.github.cubealex.commands.HomeCommand;
import com.github.cubealex.configs.CoordsYML;
import com.github.cubealex.configs.HomesYML;
import com.github.cubealex.listener.JoinListener;
import com.github.cubealex.tabcompleter.CoordsTabCompleter;
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

            //Load all Configs
            HomesYML.createConfig();
            CoordsYML.createConfig();

        }catch (Exception e) {

            e.printStackTrace();

        }

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
        this.getCommand("coords").setExecutor(new CoordsCommand());

        //Registers CommandTabCompleter
        this.getCommand("coords").setTabCompleter(new CoordsTabCompleter());

        //Registers Listeners
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new JoinListener(), this);

    }

    public static Main getPlugin() {
        return plugin;
    }
}
