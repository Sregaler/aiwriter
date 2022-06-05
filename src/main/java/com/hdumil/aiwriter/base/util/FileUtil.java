package com.hdumil.aiwriter.base.util;

import com.hdumil.aiwriter.AdminConfig;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

/**
 * 文件读取工具类
 */
public class FileUtil {
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
