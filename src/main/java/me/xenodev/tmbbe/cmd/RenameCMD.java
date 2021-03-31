package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("rename")){
            if(p.hasPermission("tmb.rename")) {
                if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    if (args.length >= 1) {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }

                        ItemStack item = p.getItemInHand();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(msg.replace("&", "§"));
                        item.setItemMeta(meta);
                        p.sendMessage(Main.prefix + "§7Du hast das Item umbenannt");
                    } else {
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
