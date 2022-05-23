package com.hdumil.aiwriter.base.util;

import com.hdumil.aiwriter.AdminConfig;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 文件读取工具类
 */
public class FileUtil {

    /**
     * 读取文件内容，作为字符串返回
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } 

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        } 

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 创建字节输入流  
        FileInputStream fis = new FileInputStream(filePath);  
        // 创建一个长度为10240的Buffer
        byte[] bbuf = new byte[10240];  
        // 用于保存实际读取的字节数  
        int hasRead = 0;  
        while ( (hasRead = fis.read(bbuf)) > 0 ) {  
            sb.append(new String(bbuf, 0, hasRead));  
        }  
        fis.close();  
        return sb.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }


    public static int deleteFilrByHtml(String htmlText, String realPath){
        Document document = Jsoup.parse(htmlText);
        Elements pItems = document.getElementsByTag("img");
        int num = 0;
        for (Element pItem : pItems) {
            String url = pItem.attr("src");
            if(new HashSet<>(Arrays.asList(url.split("/"))).contains(AdminConfig.FILE_SAVE_PATH_TEMPPUBLIC))
                if(FileUtil.deleteFile(realPath + "/" + url)) num++;
        }
        return num;
    }

    /**
     * 删除多个文件
     *
     * @param fileName  要删除的文件的文件名
     * @return 返回删除失败的文件
     */
    public static List<String> deleteFiles(List<String> fileName){
        List<String> fail = new ArrayList<>(); // 删除失败的文件
        for (String str : fileName) {
            if(!deleteFile(str)){
                fail.add(str);
            }
        }
        return fail;
    }

    /**
     * 删除多个文件
     *
     * @param fileName  要删除的文件的文件名
     * @param beforePath  前缀文件路径
     * @return 返回删除失败的文件
     */
    public static List<String> deleteFiles(List<String> fileName, String beforePath){
        List<String> fail = new ArrayList<>(); // 删除失败的文件
        for (String str : fileName) {
            if(!deleteFile(beforePath + str)){
                fail.add(str);
            }
        }
        return fail;
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
//            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
//            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static List<String> fileCopy(List<String> inputs, List<String> outputs){
        List<String> copyFail = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            try(InputStream in = new FileInputStream(inputs.get(i));
                OutputStream out = new FileOutputStream(outputs.get(i), true)){
                if(IOUtils.copy(in, out) == -1){  // 拷贝失败
                    copyFail.add(inputs.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return copyFail;
    }
}
