package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Abgaben;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BewerbenCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("bewerben") || cmd.getName().equalsIgnoreCase("abgeben")){
            if(!Bewertungen.getAnnahme(p.getUniqueId().toString()).equals("Angenommen")){
                if(!Bewertungen.getAnnahme(p.getUniqueId().toString()).equals("Teammitglied")){
                    if(!Bewertungen.getAnnahme(p.getUniqueId().toString()).equals("Waiting")) {
                        if (!Abgaben.players.contains(p.getName())) {
                            Abgaben.players.add(p.getName());
                            Bewertungen.setAnnahme(p.getUniqueId().toString(), "Waiting");
                            Bewertungen.setDate(p.getUniqueId().toString());
                            Bewertungen.setTime(p.getUniqueId().toString());
                            Bewertungen.setGrund(p.getUniqueId().toString(), Arrays.asList(""));
                            p.sendMessage(Main.prefix + "§7Du hast deine Bewerbung abgegeben");

                            for(Player all : Bukkit.getOnlinePlayers()){
                                if(all.hasPermission("tmb.bewerten")){
                                    all.sendMessage(Main.prefix + "§7Der Spieler §a" + p.getName() + " §7hat seine Bewerbung abgegeben");
                                }
                            }
                        } else {
                            p.sendMessage(Main.error + "§cDu hast dich schon beworben");
                        }
                    }else{
                        p.sendMessage(Main.error + "§cDeine Bewerbung wird schon bearbeitet");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDu bist ein Teammitglied und kannst dich deshalb nicht bewerben");
                }
            }else{
                p.sendMessage(Main.error + "§cDeine Bewerbung wurde schon bearbeitet");
            }
        }
        return false;
    }
}
