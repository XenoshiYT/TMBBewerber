package me.xenodev.tmbbe.mysql;

import me.xenodev.tmbbe.main.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLTime {

    public static boolean playerExists(UUID uuid){

        try{
            ResultSet rs = Main.mysql.query("SELECT * FROM Time WHERE UUID= '" + uuid + "'");
            if(rs.next()){
                return rs.getString("UUID") != null;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid){

        if(!(playerExists(uuid))){
            Main.mysql.update("INSERT INTO Time(UUID, HOURS, MINUTES, SECONDS) VALUES ('" + uuid + "', '0', '0', '0');");
        }
    }

    public static Integer getHours(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.mysql.query("SELECT * FROM Time WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("HOURS")) == null));
                i = rs.getInt("HOURS");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getHours(uuid);
        }

        return i;
    }

    public static Integer getMinutes(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.mysql.query("SELECT * FROM Time WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("MINUTES")) == null));
                i = rs.getInt("MINUTES");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getMinutes(uuid);
        }

        return i;
    }

    public static Integer getSeconds(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.mysql.query("SELECT * FROM Time WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("SECONDS")) == null));
                i = rs.getInt("SECONDS");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getSeconds(uuid);
        }

        return i;
    }

    public static void setHours(UUID uuid, Integer hours){
        if(playerExists(uuid)){
            Main.mysql.update("UPDATE Time SET HOURS= '" + hours + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setHours(uuid, hours);
        }
    }

    public static void setMinutes(UUID uuid, Integer minutes){
        if(playerExists(uuid)){
            Main.mysql.update("UPDATE Time SET MINUTES= '" + minutes + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setMinutes(uuid, minutes);
        }
    }

    public static void setSeconds(UUID uuid, Integer seconds){
        if(playerExists(uuid)){
            Main.mysql.update("UPDATE Time SET SECONDS= '" + seconds + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setSeconds(uuid, seconds);
        }
    }

    public static void addHours(UUID uuid, Integer hours){
        if(playerExists(uuid)){
            setHours(uuid, Integer.valueOf(getHours(uuid).intValue() + hours.intValue()));
        }else{
            createPlayer(uuid);
            addHours(uuid, hours);
        }
    }

    public static void addMinutes(UUID uuid, Integer minutes){
        if(playerExists(uuid)){
            setMinutes(uuid, Integer.valueOf(getMinutes(uuid).intValue() + minutes.intValue()));
        }else{
            createPlayer(uuid);
            addMinutes(uuid, minutes);
        }
    }

    public static void addSeconds(UUID uuid, Integer seconds){
        if(playerExists(uuid)){
            setSeconds(uuid, Integer.valueOf(getSeconds(uuid).intValue() + seconds.intValue()));
        }else{
            createPlayer(uuid);
            addSeconds(uuid, seconds);
        }
    }

    public static String changeTime(UUID uuid){
        if(getHours(uuid) != 0){
            return getHours(uuid) + "h";
        }else if(getMinutes(uuid) >= 0 && getHours(uuid) == 0){
            return getMinutes(uuid) + " min";
        }
        return "kein Eintrag!";
    }
}
