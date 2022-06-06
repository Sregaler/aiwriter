package com.hdumil.aiwriter.base.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * http 分析工具类
 */
public class HttpAnalyzeUtil {
    public static Map<String, Object> getContent_NationEmergency(String url){
        Map<String, Object> res = new HashMap<>();
        String xmlSource = HttpUtil.httpGetTrust(url,null);
        Document document = Jsoup.parse(xmlSource);
        res.put("news_content", qureyContent(document, "TRS_Editor",Integer.MAX_VALUE));
        res.put("title", qureyContent(document, "zhenwen",1));
        res.put("time_laiy", qureyContent(document, "time_laiy",1));
        return res;
    }

    public static Map<String, Object> getContent_Baijiahao(String url){
        Map<String, Object> res = new HashMap<>();
        String xmlSource = HttpUtil.httpGet(url,null);
        System.out.println(xmlSource);
        Document document = Jsoup.parse(xmlSource);
        res.put("news_content", qureyContent(document, "index-module_articleWrap_2Zphx ",Integer.MAX_VALUE));
        res.put("title", qureyContent(document, "index-module_articleTitle_28fPT ",1));
        res.put("time_laiy", qureyContent(document, "index-module_time_10s4U",1));
        return res;
    }

    public static String qureyContent(Document document, String className, int num){
        Elements pItems = document.getElementsByClass(className);
        StringBuilder news_list = new StringBuilder();
        num = Math.min(num, pItems.size());
//        System.out.println(num);
        for (int i = 0; i < num; i++) {
            Element pItem = pItems.get(i);
            Elements pEle = pItem.getElementsByTag("p");
            if(pEle.size()>0){
                for (Element pE:pEle) {
                    news_list.append("<p>").append(pE.text()).append("</p>");
                }
            }
            else {
                news_list.append("<p>").append(pItem.text()).append("<p>");
            }
        }
        return news_list.toString();
    }
}
