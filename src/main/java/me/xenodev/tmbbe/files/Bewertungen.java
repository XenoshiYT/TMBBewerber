package me.xenodev.tmbbe.files;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bewertungen {

    public static void setStatus(OfflinePlayer p, String status){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Status", status);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStatus(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Status");
    }

    public static void setDate(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(new Date());

        cfg.set("Date", null);
        cfg.set("Date", date);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDate(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Date");
    }

    public static void setTime(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time = timeFormat.format(new Date());

        cfg.set("Time", null);
        cfg.set("Time", time);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTime(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Time");
    }

    public static void setGrund(OfflinePlayer p, String grund, Boolean bool){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set(grund, bool);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean getGrund(OfflinePlayer p, String grund){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getBoolean(grund);
    }

    public static void setBemerkung(OfflinePlayer p, String bemerkung){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Bemerkung", bemerkung);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBemerkung(OfflinePlayer p){
        File file = new File("plugins//TMBClan//Bewerber", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if(cfg.getString("Bemerkung") != null){
            return cfg.getString("Bemerkung");
        }

        return "keine Bemerkung";
    }
}
