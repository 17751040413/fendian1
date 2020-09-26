package com.wowoniu.fendian.web.api.app.controller.Image;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wowoniu.fendian.model.ResponseResult;
import com.wowoniu.fendian.utils.Result;
import com.wowoniu.fendian.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 照片上传工具类
 * @author
 * @date 2020-08-11
 */
@Api(value = "图片上传工具类", tags = "图片上传工具类")
@RestController
@RequestMapping("/image")
public class UploaderController extends BaseController{

    @Value("${img.path}")
    private String fileSavePath;
    /**
     * 时间格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    @ResponseBody
    @RequestMapping("/uploadPicture")
    public Result uploadPicture(@RequestParam(value="file",required=false)MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
//        ResponseResult result = new ResponseResult();
//        Map<String, Object> map = new HashMap<String, Object>();
//        File targetFile=null;
//        String url="";//返回存储路径
//        int code=100;
//        //System.out.println(file);
//        for (MultipartFile file:files){
//            String fileName=file.getOriginalFilename();//获取文件名加后缀
//            if(fileName!=null&&fileName!=""){
//                String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
//                String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
//                String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//                fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
//
//                //先判断文件是否存在
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                String fileAdd = sdf.format(new Date());
//                //获取文件夹路径
//                File file1 =new File(path+"/"+fileAdd);
//                //如果文件夹不存在则创建
//                if(!file1 .exists()  && !file1 .isDirectory()){
//                    file1 .mkdirs();
//                }
//                //将图片存入文件夹
//                targetFile = new File(file1, fileName);
//                try {
//                    //将上传的文件写到服务器上指定的文件。
//                    file.transferTo(targetFile);
//                    url=returnUrl+fileAdd+"/"+fileName+";"+url;
//                    code=200;
//                    result.setMessage("图片上传成功");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    result.setMessage("系统异常，图片上传失败");
//                    return new Result(205,true,"上传失败");
//                }
//            }
//        }
//
//        url = url.substring(0,url.length()-1);
//        map.put("url",url);
//        return new Result(200,true,"上传成功",map);
//    }
        String url = "";
        String directory = simpleDateFormat.format(new Date());
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile file:files){
            //后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName= StringUtils.getUuid()+suffix;
            //4.创建这个新文件
            File newFile = new File(fileSavePath + directory + newFileName);
            //5.复制操作
            try {
                file.transferTo(newFile);
                //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
                url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newFileName+";"+url;

            } catch (IOException e) {
                return new Result(204,true,"上传失败");
            }
        }
        url = url.substring(0,url.length()-1);
        Map map = new HashMap();
        map.put("url",url);
        return new Result(200,true,"上传成功",map);

    }
}
