package com.trxsh.pluginforlud.player;

import com.trxsh.pluginforlud.manager.BuffManager;
import com.trxsh.pluginforlud.Main;
import com.trxsh.pluginforlud.buff.Buff;
import com.trxsh.pluginforlud.inventory.SelectInventory;
import com.trxsh.pluginforlud.manager.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerListener implements Listener {

    public List<UUID> inInventory = new ArrayList();

    @EventHandler
    public void join(PlayerJoinEvent e) {

        if(!DataManager.exists(e.getPlayer().getUniqueId())) {

            e.getPlayer().openInventory(SelectInventory.get());
            DataManager.addPlayer(new DataPlayer(e.getPlayer()));

            inInventory.add(e.getPlayer().getUniqueId());

        } else {

            DataPlayer p = DataManager.getPlayer(e.getPlayer().getUniqueId());
            p.setPlayer(e.getPlayer());

        }

    }

    @EventHandler
    public void invclose(InventoryCloseEvent e) {

        if(e.getInventory().equals(SelectInventory.currentInventory)
                && inInventory.contains(e.getPlayer().getUniqueId())) {

            Bukkit.getScheduler().runTaskLater(Main.instance, () -> {

                e.getPlayer().openInventory(SelectInventory.get());

            }, 5L);

        }

    }

    @EventHandler
    public void invclick(InventoryClickEvent e) {

        if(e.getInventory().equals(SelectInventory.currentInventory)) {

            if(e.getCurrentItem() != null) {

                ItemStack item = e.getCurrentItem();

                Buff buff = BuffManager.getBuffByName(item.getItemMeta().getDisplayName());

                DataPlayer p = DataManager.getPlayer(((Player)e.getWhoClicked()).getUniqueId());

                p.addBuff(buff);

                inInventory.remove(p.uuid);

                e.getWhoClicked().closeInventory();

                e.setCancelled(true);

                p.existingPlayer.playSound(p.existingPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, .5f);
                p.existingPlayer.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You recieved " + ChatColor.AQUA + "" + ChatColor.BOLD + buff.name + "!");

            }

        }

    }

    @EventHandler
    public void move(PlayerMoveEvent e) {

        DataPlayer p = DataManager.getPlayer(e.getPlayer().getUniqueId());

        if(p.buffs.size() > 0)
            for(Buff b : p.buffs)
                p.addEffect(b.effect, e.getPlayer());

        if(p.debuffs.size() > 0)
            for(Buff b : p.debuffs)
                p.addEffect(b.effect, e.getPlayer());

    }

    @EventHandler
    public void death(PlayerDeathEvent e) {

        DataPlayer killed = DataManager.getPlayer(e.getEntity().getUniqueId());

        if(killed.existingPlayer.getKiller() != null) {

            DataPlayer killer = DataManager.getPlayer(e.getEntity().getKiller().getUniqueId());

            List<Buff> availableBuffs = killed.buffs;

            for(Buff b : killer.buffs)
                availableBuffs.removeIf(b1 -> b.key.equalsIgnoreCase(b1.key));

            List<Buff> availableDebuffs = BuffManager.getDebuffs();

            for(Buff b : killed.debuffs)
                availableDebuffs.removeIf(b1 -> b.key.equalsIgnoreCase(b1.key));

            for(Buff b : killer.debuffs)
                availableDebuffs.removeIf(b1 -> b.key.equalsIgnoreCase(b1.key));

            if(!killed.hasBuffs() && killer.hasDebuffs()) {

                Buff r = availableDebuffs.get(0);

                killer.removeDebuff(r);
                killed.addDebuff(r);

                killer.existingPlayer.sendMessage("You got " + ChatColor.AQUA + "" + ChatColor.BOLD + r.name + "!");
                killed.existingPlayer.sendMessage("You lost " + ChatColor.RED + "" + ChatColor.BOLD + r.name + "!");

            } else if(!killed.hasBuffs()) {

                Buff r = availableDebuffs.get(0);

                killer.removeDebuff(r);
                killed.addDebuff(r);

                killed.existingPlayer.sendMessage("You got " + ChatColor.RED + "" + ChatColor.BOLD + r.name + "!");

            }  else if(killed.hasBuffs() && killer.hasDebuffs()) {

                Buff b = availableBuffs.get(0);
                Buff r = availableDebuffs.get(0);

                killer.removeDebuff(r);
                killed.removeBuff(b);

                killer.existingPlayer.sendMessage("You lost " + ChatColor.AQUA + "" + ChatColor.BOLD + r.name + "!");
                killed.existingPlayer.sendMessage("You lost " + ChatColor.RED + "" + ChatColor.BOLD + b.name + "!");

            } else if(killed.hasBuffs()) {

                Buff b = availableBuffs.get(0);
                Buff r = availableDebuffs.get(0);

                killed.removeBuff(b);
                killer.addBuff(b);

                killer.removeDebuff(r);

                killer.existingPlayer.sendMessage("You got " + ChatColor.AQUA + "" + ChatColor.BOLD + b.name + "!");
                killed.existingPlayer.sendMessage("You lost " + ChatColor.RED + "" + ChatColor.BOLD + b.name + "!");

            }

        } else {

            List<Buff> availableDebuffs = BuffManager.getDebuffs();

            for(Buff b : killed.debuffs)
                availableDebuffs.removeIf(b1 -> b.key.equalsIgnoreCase(b1.key));

            List<Buff> availableBuffs = killed.buffs;

            if(!killed.hasBuffs()) {

                Buff r = availableDebuffs.get(0);
                killed.addDebuff(r);

                killed.existingPlayer.sendMessage("You got " + ChatColor.RED + "" + ChatColor.BOLD + r.name + "!");

            } else if(killed.hasBuffs()) {

                Buff b = availableBuffs.get(0);
                killed.removeBuff(b);

                killed.existingPlayer.sendMessage("You lost " + ChatColor.RED + "" + ChatColor.BOLD + b.name + "!");

            }

        }

    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            try {

                if(e.getItem() != null)
                    if(BuffManager.getBuffByName(e.getItem().getItemMeta().getDisplayName()) != null) {

                        Buff b = BuffManager.getBuffByName(e.getItem().getItemMeta().getDisplayName());

                        DataPlayer p = DataManager.getPlayer(e.getPlayer().getUniqueId());

                        if(p.buffs.contains(b)) {

                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                            e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You already have this buff!");

                            return;

                        } else {

                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, .5f);
                            e.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You got " + ChatColor.AQUA + "" + ChatColor.BOLD + b.name);

                            p.addBuff(b);

                        }

                        if(e.getItem().getAmount() == 1) {

                            e.getItem().setAmount(0);

                        } else {

                            e.getItem().setAmount(e.getItem().getAmount() - 1);

                        }

                    }

            }catch(Exception e1) {

                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);
                e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Exception thrown! please contact trxsh 2.0#1988 for help with a picture of the details below:");

                e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + e.toString());

            }

        }

    }

}
