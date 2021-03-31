package me.xenodev.tmbbe.event;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent  implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage(Main.prefix + "§7Der Spieler §6" + p.getName() + " §7hat §cverlassen");
    }
}
