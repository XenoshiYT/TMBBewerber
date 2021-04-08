package me.xenodev.tmbbe.event;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionManagement;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import me.xenodev.tmbbe.files.Abgaben;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.files.PlayerInfo;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.SBBuilder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        File file1 = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);

        File file2 = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);


        SBBuilder.setScoreboard(p);
        IPermissionManagement manager = CloudNetDriver.getInstance().getPermissionManagement();
        IPermissionUser player = manager.getUser(p.getUniqueId());
        if(player.inGroup("Owner") || player.inGroup("Admin") || player.inGroup("Developer") || player.inGroup("Developer+") || player.inGroup("Builder") || player.inGroup("Chefbuilder") || player.inGroup("Testbuilder") || player.inGroup("Supporter")){
            p.sendMessage("");
            p.sendMessage(Main.prefix + "§cEs gibt zur Zeit §e" + Abgaben.players.size() + " §cabgegebene Bewerbungen");
            p.sendMessage("");

            if(!file1.exists()){
                Bewertungen.setStatus(p, "Teammitglied");
                Bewertungen.setDate(p);
                Bewertungen.setTime(p);
            }
            if(!file2.exists()){
                PlayerInfo.setFirstDate(p);
                PlayerInfo.setFirstTime(p);
            }
        }else{
            if(!file1.exists()){
                Bewertungen.setStatus(p, "Nicht Abgegeben");
            }
            if(!file2.exists()){
                PlayerInfo.setFirstDate(p);
                PlayerInfo.setFirstTime(p);
            }
        }
        PlayerInfo.setJoins(p, PlayerInfo.getJoins(p) + 1);

        e.setJoinMessage(Main.prefix + "§7Der Spieler §6" + p.getName() + " §7ist §abeigetreten");
    }
}
