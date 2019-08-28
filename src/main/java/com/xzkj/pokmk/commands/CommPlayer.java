package com.xzkj.pokmk.commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.EVStore;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.IVStore;
import com.xzkj.pokmk.utils.MessTool;
import com.xzkj.pokmk.utils.ShopToop;
import com.xzkj.pokmk.utils.pok.PokTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;

import static com.xzkj.pokmk.data.CoreData.MKSHOPDATA;

public class CommPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(">>请务必以玩家身份打开!");
            return true;
        }
        Player p = (Player) commandSender;
        if (strings.length == 0) {
            MessTool.logPlayer("/PMK OPEN    打开市场", p);
            MessTool.logPlayer("/PMK new [位置0-5] [价格]  上架精灵", p);
            return true;
        }
        if (strings[0].equalsIgnoreCase("open")) {
            p.closeInventory();
            Inventory i = new ShopToop().giveShopMain(p).get(0);
            if (i == null) {
                MessTool.logPlayer("当前精灵市场销售精灵为0", p);
                return true;
            }
            p.openInventory(i);
            return true;
        }
        if (strings[0].equalsIgnoreCase("new")) {
            Pokemon data = PokTool.getPlayerPokemonBoxI(p, Integer.parseInt(strings[1]));
            File dataFile = new File(MKSHOPDATA.getAbsolutePath() + "\\" + p.getName() + ".yml");
            YamlConfiguration dataYaml = YamlConfiguration.loadConfiguration(dataFile);
            EVStore dataEVS = data.getEVs();
            IVStore dataIVS = data.getIVs();
            int datalvl = data.getLevel();
            String YBNAM = data.getBaseStats().pixelmonName;
            String NAME = data.getDisplayName();
            String EC = strings[2];
            String UUID = data.getUUID().toString();
            dataYaml.set("玩家", p.getName());
            dataYaml.set("全部精灵." + UUID + ".名称", NAME);
            dataYaml.set("全部精灵." + UUID + ".EC", EC);
            dataYaml.set("全部精灵." + UUID + ".YB", YBNAM);
            dataYaml.set("全部精灵." + UUID + ".IVS.attack", dataIVS.attack);
            dataYaml.set("全部精灵." + UUID + ".IVS.defence", dataIVS.defence);
            dataYaml.set("全部精灵." + UUID + ".IVS.specialAttack", dataIVS.specialAttack);
            dataYaml.set("全部精灵." + UUID + ".IVS.specialDefence", dataIVS.specialDefence);
            dataYaml.set("全部精灵." + UUID + ".IVS.speed", dataIVS.speed);
            dataYaml.set("全部精灵." + UUID + ".IVS.hp", dataIVS.hp);
            dataYaml.set("全部精灵." + UUID + ".IVS.level", datalvl);

            dataYaml.set("全部精灵." + UUID + ".EVS.attack", dataEVS.attack);
            dataYaml.set("全部精灵." + UUID + ".EVS.defence", dataEVS.defence);
            dataYaml.set("全部精灵." + UUID + ".EVS.specialAttack", dataEVS.specialAttack);
            dataYaml.set("全部精灵." + UUID + ".EVS.specialDefence", dataEVS.specialDefence);
            dataYaml.set("全部精灵." + UUID + ".EVS.speed", dataEVS.speed);
            dataYaml.set("全部精灵." + UUID + ".EVS.hp", dataEVS.hp);
            dataYaml.set("全部精灵." + UUID + ".EVS.level", datalvl);

            try {
                dataYaml.save(dataFile);
                MessTool.logPlayer("上架完成!", p);
                Pixelmon.storageManager.getParty(p.getUniqueId()).set(Integer.parseInt(strings[1]), null);
                MessTool.logOLPlayer("玩家上架精灵:" + NAME + "等级:" + datalvl + "价格:" + EC +
                        "IVS总和:" + (dataIVS.attack + dataIVS.specialAttack + dataIVS.defence +
                        dataIVS.specialDefence + dataIVS.speed + dataIVS.hp) +
                        "EVS总和:" + (dataEVS.attack + dataEVS.specialAttack + dataEVS.defence +
                        dataEVS.specialDefence + dataEVS.speed + dataEVS.hp));
            } catch (IOException e) {
                MessTool.logPlayer("上架失败请联系插件作者!", p);
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
