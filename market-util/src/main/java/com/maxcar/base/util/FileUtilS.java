package com.maxcar.base.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileUtilS {

    /**
     * param:
     * describe:    写章节文件
     * creat_date:  lxy   2017/9/18  16:16
     **/
    public static boolean insertHtmlFile(String text, String path) throws Exception {

        if (StringUtils.isBlank(text) || StringUtils.isBlank(path)) {
            return false;
        }

        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();//创建文件夹
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(text);
        bw.flush();
        bw.close();
        return files(path);

    }

    /**
     * lxy
     * 判断文件是否存在  true 存在  false 不存在
     *
     * @param pathurl
     * @return
     */
    public static boolean files(String pathurl) {
        File file = new File(pathurl);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

}
