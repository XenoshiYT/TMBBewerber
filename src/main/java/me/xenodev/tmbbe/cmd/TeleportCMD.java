package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("tp")) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if(p.hasPermission("tmb.tp")){
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t.isOnline()){
                        p.teleport(t);
                        p.sendMessage(Main.prefix + "§7Du hast dich zu §a" + t.getName() + " §7teleportiert");
                    }else{
                        p.sendMessage(Main.error + "§cDer Spieler ist nicht online");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            } else if (args.length == 2) {
                if(p.hasPermission("tmb.tp.other")){
                    Player t1 = Bukkit.getPlayerExact(args[0]);
                    Player t2 = Bukkit.getPlayerExact(args[1]);
                    if(t1.isOnline() || t2.isOnline()){
                        t1.teleport(t2);
                        p.sendMessage(Main.prefix + "§7Du hast §a" + t1.getName() + " §7zu §a" + t2.getName() + " §7teleportiert");
                    }else{
                        p.sendMessage(Main.error + "§cDie Spieler sind nicht online");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            } else {
                p.sendMessage(Main.error + "§7Bitte benutze §a/tp <spieler> [spieler]");
            }
        }

        if(cmd.getName().equalsIgnoreCase("tphere")) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if(args[0].equalsIgnoreCase("all")){
                    if(p.hasPermission("tmb.tphere.all")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.teleport(p);
                        }
                        p.sendMessage(Main.prefix + "§7Du hast §aalle §7Spieler zu dir teleportiert");
                    }else{
                        p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                    }
                }else{
                    if(p.hasPermission("tmb.tphere")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if(t.isOnline()){
                            t.teleport(p);
                            p.sendMessage(Main.prefix + "§7Du hast §a" + t.getName() + " §7zu dir teleportiert");
                        }else{
                            p.sendMessage(Main.error + "§cDer Spieler ist nicht online");
                        }
                    }else{
                        p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                    }
                }
            } else {
                p.sendMessage(Main.error + "§7Bitte benutze §a/tphere <spieler/all>");
            }
        }

        if(cmd.getName().equalsIgnoreCase("tpcord")) {
            Player p = (Player) sender;
            if (args.length == 3) {
                if(p.hasPermission("tmb.tpcord")){
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    Location loc = new Location(p.getWorld(),x, y, z);
                    p.teleport(loc);
                    p.sendMessage(Main.prefix + "§7Du hast dich zu §a" + x + " §7, §a" + y + "§7, §a" + z + " §7teleportiert");
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            } else {
                p.sendMessage(Main.error + "§7Bitte benutze §a/tpcord <x> <y> <z>");
            }
        }
        return false;
    }
}
