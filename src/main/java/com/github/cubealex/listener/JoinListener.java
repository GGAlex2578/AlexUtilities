package com.github.cubealex.listener;

import com.github.cubealex.main.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage(Utils.joinMessage.replace("%player%", event.getPlayer().getName()));

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        event.setQuitMessage(Utils.quitMessage.replace("%player%", event.getPlayer().getName()));

    }

}
