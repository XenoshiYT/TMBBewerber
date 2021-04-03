package me.xenodev.tmbbe.event;

import me.xenodev.tmbbe.cmd.BewertenCMD;
import me.xenodev.tmbbe.files.Abgaben;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class PlayerEvent implements Listener {

    public static HashMap<Player, String> currentplayer = new HashMap<Player, String>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(BewertenCMD.bewerten.contains(p)){
            if(e.getItem().getItemMeta().getDisplayName().equals("§6Bewerberplots")){
                e.setCancelled(true);
                if(!PlayerEvent.currentplayer.containsKey(p)) {
                    Inventory inv = Bukkit.createInventory(p, 9 * 6, "§7>> §6Bewerber §7<<");

                    for (String name : Abgaben.players) {
                        inv.addItem(new ItemBuilder(Material.PLAYER_HEAD).setOwner(name).setName("§6" + name).build());
                    }

                    for (int i = 36; i < 45; i++) {
                        inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                    }
                    inv.setItem(53, new ItemBuilder(Material.BARRIER).setName("§7Breche den Vorgang ab").build());
                    p.openInventory(inv);
                }else{
                    p.sendMessage(Main.error + "§7Du hast schon einen Spieler ausgewählt");
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        if(e.getView().getTitle().equals("§7>> §6Bewerber §7<<")) {
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Breche den Vorgang ab")){
                p.closeInventory();
            }else if(e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE) || e.getCurrentItem().getType().equals(Material.BARRIER)) {
                return;
            }else{

                String name = e.getCurrentItem().getItemMeta().getDisplayName().replace("§6", "");
                currentplayer.put(p, name);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(p, "plot visit " + e.getCurrentItem().getItemMeta().getDisplayName().replace("§6", ""));
            }
        }
    }


}
