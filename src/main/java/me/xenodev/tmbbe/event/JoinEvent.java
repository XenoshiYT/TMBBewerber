package me.xenodev.tmbbe.event;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionManagement;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import me.xenodev.tmbbe.files.Bewertungen;
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

        File file = new File("plugins//TMBClan//Bewertungen", p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        e.setJoinMessage(Main.prefix + "§7Der Spieler §6" + p.getName() + " §7ist §abeigetreten");

        SBBuilder.setScoreboard(p);
        IPermissionManagement manager = CloudNetDriver.getInstance().getPermissionManagement();
        IPermissionUser player = manager.getUser(p.getUniqueId());
        if(player.inGroup("Owner") || player.inGroup("Admin")
                || player.inGroup("Developer") || player.inGroup("Developer+")
                || player.inGroup("Builder") || player.inGroup("Chefbuilder")
                || player.inGroup("Testbuilder") || player.inGroup("Supporter")){
            cfg.set("angenommen","Teammitglied");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String date = dateFormat.format(new Date());

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String time = timeFormat.format(new Date());

            cfg.set("date",date);
            cfg.set("time",time);
            cfg.set("terra.forming", false);
            cfg.set("terra.aufbau", false);
            cfg.set("terra.coloring", false);
            cfg.set("terra.nichtvorhanden", false);
            cfg.set("plot.vegetation", false);
            cfg.set("plot.zuleer", false);
            cfg.set("plot.zusammenhangslos", false);
            cfg.set("plot.zuvoll", false);
            cfg.set("mapping.baeume", false);
            cfg.set("struktur.detailsetzung", false);
            cfg.set("struktur.dach", false);
            cfg.set("struktur.detail", false);
            cfg.set("struktur.aufbau", false);
            try {
                cfg.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            cfg.set("angenommen","nicht Abgegeben");
            cfg.set("terra.forming", false);
            cfg.set("terra.aufbau", false);
            cfg.set("terra.coloring", false);
            cfg.set("terra.nichtvorhanden", false);
            cfg.set("plot.vegetation", false);
            cfg.set("plot.zuleer", false);
            cfg.set("plot.zusammenhangslos", false);
            cfg.set("plot.zuvoll", false);
            cfg.set("mapping.baeume", false);
            cfg.set("struktur.detailsetzung", false);
            cfg.set("struktur.dach", false);
            cfg.set("struktur.details", false);
            cfg.set("struktur.aufbau", false);
            try {
                cfg.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
