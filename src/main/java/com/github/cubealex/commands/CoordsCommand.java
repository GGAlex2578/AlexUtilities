package com.github.cubealex.commands;

import com.github.cubealex.configs.CoordsYML;
import com.github.cubealex.main.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class CoordsCommand implements CommandExecutor {

    private FileConfiguration coordsyml = CoordsYML.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            Player player = ((Player) sender).getPlayer();

            if (args.length >= 1) {

                if (coordsyml.contains("Coordinates." + player.getName() + "." + args[0])) {

                    if(args.length == 1 && command.getLabel().equalsIgnoreCase("coords")) {

                        //View Coordinates with specified Name
                        showCoordinates(player, args[0], coordsyml);

                    }else if (args.length == 2 && args[1].equalsIgnoreCase("delete") && command.getLabel().equalsIgnoreCase("coords")) {

                        //Delete Coordinates with the specified Name
                        deleteCoordinates(player, args[0], coordsyml, CoordsYML.getConfigFile());

                    }else if (args.length == 2 && args[1].equalsIgnoreCase("teleport") && command. getLabel().equalsIgnoreCase("coords")) {

                        //Teleports to the Coordinates with the specified Name
                        teleportToCoordinates(player, args[0], coordsyml);

                    }else
                        sender.sendMessage(Utils.usage + command.getLabel() + " <name> teleport/delete/save");

                }else if (args.length == 2 && args[1].equalsIgnoreCase("save") && command.getLabel().equalsIgnoreCase("coords")) {

                    //Save current Coordinates under the specified name
                    saveCoordinates(player, args[0], coordsyml, CoordsYML.getConfigFile());

                }else
                    sender.sendMessage(Utils.coordsNotExist);

            }else
                sender.sendMessage(Utils.usage + command.getLabel() + " <name> teleport/delete/save");

        }else
            sender.sendMessage(Utils.noPlayer);

        return false;

    }

    private void showCoordinates(Player player, String name, FileConfiguration config) {

        //Gets the Location with the specified Name from the Config File
        Location loc = config.getLocation("Coordinates." + player.getName() + "." + name);

        //Prints the Location
        String message = Utils.yourCoords.replace("%coordsname%", name) + "§6X: " + Math.round(loc.getX()) + ", Y: " + Math.round(loc.getY()) + ", Z: " + Math.round(loc.getZ()) + ", World: " + loc.getWorld().getName();

        player.sendMessage(message);

    }

    private void saveCoordinates(Player player, String name, FileConfiguration config, File configFile) {

        Location loc = player.getLocation();

        //Adds the Location with the specified Name to the Config
        config.set("Coordinates." + player.getName() + "." + name, loc);

        //Save Config
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendMessage(Utils.saveCoords.replace("%coordsname%", name));

    }

    private void deleteCoordinates(Player player, String name, FileConfiguration config, File configFile) {

        //Deletes the Location with the specified Name from the Config
        config.set("Coordinates." + player.getName() + "." + name, null);

        //Save Config
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendMessage(Utils.deleteCoords.replace("%coordsname%", name));

    }

    private void teleportToCoordinates(Player player, String name, FileConfiguration config) {

        //Gets the Location with the specified Name from the Config
        Location loc = config.getLocation("Coordinates." + player.getName() + "." + name);

        //Teleports the Player
        player.teleportAsync(loc);

        player.sendMessage(Utils.tpToCoords.replace("%coordsname%", name));

    }


}
