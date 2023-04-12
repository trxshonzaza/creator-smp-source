package com.trxsh.pluginforlud;

import com.trxsh.pluginforlud.utility.BuffType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WithdrawExecutor implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("withdraw")) {

            try {

                if(args[0] != null) {

                    Buff b = BuffManager.getBuffByNameDefault(args[0]);

                    ItemStack item = b.giveItem();

                    DataPlayer p = DataManager.getPlayer(((Player)sender).getUniqueId());

                    boolean canDrop = (((Player)sender).getInventory().firstEmpty() != -1);

                    if(b.type == BuffType.NERF) {

                        ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Cannot withdraw a debuff!");

                        return true;

                    }

                    if(!p.hasBuffs()) {

                        ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have no buffs!");

                        return true;

                    }

                    if(!p.buffs.contains(b)) {

                        ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You do not have this buff!");

                        return true;

                    }

                    if(canDrop) {

                        ((Player)sender).getInventory().addItem(item);
                        p.removeBuff(b);

                        ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, .5f);
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Recieved " + ChatColor.AQUA + "" + ChatColor.BOLD + b.name);

                    } else {

                        ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your inventory is full!");

                    }

                } else {

                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Args is null!");
                    return true;

                }

            }catch(Exception e) {

                ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Exception thrown! please contact trxsh 2.0#1988 for help with a picture of the details below:");

                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + e.toString());

            }

        }

        return true;

    }
}
