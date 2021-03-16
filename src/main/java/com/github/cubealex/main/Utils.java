package com.github.cubealex.main;

import org.bukkit.configuration.file.FileConfiguration;

public class Utils {

    private static FileConfiguration config = Main.getPlugin().getConfig();

    public static String prefix = config.getString("Prefix");

    public static String noPlayer = prefix + config.getString("NoPlayer");

    public static String noArgs = prefix + config.getString("NoArgs");

    public static String success = prefix + config.getString("Success");

    public static String usage = prefix + config.getString("Usage");

    public static String joinMessage = prefix + config.getString("JoinMessage");
    public static String quitMessage = prefix + config.getString("QuitMessage");

}
