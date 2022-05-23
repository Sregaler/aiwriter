package com.hdumil.aiwriter.base.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import com.hdumil.aiwriter.back.bean.ArticleTemplate;

/*
要有一个静态页面的模板，此静态页面中标题为##title##，我们可以动态的替换为动态的内容
filePath 模板的路径
htmlFile 保存的路径
map 替换的内容
 */
public class MakeHtmlTemplateUtil {

    public static String stringToHtml(List<String> result_ai, ArticleTemplate article_template) {
        return null;
    }

    public static boolean mapToHtmlTemplate(String templatePath, String htmlSavePath, HashMap<String, String> map) {
        StringBuffer str = new StringBuffer();
        try {
            FileInputStream is = new FileInputStream(templatePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String tempStr = "";
            while ((tempStr = br.readLine()) != null) {
                str.append(tempStr);
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String templateStr = str.toString();
        try {
            //将模板中的##title##替换成map.get("title")
//            str = str.replaceAll("##title##", map.get("title"));
//            str = str.replaceAll("##context##", map.get("context"));
//            str = str.replaceAll("##editer##", map.get("editer"));
            for(String key: map.keySet()){
                templateStr = templateStr.replaceAll(key, map.get(key));
            }
            System.out.println(templateStr);
            File file = new File(htmlSavePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(templateStr);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean jspToHtmlFileByfreeMarker(String filePath, String htmlFile, HashMap<String, String> map) {
        return false;
    }

    /*
    public static final String generate(final String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        Pattern pattern = Pattern.compile("(http://|https://){1}[//w//.//-/:]+");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        try {
            URL _url = new URL(url);
            URLConnection urlConnection = _url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void genHtml(HttpServletRequest request, String url, boolean toWebRoot, String encoding) throws IOException {

        if (null == url) {
            url = request.getRequestURL().toString();
        }

        String contextPath = request.getContextPath();
        String seq = StringUtils.substring(String.valueOf(new Date().getTime()), -6);

        String ctxPath = super.getServlet().getServletContext().getRealPath(File.separator);
        if (!ctxPath.endsWith(File.separator)) {
            ctxPath += File.separator;
        }

        String filePath = StringUtils.substringAfter(url, contextPath);
        filePath = filePath.replaceAll("//.(do|jsp|html|shtml)$", ".html");

        String savePath = "";
        String autoCreatedDateDir = "";
        if (!toWebRoot) {
            savePath = StringUtils.join(new String[] { "files", "history", "" }, File.separator);

            String[] folderPatterns = new String[] { "yyyy", "MM", "dd", "" };
            autoCreatedDateDir = DateFormatUtils.format(new Date(), StringUtils.join(folderPatterns, File.separator));

            filePath = StringUtils.substringBefore(filePath, ".html") + "-" + seq + ".html";
        }

        File file = new File(ctxPath + savePath + autoCreatedDateDir + filePath);
        FileUtils.writeStringToFile(file, generate(url), encoding);
    }
     */
}
