package com.github.cubealex.commands;

import com.github.cubealex.configs.HomesYML;
import com.github.cubealex.main.Main;
import com.github.cubealex.main.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HomeCommand implements CommandExecutor {

    private FileConfiguration config = HomesYML.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        //Checks if Sender is a Player
        if (sender instanceof Player) {

            Player player = ((Player) sender).getPlayer();

            //Checks if the Command is /sethome and if the Player has a Home
            if (args.length == 1 && cmd.getName().equalsIgnoreCase("sethome") && config.contains("Homes." + player.getName())) {

                if (args[0].equalsIgnoreCase("confirm")) {

                    saveCoordinates(player, config);

                    player.sendMessage(Utils.success);

                    return true;

                }

                //Checks if the Command is /sethome
            }else if (args.length <= 0 && cmd.getName().equalsIgnoreCase("sethome")) {

                if (config.contains("Homes." + player.getName())) {

                    player.sendMessage(Utils.usage + cmd.getName() + " confirm");

                    return false;

                }

                saveCoordinates(player, config);

                player.sendMessage(Utils.success);

                return true;

            //Checks if the Command is /home
            }else if (args.length <= 0 && cmd.getName().equalsIgnoreCase("home")) {


                //Teleports the Player to his Home and sends a Message
                teleportPlayer(player, config);

                player.sendMessage(Utils.success);

                return true;

            }else {

                player.sendMessage(Utils.noArgs);

            }


        }else {

            //Tells the Console it is not a Player
            sender.sendMessage(Utils.noPlayer);

        }

        return false;

    }

    private void saveCoordinates(Player player, FileConfiguration config) {

        //Saves the Players Coordinates to homes.yml
        config.set("Homes." + player.getName() + ".X", player.getLocation().getX());
        config.set("Homes." + player.getName() + ".Y", player.getLocation().getY());
        config.set("Homes." + player.getName() + ".Z", player.getLocation().getZ());

        //Saves the Players Rotation to homes.yml
        config.set("Homes." + player.getName() + ".Yaw", player.getLocation().getYaw());
        config.set("Homes." + player.getName() + ".Pitch", player.getLocation().getPitch());

        //Saves the Players World to homes.yml
        config.set("Homes." + player.getName() + ".World", player.getLocation().getWorld().getName());

        //Save the Changes
        try {

            config.save(HomesYML.getConfigFile());

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private void teleportPlayer(Player player, FileConfiguration config) {

        //Retrieves the Players Coordinates from homes.yml
        double x = config.getDouble("Homes." + player.getName() + ".X");
        double y = config.getDouble("Homes." + player.getName() + ".Y");
        double z = config.getDouble("Homes." + player.getName() + ".Z");

        //Retrieves the Players Rotation from homes.yml
        float yaw = (float) config.getDouble("Homes." + player.getName() + ".Yaw");
        float pitch = (float) config.getDouble("Homes." + player.getName() + ".Pitch");

        //Retrieves the Players World from homes.yml
        World world = Main.getPlugin().getServer().getWorld(config.getString("Homes." + player.getName() + ".World"));

        //Teleports the Player
        player.teleportAsync(new Location(world, x ,y ,z, yaw, pitch));

    }

}
