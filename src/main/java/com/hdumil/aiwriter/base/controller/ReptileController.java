package com.hdumil.aiwriter.base.controller;

import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.controller.baidu.NLPController;
import com.hdumil.aiwriter.base.service.impl.BaiduReptileServiceImpl;
import com.hdumil.aiwriter.base.service.impl.NationalEmergencyReptileServiceImpl;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/reptile")
@Data
public class ReptileController {

    @Autowired
    private BaiduReptileServiceImpl baiduReptile;

    @Autowired
    private NLPController nlpController;
//    @Autowired
//    private NLPController nlpController;

    @Autowired
    private NationalEmergencyReptileServiceImpl nationalEmergencyReptile;

    @RequestMapping("/baidu_image")
    @ResponseBody
    public ResultVo baiduImageByKeyWord(String keyWord, Integer num, Integer random) {
        boolean random_ = false;
        if(num==null) num = 10;
        if(random!=null&&random==1) random_ = true;
        ResultVo resultVo = new ResultVo();
        List<Map<String, String>> res = baiduReptile.imageReptile(keyWord,num,random_);
//        List<Map<String, String>> res = baiduReptile.imageReptile(keyWordByNLP(keyWord), num, random_);
        if (res.isEmpty())
        {
            resultVo.setOk(false);
            resultVo.setMess("获取失败");
        }
        else {
            resultVo.setT(res);
            resultVo.setOk(true);
            resultVo.setMess("获取成功");
        }
        return resultVo;
    }

    public String keyWordByNLP(String text){
        ResultVo resultVo = nlpController.keyword("",text);
        StringBuffer res = new StringBuffer();
        try {
            JSONObject jsonObject = new JSONObject((String) resultVo.getT());
            JSONArray keyWords = jsonObject.getJSONArray("items");
            for (int i = 0; i < keyWords.length(); i++) {
                res.append(keyWords.getJSONObject(i).get("tag")).append(" ");
            }
        } catch (JSONException ignored) {
        }
        return res.toString();
    }


//    @RequestMapping("/baidu_image_text")
//    @ResponseBody
//    public ResultVo baiduImageByText(String test, Integer num) {
//        ResultVo resultVo = new ResultVo();
//
////        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
////        NLPController nlpController = (NLPController) wac.getBean("nlpController");
//        StringBuffer keyWords = new StringBuffer();
//        try {
//            JSONObject jsonObject = new JSONObject((String) nlpController.keyword("",test).getT());
//            JSONArray jsonArray = jsonObject.getJSONArray("items");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject output = jsonArray.getJSONObject(i);
//                keyWords.append(output.getString("tag")).append(" ");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            resultVo.setOk(false);
//            resultVo.setMess("关键词调用失败。");
//            return resultVo;
//        }
//        List<Map<String, String>> res = baiduReptile.imageReptile(keyWords.toString(),num);
//        if (res.isEmpty())
//        {
//            resultVo.setOk(false);
//            resultVo.setMess("获取失败");
//        }
//        else {
//            resultVo.setT(res);
//            resultVo.setOk(true);
//            resultVo.setMess("获取成功");
//        }
//        return resultVo;
//    }

    @RequestMapping("/emergency_news/{context}/{page}")
    @ResponseBody
    public ResultVo emergencyNews(@PathVariable String context, @PathVariable Integer page) {
        ResultVo resultVo = new ResultVo();
        Map<String, Object> res = nationalEmergencyReptile.news_xw(context.trim(),page);
        if (((List<Map<String, String>>)res.get("news_list")).isEmpty())
        {
            resultVo.setOk(false);
            resultVo.setMess("获取失败");
        }
        else {
            resultVo.setT(res);
            resultVo.setOk(true);
            resultVo.setMess("获取成功");
        }
        return resultVo;
    }

    @RequestMapping("/emergency_news_details")
    @ResponseBody
    public ResultVo emergencyNewsDetials(String url) {
        ResultVo resultVo = new ResultVo();
        Map<String, Object> res = nationalEmergencyReptile.analysisHtmlContent(url);
        if (res.get("news_content").equals(""))
        {
            resultVo.setOk(false);
            resultVo.setMess("获取失败");
        }
        else {
            resultVo.setT(res);
            resultVo.setOk(true);
            resultVo.setMess("获取成功");
        }
        return resultVo;
    }
}
