package com.hdumil.aiwriter.base.controller.baidu;
import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.util.*;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@Data
@ConfigurationProperties(prefix = "baiduAPI.Review")
@RequestMapping("/baidu/review")
public class ReviewController {
    //设置APPID/AK/SK
    private String appId;
    private String apiKey;
    private String secretKey;
    private String accessToken;
    private String token_date;

    public void setAccessToken(){
        if(accessToken==null||DateTimeUtil.testBetweenDays(token_date, DateTimeUtil.getSysTime())>26){
            accessToken = AuthService.getAuth(apiKey,secretKey);
            token_date = DateTimeUtil.getSysTime();
        }
    }
    @RequestMapping("/ai_review")
    @ResponseBody
    public ResultVo ai_review(String[] data){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/creation/v1/revise";
        Map<String, Object> map = new HashMap<>();
        map.put("action", "text_correction");
        map.put("user_id", "text_correction");
        map.put("system", "windows");
        map.put("web_version", "v1.0");
        map.put("source", "web");
        map.put("type", 1);
        List<Map<String, String>> req = new ArrayList<>();
        for(int i=0;i<data.length;i++){
            Map<String, String> temp = new HashMap<>();
            temp.put("content",data[i]);
            req.add(temp);
        }
        map.put("data", req);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("文本智能校审", url, accessToken, map,null);
        ResultVo resultVo = new ResultVo();
        resultVo.setOk(true);
        resultVo.setMess("文本智能校审");
        resultVo.setT("{\"msg\":\"success\",\"code\":0,\"output\":[" +
                "{" +
                "\"content\":\"百度是一家人工只能公司，360是一家人工智能工司，腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工只能公司，腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司\",\"has_error\":true," +
                "" +
                "\"details\":[{\"sentence_id\":0,\"sentence\":\"百度是一家人工只能公司，\",\"sentence_fixed\":\"百度是一家人工智能公司，\"," +
                "\"fragments\":[{\"frag_ori\":\"人工只能\",\"frag_fixed\":\"人工智能\",\"offset\":15,\"cont_offset\":15,\"length\":12,\"score\":0.0,\"score_delta\":0.0,\"type\":8,\"operation\":2,\"explain\":\"建议用“人工智能”替换“人工只能”\",\"state\":4,\"label\":\"010200\"}]}," +
                "" +
                "{\"sentence_id\":1,\"sentence\":\"360是一家人工智能工司，\",\"sentence_fixed\":\"360是一家人工智能公司，\",\"fragments\":[{\"frag_ori\":\"工司\",\"frag_fixed\":\"公司\",\"offset\":24,\"cont_offset\":60,\"length\":6,\"score\":4.411464691162109,\"score_delta\":4.326484680175781,\"type\":3,\"operation\":2,\"explain\":\"建议用“公司”替换“工司”\",\"state\":2,\"label\":\"010200\"}]},{\"sentence_id\":2,\"sentence\":\"腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工只能公司，\",\"sentence_fixed\":\"腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司，\",\"fragments\":[{\"frag_ori\":\"人工只能\",\"frag_fixed\":\"人工智能\",\"offset\":81,\"cont_offset\":150,\"length\":12,\"score\":0.0,\"score_delta\":0.0,\"type\":8,\"operation\":2,\"explain\":\"建议用“人工智能”替换“人工只能”\",\"state\":4,\"label\":\"010200\"}]}]}," +
                "" +
                "{\"content\":\"谷歌是一家人工智能公司\",\"content_len\":33,\"content_fixed\":\"谷歌是一家人工智能公司\",\"has_error\":false}]," +
                "" +
                "\"user_id\":\"text_correction\",\"error_num\":3" +
                "" +
                "}");
        try {
            JSONArray resJson = new JSONArray();
            JSONObject jsonObject = new JSONObject((String) resultVo.getT());
            if(jsonObject.getInt("code")!=0) return resultVo;
            JSONArray jsonArray = jsonObject.getJSONArray("output");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject output = jsonArray.getJSONObject(i);
                if(output.getBoolean("has_error")){
//                    resJson.put(data1.getJSONArray("fragments"));
                    JSONArray details = output.getJSONArray("details");
                    for (int j = 0; j < details.length(); j++) {
                        JSONArray fragments = details.getJSONObject(j).getJSONArray("fragments");
                        for (int k = 0; k < fragments.length(); k++) {
                            resJson.put(fragments.getJSONObject(k));
                        }
                    }
                }
            }
            resultVo.setT("{\"code\":0,\"fragments\":"+resJson.toString()+"}");
        } catch (Exception ex) {
            ex.printStackTrace();
            resultVo.setMess("API的JSON解析出错。");
        }
        return resultVo;
    }

    @RequestMapping("/img_censor")
    @ResponseBody
    public ResultVo img_censor(String filePath, String imgType){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined";
        ResultVo resultVo = new ResultVo();
        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam + "&imgType=" + imgType;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            try{
                resultVo.setMess(jsonObject.getString("error_msg"));
                resultVo.setOk(false);
            }catch(Exception e) {
                resultVo.setMess("图像审核");
                resultVo.setOk(true);
                resultVo.setT(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setMess("获取失败。");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @RequestMapping("/text_censor")
    @ResponseBody
    public ResultVo text_censor(String text){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined";
        ResultVo resultVo = new ResultVo();
        try {
            String param = "text=" + text;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            try{
                resultVo.setMess(jsonObject.getString("error_msg"));
                resultVo.setOk(false);
            }catch(Exception e) {
                resultVo.setMess("文本审核");
                resultVo.setOk(true);
                resultVo.setT(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setMess("获取失败。");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @RequestMapping("/video_censor")
    @ResponseBody
    public ResultVo video_censor(String name, String extId, String videoUrl){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/video_censor/v2/user_defined";
        ResultVo resultVo = new ResultVo();
        try {
            String param = "name=" + name + "&extId=" + extId + "&videoUrl=" + videoUrl;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            try{
                resultVo.setMess(jsonObject.getString("error_msg"));
                resultVo.setOk(false);
            }catch(Exception e) {
                resultVo.setMess("短视频审核");
                resultVo.setOk(true);
                resultVo.setT(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setMess("获取失败。");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @RequestMapping("/voice_censor")
    @ResponseBody
    public ResultVo voice_censor(String filePath, String fmt){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/voice_censor/v3/user_defined";
        ResultVo resultVo = new ResultVo();
        try {
            // 本地文件路径
            byte[] data = FileUtil.readFileByBytes(filePath);
            String str = Base64Util.encode(data);
            String voiceParam = URLEncoder.encode(str, "UTF-8");
            String param = "base64=" + voiceParam + "&fmt=" + fmt;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            try{
                resultVo.setMess(jsonObject.getString("error_msg"));
                resultVo.setOk(false);
            }catch(Exception e) {
                resultVo.setMess("短语音审核");
                resultVo.setOk(true);
                resultVo.setT(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setMess("获取失败。");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @RequestMapping("/text_score")
    @ResponseBody
    public ResultVo text_score(@RequestBody Article article){
//        setAccessToken();
//        String url = "";
        ResultVo resultVo = new ResultVo();
        String title = article.getTitle();
        List<String> paragraphs = new ArrayList<>();
        Document document = Jsoup.parse(article.getContent());
        Elements pItems = document.getElementsByTag("p");
        for (Element pItem : pItems) {
            String temp = pItem.text().trim();
            if(!temp.equals(""))
                paragraphs.add(temp);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("paragraphs", paragraphs);
        String param = GsonUtils.toJson(map);
        System.out.println(param);

        return resultVo;
    }

}
