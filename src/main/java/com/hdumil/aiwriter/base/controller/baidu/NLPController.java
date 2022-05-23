package com.hdumil.aiwriter.base.controller.baidu;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@Data
@ConfigurationProperties(prefix = "baiduAPI.NLP")
@RequestMapping("/baidu/nlp")
public class NLPController {
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

    @RequestMapping("/news_summary")
    @ResponseBody
    public ResultVo news_summary(String title,
                                 String content,
                                 String max_summary_len){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/news_summary";
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("max_summary_len", max_summary_len);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("生成新闻摘要", url, accessToken, map,null);
        return resultVo;
    }

    @RequestMapping("/titlepredictor")
    @ResponseBody
    public ResultVo titlepredictor(String doc){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/titlepredictor";
        Map<String, Object> map = new HashMap<>();
        map.put("doc", doc);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("生成文章标题", url, accessToken, map,null);
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("生成文章标题");
        resultVo.setOk(true);
        resultVo.setT("{\"reference_titles\":[{\"title\":\"有哪些解酒的方法？\",\"score\":0.00299454875964},{\"title\":\"哪些方法可以快速解酒？\",\"score\":0.00297089506731},{\"title\":\"有哪些方法可以快速解酒？\",\"score\":0.00292691859261}],\"log_id\":1499331047647847119}");
        return resultVo;
    }

    @RequestMapping("/topic")
    @ResponseBody
    public ResultVo topic(String title, String content){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/topic";
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("文章分类", url, accessToken, map,null);
        return resultVo;
    }

    @RequestMapping("/keyword")
    @ResponseBody
    public ResultVo keyword(String title, String content){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/keyword";
        Map<String, Object> map = new HashMap<>();
        title = title.trim();
        content = content.trim();
        if (title.equals("")) title = content.substring(0,Math.min(80,content.length()));
        else if(title.length()>80) title = title.substring(0,80);
        if (content.equals("")) content = "无内容";
        map.put("title", title);
        map.put("content", content);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("文章标签", url, accessToken, map,null);
        return resultVo;
    }

    @RequestMapping("/ecnet")
    @ResponseBody
    public ResultVo ecnet(String text){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/ecnet";
        Map<String, Object> map = new HashMap<>();
        map.put("text", text);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("文本纠错", url, accessToken, map,null);
        try {
            JSONObject jsonObject = new JSONObject((String) resultVo.getT());
            JSONArray jsonArray = jsonObject.getJSONObject("item").getJSONArray("vec_fragment");
            resultVo.setT("{\"code\":0,\"fragments\":"+jsonArray.toString()+"}");
        } catch (Exception ex) {
            ex.printStackTrace();
            resultVo.setMess("API的JSON解析出错。");
        }
        return resultVo;
    }

    @RequestMapping("/simnet")
    @ResponseBody
    public ResultVo simnet(String text_1, String text_2){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v2/simnet";
        Map<String, Object> map = new HashMap<>();
        map.put("text_1", text_1);
        map.put("text_2", text_2);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("短文本相似度", url, accessToken, map,null);
        return resultVo;
    }
}
