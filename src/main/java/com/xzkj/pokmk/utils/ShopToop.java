package com.xzkj.pokmk.utils;

import com.xzkj.pokmk.data.CoreData;
import com.xzkj.pokmk.data.PokData;
import com.xzkj.pokmk.data.PokDataClass;
import com.xzkj.pokmk.data.ShopPok;
import org.apache.logging.log4j.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.xzkj.pokmk.data.CoreData.MKSHOPDATA;

public class ShopToop {
    //返回一个全部精灵商城的界面

    public List<Inventory> giveShopMain(Player p) {

        List<Inventory> retInventory = new ArrayList<>();
        ItemStack itemStack_EGG = new ItemStack(Material.EGG);
        ItemStack itemStack_EGG1 = new ItemStack(Material.EGG);
        ItemStack itemStack_PAPER = new ItemStack(Material.PAPER);
        //算法一共需要几页
        List<ShopPok> dataListShopPok = getALLPok();
        int xyys = dataListShopPok.size() / 44;
        int xyys_mo = dataListShopPok.size() % 44;
        //进入页创建
        int datadq = 0;
        for (int i = 0; i < xyys; i++) {
            Inventory dataInventory = Bukkit.createInventory(null, 9 * 6, "精灵市场");
            List<String> ItemStackLore = new ArrayList<>();
            if (i != 0){
                ItemStackLore.add("上一页");
                ItemStackLore.add(String.valueOf(i - 1));
            } else {
                ItemStackLore.add("上一页");
                ItemStackLore.add("无");
            }
            ItemMeta datameta = itemStack_PAPER.getItemMeta();
            datameta.setLore(ItemStackLore);
            itemStack_PAPER.setItemMeta(datameta);
            dataInventory.setItem(45, itemStack_PAPER);
            ItemStackLore.clear();//清空
            if (i == xyys){
                ItemStackLore.add("下一页");
                ItemStackLore.add("无");
            } else {
                ItemStackLore.add("上一页");
                ItemStackLore.add(String.valueOf(i + 1));
            }
            datameta.setLore(ItemStackLore);
            itemStack_PAPER.setItemMeta(datameta);
            dataInventory.setItem(53, itemStack_PAPER);
            ItemStackLore.clear();//清空
            //加精灵
            for (int j = 0; j < 45; j++) {
                if(datadq > dataListShopPok.size()){
                    continue;
                }
                datameta = itemStack_EGG.getItemMeta();
                datameta.setDisplayName(dataListShopPok.get(datadq).PokName);
                ItemStackLore.add("§5出售玩家");
                ItemStackLore.add(dataListShopPok.get(datadq).PlayerName);
                ItemStackLore.add("§5唯一标识UUID");
                ItemStackLore.add(dataListShopPok.get(datadq).PokUUID);
                ItemStackLore.add("§5出售价格: " + dataListShopPok.get(datadq).PokEC);
                ItemStackLore.add("§5等级: " + dataListShopPok.get(datadq).PokData.dataIVS.level);
                ItemStackLore.add("§5个体: ");
                ItemStackLore.add("  §6防御: " + dataListShopPok.get(datadq).PokData.dataIVS.defence);
                ItemStackLore.add("  §6特防: " + dataListShopPok.get(datadq).PokData.dataIVS.specialDefence);
                ItemStackLore.add("  §6攻击: " + dataListShopPok.get(datadq).PokData.dataIVS.attack);
                ItemStackLore.add("  §6特攻: " + dataListShopPok.get(datadq).PokData.dataIVS.specialAttack);
                ItemStackLore.add("  §6生命: " + dataListShopPok.get(datadq).PokData.dataIVS.hp);
                ItemStackLore.add("  §6速度: " + dataListShopPok.get(datadq).PokData.dataIVS.speed);
                ItemStackLore.add("§5努力值: ");
                ItemStackLore.add("  §6防御: " + dataListShopPok.get(datadq).PokData.dataEVS.defence);
                ItemStackLore.add("  §6特防: " + dataListShopPok.get(datadq).PokData.dataEVS.specialDefence);
                ItemStackLore.add("  §6攻击: " + dataListShopPok.get(datadq).PokData.dataEVS.attack);
                ItemStackLore.add("  §6特攻: " + dataListShopPok.get(datadq).PokData.dataEVS.specialAttack);
                ItemStackLore.add("  §6生命: " + dataListShopPok.get(datadq).PokData.dataEVS.hp);
                ItemStackLore.add("  §6速度: " + dataListShopPok.get(datadq).PokData.dataEVS.speed);
                datameta.setLore(ItemStackLore);
                itemStack_EGG.setItemMeta(datameta);
                dataInventory.setItem(j, itemStack_EGG);
                datadq++;
            }
            retInventory.add(dataInventory);
        }

        //判断有没有余数
        if (xyys_mo != 0){
            Inventory dataInventory = Bukkit.createInventory(null, 9 * 6, "精灵市场");
            List<String> ItemStackLore = new ArrayList<>();
            ItemStackLore.add("上一页");
            ItemStackLore.add(String.valueOf(xyys));
            ItemMeta datameta = itemStack_PAPER.getItemMeta();
            datameta.setLore(ItemStackLore);
            itemStack_PAPER.setItemMeta(datameta);
            dataInventory.setItem(45, itemStack_PAPER);
            ItemStackLore.clear();//清空
            ItemStackLore.add("下一页");
            ItemStackLore.add("无");
            datameta = itemStack_PAPER.getItemMeta();
            datameta.setLore(ItemStackLore);
            itemStack_PAPER.setItemMeta(datameta);
            dataInventory.setItem(45, itemStack_PAPER);
            ItemStackLore.clear();//清空
            //加精灵
            for (int x = 0; x < 45; x++) {
                if(datadq >= dataListShopPok.size()){
                    retInventory.add(dataInventory);
                    return retInventory;
                }
                datameta = itemStack_EGG1.getItemMeta();
                datameta.setDisplayName(dataListShopPok.get(datadq).PokName);
                ItemStackLore.add("§5出售玩家");
                ItemStackLore.add(dataListShopPok.get(datadq).PlayerName);
                ItemStackLore.add("§5唯一标识UUID");
                ItemStackLore.add(dataListShopPok.get(datadq).PokUUID);
                ItemStackLore.add("§5出售价格: " + dataListShopPok.get(datadq).PokEC);
                ItemStackLore.add("§5等级: " + dataListShopPok.get(datadq).PokData.dataIVS.level);
                ItemStackLore.add("§5个体: ");
                ItemStackLore.add("  §6防御: " + dataListShopPok.get(datadq).PokData.dataIVS.defence);
                ItemStackLore.add("  §6特防: " + dataListShopPok.get(datadq).PokData.dataIVS.specialDefence);
                ItemStackLore.add("  §6攻击: " + dataListShopPok.get(datadq).PokData.dataIVS.attack);
                ItemStackLore.add("  §6特攻: " + dataListShopPok.get(datadq).PokData.dataIVS.specialAttack);
                ItemStackLore.add("  §6生命: " + dataListShopPok.get(datadq).PokData.dataIVS.hp);
                ItemStackLore.add("  §6速度: " + dataListShopPok.get(datadq).PokData.dataIVS.speed);
                ItemStackLore.add("§5努力值: ");
                ItemStackLore.add("  §6防御: " + dataListShopPok.get(datadq).PokData.dataEVS.defence);
                ItemStackLore.add("  §6特防: " + dataListShopPok.get(datadq).PokData.dataEVS.specialDefence);
                ItemStackLore.add("  §6攻击: " + dataListShopPok.get(datadq).PokData.dataEVS.attack);
                ItemStackLore.add("  §6特攻: " + dataListShopPok.get(datadq).PokData.dataEVS.specialAttack);
                ItemStackLore.add("  §6生命: " + dataListShopPok.get(datadq).PokData.dataEVS.hp);
                ItemStackLore.add("  §6速度: " + dataListShopPok.get(datadq).PokData.dataEVS.speed);
                datameta.setLore(ItemStackLore);
                ItemStackLore.clear();
                itemStack_EGG1.setItemMeta(datameta);
                dataInventory.setItem(x, itemStack_EGG1);
                datadq++;
            }
            retInventory.add(dataInventory);
        }
        return retInventory;
    }

