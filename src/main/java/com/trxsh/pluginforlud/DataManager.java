package com.trxsh.pluginforlud;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataManager {

    public static List<DataPlayer> players = new ArrayList();

    public static List<DataPlayer> getPlayers() {

        return players;

    }

    public static DataPlayer getPlayer(UUID id) {

        for(DataPlayer p : players)
            if(p.uuid.equals(id))
                return p;

        return null;

    }

    public static void addPlayer(Player p) {

        players.add(new DataPlayer(p));

    }

    public static void addPlayer(DataPlayer p) {

        players.add(p);

    }

    public static void removePlayer(DataPlayer p) {

        players.remove(p);

    }

    public static boolean exists(UUID id) {

        for(DataPlayer p : players)
            if(p.uuid.equals(id))
                return true;

        return false;

    }

}
