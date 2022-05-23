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
    public static final HashMap<String, String> mFileTypes = new HashMap();
    public static final HashMap<String, String> mFileTypes2indx = new HashMap();

    static {
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
        mFileTypes.put("41433130", "dwg"); // CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); // 日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "docx");
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
        mFileTypes.put("4D5A9000", "exe/dll");
        mFileTypes.put("75736167", "txt");
        mFileTypes2indx.put("txt", "0");
        mFileTypes2indx.put("docx", "0");
        mFileTypes2indx.put("doc", "0");
        mFileTypes2indx.put("pdf", "0");
        mFileTypes2indx.put("jpg", "1");
        mFileTypes2indx.put("png", "1");
        mFileTypes2indx.put("gif", "1");
        mFileTypes2indx.put("bmp", "1");
        mFileTypes2indx.put("mp4", "2");
        mFileTypes2indx.put("avi", "2");
        mFileTypes2indx.put("wmv", "2");
        mFileTypes2indx.put("mpg", "2");
        mFileTypes2indx.put("mpeg", "2");
        mFileTypes2indx.put("mov", "2");
        mFileTypes2indx.put("rm", "2");
        mFileTypes2indx.put("ram", "2");
        mFileTypes2indx.put("rmvb", "2");
        mFileTypes2indx.put("swf", "2");
        mFileTypes2indx.put("mkv", "2");
        mFileTypes2indx.put("asf", "2");
        mFileTypes2indx.put("divx", "2");
        mFileTypes2indx.put("mp3", "3");
        mFileTypes2indx.put("wav", "3");
        mFileTypes2indx.put("cda", "3");
        mFileTypes2indx.put("aif", "3");
        mFileTypes2indx.put("wma", "3");
        mFileTypes2indx.put("ape", "3");
        mFileTypes2indx.put("html", "4");
        mFileTypes2indx.put("htm", "4");
    }


    /**
     * 根据文件路径获取文件的类型标识
     * @param filePath
     * 文件路径
     * @return 文件类型标识。0是文本，1是图片，2是视频，3是音频，4是html
     */
    public static String getFileTypeIndx(String filePath) {
        String ty = null;
        String[] temp = filePath.split("\\.");
        try {
            String file_p = temp[temp.length - 1];
            ty = mFileTypes2indx.get(file_p);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ty;
    }

    /**
     * 根据文件路径获取文件头信息
     * @param filePath
     * 文件路径
     * @return 文件头信息
     */
    public static String getFileType(String filePath) {
        return mFileTypes.get(getFileHeader(filePath));
    }

    /**
     * 根据文件路径获取文件头信息
     * @param filePath
     * 文件路径
     * @return 文件头信息
     */
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception ignored) {
        } finally {
            if (null != is) {
                try {
                    is.close();

                } catch (IOException e) {
                }

            }

        }
        return value;

    }


    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     * @param src
     * 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (byte b : src) {
            // 以十六进制(基数 16)无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(b & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();

    }

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
