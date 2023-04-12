package com.trxsh.pluginforlud;

import com.trxsh.pluginforlud.utility.BuffType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class SelectInventory {

    public static Inventory currentInventory;

    public static Inventory get() {

        Inventory n = Bukkit.createInventory(null, 54, ChatColor.GOLD + "" + ChatColor.BOLD + "Pick a Starting Buff!");

        for(Buff b : BuffManager.getBuffs())
            if(b.type == BuffType.BUFF)
                n.addItem(b.giveItem());

        currentInventory = n;

        return n;

    }

}
