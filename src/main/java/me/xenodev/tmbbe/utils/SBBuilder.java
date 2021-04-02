package me.xenodev.tmbbe.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import me.xenodev.tmbbe.files.Bewertungen;
import me.xenodev.tmbbe.mysql.SQLTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class SBBuilder {

    private static Team team;

    public static void setScoreboard(Player p) {
        File file = new File("plugins//TMBClan//Bewertungen", p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "main", "§7» §5§lT§deam§5§lM§dega§5§lB§dyte §7«");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        IPermissionGroup permgroup = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(permuser);
        obj.getScore("§7§o").setScore(15);
        obj.getScore("§fDein Name").setScore(14);
        obj.getScore("§c§l" + p.getName()).setScore(13);
        obj.getScore("§6").setScore(12);
        obj.getScore("§fRang").setScore(11);
        obj.getScore(updateTeam(board, "rang", "§9§l" + permgroup.getName(), "", ChatColor.BLUE)).setScore(10);
        obj.getScore("§5").setScore(9);
        obj.getScore("§fBewerberstatus").setScore(8);
        obj.getScore(updateTeam(board, "status", "§e§l" + cfg.get("angenommen").toString(), "", ChatColor.GRAY)).setScore(7);
        obj.getScore("§4").setScore(6);
        obj.getScore("§fOnlinezeit").setScore(5);
        obj.getScore(updateTeam(board, "time", "§a§l" + SQLTime.changeTime(p.getUniqueId()), "", ChatColor.YELLOW)).setScore(4);
        obj.getScore("§3").setScore(3);
        obj.getScore("§fTMB Teamspeak").setScore(2);
        obj.getScore("§d§l§oclan-tmb.de").setScore(1);

        p.setScoreboard(board);
    }

    public static void updateScoreboard(Player p) {
        File file = new File("plugins//TMBClan//Bewertungen", p.getName() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Scoreboard board = p.getScoreboard();
        Objective obj = board.getObjective("main");

        IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        IPermissionGroup permgroup = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(permuser);

        obj.getScore(updateTeam(board, "rang", "§9§l" + permgroup.getName(), "", ChatColor.BLUE)).setScore(10);
        obj.getScore(updateTeam(board, "status", "§e§l" + cfg.get("angenommen").toString(), "", ChatColor.GRAY)).setScore(7);
        obj.getScore(updateTeam(board, "time", "§a§l" + SQLTime.changeTime(p.getUniqueId()), "", ChatColor.YELLOW)).setScore(4);
    }

    public static Team getTeam(Scoreboard board, String Team, String prefix, String suffix) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(false);
        team.setCanSeeFriendlyInvisibles(true);

        return team;

    }

    public static String updateTeam(Scoreboard board, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());

        return entry.toString();
    }
}
