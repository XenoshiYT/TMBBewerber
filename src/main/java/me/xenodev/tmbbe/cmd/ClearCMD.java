package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("clear")){
            if (args.length == 1) {
                if(p.hasPermission("tmb.clear.other")){
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t.isOnline()) {
                        t.getInventory().clear();
                        p.sendMessage(Main.prefix + "§7Du hast das Inventar von §a" + t.getName() + " §7gelöscht");
                    }else{
                        p.sendMessage(Main.error + "§cDer Spieler §6" + t.getName() + " §cist nicht Online");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            } else {
                if(p.hasPermission("tmb.clear")){
                    p.getInventory().clear();
                    p.sendMessage(Main.prefix + "§7Du hast dein Inventar gelöscht");
                }
            }
        }


        return false;
    }
}
