package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("check")){
            p.sendMessage("");
            p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
            p.sendMessage("");
            p.sendMessage(Main.prefix + "§7Dein Bewerberstatus lautet:");
            p.sendMessage("  §8§l» §a" + Bewertungen.getAnnahme(p.getUniqueId().toString()));
            p.sendMessage(Main.prefix + "§7Deine Anmeldezeit:");
            p.sendMessage("  §8§l» §9" + Bewertungen.getDate(p.getUniqueId().toString()) + " §7- §9" + Bewertungen.getTime(p.getUniqueId().toString()));
            p.sendMessage(Main.prefix + "§7Der Grund lautet:");
            p.sendMessage("  §8§l» §e" + Bewertungen.getGrund(p.getUniqueId().toString()));
            p.sendMessage("");
            p.sendMessage("§7----------» §e§lBewerbungsinformationen §7«----------");
            p.sendMessage("");
        }

        return false;
    }
}
