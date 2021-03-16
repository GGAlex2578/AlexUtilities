package com.github.cubealex.tabcompleter;

import com.github.cubealex.configs.CoordsYML;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CoordsTabCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> coordNames = new ArrayList<>();
        List<String> completions = new ArrayList<>();

        if (sender instanceof Player) {

            Player player = sender.getServer().getPlayer(sender.getName());

            if (args.length == 1) {

                coordNames.addAll(CoordsYML.getConfig().getConfigurationSection("Coordinates." + player.getName()).getKeys(false));

                return coordNames;

            }else if (args.length == 2) {

                completions.add("teleport");
                completions.add("delete");
                completions.add("save");

                return completions;

            }

        }

        return null;

    }

}
