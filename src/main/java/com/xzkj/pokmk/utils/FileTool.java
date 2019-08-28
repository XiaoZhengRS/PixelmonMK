package com.xzkj.pokmk.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTool {

    /***
     * 获取指定目录下的所有的文件（不包括文件夹），采用了递归
     *
     * @param obj
     * @return
     */
    public static List<File> getListFiles(File obj) {
        List<File> datalist = new ArrayList<>();
        File[] fs = obj.listFiles();
        for (File f : fs) {
            if (!f.isDirectory()){ datalist.add(f);}
        }
        return datalist;
    }

}
