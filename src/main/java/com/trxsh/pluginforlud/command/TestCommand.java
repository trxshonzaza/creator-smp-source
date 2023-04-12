package com.trxsh.pluginforlud.command;

import com.trxsh.pluginforlud.inventory.SelectInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("test"))
            if(sender instanceof Player) {

                ((Player)sender).getPlayer().openInventory(SelectInventory.get());
                return true;

            }

        return false;
    }

}
