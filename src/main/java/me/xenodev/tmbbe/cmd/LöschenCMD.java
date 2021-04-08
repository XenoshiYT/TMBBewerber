package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Abgaben;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class LöschenCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("abbrechen") || cmd.getName().equalsIgnoreCase("löschen")){
            if(!Bewertungen.getStatus(p).equalsIgnoreCase("Teammitglied")) {
                if (!Bewertungen.getStatus(p).equalsIgnoreCase("Abgelehnt") || !Bewertungen.getStatus(p).equalsIgnoreCase("Angenommen")) {
                    if (Abgaben.players.contains(p.getName()) && Bewertungen.getStatus(p).equalsIgnoreCase("Waiting")) {
                        Abgaben.players.remove(p.getName());
                        Bewertungen.setStatus(p, "Nicht Abgegeben");
                        p.sendMessage(Main.prefix + "§7Du hast deine Bewerbung gelöscht");

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("tmb.bewerten")) {
                                all.sendMessage(Main.prefix + "§a" + p.getName() + " §7hat seine Bewerbung zurückgezogen");
                            }
                        }
                    } else {
                        p.sendMessage(Main.error + "§cDu hast dich noch nicht beworben");
                    }
                } else {
                    p.sendMessage(Main.error + "§cDeine Bewerbung wurde schon bearbeitet");
                }
            }else{
                p.sendMessage(Main.error + "§cDu bist ein Teammitglied und kannst die Bewerbung nicht löschen");
            }
        }
        return false;
    }
}
