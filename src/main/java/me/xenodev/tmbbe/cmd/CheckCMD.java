package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.io.File;

public class CheckCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        File file = new File("plugins//TMBClan//Bewertungen//" + p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cmd.getName().equalsIgnoreCase("check")) {
            p.sendMessage("");
            p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
            p.sendMessage("");
            p.sendMessage(Main.prefix + "§7Dein Bewerberstatus lautet:");
            p.sendMessage("  §8§l» §a" + cfg.get("angenommen").toString());
            p.sendMessage(Main.prefix + "§7Deine Anmeldezeit:");
            p.sendMessage("  §8§l» §9" + cfg.get("date").toString() + " §7- §9" + cfg.get("date").toString());
            if (cfg.get("angenommen").equals("Abgelehnt")) {
                p.sendMessage(Main.prefix + "§7Die Ablehnungsgründe lauten:");
                if (cfg.get("terra.forming").equals(true)) {
                    p.sendMessage("  §8§l» §e ");
                } else if (cfg.get("terra.aufbau").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("terra.nichtvorhanden").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("terra.coloring").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("struktur.detailsetzung").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("struktur.detailsetztung").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("struktur.dach").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("struktur.aufbau").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("plot.vegegation").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("plot.zuleer").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("plot.zusammenhangslos").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("plot.zuvoll").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                } else if (cfg.get("mapping.baeume").equals(true)) {
                    p.sendMessage("  §8§l» §e");
                }

                    p.sendMessage("  §8§l» §e");
            }

            p.sendMessage("");
            p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
            p.sendMessage("");
        }

        return false;
    }
}
