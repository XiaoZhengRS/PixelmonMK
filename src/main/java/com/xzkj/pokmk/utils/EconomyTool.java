package com.xzkj.pokmk.utils;

import com.xzkj.pokmk.data.CoreData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EconomyTool {
    //判断玩家账号是否存在指定金钱

    public static Boolean ifPlauerEc(Player p, int ec){
        return CoreData.TMEcecon.bankHas(p.getName(),ec).transactionSuccess();
    }

    //扣除玩家钱

    public static void banPlayerEC(Player p, int ec){
        CoreData.TMEcecon.bankWithdraw(p.getName(),ec);
    }

    //给玩家钱 不在线

    public static void givePlayerEC(OfflinePlayer p, int ec){
        CoreData.TMEcecon.depositPlayer(p,ec);
    }
}
