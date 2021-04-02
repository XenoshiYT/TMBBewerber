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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
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
