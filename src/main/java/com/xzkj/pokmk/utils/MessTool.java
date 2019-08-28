package com.xzkj.pokmk.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MessTool {
    public static void logServer(String str){
        Bukkit.getLogger().info("§d[§5PixelMonMK§d]§7>>>§b " + str);
    }
    public static void logPlayer(String str, Player p){
        p.sendMessage("§d[§5PixelMonMK§d]§7>>>§b " + str);
    }
    public static void logOLPlayer(String str){
        Collection<? extends Player> OnlinePlayer = Bukkit.getOnlinePlayers();
        OnlinePlayer.forEach(player -> player.sendMessage(str));
    }
}
