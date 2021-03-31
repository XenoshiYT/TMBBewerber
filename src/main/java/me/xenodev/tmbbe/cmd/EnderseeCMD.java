package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderseeCMD  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("endersee")){
            if(args.length == 1){
                if(p.hasPermission("tmb.endersee")){
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t.isOnline()){
                        p.openInventory(t.getInventory());
                        p.sendMessage(Main.prefix + "§7Du öffnest die Enderchest von §a" + t.getName());
                    }else{
                        p.sendMessage(Main.error + "§cDer Spieler ist nicht online");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            } else {
                p.sendMessage(Main.error + "§7Bitte benutze §a/endersee <spieler>");
            }
        }


        return false;
    }
}
