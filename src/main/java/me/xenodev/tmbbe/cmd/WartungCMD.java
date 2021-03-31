package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class WartungCMD implements CommandExecutor, Listener {

    public static File file = new File("plugins//TMBClan//settings.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("wartung")){
            if(sender instanceof Player) {
                Player p = (Player)sender;
                if (p.hasPermission("tmb.wartung")) {
                    if (getWartung().equals(false)) {
                        setWartung(true);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("tmb.wartung.bypass")) {
                                all.kickPlayer(Main.prefix + "§7Die §4Wartungsarbeiten §7sind §caktiviert " + Main.prefix);
                            } else {
                                all.sendMessage(Main.prefix + "§7Die §4Wartungsarbeiten §7wurde §aaktiviert");
                            }
                        }
                    } else {
                        setWartung(false);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("tmb.wartung.bypass")) {
                                all.sendMessage(Main.prefix + "§7Die §4Wartungsarbeiten §7wurde §cdeaktiviert");
                            }
                        }
                    }
                } else {
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            }else{
                if (getWartung().equals(false)) {
                    setWartung(true);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if(all.isOnline()) {
                            if (!all.hasPermission("tmb.wartung.bypass")) {
                                all.kickPlayer(Main.prefix + "§7Die §4Wartungsarbeiten §7sind §caktiviert " + Main.prefix);
                            } else {
                                all.sendMessage(Main.prefix + "§7Die §4Wartungsarbeiten §7wurde §aaktiviert");
                            }
                        }
                    }
                    sender.sendMessage(Main.prefix + "§7Die §4Wartungsarbeiten §7wurde §aaktiviert");
                } else {
                    setWartung(false);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("tmb.wartung.bypass")) {
                            all.sendMessage(Main.prefix + "§7Die §4Wartungsarbeiten §7wurde §cdeaktiviert");
                        }
                    }
                    sender.sendMessage(Main.prefix + "§7Die §4Wartungsarbeiten §7wurde §cdeaktiviert");
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(getWartung().equals(true)){
            if(!p.hasPermission("tmb.wartung.bypass")){
                p.kickPlayer(Main.prefix + "§7Die §4Wartungsarbeiten §7sind §caktiviert " + Main.prefix);
            }
        }
    }

    private static void setWartung(Boolean bool){
        cfg.set("Wartung", bool);
        save();
    }

    private static Boolean getWartung(){
        return cfg.getBoolean("Wartung");
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
