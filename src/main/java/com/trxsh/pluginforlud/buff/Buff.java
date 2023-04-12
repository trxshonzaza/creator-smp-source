package com.trxsh.pluginforlud.buff;

import com.trxsh.pluginforlud.utility.BuffType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.UUID;

public class Buff {

    public BuffType type;
    public String key;
    public String name;
    public PotionEffect effect;

    public Buff(BuffType type, String key, String name, PotionEffect effect) {

        this.type = type;
        this.key = key;
        this.name = name;
        this.effect = effect;

    }

    public ItemStack giveItem() {

        ItemStack stack = new ItemStack(Material.AMETHYST_SHARD);

        ItemMeta m = stack.getItemMeta();

        m.addEnchant(Enchantment.DURABILITY, 10, true);
        m.setUnbreakable(true);

        if(type == BuffType.BUFF)
            m.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + name);
        else if(type == BuffType.NERF)
            m.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + name);

        m.setLore(Arrays.asList("Grants effect: " + name));

        stack.setItemMeta(m);

        return stack;

    }

}
