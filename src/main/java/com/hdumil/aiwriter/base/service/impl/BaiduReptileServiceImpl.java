package com.hdumil.aiwriter.base.service.impl;

import com.hdumil.aiwriter.base.service.BaiduReptileService;
import lombok.Data;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Data
//@ConfigurationProperties(prefix = "BaiduReptile")
public class BaiduReptileServiceImpl implements BaiduReptileService {

//    private String BaiduReptileCookie;

    @Override
    public List<Map<String, String>> imageReptile(String Word, int count_max, boolean random) {
        Word = Jsoup.parse(Word).text();  // 去除html标签
        String SerchWord = Word;
        if(random){
//            int start = new Random().nextInt(Word.length());
            int start = 0;
            int end = new Random().nextInt(Word.length() - start) + start + 1;
            SerchWord = Word.substring(start,end);
        }
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> parm = new HashMap<>();
        parm.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        parm.put("Accept-Encoding","gzip, deflate, br");
        parm.put("Accept-Language","zh-CN,zh;q=0.9");
        parm.put("Connection","keep-alive");
        parm.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
//        parm.put("Cookie",BaiduReptileCookie);
        for (int i = 0; i < 6; i++) {
            try {
                int img_count = 0;
                String url = "https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111110&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&pn=1&word=" + SerchWord;
//                System.out.println(url);
                Connection connection = Jsoup.connect(url).data().headers(parm).timeout(5000);
                Document document = connection.get();

                String xmlSource = document.toString();
                xmlSource = StringEscapeUtils.unescapeHtml3(xmlSource);
    //            System.out.println(xmlSource);
                String reg = "\"thumbURL\":\"https://img.+?\",\"";
                Pattern pattern = Pattern.compile(reg);
                Matcher m = pattern.matcher(xmlSource);
                while (m.find()) {
                    String finalURL = m.group();
    //                System.out.println(finalURL.substring(12,finalURL.length()-3));
                    Map<String, String> temp = new HashMap<>();
                    temp.put("url", finalURL.substring(12,finalURL.length()-3));
                    temp.put("title", "图片名字");
                    temp.put("message", "图片");
                    temp.put("time", "2022/3/12");
                    res.add(temp);
                    img_count++;
                    if(img_count >= count_max) break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(res.size()>0) break;
            int start = new Random().nextInt(Word.length());
            int end = new Random().nextInt(Word.length() - start) + start + 1;
            SerchWord = Word.substring(start,end);
        }
        return res;
    }

}
