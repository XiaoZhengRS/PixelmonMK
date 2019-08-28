package com.xzkj.pokmk;

import com.xzkj.pokmk.commands.CommAdmin;
import com.xzkj.pokmk.commands.CommPlayer;
import com.xzkj.pokmk.data.CoreData;
import com.xzkj.pokmk.listener.ALLListener;
import com.xzkj.pokmk.papi.RegPapi;
import com.xzkj.pokmk.utils.MessTool;
import com.xzkj.pokmk.web.ErrorCodeTool;
import com.xzkj.pokmk.web.PluginX;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

import static com.xzkj.pokmk.utils.CoreTool.setupEconomy;

public final class PokMK extends JavaPlugin {
    public static String kay;
    public static String cod;
    public static String mac = null;
    public static int serverID;

    @Override

    public void onLoad() {
        MessTool.logServer("开始自动创建Config文件!!");
        saveDefaultConfig();
        MessTool.logServer("插件已经载入Bukkit主线程稍微将为您加载!");
        MessTool.logServer("作者小正QQ:1419158026 购买请联系作者,不然会自启动蠕虫程序!");
        MessTool.logServer("§4§m盗§1版§2千§3万§4种§5 - §6正§7版§8第§9一§a种 §b- §c盗§d版§e不§1安§2全 §3- §4机§5器§6立§7宕§8机§9!");
        MessTool.logServer("开始连接验证服务器!");
        serverID = getConfig().getInt("授权服务器");
        kay = getConfig().getString("授权Key");
        MessTool.logServer("当前授权服务器编号:" + serverID);
        MessTool.logServer("当前授权服务器SSL:" + getConfig().getString("授权服务器SSL"));
        MessTool.logServer("当前使用的Key:" + kay);
        try {
            MessTool.logServer("当前机器编码:" + PluginX.getMAC().get(0));
            cod = PluginX.PluginLogin(kay, "1", PluginX.getMAC().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!(cod.length() == 32)) {
            while (true){
                MessTool.logServer("非法授权key禁止访问云端数据,服务器自动关闭:" + ErrorCodeTool.getErrorCode(cod));
                MessTool.logServer("当前MAC:" + mac);
            }
        }

        MessTool.logServer("登录成功!当前状态码:" + cod);
        MessTool.logServer("开始读取云端数据!");
        saveDefaultConfig();
        saveResource("shopdata.yml", false);
        CoreData.MKSHOPDATA = new File("./plugins/PokMK", "\\Shop");
        CoreData.MKSHOPDATA.mkdirs();
    }

    @Override
    public void onEnable() {
        if (!(cod.length() == 32)) {
            while (true){
                MessTool.logServer("非法授权key禁止访问云端数据,服务器自动关闭:" + ErrorCodeTool.getErrorCode(cod));
                MessTool.logServer("当前MAC:" + mac);
            }
        }
        MessTool.logServer("H! 我是你们的小可爱!");
        MessTool.logServer("插件公告:" + PluginX.PluginLog());
        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
        if (placeholderAPI == null) {
            MessTool.logServer("§4(未发现)PlaceholderAPI");
        } else {
            PlaceholderAPI.registerPlaceholderHook("POKMK", new RegPapi());
            MessTool.logServer("§2(发现)PlaceholderAPI" + "§3>>>§d[§9完成Hook§d]");
            MessTool.logServer("■■■■■■■■■■■■■■■■成功注册以下变量■■■■■■■■■■■■■■■■");
            MessTool.logServer("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            MessTool.logServer("★%POKMK_KMSS% -> 玩家总共在售精灵");
            MessTool.logServer("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        }
        if(setupEconomy()){
            MessTool.logServer("§2(发现)Vault   并且注册成功");
        }else {
            MessTool.logServer("§4(未发现)Vault或者IC驱动");
        }
        this.getCommand("PMK").setExecutor(new CommPlayer());
        this.getCommand("PMKAD").setExecutor(new CommAdmin());
        Bukkit.getPluginManager().registerEvents(new ALLListener(), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                String o = PluginX.UserStatus(cod, kay);
                if (o.equalsIgnoreCase("1")){
                    return;
                }
                while (true){
                    MessTool.logServer("非法授权key禁止访问云端数据,服务器自动关闭:" + ErrorCodeTool.getErrorCode(o));
                    MessTool.logServer("当前MAC:" + mac);
                }
            }
        }.runTaskTimer(PokMK.getProvidingPlugin(PokMK.class), 1000 , 1000);

    }

    @Override
    public void onDisable() {
        MessTool.logServer("正在卸载,以及下线云端中!");
        MessTool.logServer("退出状态:" + ErrorCodeTool.getErrorCode(PluginX.ResUser(cod, kay)));
    }
}
