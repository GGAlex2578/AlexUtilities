package com.github.cubealex.commands;

import com.github.cubealex.configs.BackpackYML;
import com.github.cubealex.main.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class BackpackCommand implements CommandExecutor, Listener {

    private FileConfiguration config = BackpackYML.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //Checks if the sender is a Player
        if (sender instanceof Player) {

            Player player = ((Player) sender).getPlayer();

            if (args.length == 0 ) {

                Inventory backpackInventory = Bukkit.createInventory(player, 4 * 9, Component.text("§6§lBackpack"));

                player.openInventory(backpackInventory);

            }else
                sender.sendMessage(Utils.noArgs);

        }else
            //Tells the Console it is not a Player
            sender.sendMessage(Utils.noPlayer);

        return false;

    }

}
