package com.wowoniu.fendian.utils;

import java.io.*;

public class CreateTxt {


    public static void writeToText(String musicInfo, String fileName) throws IOException {
        // 生成的文件路径
        String path = "C:\\data\\" + fileName + ".txt";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write 解决中文乱码问题
        // FileWriter fw = new FileWriter(file, true);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(musicInfo);
        bw.flush();
        bw.close();
        fw.close();


    }
}
