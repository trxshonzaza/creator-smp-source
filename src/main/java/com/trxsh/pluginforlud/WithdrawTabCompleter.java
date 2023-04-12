package com.trxsh.pluginforlud;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WithdrawTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> result = new ArrayList();

        DataPlayer p = DataManager.getPlayer(((Player)sender).getUniqueId());

        for(Buff b : p.buffs)
            result.add(b.name.replaceAll("\\s", ""));

        return result;
    }

}
