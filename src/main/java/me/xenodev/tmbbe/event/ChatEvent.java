package me.xenodev.tmbbe.event;

import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class ChatEvent implements Listener {

    public static ArrayList<Player> bemerkungplayer = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        if(bemerkungplayer.contains(p)){
            if(e.getMessage().equalsIgnoreCase("!abbrechen")){
                e.setCancelled(true);
                bemerkungplayer.remove(p);
                p.sendMessage(Main.prefix + "§cBemerkung abgebrochen");
                BewertenEvent.openAblehnen(e.getPlayer());
            } else {
                e.setCancelled(true);
                bemerkungplayer.remove(p);
                OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
                Bewertungen.setBemerkung(t, e.getMessage());
                p.sendMessage(Main.prefix + "§7Du hast die Bemerkung auf §e" + e.getMessage() + " §7gesetzt!");
                BewertenEvent.openAblehnen(e.getPlayer());
            }
        }
    }

}
