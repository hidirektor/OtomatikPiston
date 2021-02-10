package com.kekn9ne.otomatikpiston.commands;

import com.kekn9ne.otomatikpiston.OtomatikPiston;
import com.kekn9ne.otomatikpiston.util.Item;
import com.kekn9ne.otomatikpiston.util.MessageUtil;
import com.kekn9ne.otomatikpiston.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PistonCommand implements CommandExecutor {
    private static SettingsManager manager = SettingsManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("otopiston")) {
            Player p = (Player) sender;
            if(args.length == 0) {
                if(p.isOp() || p.hasPermission("otomatikpiston.general")) {
                    for(String s: MessageUtil.INFO) {
                        p.sendMessage(String.valueOf(s));
                    }
                } else {
                    p.sendMessage(MessageUtil.PERMERROR);
                }
            } else if(args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase("give")) {
                if(p.isOp() || p.hasPermission("otomatikpiston.ver") || p.hasPermission("otomatikpiston.give")) {
                    if(args.length < 3 || args.length > 3) {
                        sender.sendMessage(MessageUtil.GIVEERROR);
                    } else {
                        Player taker = Bukkit.getPlayer(args[1]);
                        if(isInteger(args[2])) {
                            int amount = Integer.parseInt(args[2]);
                            if(Item.checkInventory(taker)) {
                                for(int i=0; i<amount; i++) {
                                    Bukkit.getPlayer(args[1]).getInventory().addItem(OtomatikPiston.item.piston);
                                }
                                sender.sendMessage(MessageUtil.ADDED.replaceAll("%player%", taker.getName()).replaceAll("%amount%", String.valueOf(amount)).replaceAll("%piston%", MessageUtil.ITEMNAME));
                                taker.sendMessage(MessageUtil.ADD.replaceAll("%amount%", String.valueOf(amount)).replaceAll("%piston%", MessageUtil.ITEMNAME));
                            } else {
                                sender.sendMessage((MessageUtil.INVENTORY_IS_FULL).replaceAll("%player%", taker.getName()).replaceAll("%piston%", MessageUtil.ITEMNAME));
                                taker.sendMessage((MessageUtil.INVENTORY_FULL).replaceAll("%piston%", MessageUtil.ITEMNAME));
                            }
                        } else {
                            sender.sendMessage(MessageUtil.NUMBER);
                        }
                    }
                } else {
                    p.sendMessage(MessageUtil.PERMERROR);
                }
            } else if(args[0].equalsIgnoreCase("al") || args[0].equalsIgnoreCase("take")) {
                if(sender instanceof Player) {
                    if(p.isOp() || p.hasPermission("otomatikpiston.al") || p.hasPermission("otomatikpiston.take")) {
                        Player taker = (Player) sender;
                        if(isInteger(args[1])) {
                            int amount = Integer.parseInt(args[1]);
                            if(Item.checkInventory(taker)) {
                                for(int i=0; i<amount; i++) {
                                    taker.getInventory().addItem(OtomatikPiston.item.piston);
                                }
                                taker.sendMessage(MessageUtil.ADD);
                            } else {
                                taker.sendMessage(MessageUtil.INVENTORY_FULL);
                            }
                        } else {
                            sender.sendMessage(MessageUtil.NUMBER);
                        }
                    } else {
                        p.sendMessage(MessageUtil.PERMERROR);
                    }
                } else {
                    sender.sendMessage(MessageUtil.CONSOLE);
                }
            } else if(args[0].equalsIgnoreCase("reload")) {
                if(p.isOp() || p.hasPermission("otomatikpiston.reload")) {
                    manager.reloadConfig();
                    MessageUtil.loadMessages();
                    manager.saveConfig();
                    p.sendMessage(MessageUtil.RELOAD);
                } else {
                    p.sendMessage(MessageUtil.PERMERROR);
                }
            }
        }
        return true;
    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        }
        catch (NumberFormatException ex) {
        }
        return isValidInteger;
    }
}
