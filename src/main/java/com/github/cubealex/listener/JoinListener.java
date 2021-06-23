package com.github.cubealex.listener;

import com.github.cubealex.main.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    private Component joinMessage;
    private Component quitMessage;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        joinMessage  = Component.text(Utils.joinMessage.replace("%player%", event.getPlayer().getName()));
        event.joinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        quitMessage = Component.text(Utils.quitMessage.replace("%player%", event.getPlayer().getName()));
        event.quitMessage(quitMessage);
    }

}
