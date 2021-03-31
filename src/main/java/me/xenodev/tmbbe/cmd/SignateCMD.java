package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SignateCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("signate") || cmd.getName().equalsIgnoreCase("sign")){
            if(p.hasPermission("gb.signate")) {
                if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    if(args.length >= 1) {
                        String msg = "";
                        for (int i = 0; i < args.length; i++){
                            msg = msg + args[i] + " ";
                        }

                        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
                        String odate = date.format(new Date());
                        ItemStack item = p.getItemInHand();
                        ItemMeta meta = item.getItemMeta();
                        meta.setLore(Arrays.asList("", msg.replace("&", "§"), "§7-------------------------------------------", "§7Signiert von §e§l" + p.getName() + " §7am §a§l" + odate));
                        item.setItemMeta(meta);
                        p.sendMessage(Main.prefix + "§7Du hast das Item segniert");
                    }else{
                        p.sendMessage(Main.error + "§7Bitte benutze §a/rename <name>");
                    }
                } else {
                    p.sendMessage(Main.error + "§cDu hast kein Item in der Hand");
                }
            }else{
                p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
            }
        }

        return false;
    }
}
