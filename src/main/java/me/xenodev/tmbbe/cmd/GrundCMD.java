package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.event.BewertenEvent;
import me.xenodev.tmbbe.event.PlayerEvent;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GrundCMD implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("grund")){
            if(p.hasPermission("tmb.bewerten.grund")){
                Inventory inv = Bukkit.createInventory(p, 9 * 3, "§7>> §6Unsere Gründe §7<<");

                for(int i = 0; i < 27; i++){
                    inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                }

                inv.setItem(0, new ItemBuilder(Material.GRASS_BLOCK).setName("§6§lTerra - Forming").setLore("", "§7Versuche dein Terraforming zu verbessern").build());
                inv.setItem(9, new ItemBuilder(Material.PODZOL).setName("§6§lTerra - Aufbau").setLore("", "§7Verbessere den Aufbau deines Terras").build());
                inv.setItem(18, new ItemBuilder(Material.WHITE_TERRACOTTA).setName("§6§lTerra - Coloring").setLore("", "§7Verbessere dein Terracoloring durch passende Blockwahl").build());
                inv.setItem(19, new ItemBuilder(Material.BARRIER).setName("§6§lTerra - nicht Vorhanden").setLore("", "§7Dein Plot muss Terraforming besitzen").build());
                inv.setItem(8, new ItemBuilder(Material.COBBLESTONE_WALL).setName("§6§lStruktur - Detailsetzung").setLore("", "§7Versuche die Details deiner Struktur besser zu setzen").build());
                inv.setItem(17, new ItemBuilder(Material.BRICK_STAIRS).setName("§6§lStruktur - Dach").setLore("", "§7Der Aufbau deines Daches muss verbessert werden").build());
                inv.setItem(25, new ItemBuilder(Material.OAK_FENCE).setName("§6§lStruktur - Details").setLore("", "§7Verbessere die Details deiner Strukturen").build());
                inv.setItem(26, new ItemBuilder(Material.BRICKS).setName("§6§lStruktur - Aufbau").setLore("", "§7Verbessere den Aufbau deiner Struktur").build());
                inv.setItem(2, new ItemBuilder(Material.WHITE_STAINED_GLASS).setName("§6§lPlot - Leer").setLore("", "§7Dein Plot fühlt sich zu leer an").build());
                inv.setItem(3, new ItemBuilder(Material.VINE).setName("§6§lPlot - Vegetation").setLore("", "§7Die Vegetation auf deinem Plot fehlt oder ist schlecht plaziert").build());
                inv.setItem(5, new ItemBuilder(Material.MAP).setName("§6§lPlot - Zusammenhangslos").setLore("", "§7Der Aufbau deines Plots hat keinen zusammenhang").build());
                inv.setItem(6, new ItemBuilder(Material.BLACK_STAINED_GLASS).setName("§6§lPlot - Voll").setLore("", "§7Dein Plot fühlt sich zu voll an").build());
                inv.setItem(21, new ItemBuilder(Material.SPRUCE_SAPLING).setName("§6§lMapping - Bäume").setLore("", "§7Verbessere die Qualität deiner Bäume").build());
                inv.setItem(22, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§6§lMapping - Organic").setLore("", "§7Verbessere den Aufbau deiner Organic").build());
                inv.setItem(23, new ItemBuilder(Material.BOOKSHELF).setName("§6§lMapping - Einrichtung").setLore("", "§7Verbessere den Aufbau deiner Einrichtung").build());

                p.openInventory(inv);
            }else{
                p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
            }
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase("§7>> §6Unsere Gründe §7<<")){
            e.setCancelled(true);
        }
    }
}
