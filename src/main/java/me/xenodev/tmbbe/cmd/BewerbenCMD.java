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

        //Create Default Configuration
        File file = new File("plugins//TMBClan//Bewertungen", p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            file.mkdir();
        }
        cfg.set("angenommen", " ");

        //Custom noch nicht implementiert
        //cfg.set("custom",false);
        //cfg.set("custom.text",null);

        if(cmd.getName().equalsIgnoreCase("bewerben") || cmd.getName().equalsIgnoreCase("abgeben")){
            if(!cfg.get("angenommen").equals("Angenommen")){
                if(!cfg.get("angenommen").equals("Teammitglied")){
                    if(!cfg.get("angenommen").equals("Waiting")) {
                        if (!Abgaben.players.contains(p.getName())) {
                            Abgaben.players.add(p.getName());
                            cfg.set("angenommen","Waiting");

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            String date = dateFormat.format(new Date());

                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                            String time = timeFormat.format(new Date());

                            cfg.set("date",date);
                            cfg.set("time",time);
                            cfg.set("terra.forming", false);
                            cfg.set("terra.aufbau", false);
                            cfg.set("terra.coloring", false);
                            cfg.set("terra.nichtvorhanden", false);
                            cfg.set("plot.vegetation", false);
                            cfg.set("plot.zuleer", false);
                            cfg.set("plot.zusammenhangslos", false);
                            cfg.set("plot.zuvoll", false);
                            cfg.set("mapping.baeume", false);
                            cfg.set("struktur.detailsetzung", false);
                            cfg.set("struktur.dach", false);
                            cfg.set("struktur.detail", false);
                            cfg.set("struktur.aufbau", false);
                            try {
                                cfg.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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
