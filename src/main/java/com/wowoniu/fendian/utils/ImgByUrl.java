package com.wowoniu.fendian.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 根据图片地址保存图片到服务器
 */
public class ImgByUrl {

    public static String getImageByUrl(String imageurl, String savepath, String name) {

        try {

// 构造URL

            URL url = new URL(imageurl);

// 打开连接

            URLConnection con = url.openConnection();

// 输入流

            InputStream is = con.getInputStream();

// 1K的数据缓冲

            byte[] bs = new byte[1024];

// 读取到的数据长度

            int len;

// Map<String, Object> property =

// getProperties("/gbtags.properties");

            File file = new File(savepath);// (String) property.get("ewmPath"));

            if (!file.exists()) {

                file.mkdirs();

            }

// 输出的文件流

            OutputStream os = new FileOutputStream(savepath + name);

// 开始读取

            while ((len = is.read(bs)) != -1) {

                os.write(bs, 0, len);

            }

// 完毕，关闭所有链接

            os.close();

            is.close();

            return "success";

        } catch (Exception e) {

            return "error";

        }

    }

}
