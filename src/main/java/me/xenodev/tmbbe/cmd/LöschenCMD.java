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
        File file = new File("plugins//TMBClan//Bewertungen//" + p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if(cmd.getName().equalsIgnoreCase("abbrechen") || cmd.getName().equalsIgnoreCase("löschen")){
            if(!cfg.get("angenommen").equals("Abgelehnt") || !cfg.get("angenommen").equals("Angenommen")){
                if(Abgaben.players.contains(p.getName()) && cfg.get("angenommen").equals("Waiting")){
                    Abgaben.players.remove(p.getName());
                    file.delete();
                    p.sendMessage(Main.prefix + "§7Du hast deine Bewerbung gelöscht");

                    for(Player all : Bukkit.getOnlinePlayers()){
                        if(all.hasPermission("tmb.bewerten")){
                            all.sendMessage(Main.prefix + "§7Der Spieler §a" + p.getName() + " §7hat seine Bewerbung zurückgezogen");
                        }
                    }
                }else{
                    p.sendMessage(Main.error + "§cDu hast dich noch nicht beworben");
                }
            }else{
                p.sendMessage(Main.error + "§cDeine Bewerbung wurde schon bearbeitet");
            }
        }
        return false;
    }
}
