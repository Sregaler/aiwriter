package com.hdumil.aiwriter.base.controller.baidu;

import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.util.CodeChangeUtils;
import com.hdumil.aiwriter.base.util.HttpUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@CrossOrigin
@Controller
@Data
@RequestMapping("/baidu/search")
public class SearchController {
    @Value("${baiduAPI.SearchAuthorization}")
    private String SearchAuthorization;

    @RequestMapping("/correlation_list")
    @ResponseBody
    public ResultVo correlation_list(String query,
                                     String end_date,
                                     String start_date){
        ResultVo resultVo = new ResultVo();
        String url_path = "http://106.12.146.251:8152/correlation/correlation_list/";
        HashMap<String, String> map = new HashMap<>();
        if(query!=null) map.put("query",query);
        if(end_date!=null) map.put("end_date",end_date);
        if(start_date!=null) map.put("start_date",start_date);
        if(map.isEmpty()){
            resultVo.setMess("参数为空");
            resultVo.setOk(false);
            return resultVo;
        }
        url_path = HttpUtil.appendUrl(url_path,map);
        System.out.println(url_path);
        map.clear();
        map.put("Authorization", SearchAuthorization);
        map.put("Content-Type","application/json");
        String centent = HttpUtil.httpGetTrust(url_path, map);
        centent = CodeChangeUtils.decodeUnicode(centent);
        resultVo.setMess("主题关联");
        resultVo.setOk(true);
        resultVo.setT(centent);
        return resultVo;
    }

    @RequestMapping("/search_topic_info_unique")
    @ResponseBody
    public ResultVo search_topic_info_unique(String topic_name,
                                             String start_time,
                                             String end_time,
                                             String id){
        String url_path = "http://106.12.146.251:8152/topicgraph_search/search_topic_info_unique";
        ResultVo resultVo = new ResultVo();
        HashMap<String, String> map = new HashMap<>();
        if(topic_name!=null) map.put("topic_name",topic_name);
        if(start_time!=null) map.put("start_time",start_time);
        if(end_time!=null) map.put("end_time",end_time);
        if(id!=null) map.put("id",id);
        if(map.isEmpty()){
            resultVo.setMess("参数为空");
            resultVo.setOk(false);
            return resultVo;
        }
        url_path = HttpUtil.appendUrl(url_path, map);
        map.clear();
        map.put("Authorization", SearchAuthorization);
        map.put("Content-Type","application/json");
        String centent = HttpUtil.httpGetTrust(url_path,map);
        centent = CodeChangeUtils.decodeUnicode(centent);
        resultVo.setMess("模糊检索主题以及相关信息");
        resultVo.setOk(true);
        resultVo.setT(centent);
        return resultVo;
    }

    @Data
    static class RelatedNews {
        String query;
        String summary;
        String end_date;
        String start_date;
        String domain;
        String site;
        String num;
        String url;
        String need_deadlink_detect;
        String need_content;
        String need_html_content;
        String sort;
        String search_dup;

        public String getParam(){
            HashMap<String, String> map = new HashMap<>();
            if(query!=null) map.put("query",query);
            if(end_date!=null) map.put("end_date",end_date);
            if(start_date!=null) map.put("start_date",start_date);
            if(domain!=null) map.put("start_date",domain);
            if(site!=null) map.put("start_date",site);
            if(num!=null) map.put("start_date",num);
            if(url!=null) map.put("start_date",url);
            if(need_deadlink_detect!=null) map.put("start_date",need_deadlink_detect);
            if(need_content!=null) map.put("start_date",need_content);
            if(need_html_content!=null) map.put("start_date",need_html_content);
            if(sort!=null) map.put("start_date",sort);
            if(search_dup!=null) map.put("start_date",search_dup);
            return HttpUtil.appendUrl("",map);
        }
    }

    @RequestMapping("/related_news_data")
    @ResponseBody
    public ResultVo related_news_data(RelatedNews relatedNews){
        String url_path = "http://106.12.146.251:8152/related_news/data/" + relatedNews.getParam();
        ResultVo resultVo = new ResultVo();
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", SearchAuthorization);
        map.put("Content-Type","application/json");
        String centent = HttpUtil.httpGetTrust(url_path, map);
        centent = CodeChangeUtils.decodeUnicode(centent);
        resultVo.setMess("资讯检索");
        resultVo.setOk(true);
        resultVo.setT(centent);
        return resultVo;
    }

    @RequestMapping("/video_search")
    @ResponseBody
    public ResultVo video_search(String query,
                                 String end_date,
                                 String start_date,
                                 String num,
                                 String category,
                                 String check_sim){
        String url_path = "http://106.12.146.251:8152/video_search/video_search/";
        ResultVo resultVo = new ResultVo();
        HashMap<String, String> map = new HashMap<>();
        if(query!=null) map.put("query",query);
        if(end_date!=null) map.put("end_date",end_date);
        if(start_date!=null) map.put("start_date",start_date);
        if(num!=null) map.put("num",num);
        if(category!=null) map.put("category",category);
        if(check_sim!=null) map.put("check_sim",check_sim);
        if(map.isEmpty()){
            resultVo.setMess("参数为空");
            resultVo.setOk(false);
            return resultVo;
        }
        url_path = HttpUtil.appendUrl(url_path, map);
        map.clear();
        map.put("Authorization", SearchAuthorization);
        map.put("Content-Type","application/json");
        String centent = HttpUtil.httpGetTrust(url_path,map);
        centent = CodeChangeUtils.decodeUnicode(centent);
        resultVo.setMess("相关视频");
        resultVo.setOk(true);
        resultVo.setT(centent);
        return resultVo;
    }
}
