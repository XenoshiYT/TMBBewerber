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
        File file = new File("plugins//TMBClan//Bewertungen", p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cmd.getName().equalsIgnoreCase("check")) {
            p.sendMessage("");
            p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
            p.sendMessage("");
            p.sendMessage(Main.prefix + "§7Dein Bewerberstatus lautet:");
            p.sendMessage("  §8§l» §a" + cfg.get("angenommen").toString());
            p.sendMessage(Main.prefix + "§7Deine Anmeldezeit:");
            p.sendMessage("  §8§l» §9" + cfg.get("date").toString() + " §7- §9" + cfg.get("time").toString());
            if (cfg.get("angenommen").equals("Abgelehnt")) {
                p.sendMessage(Main.prefix + "§7Die Ablehnungsgründe lauten:");
                if (cfg.get("terra.forming").equals(true)) {
                    p.sendMessage("  §8§l» §eVersuche dein Terraforming zu Verbessern");
                }
                if (cfg.get("terra.aufbau").equals(true)) {
                    p.sendMessage("  §8§l» §eVerbessere den Aufbau deines Terras");
                }
                if (cfg.get("terra.nichtvorhanden").equals(true)) {
                    p.sendMessage("  §8§l» §eDu musst dein Terra formen");
                }
                if (cfg.get("terra.coloring").equals(true)) {
                    p.sendMessage("  §8§l» §eVerbessere dein Terracoloring durch passende Blockwahl");
                }
                if (cfg.get("struktur.detailsetzung").equals(true)) {
                    p.sendMessage("  §8§l» §eVersuche die Details deiner Struktur besser zu setzen");
                }
                if (cfg.get("struktur.details").equals(true)) {
                    p.sendMessage("  §8§l» §eVerbessere die Details deiner Strukturen");
                }
                if (cfg.get("struktur.dach").equals(true)) {
                    p.sendMessage("  §8§l» §eDer Aufbau deines Dachs ist verbesserungswürdig");
                }
                if (cfg.get("struktur.aufbau").equals(true)) {
                    p.sendMessage("  §8§l» §eVerbessere den Aufbau deiner Struktur");
                }
                if (cfg.get("plot.vegegation").equals(true)) {
                    p.sendMessage("  §8§l» §eDie Vegetation auf deinem Plot fehlt oder ist schlecht plaziert");
                }
                if (cfg.get("plot.zuleer").equals(true)) {
                    p.sendMessage("  §8§l» §eDein Plot fühlt sich zu leer an");
                }
                if (cfg.get("plot.zusammenhangslos").equals(true)) {
                    p.sendMessage("  §8§l» §eDer Aufbau deines Plots hat keinen zusammenhang");
                }
                if (cfg.get("plot.zuvoll").equals(true)) {
                    p.sendMessage("  §8§l» §eDein Plot fühlt sich zu voll an");
                }
                if (cfg.get("mapping.baeume").equals(true)) {
                    p.sendMessage("  §8§l» §eDie Qualität deiner Bäume entspricht nicht unseren Standards");
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
