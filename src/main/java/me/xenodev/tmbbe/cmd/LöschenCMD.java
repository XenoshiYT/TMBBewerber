package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Abgaben;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LöschenCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("abbrechen") || cmd.getName().equalsIgnoreCase("löschen")){
            if(!Bewertungen.getAnnahme(p.getUniqueId().toString()).equals("Abgelehnt") || !Bewertungen.getAnnahme(p.getUniqueId().toString()).equals("Angenommen")){
                if(Abgaben.players.contains(p.getName()) && Bewertungen.getAnnahme(p.getUniqueId().toString()).equals("Waiting")){
                    Abgaben.players.remove(p.getName());
                    Bewertungen.setAnnahme(p.getUniqueId().toString(), null);
                    Bewertungen.setAnnahme(p.getUniqueId().toString(), "nothing");
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
