package com.xzkj.pokmk.commands;

import com.xzkj.pokmk.utils.MessTool;
import com.xzkj.pokmk.utils.pok.PokTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(">>请务必以玩家身份打开!");
            return true;
        }
        Player p = (Player) commandSender;
        MessTool.logServer(PokTool.getPlayerPokemonBoxI(p, 0).getBaseStats().pixelmonName);

        return false;
    }
}
