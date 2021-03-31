package me.xenodev.tmbbe.event;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionManagement;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.main.Main;
import me.xenodev.tmbbe.utils.SBBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(Main.prefix + "§7Der Spieler §6" + p.getName() + " §7ist §abeigetreten");

        SBBuilder.setScoreboard(p);
        IPermissionManagement manager = CloudNetDriver.getInstance().getPermissionManagement();
        IPermissionUser player = manager.getUser(p.getUniqueId());
        if(player.inGroup("Owner") || player.inGroup("Admin")
                || player.inGroup("Developer") || player.inGroup("Developer+")
                || player.inGroup("Builder") || player.inGroup("Chefbuilder")
                || player.inGroup("Testbuilder") || player.inGroup("Supporter")){
            Bewertungen.setAnnahme(p.getUniqueId().toString(), "Teammitglied");
            Bewertungen.setDate(p.getUniqueId().toString());
            Bewertungen.setTime(p.getUniqueId().toString());
            Bewertungen.setGrund(p.getUniqueId().toString(), Arrays.asList(""));
        }
    }
}
