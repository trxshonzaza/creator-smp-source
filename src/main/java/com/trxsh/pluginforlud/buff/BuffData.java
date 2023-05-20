package com.trxsh.pluginforlud.buff;

import com.trxsh.pluginforlud.utility.BuffType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class BuffData {

    public static List<Buff> buffData = new ArrayList();
    static {

        buffData.add(new Buff(BuffType.BUFF, "regen_health", "Regeneration", new PotionEffect(
                PotionEffectType.REGENERATION,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "water_breathing", "Water Breathing", new PotionEffect(
                PotionEffectType.WATER_BREATHING,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "speed_buff", "Speed", new PotionEffect(
                PotionEffectType.SPEED,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "fast_digging", "Haste", new PotionEffect(
                PotionEffectType.FAST_DIGGING,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "damage_resistance", "Resistance", new PotionEffect(
                PotionEffectType.DAMAGE_RESISTANCE,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "water_speed", "Dolphins Grace", new PotionEffect(
                PotionEffectType.DOLPHINS_GRACE,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "full_bright", "Night Vision", new PotionEffect(
                PotionEffectType.NIGHT_VISION,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "jump_height", "Jump Boost", new PotionEffect(
                PotionEffectType.JUMP,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "hunger_increase", "Saturation", new PotionEffect(
                PotionEffectType.SATURATION,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.BUFF, "lucky", "Luck", new PotionEffect(
                PotionEffectType.LUCK,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "hunger_decrease", "Hunger", new PotionEffect(
                PotionEffectType.HUNGER,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "speed_slow", "Slowness", new PotionEffect(
                PotionEffectType.SLOW,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "decrease_health", "Poison", new PotionEffect(
                PotionEffectType.POISON,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "decrease_damage", "Weakness", new PotionEffect(
                PotionEffectType.WEAKNESS,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "blind_player", "Blindness", new PotionEffect(
                PotionEffectType.BLINDNESS,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "glow_player", "Glowing", new PotionEffect(
                PotionEffectType.GLOWING,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "slow_digging", "Mining Fatigue", new PotionEffect(
                PotionEffectType.SLOW_DIGGING,
                (20 * 20),
                1
        )));

        buffData.add(new Buff(BuffType.NERF, "un_lucky", "Bad Luck", new PotionEffect(
                PotionEffectType.UNLUCK,
                (20 * 20),
                1
        )));

    }

}
