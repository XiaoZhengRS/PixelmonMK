package com.xzkj.pokmk.utils.pok;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import com.xzkj.pokmk.data.PokDataClass;
import com.xzkj.pokmk.utils.MessTool;
import com.xzkj.pokmk.utils.ShopToop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static com.xzkj.pokmk.data.CoreData.MKSHOPDATA;

public class PokTool {
    //获取玩家指定位置的精灵

    public static Pokemon getPlayerPokemonBoxI(Player p, int o){
        PlayerPartyStorage playerStorage = Pixelmon.storageManager.getParty(p.getUniqueId());
        return playerStorage.get(o);
    }

    //给与玩家一只宝可梦

    public static Boolean givePlayerPokemon(Player p, Pokemon o) {
        PlayerPartyStorage playerStorage = Pixelmon.storageManager.getParty(p.getUniqueId());
        return playerStorage.add(o);
    }

    //通过指令给玩家一只宝可梦

    public static void givePlayerPokemonCmd(Player p,String MJ,String PokUUID){
        /*String cmd = "pokegive ID charizard lvl:1 " +
                "ivhp:31 ivatk:31 ivsatk:31 ivsdef:31 ivdef:31 ivspd:31 " +
                "evhp:255 evatk:255 evsatk:255 evsdef:255 evdef:255 evspd:255";*/
        YamlConfiguration dataYaml = new ShopToop().getPlayerShopYaml(MJ);
        String YBNAME = dataYaml.getString("全部精灵." + PokUUID + ".YB");
        PokDataClass IVSD = new PokDataClass(
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.attack"),
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.defence"),
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.specialAttack"),
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.specialDefence"),
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.speed"),
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.hp"),
                dataYaml.getInt("全部精灵." + PokUUID + ".IVS.level")
        );
        PokDataClass EVSD = new PokDataClass(
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.attack"),
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.defence"),
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.specialAttack"),
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.specialDefence"),
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.speed"),
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.hp"),
                dataYaml.getInt("全部精灵." + PokUUID + ".EVS.level")
        );
        String cmd = "pokegive " + p.getName() + " "
                + YBNAME + " "
                + "lvl:" + IVSD.level + " "
                + "ivhp:" + IVSD.hp + " "
                + "ivatk:" + IVSD.attack + " "
                + "ivsatk:" + IVSD.specialAttack + " "
                + "ivdef:" + IVSD.defence + " "
                + "ivsdef:" + IVSD.specialDefence + " "
                + "ivspd:" + IVSD.speed + " "
                + "evhp:" + EVSD.hp + " "
                + "evspd:" + EVSD.speed + " "
                + "evatk:" + EVSD.attack + " "
                + "evsatk:" + EVSD.specialAttack + " "
                + "evdef:" + EVSD.defence + " "
                + "evsdef:" + EVSD.specialDefence;
        dataYaml.set("全部精灵." + PokUUID, null);
        File dataFile = new File(MKSHOPDATA.getAbsolutePath() +"\\"+ p.getName() + ".yml");
        try {
            dataYaml.save(dataFile);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),  cmd);
            MessTool.logPlayer("兑换完成精灵已经送往背包或者仓库!", p);
        } catch (IOException e) {
            e.printStackTrace();
            MessTool.logPlayer("兑换精灵失败!", p);
        }

    }
}
