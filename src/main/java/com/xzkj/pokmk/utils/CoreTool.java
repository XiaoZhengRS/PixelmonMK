package com.xzkj.pokmk.utils;

import com.xzkj.pokmk.data.CoreData;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class CoreTool {
    public static boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        CoreData.TMEcecon = (Economy) economyProvider.getProvider();
        return CoreData.TMEcecon != null;
    }
}
