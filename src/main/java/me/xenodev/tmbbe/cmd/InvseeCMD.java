package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("invsee")){
            if(args.length == 1){
                if(p.hasPermission("tmb.invsee")){
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t.isOnline()){
                        p.openInventory(t.getInventory());
                        p.sendMessage(Main.prefix + "§7Du öffnest das Inventar von §a" + t.getName());
                    }else{
                        p.sendMessage(Main.error + "§cDer Spieler ist nicht online");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            } else {
                p.sendMessage(Main.error + "§7Bitte benutze §a/invsee <spieler>");
            }
        }


        return false;
    }
}
