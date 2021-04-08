package me.xenodev.tmbbe.files;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerInfo {

    public static void setJoins(OfflinePlayer p, Integer amount){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Joins", amount);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getJoins(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getInt("Joins");
    }

    public static void setFirstDate(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(new Date());

        cfg.set("Firstdate", null);
        cfg.set("Firstdate", date);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFirstDate(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Firstdate");
    }

    public static void setFirstTime(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time = timeFormat.format(new Date());

        cfg.set("Firsttime", null);
        cfg.set("Firsttime", time);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFirstTime(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Firsttime");
    }

    public static void setLastDate(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(new Date());

        cfg.set("Lastdate", null);
        cfg.set("Lastdate", date);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLastDate(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Lastdate");
    }

    public static void setLastTime(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time = timeFormat.format(new Date());

        cfg.set("Lasttime", null);
        cfg.set("Lasttime", time);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLastTime(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Info", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Lasttime");
    }
}
