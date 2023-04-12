package com.trxsh.pluginforlud.manager;

import com.trxsh.pluginforlud.utility.BuffProperty;
import com.trxsh.pluginforlud.buff.Buff;
import com.trxsh.pluginforlud.player.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class FileManager {

    public static File players = new File("players.mgr");
    public static String separator = System.getProperty("line.separator");

    public static void savePlayers() throws IOException {

        Map<UUID, List<Buff>> playerBuffs = new HashMap<>();
        Map<UUID, List<Buff>> playerDebuffs = new HashMap<>();

       for(DataPlayer p : DataManager.getPlayers()) {

           playerBuffs.put(p.uuid, p.buffs);
           playerDebuffs.put(p.uuid, p.debuffs);

       }

       FileWriter w = new FileWriter(players, false);

       StringBuilder sb = new StringBuilder();

       for(UUID id : playerBuffs.keySet()) {

           List<Buff> b = playerBuffs.get(id);

           for(Buff ub : b)
               sb.append(id.toString() + "!!" + ub.key + separator);

       }

        for(UUID id : playerDebuffs.keySet()) {

            List<Buff> b = playerDebuffs.get(id);

            for(Buff ub : b)
                sb.append(id.toString() + "!~" + ub.key + separator);

        }

        w.write(sb.toString());
        w.close();


    }

    public static void loadPlayers() throws IOException {

        List<BuffProperty> playerBuffs = new ArrayList();
        List<BuffProperty> playerDebuffs = new ArrayList();

        for(String str : Files.readAllLines(players.toPath())) {

            if(str.contains("!!")) {

                UUID id = UUID.fromString(str.split("!!")[0]);
                Buff b = BuffManager.getBuffByKey(str.split("!!")[1]);

                playerBuffs.add(new BuffProperty(b, id));
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "loaded new BuffProperty -> " + id + ":" + b.key);

            }

            if(str.contains("!~")) {

                UUID id = UUID.fromString(str.split("!~")[0]);
                Buff b = BuffManager.getBuffByKey(str.split("!~")[1]);

                playerDebuffs.add(new BuffProperty(b, id));
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "loaded new BuffProperty -> " + id + ":" + b.key);

            }

        }

        for(BuffProperty b : playerBuffs) {

            DataPlayer p = null;

            if(!DataManager.exists(b.getID())) {

                p = new DataPlayer(Bukkit.getOfflinePlayer(b.getID()));
                DataManager.addPlayer(p);

            } else {

                p = DataManager.getPlayer(b.getID());
                DataManager.addPlayer(p);

            }

            p.addBuff(b.getBuff());

        }

        for(BuffProperty b : playerDebuffs) {

            DataPlayer p = null;

            if(!DataManager.exists(b.getID())) {

                p = new DataPlayer(Bukkit.getOfflinePlayer(b.getID()));
                DataManager.addPlayer(p);

            } else {

                p = DataManager.getPlayer(b.getID());
                DataManager.addPlayer(p);

            }

            p.addDebuff(b.getBuff());

        }

    }

}
