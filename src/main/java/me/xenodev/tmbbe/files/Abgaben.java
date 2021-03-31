package me.xenodev.tmbbe.files;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Abgaben {

    public static File file = new File("plugins//TMBClan//abgaben.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static ArrayList<String> players = new ArrayList<String>();

    public static void addList(List list){
        cfg.set("List", null);
        cfg.set("List", list);
        save();
    }

    public static List getList(){
        return cfg.getStringList("List");
    }

    public static void addArray(){
        players = (ArrayList<String>) getList();
        save();
    }

    public static String getPlayer(String name){
        if(players.contains(name)){
            return name;
        }
        return Main.error + "§cKein Name gefunden!";
    }

    public static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {}
    }

}
