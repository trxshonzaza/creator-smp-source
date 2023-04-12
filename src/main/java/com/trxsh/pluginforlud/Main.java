package com.trxsh.pluginforlud;

import com.trxsh.pluginforlud.command.TestCommand;
import com.trxsh.pluginforlud.command.WithdrawExecutor;
import com.trxsh.pluginforlud.command.WithdrawTabCompleter;
import com.trxsh.pluginforlud.manager.DataManager;
import com.trxsh.pluginforlud.player.DataPlayer;
import com.trxsh.pluginforlud.manager.FileManager;
import com.trxsh.pluginforlud.player.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "loading SMP Plugin (prod: trxsh 2.0#1988)");

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        Bukkit.getPluginCommand("test").setExecutor(new TestCommand());
        Bukkit.getPluginCommand("withdraw").setExecutor(new WithdrawExecutor());

        Bukkit.getPluginCommand("withdraw").setTabCompleter(new WithdrawTabCompleter());

        instance = this;

        if(FileManager.players.exists())
            try {
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "fetching player data");
                FileManager.loadPlayers();
            } catch (IOException e) { e.printStackTrace(); }

        for(Player p : Bukkit.getOnlinePlayers())
            if(!DataManager.exists(p.getUniqueId()))
                DataManager.addPlayer(new DataPlayer(p));

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "loaded SMP Plugin (prod trxsh 2.0#1988)");

    }

    @Override
    public void onDisable() {
        try {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "disabling SMP Plugin (prod trxsh 2.0#1988)");
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "saving player data");
            FileManager.savePlayers();
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "disabled SMP Plugin (prod trxsh 2.0#1988)");
        } catch (IOException e) { e.printStackTrace(); } }
}
