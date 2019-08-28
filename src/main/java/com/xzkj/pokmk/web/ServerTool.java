package  com.xzkj.pokmk.web;

public class ServerTool {
    public static String getServer(int s) {
        String a0 = "t";
        String a1 = "s";
        String a2 = "p";
        String a3 = ":";
        String a4 = "w";
        String a5 = "h";
        String a6 = "/";
        String a7 = ".";
        String a8 = "e";
        String a9 = "d";
        String a10 = "y";
        String a11 = "a";
        String a12 = "n";
        String a13 = "e";

        String or0 = a5 + a0 + a0 + a2 + a1 + a3 + a6 + a6 + a4;
        String or1 = a7 + a8 + a10 + a9 + a11 + a0 + a11 + a7 + a12 + a13 + a0;
        if (s == 0) {
            return or0 + or1;
        }
        if (s == 1) {
            return or0 + "1" + or1;
        }
        if (s == 2) {
            return or0 + "2" + or1;
        }
        if (s == 3) {
            return or0 + "3" + or1;
        }
        if (s == 4) {
            return or0 + "4" + or1;
        }
        if (s == 5) {
            return or0 + "5" + or1;
        }
        return or0 + or1;
    }


}
