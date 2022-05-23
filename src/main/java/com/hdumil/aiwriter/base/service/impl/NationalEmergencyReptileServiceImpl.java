package com.hdumil.aiwriter.base.service.impl;

import com.hdumil.aiwriter.base.service.NationalEmergencyReptileService;
import com.hdumil.aiwriter.base.util.HttpUtil;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class NationalEmergencyReptileServiceImpl implements NationalEmergencyReptileService {

    @Override
    public Map<String, Object> news_xw(String context, Integer page) {
        String url = "https://www.mem.gov.cn/xw/" + context + "/index.shtml";
        if(page != 1){
            url = "https://www.mem.gov.cn/xw/" + context + "/index_"+ page +".shtml";
        }
        return analysisHtml(url,context);
    }


    private static Map<String, Object> analysisHtml(String url,String context) {
        Map<String, Object> res = new HashMap<>();
        List<Map<String, String>> news_list = new ArrayList<>();
        String xmlSource = HttpUtil.httpGet(url,null);
        Document document = Jsoup.parse(xmlSource);
        Elements pItems = document.getElementsByClass("cont");
        for (Element pIt : pItems) {
            Elements aIts = pIt.select("a");
            for (Element aIt : aIts) {
                Map<String, String> temp = new HashMap<>();
                temp.put("url", getCompleteUrl(aIt.attr("href"), context));
                String text = aIt.text();
                temp.put("title", text.substring(0,text.length()-16));
                temp.put("message", "新闻");
                temp.put("time", text.substring(text.length()-16));
                news_list.add(temp);
            }
        }
        res.put("news_list", news_list);
        res.put("countPage", "1");
        res.put("dataCount", news_list.size());
        String[] data = null;
        try {
            /*取得JS变量数组*/
            data = document.getElementsByClass("page").get(0).getElementsByTag("script").get(0).data().substring(0, 160).split("var");
        } catch (IndexOutOfBoundsException ex) {
            if (news_list.isEmpty())
                System.out.println("获取错误");
        }
        /*取得单个JS变量*/
        for(String variable : data){
            try {
                /*过滤variable为空的数据*/
                if(variable.contains("=")){
                    String[]  kvp = variable.split("=");
                    if(kvp[0].trim().equals("countPage")){
                        res.put("countPage", kvp[1].split("//")[0].trim());
                    }
                    else if(kvp[0].trim().equals("dataCount")){
                        res.put("dataCount", kvp[1].split("\"")[1]);
                    }
                }
            }catch (ArrayIndexOutOfBoundsException ignored){
            }
        }
        return res;
    }

    private static String getCompleteUrl(String url, String contxt) {
        if(!url.contains("http")){
            if (url.split("/")[0].equals(".")) {
                url = "https://www.mem.gov.cn/xw/" + contxt + url.substring(1);
            }
            else if(url.split("/")[0].equals("..")){
                url = "https://www.mem.gov.cn/xw/" + url.substring(3);
            }
        }
        return url;
    }

    /**
     * 建立url连接，解析并获取网页中的内容。
     * @return 标题，内容，来源
     */
    public static Map<String, Object> analysisHtmlContent(String url){
        Map<String, Object> res = new HashMap<>();
        String xmlSource = HttpUtil.httpGet(url,null);
        Document document = Jsoup.parse(xmlSource);
        res.put("news_content", addContent(document, res, "TRS_Editor",Integer.MAX_VALUE));
        res.put("title", addContent(document, res, "zhenwen",1));
        res.put("time_laiy", addContent(document, res, "time_laiy",1));
        return res;
    }

    public static String addContent(Document document, Map<String, Object> res, String className, int num){
        Elements pItems = document.getElementsByClass(className);
        StringBuilder news_list = new StringBuilder();
        num = Math.min(num, pItems.size());
        for (int i = 0; i < num; i++) {
            Element pItem = pItems.get(i);
            Elements pEle = pItem.getElementsByTag("p");
            if(pEle.size()>0){
                for (Element pE:pEle) {
                    news_list.append("<p>").append(pE.text()).append("</p>");
                }
            }
            else {
                news_list.append(pItem.text());
            }
        }
        return news_list.toString();
    }

}
