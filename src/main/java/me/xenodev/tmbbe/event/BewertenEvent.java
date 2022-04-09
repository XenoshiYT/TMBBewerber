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
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.io.IOException;

public class BewertenEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (BewertenCMD.bewerten.contains(p)) {
            if (e.getItem().getItemMeta().getDisplayName().equals("§6Plot Bewerten")) {
                if (PlayerEvent.currentplayer.containsKey(p)) {
                    e.setCancelled(true);
                    Inventory inv = Bukkit.createInventory(p, 9 * 3, "§7>> §6Bewerten §7<<");

                    for (int i = 0; i < 27; i++) {
                        inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                    }

                    inv.setItem(10, new ItemBuilder(Material.LIME_DYE).setName("§aAnnehmen").setLore("", "§7Nehme den ausgewählten Spieler an").build());
                    OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
                    inv.setItem(12, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).setLore("§7Hat sich beworben am", "", "§7Datum: §a" + Bewertungen.getDate(t), "§7Zeit: §a" + Bewertungen.getTime(t)).build());
                    inv.setItem(14, new ItemBuilder(Material.RED_DYE).setName("§cAblehnen").setLore("", "§7Lehne den ausgewählten Spieler ab").build());
                    inv.setItem(16, new ItemBuilder(Material.BARRIER).setName("§7Breche den Vorgang ab").build());
                    p.openInventory(inv);
                } else {
                    e.setCancelled(true);
                    p.sendMessage(Main.error + "§7Du hast noch keinen Spieler ausgewählt");
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
        if (e.getView().getTitle().equals("§7>> §6Bewerten §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnnehmen")) {
                p.closeInventory();
                Inventory inv = Bukkit.createInventory(null, 9 * 1, "§7>> §6Annehmen? §7<<");

                inv.setItem(1, new ItemBuilder(Material.LIME_DYE).setName("§aBestätigen").build());
                inv.setItem(7, new ItemBuilder(Material.RED_DYE).setName("§cAbbrechen").build());

                inv.setItem(4, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).setLore("§7Hat sich beworben am", "", "§7Datum: §a" + Bewertungen.getDate(t), "§7Zeit: §a" + Bewertungen.getTime(t)).build());
                p.openInventory(inv);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAblehnen")) {
                p.closeInventory();
                openAblehnen(p);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Breche den Vorgang ab")) {
                p.closeInventory();
                PlayerEvent.currentplayer.remove(p);
            }
        } else if (e.getView().getTitle().equals("§7>> §6Annehmen? §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBestätigen")) {
                if (!Bewertungen.getStatus(t).equalsIgnoreCase("Angenommen")) {
                    Bewertungen.setStatus(t, "Angenommen");
                    IPermissionManagement manager = CloudNetDriver.getInstance().getPermissionManagement();
                    IPermissionUser player = manager.getUser(t.getUniqueId());
                    if (player.inGroup("Neuling")) {
                        player.addGroup("Azubi");
                        manager.updateUser(player);
                    } else if (player.inGroup("Azubi")) {
                        player.removeGroup("Azubi");
                        player.addGroup("Test-Builder");
                        manager.updateUser(player);
                    }
                    Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                    PlayerEvent.currentplayer.remove(p);
                    Abgaben.addList(Abgaben.players);
                    Abgaben.addArray();
                    p.closeInventory();
                    p.sendMessage(Main.prefix + "§7Der Spieler wurde angenommen");

                } else {
                    Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                    PlayerEvent.currentplayer.remove(p);
                    Abgaben.addList(Abgaben.players);
                    Abgaben.addArray();
                    p.closeInventory();
                    p.sendMessage(Main.error + "§cDer Spieler wurde bereits angenommen");

                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAbbrechen")) {
                p.closeInventory();
                PlayerEvent.currentplayer.remove(p);
            }
        } else if (e.getView().getTitle().equals("§7>> §6Grund §7<<")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAbbrechen")) {
                p.closeInventory();
                Bewertungen.setGrund(t, "Terra.Form", false);
                Bewertungen.setGrund(t, "Terra.Aufbau", false);
                Bewertungen.setGrund(t, "Terra.Besitz", false);
                Bewertungen.setGrund(t, "Terra.Coloring", false);
                Bewertungen.setGrund(t, "Struktur.Detailsetzung", false);
                Bewertungen.setGrund(t, "Struktur.Details", false);
                Bewertungen.setGrund(t, "Struktur.Dach", false);
                Bewertungen.setGrund(t, "Struktur.Aufbau", false);
                Bewertungen.setGrund(t, "Plot.Vegetation", false);
                Bewertungen.setGrund(t, "Plot.Leer", false);
                Bewertungen.setGrund(t, "Plot.Zusammenhang", false);
                Bewertungen.setGrund(t, "Plot.Voll", false);
                Bewertungen.setGrund(t, "Mapping.Baum", false);
                Bewertungen.setGrund(t, "Mapping.Organic", false);
                Bewertungen.setGrund(t, "Mapping.Einrichtung", false);
                Bewertungen.setBemerkung(t, null);
                PlayerEvent.currentplayer.remove(p);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBestätigen")) {
                p.closeInventory();
                Inventory inv = Bukkit.createInventory(null, 9 * 1, "§7>> §6Ablehnen? §7<<");

                inv.setItem(1, new ItemBuilder(Material.LIME_DYE).setName("§aBestätigen").build());
                inv.setItem(7, new ItemBuilder(Material.RED_DYE).setName("§cAbbrechen").build());

                inv.setItem(4, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).build());

                p.openInventory(inv);
            } else {
                if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.CHANNELING)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - Forming")) {
                        Bewertungen.setGrund(t, "Terra.Form", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - Aufbau")) {
                        Bewertungen.setGrund(t, "Terra.Aufbau", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - Coloring")) {
                        Bewertungen.setGrund(t, "Terra.Coloring", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - nicht Vorhanden")) {
                        Bewertungen.setGrund(t, "Terra.Besitz", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - Vegetation")) {
                        Bewertungen.setGrund(t, "Plot.Vegetation", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - zu Leer")) {
                        Bewertungen.setGrund(t, "Plot.Leer", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - Zusammenhangslos")) {
                        Bewertungen.setGrund(t, "Plot.Zusammenhang", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - zu Voll")) {
                        Bewertungen.setGrund(t, "Plot.Voll", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMapping - Bäume")) {
                        Bewertungen.setGrund(t, "Mapping.Baum", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMapping - Organic")) {
                        Bewertungen.setGrund(t, "Mapping.Organic", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMapping - Einrichtung")) {
                        Bewertungen.setGrund(t, "Mapping.Einrichtung", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Detailsetzung")) {
                        Bewertungen.setGrund(t, "Struktur.Detailsetzung", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Dach")) {
                        Bewertungen.setGrund(t, "Struktur.Dach", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Aufbau")) {
                        Bewertungen.setGrund(t, "Struktur.Aufbau", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Details")) {
                        Bewertungen.setGrund(t, "Struktur.Details", false);
                        p.closeInventory();
                        openAblehnen(p);
                    } else p.sendMessage(Main.error + "§cDiesen Grund gibt es nicht");
                } else if (!e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.CHANNELING)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - Forming")) {
                        Bewertungen.setGrund(t, "Terra.Form", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - Aufbau")) {
                        Bewertungen.setGrund(t, "Terra.Aufbau", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - Coloring")) {
                        Bewertungen.setGrund(t, "Terra.Coloring", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTerra - nicht Vorhanden")) {
                        Bewertungen.setGrund(t, "Terra.Besitz", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - Vegetation")) {
                        Bewertungen.setGrund(t, "Plot.Vegetation", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - zu Leer")) {
                        Bewertungen.setGrund(t, "Plot.Leer", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - Zusammenhangslos")) {
                        Bewertungen.setGrund(t, "Plot.Zusammenhang", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPlot - zu Voll")) {
                        Bewertungen.setGrund(t, "Plot.Voll", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMapping - Bäume")) {
                        Bewertungen.setGrund(t, "Mapping.Baum", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMapping - Organic")) {
                        Bewertungen.setGrund(t, "Mapping.Organic", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMapping - Einrichtung")) {
                        Bewertungen.setGrund(t, "Mapping.Einrichtung", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Detailsetzung")) {
                        Bewertungen.setGrund(t, "Struktur.Detailsetzung", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Dach")) {
                        Bewertungen.setGrund(t, "Struktur.Dach", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Aufbau")) {
                        Bewertungen.setGrund(t, "Struktur.Aufbau", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lStruktur - Details")) {
                        Bewertungen.setGrund(t, "Struktur.Details", true);
                        p.closeInventory();
                        openAblehnen(p);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lBemerkung")) {
                        ChatEvent.bemerkungplayer.add(p);
                        p.sendMessage(Main.prefix + "§4§lSchreibe deine Bemerkung in den Chat");
                        p.sendMessage(Main.prefix + "§4§loder um abzubrechen: !abbrechen");
                        p.closeInventory();
                    } else p.sendMessage(Main.error + "§cDiesen Grund gibt es nicht");
                }
            }
        } else if (e.getView().getTitle().equals("§7>> §6Ablehnen? §7<<")) {
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBestätigen")){
                p.closeInventory();
                Bewertungen.setStatus(t, "Abgelehnt");
                Abgaben.players.remove(PlayerEvent.currentplayer.get(p));
                PlayerEvent.currentplayer.remove(p);
                Abgaben.addList(Abgaben.players);
                Abgaben.addArray();
                p.sendMessage(Main.prefix + "§7Der Spieler wurde abgelehnt");
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbrechen")){
                p.closeInventory();
                openAblehnen(p);
            }
        }
    }

    public static void openAblehnen(Player p) {
        OfflinePlayer t = Bukkit.getOfflinePlayer(PlayerEvent.currentplayer.get(p));
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§7>> §6Grund §7<<");

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
        }

        inv.setItem(4, new ItemBuilder(Material.BOOK).setName("§6§lBemerkung").setLore("", Bewertungen.getBemerkung(t)).build());

        if (Bewertungen.getGrund(t, "Terra.Form").equals(true)) {
            inv.setItem(0, new ItemBuilder(Material.GRASS_BLOCK).setName("§6§lTerra - Forming").setLore("", "§7Versuche dein Terraforming zu verbessern").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(0, new ItemBuilder(Material.GRASS_BLOCK).setName("§6§lTerra - Forming").setLore("", "§7Versuche dein Terraforming zu verbessern").build());
        }

        if (Bewertungen.getGrund(t, "Terra.Aufbau").equals(true)) {
            inv.setItem(9, new ItemBuilder(Material.PODZOL).setName("§6§lTerra - Aufbau").setLore("", "§7Verbessere den Aufbau deines Terras").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(9, new ItemBuilder(Material.PODZOL).setName("§6§lTerra - Aufbau").setLore("", "§7Verbessere den Aufbau deines Terras").build());
        }
        if (Bewertungen.getGrund(t, "Terra.Coloring").equals(true)) {
            inv.setItem(18, new ItemBuilder(Material.WHITE_TERRACOTTA).setName("§6§lTerra - Coloring").setLore("", "§7Verbessere dein Terracoloring durch passende Blockwahl").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(18, new ItemBuilder(Material.WHITE_TERRACOTTA).setName("§6§lTerra - Coloring").setLore("", "§7Verbessere dein Terracoloring durch passende Blockwahl").build());
        }
        if (Bewertungen.getGrund(t, "Terra.Besitz").equals(true)) {
            inv.setItem(19, new ItemBuilder(Material.BARRIER).setName("§6§lTerra - nicht Vorhanden").setLore("", "§7Dein Plot muss Terraforming besitzen").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(19, new ItemBuilder(Material.BARRIER).setName("§6§lTerra - nicht Vorhanden").setLore("", "§7Dein Plot muss Terraforming besitzen").build());
        }
        if (Bewertungen.getGrund(t, "Struktur.Detailsetzung").equals(true)) {
            inv.setItem(8, new ItemBuilder(Material.COBBLESTONE_WALL).setName("§6§lStruktur - Detailsetzung").setLore("", "§7Versuche die Details deiner Struktur besser zu setzen").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(8, new ItemBuilder(Material.COBBLESTONE_WALL).setName("§6§lStruktur - Detailsetzung").setLore("", "§7Versuche die Details deiner Struktur besser zu setzen").build());
        }
        if (Bewertungen.getGrund(t, "Struktur.Dach").equals(true)) {
            inv.setItem(17, new ItemBuilder(Material.BRICK_STAIRS).setName("§6§lStruktur - Dach").setLore("", "§7Der Aufbau deines Daches muss verbessert werden").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(17, new ItemBuilder(Material.BRICK_STAIRS).setName("§6§lStruktur - Dach").setLore("", "§7Der Aufbau deines Daches muss verbessert werden").build());
        }
        if (Bewertungen.getGrund(t, "Struktur.Details").equals(true)) {
            inv.setItem(25, new ItemBuilder(Material.OAK_FENCE).setName("§6§lStruktur - Details").setLore("", "§7Verbessere die Details deiner Strukturen").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(25, new ItemBuilder(Material.OAK_FENCE).setName("§6§lStruktur - Details").setLore("", "§7Verbessere die Details deiner Strukturen").build());
        }
        if (Bewertungen.getGrund(t, "Struktur.Aufbau").equals(true)) {
            inv.setItem(26, new ItemBuilder(Material.BRICKS).setName("§6§lStruktur - Aufbau").setLore("", "§7Verbessere den Aufbau deiner Struktur").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(26, new ItemBuilder(Material.BRICKS).setName("§6§lStruktur - Aufbau").setLore("", "§7Verbessere den Aufbau deiner Struktur").build());
        }
        if (Bewertungen.getGrund(t, "Plot.Leer").equals(true)) {
            inv.setItem(2, new ItemBuilder(Material.WHITE_STAINED_GLASS).setName("§6§lPlot - zu Leer").setLore("", "§7Dein Plot fühlt sich zu leer an").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(2, new ItemBuilder(Material.WHITE_STAINED_GLASS).setName("§6§lPlot - zu Leer").setLore("", "§7Dein Plot fühlt sich zu leer an").build());
        }
        if (Bewertungen.getGrund(t, "Plot.Vegetation").equals(true)) {
            inv.setItem(11, new ItemBuilder(Material.VINE).setName("§6§lPlot - Vegetation").setLore("", "§7Die Vegetation auf deinem Plot fehlt oder ist schlecht plaziert").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(11, new ItemBuilder(Material.VINE).setName("§6§lPlot - Vegetation").setLore("", "§7Die Vegetation auf deinem Plot fehlt oder ist schlecht plaziert").build());
        }
        if (Bewertungen.getGrund(t, "Plot.Zusammenhang").equals(true)) {
            inv.setItem(15, new ItemBuilder(Material.MAP).setName("§6§lPlot - Zusammenhangslos").setLore("", "§7Der Aufbau deines Plots hat keinen zusammenhang").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(15, new ItemBuilder(Material.MAP).setName("§6§lPlot - Zusammenhangslos").setLore("", "§7Der Aufbau deines Plots hat keinen zusammenhang").build());
        }
        if (Bewertungen.getGrund(t, "Plot.Voll").equals(true)) {
            inv.setItem(6, new ItemBuilder(Material.BLACK_STAINED_GLASS).setName("§6§lPlot - zu Voll").setLore("", "§7Dein Plot fühlt sich zu voll an").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(6, new ItemBuilder(Material.BLACK_STAINED_GLASS).setName("§6§lPlot - zu Voll").setLore("", "§7Dein Plot fühlt sich zu voll an").build());
        }
        if (Bewertungen.getGrund(t, "Mapping.Baum").equals(true)) {
            inv.setItem(21, new ItemBuilder(Material.SPRUCE_SAPLING).setName("§6§lMapping - Bäume").setLore("", "§7Verbessere die Qualität deiner Bäume").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(21, new ItemBuilder(Material.SPRUCE_SAPLING).setName("§6§lMapping - Bäume").setLore("", "§7Verbessere die Qualität deiner Bäume").build());
        }
        if (Bewertungen.getGrund(t, "Mapping.Organic").equals(true)) {
            inv.setItem(22, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§6§lMapping - Organic").setLore("", "§7Verbessere den Aufbau deiner Organic").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(22, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§6§lMapping - Organic").setLore("", "§7Verbessere den Aufbau deiner Organic").build());
        }
        if (Bewertungen.getGrund(t, "Mapping.Einrichtung").equals(true)) {
            inv.setItem(23, new ItemBuilder(Material.BOOKSHELF).setName("§6§lMapping - Einrichtung").setLore("", "§7Verbessere den Aufbau deiner Einrichtung").addEnchantment(Enchantment.CHANNELING, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build());
        }else{
            inv.setItem(23, new ItemBuilder(Material.BOOKSHELF).setName("§6§lMapping - Einrichtung").setLore("", "§7Verbessere den Aufbau deiner Einrichtung").build());
        }

        inv.setItem(38, new ItemBuilder(Material.LIME_DYE).setName("§aBestätigen").build());
        inv.setItem(40, new ItemBuilder(Material.PLAYER_HEAD).setOwner(PlayerEvent.currentplayer.get(p)).setName("§6" + PlayerEvent.currentplayer.get(p)).setLore("§7Hat sich beworben am", "", "§7Datum: §a" + Bewertungen.getDate(t), "§7Zeit: §a" + Bewertungen.getTime(t)).build());
        inv.setItem(42, new ItemBuilder(Material.RED_DYE).setName("§cAbbrechen").build());


        p.openInventory(inv);
    }

}
