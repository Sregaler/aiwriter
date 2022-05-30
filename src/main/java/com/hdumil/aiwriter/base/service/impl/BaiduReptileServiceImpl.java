package com.hdumil.aiwriter.base.service.impl;

import com.hdumil.aiwriter.base.service.BaiduReptileService;
import com.hdumil.aiwriter.base.util.HttpUtil;
import lombok.Data;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Data
//@ConfigurationProperties(prefix = "BaiduReptile")
public class BaiduReptileServiceImpl implements BaiduReptileService {

//    private String BaiduReptileCookie;
    private Random random1 = new Random();
    @Override
    public List<Map<String, String>> imageReptile(String Word, int count_max, boolean random) {
        Word = Jsoup.parse(Word).text();  // 去除html标签
        String SerchWord = Word;
        if(random){
            SerchWord = randomSubString(Word);
        }
        List<Map<String, String>> res = new ArrayList<>();
        int img_count = 0;
        for (int i = 0; i < 6; i++) {
//            String url = "https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111110&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&pn=1&word=" + SerchWord;
            String url = "https://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&dyTabStr=MCwzLDIsMSw3LDgsNCw2LDUsOQ%3D%3D&word=" + SerchWord;
//            System.out.println(url);
            String xmlSource = HttpUtil.httpGetByJsoup(url,null);
//            xmlSource = StringEscapeUtils.unescapeHtml3(xmlSource);
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
            SerchWord = randomSubString(Word);
        }
        return res;
    }

    public String randomSubString(String mainString){
        int start = 0;
        if(random1.nextInt(2)==0)
            start = random1.nextInt(mainString.length()/2);
        int end = random1.nextInt(mainString.length() - start) + start + 1;
        String SubWord = mainString.substring(start,end);
        return SubWord;
    }

}