    //获取配置文件里面的全部精灵

    public static List<ShopPok> getALLPok() {
        List<ShopPok> retShopPok = new ArrayList<>();
        List<File> dataFile = FileTool.getListFiles(CoreData.MKSHOPDATA);
        for (File data : dataFile) {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(data);
            if (yaml.getString("玩家") != null) {
                String PlayerNmae = yaml.getString("玩家");
                //进入精灵遍历
                Set<String> dataSetStr = yaml.getConfigurationSection("全部精灵").getKeys(false);
                for (String data0 : dataSetStr) {
                    //判断精灵是否已经销售
                    if (yaml.getString("全部精灵." + data0) != null){
                        //进入详情
                        ShopPok dataShopPok = new ShopPok(
                                PlayerNmae,
                                yaml.getString("全部精灵." + data0 + ".名称"),
                                data0,
                                yaml.getString("全部精灵." + data0 + ".EC"),
                                new PokData(
                                        yaml.getString("全部精灵." + data0 + ".名称"),
                                        new PokDataClass(
                                                yaml.getInt("全部精灵." + data0 + ".IVS.attack"),
                                                yaml.getInt("全部精灵." + data0 + ".IVS.defence"),
                                                yaml.getInt("全部精灵." + data0 + ".IVS.specialAttack"),
                                                yaml.getInt("全部精灵." + data0 + ".IVS.specialDefence"),
                                                yaml.getInt("全部精灵." + data0 + ".IVS.speed"),
                                                yaml.getInt("全部精灵." + data0 + ".IVS.hp"),
                                                yaml.getInt("全部精灵." + data0 + ".IVS.level")
                                        ),
                                        new PokDataClass(
                                                yaml.getInt("全部精灵." + data0 + ".EVS.attack"),
                                                yaml.getInt("全部精灵." + data0 + ".EVS.defence"),
                                                yaml.getInt("全部精灵." + data0 + ".EVS.specialAttack"),
                                                yaml.getInt("全部精灵." + data0 + ".EVS.specialDefence"),
                                                yaml.getInt("全部精灵." + data0 + ".EVS.speed"),
                                                yaml.getInt("全部精灵." + data0 + ".EVS.hp"),
                                                yaml.getInt("全部精灵." + data0 + ".EVS.level")
                                        )

                                ));
                        //加入集合
                        retShopPok.add(dataShopPok);
                    }
                }
            }
        }
        return retShopPok;
    }

    //用玩家ID获取店铺文件

    public YamlConfiguration getPlayerShopYaml(String p){
        File dataFile = new File(MKSHOPDATA.getAbsolutePath() +"\\"+ p + ".yml");
        return YamlConfiguration.loadConfiguration(dataFile);
    }


}
