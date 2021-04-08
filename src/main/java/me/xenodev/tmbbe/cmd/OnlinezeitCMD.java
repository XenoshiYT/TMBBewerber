package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.mysql.SQLTime;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OnlinezeitCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("oz") || cmd.getName().equalsIgnoreCase("onlinezeit") || cmd.getName().equalsIgnoreCase("onlinetime") || cmd.getName().equalsIgnoreCase("ot")){
            if(args.length == 1){
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lOnlinetimeinformationen §7«----------");
                p.sendMessage("");
                p.sendMessage(Main.prefix + "§7Die Onlinetime von §a§l" + t.getName() + "§7");
                p.sendMessage("  §8§l» §7Stunden: §e" + SQLTime.getHours(t.getUniqueId()));
                p.sendMessage("  §8§l» §7Minuten: §e" + SQLTime.getMinutes(t.getUniqueId()));
                p.sendMessage("  §8§l» §7Sekunden: §e" + SQLTime.getSeconds(t.getUniqueId()));
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lOnlinetimeinformationen §7«----------");
                p.sendMessage("");
            }else{
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lOnlinetimeinformationen §7«----------");
                p.sendMessage("");
                p.sendMessage(Main.prefix + "§7Die Onlinetime von §a§l" + "Dir" + "§7");
                p.sendMessage("  §8§l» §7Stunden: §e" + SQLTime.getHours(p.getUniqueId()));
                p.sendMessage("  §8§l» §7Minuten: §e" + SQLTime.getMinutes(p.getUniqueId()));
                p.sendMessage("  §8§l» §7Sekunden: §e" + SQLTime.getSeconds(p.getUniqueId()));
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lOnlinetimeinformationen §7«----------");
                p.sendMessage("");
            }
        }
        return false;
    }
}
