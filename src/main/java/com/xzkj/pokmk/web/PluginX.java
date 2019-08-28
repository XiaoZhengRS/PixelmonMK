package  com.xzkj.pokmk.web;

import com.xzkj.pokmk.PokMK;
import com.xzkj.pokmk.web.tool.HttpTool;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PluginX {
    public static String C_url = ServerTool.getServer(PokMK.serverID);
    public static String C_login = "4bab64cec1d1c941";
    public static String C_Core = "309b429bb9e17e5e";
    public static String C_Var = "00f7d62c0f40e592";
    public static String C_OL = "8bbb73cb45629557";
    public static String C_User = "b0d27acc75358dde";
    public static String C_log = "8bb6f2a061629173";
    public static String C_RES = "c7770098c931dd9c";
    public static String C_X = "f2b659eb7f314223";
	
	//获取MAC
	
	public static List<String> getMAC() throws Exception{
		java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tmpMacList=new ArrayList<>();
        while(en.hasMoreElements()){
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for(InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if(network==null){continue;}
                byte[] mac = network.getHardwareAddress();
                if(mac==null){continue;}
                sb.delete( 0, sb.length() );
                for (int i = 0; i < mac.length; i++) {sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));}
                tmpMacList.add(sb.toString());
            }        }
        if(tmpMacList.size()<=0){return tmpMacList;}
        /***去重，别忘了同一个网卡的ipv4,ipv6得到的mac都是一样的，肯定有重复，下面这段代码是。。流式处理***/
        List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
        return unique;
	}
	
	
    //登录密码卡
	
    public static String PluginLogin(String key, String version, String MAC) {
        String Status_Code = HttpTool.sendPost
                (C_url + "/" + C_login, "SingleCode=" + key + "&Ver=" + version + "&Mac=" + MAC);
        return Status_Code;
    }

    //获取核心数据

    public static String PluginCore(String Status_Code, String key) {
        String Core = HttpTool.sendPost
                (C_url + "/" + C_Core, "StatusCode=" + Status_Code + "&UserName=" + key);
        return Core;
    }

    //获取变量数据

    public static String PluginVar(String Status_Code, String key, String VarID, String VarName) {
        String Var = HttpTool.sendPost
                (C_url + "/" + C_Var, "StatusCode=" + Status_Code + "&UserName=" + key + "&VariableId=" + VarID + "&VariableName=" + VarName);
        return Var;
    }
    //获取在线人数

    public static String PluginOL(String Status_Code, String UserVar) {
        String OL = HttpTool.sendPost
                (C_url + "/" + C_OL, "UserName=" + Status_Code + "&Type=" + UserVar);
        return OL;
    }
    //获取用户到期时间

    public static String PluginUser(String Key) {
        String date = HttpTool.sendPost
                (C_url + "/" + C_User, "UserName=" + Key);
        return date;
    }

    //用户退出

    public static String ResUser(String Status_Code, String Key) {
        String date = HttpTool.sendPost
                (C_url + "/" + C_RES, "StatusCode=" + Status_Code + "&UserName=" + Key);
        return date;
    }

    //用户状态

    public static String UserStatus(String Status_Code, String Key) {
        String date = HttpTool.sendPost
                (C_url + "/" + C_X, "StatusCode=" + Status_Code + "&UserName=" + Key);
        return date;
    }

    //获取公告

    public static String PluginLog() {
        String date = HttpTool.sendPost
                (C_url + "/" + C_log, "");
        String utf8 = null;
        String gbk = null;
        String unicode = null;
        try {
            unicode = new String(date.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            gbk = new String(unicode.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return gbk;
    }

    //测试

    public static void main(String[] args) {
        String key = "ZZOD5F42329609B7B02E454152A2D4E0";
        C_url = ServerTool.getServer(1);
        String MAC = null;
        try {
            MAC = getMAC().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录
        String Status_code = PluginLogin(key, "1", MAC);
        System.out.println("登录返回状态码:" + Status_code.length());
        System.out.println("云端核心数据:" + PluginCore(Status_code, key));
        System.out.println("云端公告:" + PluginLog());
        System.out.println("云端变量数据:" + PluginVar(Status_code, key, "变量编号", "变量名称"));
        System.out.println("插件在线人数:" + PluginOL(Status_code, key));
        System.out.println("用户到期时间:" + PluginUser(key));
        System.out.println("用户当前状态:" + UserStatus(Status_code,key));
    }
}
