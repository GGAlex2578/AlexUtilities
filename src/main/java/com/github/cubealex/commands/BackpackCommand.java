package com.github.cubealex.commands;

import com.github.cubealex.configs.BackpackYML;
import com.github.cubealex.main.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BackpackCommand implements CommandExecutor, Listener {

    private FileConfiguration config = BackpackYML.getConfig();
    private List<ItemStack> contents = new ArrayList<>();
    private Component title = Component.text("§6§lBackpack");
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //Checks if the sender is a Player
        if (sender instanceof Player) {

            Player player = ((Player) sender).getPlayer();

            if (args.length == 0 ) {

                Inventory backpackInventory = Bukkit.getServer().createInventory(player, 9 * 4, title);

//                contents = config.getConfigurationSection(player.getName()).getIte

                backpackInventory.setContents(contents.toArray(new ItemStack[contents.size()]));
                
                player.openInventory(backpackInventory);

            }else
                sender.sendMessage(Utils.noArgs);

        }else
            //Tells the Console it is not a Player
            sender.sendMessage(Utils.noPlayer);

        return false;

    }

    @EventHandler
    public void onBackpackClose(InventoryCloseEvent event) {

        Inventory inventory = event.getInventory();

        Player player = (Player) event.getPlayer();

        if (player.getOpenInventory().title().equals(title)) {
            //Saves each individual ItemStack to backpack.yml
            for (ItemStack is : inventory.getContents())
                config.set(player.getName(), is);
            //Save Config
            try {
                config.save(BackpackYML.getConfigFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
