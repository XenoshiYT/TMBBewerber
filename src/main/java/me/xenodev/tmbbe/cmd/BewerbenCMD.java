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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BewerbenCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("bewerben") || cmd.getName().equalsIgnoreCase("abgeben")){
            if(!Bewertungen.getStatus(p).equalsIgnoreCase("Angenommen")){
                if(!Bewertungen.getStatus(p).equalsIgnoreCase("Teammitglied")){
                    if(!Bewertungen.getStatus(p).equalsIgnoreCase("Waiting")) {
                        if (!Abgaben.players.contains(p.getName())) {
                            Abgaben.players.add(p.getName());
                            Bewertungen.setStatus(p, "Waiting");
                            Bewertungen.setDate(p);
                            Bewertungen.setTime(p);
                            p.sendMessage(Main.prefix + "§7Du hast deine Bewerbung abgegeben");

                            for(Player all : Bukkit.getOnlinePlayers()){
                                if(all.hasPermission("tmb.bewerten")){
                                    all.sendMessage(Main.prefix + "§a" + p.getName() + " §7hat seine Bewerbung abgegeben");
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
