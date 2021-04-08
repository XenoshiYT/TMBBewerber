package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.PlayerInfo;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.mysql.SQLTime;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfoCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("playerinfo") || cmd.getName().equalsIgnoreCase("pi")){
            if(args.length == 1){
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lPlayerInfo §7«----------");
                p.sendMessage("");
                p.sendMessage(Main.prefix + "§7Die Playerinfos von §a§l" + t.getName() + "§7");
                p.sendMessage("  §8§l» §7UUID: §e" + t.getUniqueId());
                p.sendMessage("  §8§l» §7First Login: §e" + PlayerInfo.getFirstDate(t) + " §7- §e" + PlayerInfo.getFirstTime(t));
                p.sendMessage("  §8§l» §7Last Login: §e" + PlayerInfo.getLastDate(t) + " §7- §e" + PlayerInfo.getLastTime(t));
                p.sendMessage("  §8§l» §7Total Logins: §e" + PlayerInfo.getJoins(t));
                p.sendMessage("");
                p.sendMessage("§7----------» §e§lPlayerInfo §7«----------");
                p.sendMessage("");
            }else{
                p.sendMessage(Main.error + "§cDu musst einen Spieler angeben!");
            }
        }
        return false;
    }
}
