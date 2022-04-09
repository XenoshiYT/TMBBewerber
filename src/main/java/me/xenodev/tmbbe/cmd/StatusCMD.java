package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatusCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("status")){
            if(p.hasPermission("tmb.status.set")) {
                if (args.length >= 2) {
                    OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                    String status = "";
                    for(int i = 1; i < args.length; i++){
                        status = status + " " + args[i];
                    }
                    Bewertungen.setStatus(t, status);
                    Bewertungen.setDate(t);
                    Bewertungen.setTime(t);
                    p.sendMessage(Main.prefix + "§7Du hast den Status von §a" + t.getName() + " §7auf §e" + status + " §7gesetzt");
                } else {
                    p.sendMessage(Main.error + "§7Bitte benutze §a/status <status> <spieler>");
                }
            }else{
                p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
            }
        }


        return false;
    }
}
