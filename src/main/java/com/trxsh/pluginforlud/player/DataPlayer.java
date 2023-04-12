package com.trxsh.pluginforlud.player;

import com.trxsh.pluginforlud.buff.Buff;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class DataPlayer {

    public static DataPlayer instance;

    public boolean isOffline = true;

    public List<Buff> buffs = new ArrayList();
    public List<Buff> debuffs = new ArrayList();

    public Player existingPlayer;

    public UUID uuid;

    public String name;

    public DataPlayer(Player player) {

        this.uuid = player.getUniqueId();
        this.existingPlayer = player;
        this.name = player.getName();

        instance = this;

    }

    public DataPlayer(OfflinePlayer player) {

        isOffline = true;

        if(player.isOnline()) {

            this.isOffline = false;
            this.existingPlayer = player.getPlayer();

        }

        this.name = player.getName();
        this.uuid = player.getUniqueId();

        instance = this;

    }

    public void setPlayer(Player player) {

        if(player.isOnline()) {

            this.isOffline = false;
            this.existingPlayer = player.getPlayer();

        }

        this.name = player.getName();
        this.uuid = player.getUniqueId();

    }

    public Buff addBuff(Buff b) {

        buffs.add(b);

        return b;

    }

    public Buff removeBuff(Buff b) {

        buffs.remove(b);

        return b;

    }

    public Buff addDebuff(Buff b) {

        debuffs.add(b);

        return b;

    }

    public Buff removeDebuff(Buff b) {

        debuffs.remove(b);

        return b;

    }

    public boolean hasBuffs() {

        return buffs.size() > 0;

    }

    public boolean hasDebuffs() {

        return debuffs.size() > 0;

    }

    public void addEffect(PotionEffect e, Player player) {

        existingPlayer = player;

        existingPlayer.addPotionEffect(e);

    }

    public Collection<PotionEffect> getEffects() {

        return existingPlayer.getActivePotionEffects();

    }

    public void removeEffect(PotionEffect e, Player player) {

        existingPlayer = player;

        existingPlayer.removePotionEffect(e.getType());

    }

    public void removeEffect(PotionEffectType e) {

        if(isOffline && existingPlayer.isOnline())
            existingPlayer = Bukkit.getPlayer(uuid);

        existingPlayer.removePotionEffect(e);

    }

}
