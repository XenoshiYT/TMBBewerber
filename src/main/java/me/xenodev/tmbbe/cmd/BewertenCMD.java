package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.event.PlayerEvent;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BewertenCMD implements CommandExecutor {

    public static ArrayList<Player> bewerten = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("bewerten")){
            if(p.hasPermission("tmb.bewerten")){
                if(bewerten.contains(p)){
                    bewerten.remove(p);
                    p.sendMessage(Main.prefix + "§7Du hast den Bewertungsmodus §cverlassen");
                    PlayerEvent.currentplayer.remove(p);
                    p.getInventory().setItem(7, new ItemStack(Material.AIR));
                    p.getInventory().setItem(8, new ItemStack(Material.AIR));
                }else{
                    bewerten.add(p);
                    p.sendMessage(Main.prefix + "§7Du hast den Bewertungsmodus §cbetreten");
                    p.getInventory().setItem(7, new ItemBuilder(Material.FEATHER).setName("§6Plot Bewerten").setLore("", "§7Bewerte das Plot von dem Spieler den du ausgewählt hast").build());
                    p.getInventory().setItem(8, new ItemBuilder(Material.HEART_OF_THE_SEA).setName("§6Bewerberplots").setLore("", "§7Wähle ein abgegebenes Plot aus").build());
                }
            }else{
                p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
            }
        }


        return false;
    }
}
