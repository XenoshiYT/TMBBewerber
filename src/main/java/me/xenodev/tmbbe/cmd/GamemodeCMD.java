package me.xenodev.tmbbe.cmd;

import me.xenodev.tmbbe.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("gm") || cmd.getName().equalsIgnoreCase("gamemode")) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    if (p.hasPermission("tmb.gm.survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eSurvival §7geändert");
                    } else {
                        p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                    }
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    if (p.hasPermission("tmb.gm.creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eCreative §7geändert");
                    } else {
                        p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                    }
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    if (p.hasPermission("tmb.gm.adventure")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eAdventure §7geändert");
                    } else {
                        p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                    }
                } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    if (p.hasPermission("tmb.gm.spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eSpectator §7geändert");
                    } else {
                        p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                    }
                }else{
                    p.sendMessage(Main.error + "§cDiesen Gamemode gibt es nicht");
                }
            } else if (args.length == 2) {
                Player t = Bukkit.getPlayerExact(args[1]);
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if (p.hasPermission("tmb.gm.survival.other")) {
                            t.setGameMode(GameMode.SURVIVAL);
                            t.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eSurvival §7geändert");
                            p.sendMessage(Main.prefix + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eSurvival §7geändert");
                        } else {
                            p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                        }
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if (p.hasPermission("tmb.gm.creative.other")) {
                            t.setGameMode(GameMode.CREATIVE);
                            t.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eCreative §7geändert");
                            p.sendMessage(Main.prefix + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eCreative §7geändert");
                        } else {
                            p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                        }
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if (p.hasPermission("tmb.gm.adventure.other")) {
                            t.setGameMode(GameMode.ADVENTURE);
                            t.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eAdventure §7geändert");
                            p.sendMessage(Main.prefix + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eAdventure §7geändert");
                        } else {
                            p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                        }
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if (p.hasPermission("tmb.gm.spectator.other")) {
                            t.setGameMode(GameMode.SPECTATOR);
                            t.sendMessage(Main.prefix + "§7Dein Gamemode wurde auf §eSpectator §7geändert");
                            p.sendMessage(Main.prefix + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eSpectator §7geändert");
                        } else {
                            p.sendMessage(Main.error + "§cDazu hast du keine Rechte");
                        }
                    }else{
                        p.sendMessage(Main.error + "§cDiesen Gamemode gibt es nicht");
                    }
            } else {
                p.sendMessage(Main.error + "§7Bitte benutze §a/gm <0/1/2/3>");
            }
        }
        return false;
    }
}
