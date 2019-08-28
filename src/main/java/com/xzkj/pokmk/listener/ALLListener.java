package com.xzkj.pokmk.listener;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.xzkj.pokmk.utils.EconomyTool;
import com.xzkj.pokmk.utils.MessTool;
import com.xzkj.pokmk.utils.ShopToop;
import com.xzkj.pokmk.utils.pok.PokTool;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.List;

public class ALLListener implements Listener {
    //点击事件

    @EventHandler
    public void onClick(InventoryClickEvent e) throws Exception {
        //判断点击的是否是自己的GUI
        if (e.getView().getTitle().equalsIgnoreCase("精灵市场")) {
            if (e.getRawSlot() == -999) {
                return;
            }
            Player p = (Player) e.getWhoClicked();
            //判断点击是否为空白
            if (e.getCurrentItem() == null) {
                e.setCancelled(true);
                return;
            }
            //判断点击是否是反页i
            if (e.getRawSlot() == 45) {
                if (e.getCurrentItem().getItemMeta().getLore().get(1).equalsIgnoreCase("无")) {
                    MessTool.logPlayer("已经到头!", p);
                    e.setCancelled(true);
                    return;
                }
                //进行反页
                List<Inventory> dataInventory = new ShopToop().giveShopMain(p);
                e.setCancelled(true);
                p.closeInventory();
                p.openInventory(dataInventory.get(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(1))));
                return;

            } else if (e.getRawSlot() == 53) {
                if (e.getCurrentItem().getItemMeta().getLore().get(1).equalsIgnoreCase("无")) {
                    MessTool.logPlayer("已经到头!", p);
                    e.setCancelled(true);
                    return;
                }
                //进行反页
                List<Inventory> dataInventory = new ShopToop().giveShopMain(p);
                e.setCancelled(true);
                p.closeInventory();
                p.openInventory(dataInventory.get(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(1))));
                return;
            }

            //进入点击购买
            String CS_Player = e.getCurrentItem().getItemMeta().getLore().get(1);
            String Pok_UUID = e.getCurrentItem().getItemMeta().getLore().get(3);
            YamlConfiguration dataShopYaml = new ShopToop().getPlayerShopYaml(CS_Player);
            if (!EconomyTool.ifPlauerEc(p, Integer.parseInt(dataShopYaml.getString("全部精灵." + Pok_UUID + ".EC")))) {
                MessTool.logPlayer("您的账号余额不足,无法购买!", p);
                e.setCancelled(true);
                p.closeInventory();
                return;
            }
            //进入购买
            EconomyTool.banPlayerEC(p, Integer.parseInt(dataShopYaml.getString("全部精灵." + Pok_UUID + ".EC")));
            EconomyTool.givePlayerEC(Bukkit.getOfflinePlayer(CS_Player), Integer.parseInt(dataShopYaml.getString("全部精灵." + Pok_UUID + ".EC")));
            //进入发放
            PokTool.givePlayerPokemonCmd(p, CS_Player, Pok_UUID);
            e.setCancelled(true);
            p.closeInventory();
        }
    }
}
