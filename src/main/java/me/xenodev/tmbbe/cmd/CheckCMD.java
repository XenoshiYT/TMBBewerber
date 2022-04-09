package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
        if (cmd.getName().equalsIgnoreCase("check")) {
            if(args.length == 1){
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lBewerbungsinformationen  §7«----------");
                p.sendMessage("");
                p.sendMessage(Main.prefix + "§7Die Informationen von §a§l" + t.getName() + "§7");
                p.sendMessage("  §8§l» §7Status: §9" + Bewertungen.getStatus(t));
                p.sendMessage("  §8§l» §7Abgabezeit: §9" + Bewertungen.getDate(t) + " §7- §9" + Bewertungen.getTime(t));
                if(Bewertungen.getStatus(t).equalsIgnoreCase("Abgelehnt")) {
                    p.sendMessage("  §8§l» §7Ablehngründe:");
                    if (Bewertungen.getGrund(t, "Terra.Form").equals(true)) {
                        p.sendMessage("  §8§l- §9Versuche dein Terraforming zu verbessern");
                    }
                    if (Bewertungen.getGrund(t, "Terra.Aufbau").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deines Terras");
                    }
                    if (Bewertungen.getGrund(t, "Terra.Besitz").equals(true)) {
                        p.sendMessage("  §8§l- §9Dein Plot muss Terraforming besitzen");
                    }
                    if (Bewertungen.getGrund(t, "Terra.Coloring").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere dein Terracoloring durch passende Blockwahl");
                    }
                    if (Bewertungen.getGrund(t, "Struktur.Detailsetzung").equals(true)) {
                        p.sendMessage("  §8§l- §9Versuche die Details deiner Struktur besser zu setzen");
                    }
                    if (Bewertungen.getGrund(t, "Struktur.Details").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere die Details deiner Strukturen");
                    }
                    if (Bewertungen.getGrund(t, "Struktur.Dach").equals(true)) {
                        p.sendMessage("  §8§l- §9Der Aufbau deines Daches muss verbessert werden");
                    }
                    if (Bewertungen.getGrund(t, "Struktur.Aufbau").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deiner Struktur");
                    }
                    if (Bewertungen.getGrund(t, "Plot.Vegetation").equals(true)) {
                        p.sendMessage("  §8§l- §9Die Vegetation auf deinem Plot fehlt oder ist schlecht plaziert");
                    }
                    if (Bewertungen.getGrund(t, "Plot.Leer").equals(true)) {
                        p.sendMessage("  §8§l- §9Dein Plot fühlt sich zu leer an");
                    }
                    if (Bewertungen.getGrund(t, "Plot.Zusammenhang").equals(true)) {
                        p.sendMessage("  §8§l- §9Der Aufbau deines Plots hat keinen zusammenhang");
                    }
                    if (Bewertungen.getGrund(t, "Plot.Voll").equals(true)) {
                        p.sendMessage("  §8§l- §9Dein Plot fühlt sich zu voll an");
                    }
                    if (Bewertungen.getGrund(t, "Mapping.Baum").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere die Qualität deiner Bäume");
                    }
                    if (Bewertungen.getGrund(t, "Mapping.Organic").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deiner Organic");
                    }
                    if (Bewertungen.getGrund(t, "Mapping.Einrichtung").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deiner Einrichtung");
                    }

                    p.sendMessage("");
                    p.sendMessage("  §8§l» §7Bemerkung:");
                    p.sendMessage("  §8§l- §c" + Bewertungen.getBemerkung(t));
                }

                p.sendMessage("");
                p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
                p.sendMessage("");
            }else{
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
                p.sendMessage("");
                p.sendMessage(Main.prefix + "§7Die Informationen von §a§l" + "Dir" + "§7");
                p.sendMessage("  §8§l» §7Status: §9" + Bewertungen.getStatus(p));
                p.sendMessage("  §8§l» §7Abgabezeit: §9" + Bewertungen.getDate(p) + " §7- §9" + Bewertungen.getTime(p));
                if(Bewertungen.getStatus(p).equalsIgnoreCase("Abgelehnt")) {
                    p.sendMessage("  §8§l» §7Ablehngründe:");
                    if (Bewertungen.getGrund(p, "Terra.Form").equals(true)) {
                        p.sendMessage("  §8§l- §9Versuche dein Terraforming zu verbessern");
                    }
                    if (Bewertungen.getGrund(p, "Terra.Aufbau").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deines Terras");
                    }
                    if (Bewertungen.getGrund(p, "Terra.Besitz").equals(true)) {
                        p.sendMessage("  §8§l- §9Dein Plot muss Terraforming besitzen");
                    }
                    if (Bewertungen.getGrund(p, "Terra.Coloring").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere dein Terracoloring durch passende Blockwahl");
                    }
                    if (Bewertungen.getGrund(p, "Struktur.Detailsetzung").equals(true)) {
                        p.sendMessage("  §8§l- §9Versuche die Details deiner Struktur besser zu setzen");
                    }
                    if (Bewertungen.getGrund(p, "Struktur.Details").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere die Details deiner Strukturen");
                    }
                    if (Bewertungen.getGrund(p, "Struktur.Dach").equals(true)) {
                        p.sendMessage("  §8§l- §9Der Aufbau deines Daches muss verbessert werden");
                    }
                    if (Bewertungen.getGrund(p, "Struktur.Aufbau").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deiner Struktur");
                    }
                    if (Bewertungen.getGrund(p, "Plot.Vegetation").equals(true)) {
                        p.sendMessage("  §8§l- §9Die Vegetation auf deinem Plot fehlt oder ist schlecht plaziert");
                    }
                    if (Bewertungen.getGrund(p, "Plot.Leer").equals(true)) {
                        p.sendMessage("  §8§l- §9Dein Plot fühlt sich zu leer an");
                    }
                    if (Bewertungen.getGrund(p, "Plot.Zusammenhang").equals(true)) {
                        p.sendMessage("  §8§l- §9Der Aufbau deines Plots hat keinen zusammenhang");
                    }
                    if (Bewertungen.getGrund(p, "Plot.Voll").equals(true)) {
                        p.sendMessage("  §8§l- §9Dein Plot fühlt sich zu voll an");
                    }
                    if (Bewertungen.getGrund(p, "Mapping.Baum").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere die Qualität deiner Bäume");
                    }
                    if (Bewertungen.getGrund(p, "Mapping.Organic").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deiner Organic");
                    }
                    if (Bewertungen.getGrund(p, "Mapping.Einrichtung").equals(true)) {
                        p.sendMessage("  §8§l- §9Verbessere den Aufbau deiner Einrichtung");
                    }

                    p.sendMessage("");
                    p.sendMessage("  §8§l» §7Bemerkung:");
                    p.sendMessage("  §8§l- §c" + Bewertungen.getBemerkung(p));
                }

                p.sendMessage("");
                p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
                p.sendMessage("");
            }
        }

        return false;
    }
}
