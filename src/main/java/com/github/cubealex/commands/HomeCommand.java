package com.github.cubealex.commands;

import com.github.cubealex.main.Main;
import com.github.cubealex.main.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        //Checks if Sender is a Player
        if (sender instanceof Player) {

            //Checks if the Command is /sethome
            if (args.length <= 0 && cmd.getName().equalsIgnoreCase("sethome")) {

                //Sends the Player his Coordinates
                sender.sendMessage("Coords: " + ((Player) sender).getLocation());

                return true;

            //Checks if the Command is /home
            }else if (args.length <= 0 && cmd.getName().equalsIgnoreCase("home")) {

                //Sends the Player his World
                sender.sendMessage("World: " + ((Player) sender).getWorld());

                return true;

            }


        }else {

            //Tells the Console it is not a Player
            sender.sendMessage(Utils.NOPLAYER);

        }

        return false;

    }

}
