package com.hdumil.aiwriter.base.util;

import com.hdumil.aiwriter.AdminConfig;
import com.hdumil.aiwriter.back.bean.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : 用于上传图片工具类
 */
public class FileUploadUtil {

    //上传md的图片
    public static Map<String, Object> fileUpload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile img,
                                                 HttpSession session) {
        /*
        准备把上传图片保存在upload目录下，还有子目录 upload/时间/用户名/很多的图片
        */
        String realPath = session.getServletContext().getRealPath("/upload");
        //获取登录用户
        User user = (User) session.getAttribute("user");
        realPath += "/" + user.getUsername();

        File file = new File(realPath);
        if (!file.exists()) {
            //创建带层级的目录 mkdir:只能创建一级目录
            file.mkdirs();
        }
        //相同用户可能会上传相同的文件名的图片，防止文件重名
        //获取用户名
        String fileName = img.getOriginalFilename();

        //1223434324文件名.png/jpg
        fileName = System.currentTimeMillis() + fileName;

        //定义用于给Editormd返回的map数据
        Map<String, Object> map = new HashMap<>();
        //获取回调地址
        String url = "http://localhost/blog/upload/" + DateTimeUtil.getDate()
                + "/" + user.getUsername() + "/" + fileName;
        try {
            img.transferTo(new File(realPath + "/" + fileName));
            //返回success:1(数字) url:图片回调地址(图片在服务器存储路径)
            map.put("success", 1);
            map.put("url", url);
            map.put("message", "上传图片成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    //上传用户个人的单个文件
    public static Map<String, Object> Upload2User(CommonsMultipartFile files, HttpSession session){
        String subPath = "/upload/user";
//        String subPath = "/upload/user/videos";
//        String subPath = "/upload/user/audios";
        User user = (User) session.getAttribute("user");
        subPath = subPath + "/" + user.getUsername();
        String realPath = session.getServletContext().getRealPath(subPath);
        return Upload2Lib(files, realPath, subPath);
    }

    //上传用户个人的多个文件
    public static List<Map<String, Object>> Upload2User_s(CommonsMultipartFile[] files, HttpSession session){
        String subPath = "/upload/" + AdminConfig.FILE_SAVE_PATH_USER;
//        String subPath = "/upload/user/videos";
//        String subPath = "/upload/user/audios";
        User user = (User) session.getAttribute("user");
        subPath = subPath + "/" + user.getUsername();
        String realPath = session.getServletContext().getRealPath(subPath);
        return Upload2Lib_s(files, realPath, subPath);
    }

    //上传到公共临时库的文件
    public static List<Map<String, Object>> Upload2TempPublic(CommonsMultipartFile[] files, HttpSession session){
        String subPath = "/upload/" + AdminConfig.FILE_SAVE_PATH_TEMPPUBLIC;
        String Date_n = DateTimeUtil.getDate();
        subPath = subPath + "/" + Date_n;  //日期
        String realPath = session.getServletContext().getRealPath(subPath);
        return Upload2Lib_s(files, realPath, subPath);
    }

    //上传到公共库的文件
    public static Map<String, Object> Upload2Public(CommonsMultipartFile file, HttpSession session){
        String subPath = "/upload/" + AdminConfig.FILE_SAVE_PATH_PUBLIC;
        String Date_n = DateTimeUtil.getDate();
        subPath = subPath + "/" + Date_n;  //日期
        String realPath = session.getServletContext().getRealPath(subPath);
        return Upload2Lib(file, realPath, subPath);
    }

    //上传多个文件到指定库中
    public static List<Map<String, Object>> Upload2Lib_s(CommonsMultipartFile[] files, String realPath, String subPath) {
        List<Map<String, Object>> res = new ArrayList<>();
        for (CommonsMultipartFile file : files) {
            res.add(Upload2Lib(file, realPath, subPath));
        }
        return res;
    }

    //上传一个文件到指定库中
    public static Map<String, Object> Upload2Lib(CommonsMultipartFile inputFiles, String realPath, String subPath) {
        /*
        准备把上传图片保存在upload目录下，还有子目录 realPath/很多的图片
        */
        File file = new File(realPath);
        if (!file.exists()) {
            //创建带层级的目录 mkdir:只能创建一级目录
            file.mkdirs();
        }
        Map<String, Object> map = new HashMap<>();
        //获取回调地址
        subPath = subPath + "/";
        try {
            if (!inputFiles.isEmpty()) {
                String fileName = inputFiles.getOriginalFilename();//获取文件名
                String newName = System.currentTimeMillis() + "￥" + fileName;
                File file2 = new File(realPath, newName); //新建一个文件
                inputFiles.transferTo(file2);
                map.put("url", subPath + newName);
                map.put("success", 1);
                map.put("message", "上传成功");
            }
        } catch (IOException e) {
            map.put("url", inputFiles.getOriginalFilename());
            map.put("success", 0);
            map.put("message", "上传失败");
            e.printStackTrace();
        }
        return map;
    }
}
