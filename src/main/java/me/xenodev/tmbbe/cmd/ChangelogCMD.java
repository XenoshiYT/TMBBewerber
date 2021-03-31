package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class ChangelogCMD  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("changelog")){
            PluginDescriptionFile pdf = Main.instance.getDescription();

            //p.sendMessage("§8» §7/endersee §8| §7§oSchue die Enderchast eines Spielers an");

            p.sendMessage("");
            p.sendMessage("§7----------» §e§lChangelog §7«----------");
            p.sendMessage("§7§oPluginversion: §5§ov" + pdf.getVersion());
            p.sendMessage("");
            p.sendMessage("§a+ §7Neue Befehle");
            p.sendMessage("§8» §7...");
            p.sendMessage("");
            p.sendMessage("§a+ §7Neue Funktionen");
            p.sendMessage("§8» §7Ist man im Team kann man sich nicht bewerben");
            p.sendMessage("");
            p.sendMessage("§c- §7Weggefallen");
            p.sendMessage("§8» §7...");
            p.sendMessage("");
            p.sendMessage("§a+ §7Bugfixes");
            p.sendMessage("§8» §7Abgaben mit Offlineplayer abfragen");
            p.sendMessage("");
            p.sendMessage("§7----------» §e§lChangelog §7«----------");
            p.sendMessage("");
        }


        return false;
    }
}
