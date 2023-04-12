package com.trxsh.pluginforlud.manager;

import com.trxsh.pluginforlud.buff.Buff;
import com.trxsh.pluginforlud.buff.BuffData;
import com.trxsh.pluginforlud.utility.BuffType;

import java.util.ArrayList;
import java.util.List;

public class BuffManager {


    public static List<Buff> getBuffs() {

        List<Buff> l = new ArrayList();

        for(Buff b : BuffData.buffData)
            if(b.type == BuffType.BUFF)
                l.add(b);

        return l;

    }

    public static List<Buff> getDebuffs() {

        List<Buff> l = new ArrayList();

        for(Buff b : BuffData.buffData)
            if(b.type == BuffType.NERF)
                l.add(b);

        return l;

    }

    public static Buff getBuffByName(String name) {

        for(Buff b : BuffData.buffData)
            if(b.giveItem().getItemMeta().getDisplayName().equalsIgnoreCase(name))
                return b;

        return null;

    }

    public static Buff getBuffByNameDefault(String name) {

        for(Buff b : BuffData.buffData)
            if(b.name.replaceAll("\\s", "").equalsIgnoreCase(name.replaceAll("\\s", "")))
                return b;

        return null;

    }

    public static Buff getBuffByKey(String key) {

        for(Buff b : BuffData.buffData)
            if(b.key.equalsIgnoreCase(key))
                return b;

        return null;

    }

}
