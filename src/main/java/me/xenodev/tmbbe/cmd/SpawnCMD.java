package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

public class SpawnCMD implements CommandExecutor, Listener {

    public static File file = new File("plugins//TMBClan//settings.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("spawn")){
            if(args.length == 1){
                if(p.hasPermission("tmb.setspawn")){
                    if(args[0].equalsIgnoreCase("set")){
                        setSpawn(p.getLocation());
                        p.sendMessage(Main.prefix + "§7Du hast den §eSpawn §7gesetzt");
                    }else{
                        p.sendMessage(Main.error + "§7Bitte benutze §a/spawn <set>");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                }
            }else{
                Location loc = getSpawn();
                p.teleport(loc);
                p.sendMessage(Main.prefix + "§7Du hast dich zum §eSpawn §7teleportiert");
            }
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPermission("tmb.spawn.bypass")){
            Location loc = getSpawn();
            p.teleport(loc);
        }
    }

    private static void setSpawn(Location loc){
        cfg.set("Spawn.x", loc.getX());
        cfg.set("Spawn.y", loc.getY());
        cfg.set("Spawn.z", loc.getZ());
        cfg.set("Spawn.yaw", loc.getYaw());
        cfg.set("Spawn.pitch", loc.getPitch());
        cfg.set("Spawn.world", loc.getWorld().getName());
        save();
    }

    private static Location getSpawn(){
        World world = Bukkit.getWorld(cfg.getString("Spawn.world"));
        double x = cfg.getDouble("Spawn.x");
        double y = cfg.getDouble("Spawn.y");
        double z = cfg.getDouble("Spawn.z");
        Location loc = new Location(world, x, y, z);
        loc.setYaw(cfg.getInt("Spawn.yaw"));
        loc.setPitch(cfg.getInt("Spawn.pitch"));
        return loc;
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
