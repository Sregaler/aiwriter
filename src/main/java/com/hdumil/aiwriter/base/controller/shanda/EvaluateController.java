package com.hdumil.aiwriter.base.controller.shanda;

import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.controller.baidu.AuthService;
import com.hdumil.aiwriter.base.util.FileUtil;
import com.hdumil.aiwriter.base.util.HttpUtil;
import lombok.Data;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@Controller
@Data
@RequestMapping("/shanda/evaluate")
public class EvaluateController {

    class ParagraphNode {
        public String type;
        public String content;
        public ParagraphNode(){}
        public ParagraphNode(String _type, String _content){
            this.type = _type;
            this.content = _content;
        }
    }

    @RequestMapping("/news_score")
    @ResponseBody
    public ResultVo news_score(String title,
                               String content){
        String url = "http://4472zn3236.zicp.vip/test_text";
        Map<String, Object> map = new HashMap<>();
        List<String> paragraphs = new ArrayList<>();
        Document document = Jsoup.parse(content);
        Elements pItems = document.getElementsByTag("p");
        if(pItems.size()>0){
            for (Element pItem : pItems) {
                if(!pItem.text().equals(""))
                    paragraphs.add(pItem.text());
            }
        }
        else {
            paragraphs.add(document.text());
        }
        map.put("title", title);
        map.put("paragraphs", paragraphs);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("稿件评分", url, "null", map,null);
        try {
            JSONObject jsonObject = new JSONObject((String) resultVo.getT());
            if(!jsonObject.getString("success").equals("true")){
                resultVo.setOk(false);
            }
            else {
                resultVo.setT(jsonObject.getString("message"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultVo;
    }

    @RequestMapping("/news_score_v2")
    @ResponseBody
    public ResultVo news_score_v2(String title,
                               String content,
                               HttpSession session){
        String url = "http://4472zn3236.zicp.vip/evaluate_news";
        Map<String, Object> map = new HashMap<>();
        List<ParagraphNode> paragraphs = new ArrayList<>();
        Document document = Jsoup.parse(content);
//        Elements pItems = document.getElementsByTag("p");
        Elements pItems = document.getAllElements();
        if(pItems.size()>0){
            for (Element pItem : pItems) {
                if(pItem.tagName().equals("p"))
                    if(!pItem.text().equals(""))
                        paragraphs.add(new ParagraphNode("0",pItem.text()));
                else{
                    ParagraphNode paragraphNode = new ParagraphNode();
                    switch (pItem.tagName()) {
                        case "img":
                            paragraphNode.type = "1";
                            break;
                        case "video":
                            paragraphNode.type = "2";
                            break;
                        case "audio":
                            paragraphNode.type = "3";
                            break;
                        default:
                            continue;
                    }
                    try {
                        byte[] fileBytes;
                        String file_url = pItem.attr("src");
                        // 需要判断图片的来源
                        if(file_url.substring(0,4).equals("http")){
                            fileBytes = HttpUtil.getFileBytes(file_url);
                        }
                        else {
                            fileBytes = FileUtil.readFileByBytes(session.getServletContext().getRealPath(file_url));
                        }
                        paragraphNode.content = Base64.getEncoder().encodeToString(fileBytes);
                    } catch (IOException ex) {
                        paragraphNode.content = "";
                    }
                    paragraphs.add(paragraphNode);
                }
            }
        }
        else {
            paragraphs.add(new ParagraphNode("0",document.text()));
        }
        map.put("title", title);
        map.put("paragraphs", paragraphs);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("稿件评分", url, "null", map,null);
        try {
            JSONObject jsonObject = new JSONObject((String) resultVo.getT());
            if(!jsonObject.getString("success").equals("true")){
                resultVo.setOk(false);
            }
            else {
                resultVo.setT(jsonObject.getString("message"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultVo;
    }
}
