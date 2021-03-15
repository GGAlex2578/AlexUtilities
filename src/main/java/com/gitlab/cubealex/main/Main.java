package com.gitlab.cubealex.main;

import com.gitlab.cubealex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main plugin;
    
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(Utils.PREFIX + "§aDas Plugin wird geladen...");

        try {

            //Try to register all Commands and Listeners
            Main.plugin = this;
            this.register();


        }
        catch (Exception e) {

            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(Utils.PREFIX + "§cDas Plugin konnte nicht geladen werden!");
        }

        Bukkit.getConsoleSender().sendMessage(Utils.PREFIX + "§aDas Plugin wurde erfolgreich geladen!");
    }
    
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(Utils.PREFIX + "§aDas Plugin wurde erfolgreich gestoppt!");

    }
    
    private void register() {

        final PluginManager pm = Bukkit.getPluginManager();

    }
    
    public static Main getPlugin() {
        return Main.plugin;
    }
}
