package me.xenodev.tmbbe.event;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionManagement;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
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

public class BewertenEvent implements Listener {

    public Integer amount = 0;

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(BewertenCMD.bewerten.contains(p)){
            if(e.getItem().getItemMeta().getDisplayName().equals("§6Plot Bewerten")){
                if(PlayerEvent.currentplayer.containsKey(p)) {
                    e.setCancelled(true);
                    Inventory inv = Bukkit.createInventory(p, 9 * 3, "§7>> §6Bewerten §7<<");

                    for (int i = 0; i < 27; i++) {
                        inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                    }

                    inv.setItem(10, new ItemBuilder(Material.LIME_DYE).setName("§aAnnehmen").setLore("", "§7Nehme den ausgewählten Spieler an").build());
                    OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
                    inv.setItem(12, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).setLore("§7Hat sich beworben am", "", "§7Datum: §a" + Bewertungen.getDate(t.getUniqueId().toString()), "§7Zeit: §a" + Bewertungen.getTime(t.getUniqueId().toString())).build());
                    inv.setItem(14, new ItemBuilder(Material.RED_DYE).setName("§cAblehnen").setLore("", "§7Lehne den ausgewählten Spieler ab").build());
                    inv.setItem(16, new ItemBuilder(Material.BARRIER).setName("§7Breche den Vorgang ab").build());
                    p.openInventory(inv);
                }else {
                    e.setCancelled(true);
                    p.sendMessage(Main.error + "§7Du hast noch keinen Spieler ausgewählt");
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        if(e.getView().getTitle().equals("§7>> §6Bewerten §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnnehmen")) {
                p.closeInventory();
                Inventory inv = Bukkit.createInventory(null, 9*1, "§7>> §6Annehmen? §7<<");

                inv.setItem(1, new ItemBuilder(Material.LIME_DYE).setName("§aBestätigen").build());
                inv.setItem(7, new ItemBuilder(Material.RED_DYE).setName("§cAbbrechen").build());

                OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
                inv.setItem(4, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).setLore("§7Hat sich beworben am", "", "§7Datum: §a" + Bewertungen.getDate(t.getUniqueId().toString()), "§7Zeit: §a" + Bewertungen.getTime(t.getUniqueId().toString())).build());

                p.openInventory(inv);
            }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAblehnen")) {
                p.closeInventory();
                Inventory inv = Bukkit.createInventory(null, 9*6, "§7>> §6Grund §7<<");
                openAblehnen(p,inv);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Breche den Vorgang ab")) {
                p.closeInventory();
                PlayerEvent.currentplayer.remove(p);
            }
        }else if(e.getView().getTitle().equals("§7>> §6Annehmen? §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBestätigen")) {
                Player t = Bukkit.getPlayerExact(PlayerEvent.currentplayer.get(p));
                if(!Bewertungen.getAnnahme(t.getUniqueId().toString()).equals("Angenommen")) {
                    Bewertungen.setAnnahme(t.getUniqueId().toString(), "Angenommen");
                    IPermissionManagement manager = CloudNetDriver.getInstance().getPermissionManagement();
                    IPermissionUser player = manager.getUser(t.getUniqueId());
                    if(player.inGroup("Neuling")){
                        player.addGroup("Azubi");
                    }else if(player.inGroup("Azubi")){
                        player.removeGroup("Azubi");
                        player.addGroup("Testbuilder");
                    }
                    Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                    PlayerEvent.currentplayer.remove(p);
                    Abgaben.addList(Abgaben.players);
                    Abgaben.addArray();
                    p.closeInventory();
                    p.sendMessage(Main.prefix + "§7Der Spieler wurde angenommen");
                    amount = 0;
                }else{
                    Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                    PlayerEvent.currentplayer.remove(p);
                    Abgaben.addList(Abgaben.players);
                    Abgaben.addArray();
                    p.closeInventory();
                    p.sendMessage(Main.error + "§cDer Spieler wurde bereits angenommen");
                    amount = 0;
                }
            }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAbbrechen")) {
                amount = 0;
                p.closeInventory();
                PlayerEvent.currentplayer.remove(p);
            }
        }else if(e.getView().getTitle().equals("§7>> §6Grund §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAbbrechen")) {
                p.closeInventory();
                PlayerEvent.currentplayer.remove(p);
            }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBestätigen")) {
                p.closeInventory();
                Inventory inv = Bukkit.createInventory(null, 9*1, "§7>> §6Ablehnen? §7<<");

                inv.setItem(1, new ItemBuilder(Material.LIME_DYE).setName("§aBestätigen").build());
                inv.setItem(7, new ItemBuilder(Material.RED_DYE).setName("§cAbbrechen").build());

                OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
                inv.setItem(4, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).build());

                p.openInventory(inv);
            }else {
                if(amount <= 12) {
                    String msg = e.getCurrentItem().getItemMeta().getLore().toString();
                    msg = msg.replace("§7", "");
                    msg = msg.replace(", ", "");
                    msg = msg.replace("[", "");
                    msg = msg.replace("]", "");
                    amount = amount + 1;
                    Bewertungen.reason.put(amount, msg);
                    p.sendMessage(Main.prefix + "§7Du hast bereits §6" + amount + " §7Gründe ausgewählt");
                }else{
                    p.sendMessage(Main.error + "§cDu hast zu viele Gründe ausgewählt");
                }
            }
        }else if(e.getView().getTitle().equals("§7>> §6Ablehnen? §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBestätigen")) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
                if(!Bewertungen.getAnnahme(t.getUniqueId().toString()).equals("Abgelehnt")) {
                    Bewertungen.setAnnahme(t.getUniqueId().toString(), "Abgelehnt");
                    for(int i = 1; i < 14; i++){
                        Bewertungen.endreason.add(Bewertungen.reason.get(i));
                    }
                    Bewertungen.setGrund(t.getUniqueId().toString(), Bewertungen.endreason);
                    Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                    PlayerEvent.currentplayer.remove(p);
                    Abgaben.addList(Abgaben.players);
                    Abgaben.addArray();
                    p.closeInventory();
                    p.sendMessage(Main.prefix + "§7Der Spieler wurde abgelehnt");
                    amount = 0;
                }else{
                    Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                    PlayerEvent.currentplayer.remove(p);
                    Abgaben.addList(Abgaben.players);
                    Abgaben.addArray();
                    p.closeInventory();
                    p.sendMessage(Main.error + "§cDer Spieler wurde bereits abgelehnt");
                    amount = 0;
                }
            }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAbbrechen")) {
                amount = 0;
                PlayerEvent.currentplayer.remove(p);
                p.closeInventory();
            }else{

            }
        }
    }

    private void openAbhlenen(Player p,Inventory inv){


        for(int i = 0; i < 54; i++){
            inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
        }

        inv.setItem(0, new ItemBuilder(Material.STONE).setName("§6Terra - Forming").setLore("", "§7Versuche dein Terraforming zu Verbessern").build());
        inv.setItem(9, new ItemBuilder(Material.NETHER_BRICK).setName("§6Terra - Aufbau").setLore("", "§7Verbessere den Aufbau deines Terras").build());
        inv.setItem(10, new ItemBuilder(Material.OAK_FENCE).setName("§6Terra - Nicht Vorhanden").setLore("", "§7Du musst dein Terra formen").build());
        inv.setItem(18, new ItemBuilder(Material.WHITE_TERRACOTTA).setName("§6Terra - Coloring").setLore("", "§7Verbessere dein Terracoloring durch passende Blockwahl").build());

        inv.setItem(8, new ItemBuilder(Material.COBBLESTONE_WALL).setName("§6Struktur - Detailsetzung").setLore("", "§7Versuche die Details deiner Struktur besser zu setzen").build());
        inv.setItem(16, new ItemBuilder(Material.OAK_FENCE).setName("§6Struktur - Details").setLore("", "§7Verbessere die Details deiner Strukturen").build());
        inv.setItem(17, new ItemBuilder(Material.BRICK_STAIRS).setName("§6Struktur - Dach").setLore("", "§7Der Aufbau deines Dachs ist verbesserungswürdig").build());
        inv.setItem(26, new ItemBuilder(Material.BRICKS).setName("§6Struktur - Aufbau").setLore("", "§7Verbessere den Aufbau deiner Struktur").build());

        inv.setItem(4, new ItemBuilder(Material.VINE).setName("§6Plot - Vegetation").setLore("", "§7Die Vegetation auf deinem Plot fehlt oder ist schlecht plaziert").build());
        inv.setItem(12, new ItemBuilder(Material.WHITE_STAINED_GLASS).setName("§6Plot - zu leer").setLore("", "§7Dein Plot fühlt sich zu leer an").build());
        inv.setItem(13, new ItemBuilder(Material.MOSSY_COBBLESTONE).setName("§6Plot - Zusammenhangslos").setLore("", "§7Der Aufbau deines Plots hat keinen zusammenhang").build());
        inv.setItem(14, new ItemBuilder(Material.COAL).setName("§6Plot - zu voll").setLore("", "§7Dein Plot fühlt sich zu voll an").build());
        inv.setItem(22, new ItemBuilder(Material.OAK_LEAVES).setName("§6Mapping - Bäume").setLore("", "§7Die Qualität deiner Bäume entspricht nicht unseren Standards").build());

        inv.setItem(38, new ItemBuilder(Material.LIME_DYE).setName("§aBestätigen").build());
        OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
        inv.setItem(40, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).setLore("§7Hat sich beworben am", "", "§7Datum: §a" + Bewertungen.getDate(t.getUniqueId().toString()), "§7Zeit: §a" + Bewertungen.getTime(t.getUniqueId().toString())).build());
        inv.setItem(42, new ItemBuilder(Material.RED_DYE).setName("§cAbbrechen").build());


        p.openInventory(inv);
    }

}
