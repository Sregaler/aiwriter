package com.hdumil.aiwriter.base.controller.baidu;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.util.CodeChangeUtils;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import com.hdumil.aiwriter.base.util.GsonUtils;
import com.hdumil.aiwriter.base.util.HttpUtil;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@Data
@ConfigurationProperties(prefix = "baiduAPI.Customized")
@RequestMapping("/baidu/customized")
public class CustomizedController {
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

    @RequestMapping("/weather")
    @ResponseBody
    public ResultVo weather(String article_type, String province, String city, String county, String start_date, String end_date){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/nlp/v1/gen_article/weather";
        Map<String, Object> map = new HashMap<>();
        map.put("article_type", article_type);
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("天气文章-定制版", url, accessToken, map,null);
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("天气文章-定制版");
        resultVo.setOk(true);
        resultVo.setT("{\"status\": 0," +
                "\"source\": \"中国天气网\"," +
                "\"error_message\": \"success\"" +
                ",\"content\":{\"num\": 1,\"article_type\": \"day\",\"public_time\": \"2021-10-11 08:17:37\","+
                "\"list\": [" +
                "{" +
                "\"source\": \"http://www.weather.com.cn/weather1d/101280604.shtml\",\"title\": \"「深圳南山」10/11，晴，23~30℃，无持续风向3~4级，空气质量优\", " +
                "\"content\": [" +
                "{\"text\": \"深圳南山，今日晴，白天最高气温30℃，夜间最低温度23℃，无持续风向3~4级，空气质量优。\"}," +
                "{\"image\":\"/upload/temp_public\\\\2022-03-01\\\\16461004774841.jpg\"}," +
                "{\"image\":\"/upload/temp_public\\\\2022-03-01\\\\16461004775032.jpg\"}" +
                "]," +
                "\"tag\": {" +
                "\"region\": [\"珠三角\"]," +
                "\"location\": [{\"province\": \"广东省\",\"city\": \"深圳市\",\"county\": \"南山区\"}]" +
                "}," +
                "\"date\": \"2021-10-11\"" +
                "}" +
                "]"+
                "}}");
        return resultVo;
    }

    @RequestMapping("/weather_warning")
    @ResponseBody
    public ResultVo weather_warning(String article_type, String province, String city, String county,String start_date,String end_date,String event_type){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/nlp/v1/gen_article/weather_warning";
        Map<String, Object> map = new HashMap<>();
        map.put("article_type", article_type);
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        map.put("event_type", event_type);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("天气预警文章-定制版", url, accessToken, map,"UTF-8");
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("天气预警文章-定制版");
        resultVo.setOk(true);
        resultVo.setT("{" +
                "    \"status\": 0," +
                "    \"source\": \"国家预警中心\"," +
                "    \"error_message\": \"success\"," +
                "    \"content\": {" +
                "        \"msg_type_statis\": {" +
                "            \"Cancel\": 1," +
                "            \"Alert\": 1" +
                "        }," +
                "        \"num\": 2," +
                "        \"list\": [" +
                "            {" +
                "                \"title\": \"【雷雨大风预警】8月30日珠海市气象台发布雷雨大风黄色预警\"," +
                "                \"content\": [" +
                "                    {" +
                "                        \"text\": \"珠海市气象台于08月30日12时55分发布珠海市雷雨大风黄色预警信号：\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"请注意防御。\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"（信息来源：国家预警发布中心）\"" +
                "                    }," +
                "                    {" +
                "                        \"image\": \"/upload/temp_public\\\\2022-03-01\\\\16461005686764.jpg\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"<hr />\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"<h3>防御指南</h3>\"" +
                "                    }," +
                "                    {" +
                "                        \"image\": \"/upload/temp_public\\\\2022-03-01\\\\16461004775043.jpg\"" +
                "                    }" +
                "                ]," +
                "                \"source\": \"http://www.12379.cn/html/new2018/alarmcontent.shtml?file=44040041600000_20210830130023.html\"," +
                "                \"public_time\": \"2021-08-30 13:06:10\"," +
                "                \"tag\": {" +
                "                    \"sender\": \"珠海市气象台\"," +
                "                    \"msg_type\": \"Alert\"," +
                "                    \"effective\": \"2021-08-30 12:55:00\"," +
                "                    \"level\": \"Yellow\"," +
                "                    \"region\": [" +
                "                        \"珠三角\"" +
                "                    ]," +
                "                    \"location\": [" +
                "                        {" +
                "                            \"province\": \"广东省\"," +
                "                            \"city\": \"珠海市\"," +
                "                            \"county\": \"城区\"" +
                "                        }" +
                "                    ]," +
                "                    \"event_type\": \"雷雨大风\"" +
                "                }," +
                "                \"date\": \"2021-08-30\"" +
                "            }," +
                "            {" +
                "                \"title\": \"【雷雨大风预警信息解除】8月30日珠海市气象台解除雷雨大风黄色预警\"," +
                "                \"content\": [" +
                "                    {" +
                "                        \"text\": \"<b>【预警解除】</b>珠海市气象台08月30日15时13分解除珠海市雷雨大风黄色预警\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"（信息来源：国家预警发布中心）\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"<hr />\"" +
                "                    }" +
                "                ]," +
                "                \"source\": \"http://www.12379.cn/html/new2018/alarmcontent.shtml?file=44040041600000_20210830151103.html\"," +
                "                \"public_time\": \"2021-08-30 15:18:22\"," +
                "                \"tag\": {" +
                "                    \"sender\": \"珠海市气象台\"," +
                "                    \"msg_type\": \"Cancel\"," +
                "                    \"effective\": \"2021-08-30 15:10:00\"," +
                "                    \"level\": \"Yellow\"," +
                "                    \"region\": [" +
                "                        \"珠三角\"" +
                "                    ]," +
                "                    \"location\": [" +
                "                        {" +
                "                            \"province\": \"广东省\"," +
                "                            \"city\": \"珠海市\"," +
                "                            \"county\": \"城区\"" +
                "                        }" +
                "                    ]," +
                "                    \"event_type\": \"雷雨大风\"" +
                "                }," +
                "                \"date\": \"2021-08-30\"" +
                "            }" +
                "        ]," +
                "        \"event_type_statis\": {" +
                "            \"雷雨大风\": 2" +
                "        }" +
                "    }" +
                "}");
        return resultVo;
    }

    @RequestMapping("/sports_events")
    @ResponseBody
    public ResultVo sports_events(String article_type, String match_name, String team_name, String start_date,String end_date){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/nlp/v1/gen_article/sports_events";
        Map<String, Object> map = new HashMap<>();
        map.put("article_type", article_type);
        map.put("match_name", match_name);
        map.put("team_name", team_name);
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("体育战报文章-定制版", url, accessToken, map,"UTF-8");
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("体育战报文章-定制版");
        resultVo.setOk(true);
        resultVo.setT("{" +
                "    \"status\": 0," +
                "    \"source\": \"百度体育\"," +
                "    \"error_message\": \"success\"," +
                "    \"content\": {" +
                "        \"num\": 2," +
                "        \"list\": [" +
                "            {" +
                "                \"content\": [" +
                "                    {" +
                "                        \"text\": \"北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"【双方表现】\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板\"" +
                "                    }" +
                "                ]," +
                "                \"match_name\": \"NBA\"," +
                "                \"public_time\": \"2021-10-11 08:20:03\"," +
                "                \"tag\": {" +
                "                    \"right_goal\": \"100\"," +
                "                    \"region\": []," +
                "                    \"left_goal\": \"101\"," +
                "                    \"left_team\": \"马刺\"," +
                "                    \"match_desc\": \"NBA季前赛\"," +
                "                    \"right_team\": \"魔术\"" +
                "                }," +
                "                \"source\": \"http://tiyu.baidu.com/live/detail/576O5Zu955S35a2Q6IGM5Lia56+u55CD6IGU6LWbI2Jhc2tldGJhbGwjMjAyMS0xMC0xMSPpqazliLp2c+mtlOacrw==\"," +
                "                \"title\": \"【NBA战报】泰伦斯·罗斯20分难救主 魔术客场憾负马刺\"," +
                "                \"date\": \"2021-10-11\"," +
                "                \"start_time\": {" +
                "                    \"day\": \"2021-10-11\"," +
                "                    \"time\": \"06:00:00\"" +
                "                }" +
                "            }," +
                "            {" +
                "                \"content\": [" +
                "                    {" +
                "                        \"text\": \"北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"【双方表现】\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板\"" +
                "                    }" +
                "                ]," +
                "                \"match_name\": \"NBA\"," +
                "                \"public_time\": \"2021-10-11 08:20:02\"," +
                "                \"tag\": {" +
                "                    \"right_goal\": \"100\"," +
                "                    \"region\": []," +
                "                    \"left_goal\": \"101\"," +
                "                    \"left_team\": \"马刺\"," +
                "                    \"match_desc\": \"NBA季前赛\"," +
                "                    \"right_team\": \"魔术\"" +
                "                }," +
                "                \"source\": \"http://tiyu.baidu.com/live/detail/576O5Zu955S35a2Q6IGM5Lia56+u55CD6IGU6LWbI2Jhc2tldGJhbGwjMjAyMS0xMC0xMSPpqazliLp2c+mtlOacrw==\"," +
                "                \"title\": \"【NBA战报】泰伦斯·罗斯20分难救主 魔术客场惜败马刺\"," +
                "                \"date\": \"2021-10-11\"," +
                "                \"start_time\": {" +
                "                    \"day\": \"2021-10-11\"," +
                "                    \"time\": \"06:00:00\"" +
                "                }" +
                "            }" +
                "        ]" +
                "    }" +
                "}");
        return resultVo;
    }

    @RequestMapping("/company_financial_report")
    @ResponseBody
    public ResultVo company_financial_report(String article_type, String stock_code_list, String start_date, String end_date){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/nlp/v1/gen_article/company_financial_report";
        Map<String, Object> map = new HashMap<>();
        map.put("article_type", article_type);
        map.put("stock_code_list", stock_code_list.split(","));
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        //调用接口获取信息
        String param = GsonUtils.toJson(map);
//        ResultVo resultVo = AuthService.jsonPostUrl("公司财报文章-定制版", url, accessToken, map,"UTF-8");
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("公司财报文章-定制版");
        resultVo.setOk(true);
        resultVo.setT("{" +
                "    \"status\": 0," +
                "    \"source\": \"证券交易所\"," +
                "    \"error_message\": \"success\"," +
                "    \"content\": {" +
                "        \"num\": 1," +
                "        \"list\": [" +
                "            {" +
                "                \"content\": [" +
                "                    {" +
                "                        \"text\": \"3月30日，广联达（002410）发布年度公告，公司在2020年（全年）实现营业收入39.47亿元，同比增长13.94%，资产总额同比增加54.80%达到95.47亿元。\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"归属于上市公司股东的净利润为3.30亿元，同比增长40.55%。\"" +
                "                    }," +
                "                    {" +
                "                        \"text\": \"根据该财报，截止目前每股收益为0.2867元，同时，归属股东的净资产为64.02亿元，与上年度末相比增长95.83%。\"" +
                "                    }" +
                "                ]," +
                "                \"article_type\": \"年度\"," +
                "                \"public_time\": \"2021-03-30 03:50:02\"," +
                "                \"tag\": {" +
                "                    \"stock_code\": \"002410\"," +
                "                    \"region\": []," +
                "                    \"report_name\": \"广联达：2020年年度报告\"," +
                "                    \"article_type\": \"年度\"," +
                "                    \"company_name\": \"广联达\"," +
                "                    \"year\": \"2020\"" +
                "                }," +
                "                \"stock_code\": \"002410\"," +
                "                \"source\": \"http://disc.static.szse.cn/download//disc/disk02/finalpage/2021-03-30/3df3e25e-e48d-4180-b75c-393ea2d49463.PDF\"," +
                "                \"title\": \"「广联达」今日发布2020年年度财报\"," +
                "                \"date\": \"2021-03-30\"" +
                "            }" +
                "        ]" +
                "    }" +
                "}");
        return resultVo;
    }

    @RequestMapping("/shares")
    @ResponseBody
    public ResultVo shares(String article_type, String stock_code_list, String start_date, String end_date){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/nlp/v1/gen_article/shares";
        Map<String, Object> map = new HashMap<>();
        map.put("article_type", article_type);
        map.put("stock_code_list", stock_code_list.split(","));
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("股票收盘文章-定制版", url, accessToken, map,"UTF-8");
//        resultVo.setT(CodeChangeUtils.decodeUnicode((String) resultVo.getT()));
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("股票收盘文章-定制版");
        resultVo.setOk(true);
        resultVo.setT("{\"status\": 0, \"source\": \"百度\", \"error_message\": \"success\", \"content\": {\"num\": 9, \"list\": [{\"stock_code\": \"600036\", \"title\": \"收市简讯：「招商银行」今日股价下跌0.49%，报收42.89元\", \"content\": [{\"text\": \"北京时间12月21日，招商银行开盘报价43.04元，收盘于42.89元，下跌0.49%。当日最高价为43.15元，最低达42.31元，成交量62.39万手，总市值为10816.79亿元。\"}, {\"text\": \"最近两个交易日，招商银行连续下跌，跌幅分别为-2.16%和-0.49%，最高价降到42.89元，总市值缩减了292.55亿元。\"}, {\"text\": \"在近30个交易日中，招商银行有14天上涨。期间整体上涨了1.52%，最高价为46.80元，最低价为42.58元。和30个交易日前相比，招商银行的市值下跌了209.33亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的融资融券板块共有385支个股，本交易日整体上涨0.95%，板块中领涨的个股是华银电力(涨幅10.17%)、古越龙山(涨幅10.04%)、金枫酒业(涨幅10.04%)，跌幅较大的个股是*ST金钰(跌幅5.26%)、美邦服饰(跌幅4.87%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨0.76%，报收于3420.57点。\"}], \"source\": \"\", \"public_time\": \"2020-12-21 17:10:38\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [], \"board\": \"主板\"}, \"date\": \"2020-12-21\"}, {\"stock_code\": \"600036\", \"title\": \"「招商银行」简讯：今日股价下跌2.29%，报收41.91元\", \"content\": [{\"text\": \"北京时间12月22日，招商银行开盘报价42.99元，收盘于41.91元，下跌2.29%。当日最高价为43.00元，最低达41.87元，成交量78.47万手，总市值为10569.64亿元。\"}, {\"text\": \"最近两个交易日，招商银行连续下跌，跌幅分别为-0.49%和-2.28%，最高价降到41.91元，总市值缩减了300.11亿元。\"}, {\"text\": \"在近5个交易日中，招商银行表现较差，近2天连续下跌了2.77%。期间整体下跌了0.00%，最高价为44.06元，最低价为41.91元，总成交量3.04亿。\"}, {\"text\": \"在近30个交易日中，招商银行有13天上涨。期间整体上涨了1.51%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了615.36亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的央企50板块共有48支个股，本交易日整体下跌2.59%，板块中领涨的个股是保变电气(涨幅6.81%)、中国西电(涨幅3.49%)、三友化工(涨幅0.82%)，跌幅较大的个股是上海能源(跌幅6.52%)、中远海控(跌幅5.89%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数下跌1.87%，报收于3356.78点。\"}], \"source\": \"http://stocks.sina.cn/sh/index?vt=4&code=sh600036\", \"public_time\": \"2020-12-22 15:55:25\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-22\"}, {\"stock_code\": \"600036\", \"title\": \"「招商银行」简讯：今日股价微涨0.98%，报收42.32元\", \"content\": [{\"text\": \"北京时间12月23日，招商银行开盘报价41.71元，收盘于42.32元，上涨0.98%。当日最高价为42.41元，最低达41.71元，成交量7118.20万，总市值为10673.04亿元。\"}, {\"text\": \"最近两个交易日，招商银行从跌到涨，涨跌幅分别为-2.28%和0.98%，当前最高价为42.32元，总市值缩减了143.75亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有1天上涨，近2天涨幅波动不定。期间整体下跌了0.98%，最高价为44.05元，最低价为41.91元，总成交量2.68亿。\"}, {\"text\": \"在近30个交易日中，招商银行有13天上涨。期间整体上涨了1.48%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了317.77亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的融资融券板块共有387支个股，本交易日整体上涨1.02%，板块中领涨的个股是昊华能源(涨幅10.09%)、南山铝业(涨幅10.04%)、金杯汽车(涨幅10.04%)，跌幅较大的个股是鲁商发展(跌幅9.26%)、郑煤机(跌幅5.75%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨0.76%，报收于3382.32点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-23 15:23:39\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"前海概念\", \"基金重仓\", \"深圳本地\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-23\"}, {\"stock_code\": \"600036\", \"title\": \"收市简讯：「招商银行」今日股价微涨0.59%，报收42.57元\", \"content\": [{\"text\": \"北京时间12月24日，招商银行开盘报价42.35元，收盘于42.57元，上涨0.59%。当日最高价为43.10元，最低达42.32元，成交量4821.10万，总市值为10736.09亿元。\"}, {\"text\": \"最近两个交易日，招商银行连续上涨，涨幅分别为0.98%和0.59%，最高价达到42.57元，总市值增长了166.45亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有2天上涨，近2天连续上涨了1.57%。期间整体下跌了0.78%，最高价为43.10元，最低价为41.91元，总成交量3.36亿。\"}, {\"text\": \"在近30个交易日中，招商银行有14天上涨。期间整体上涨了1.41%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了2.52亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的前海概念板块共有31支个股，本交易日整体下跌2.02%，板块中领涨的个股是南  玻Ａ(涨幅4.33%)、招商证券(涨幅1.25%)、招商银行(涨幅0.59%)，跌幅较大的个股是新宙邦(跌幅7.10%)、恒生电子(跌幅6.57%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数下跌0.57%，报收于3363.11点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-24 15:23:16\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"前海概念\", \"超大盘\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-24\"}, {\"stock_code\": \"600036\", \"title\": \"收市简讯：「招商银行」今日股价微涨0.35%，报收42.72元\", \"content\": [{\"text\": \"北京时间12月25日，招商银行开盘报价42.55元，收盘于42.72元，上涨0.35%。当日最高价为42.90元，最低达41.98元，成交量4767.32万，总市值为10773.92亿元。\"}, {\"text\": \"最近两个交易日，招商银行连续上涨，涨幅分别为0.59%和0.35%，最高价达到42.72元，总市值增长了100.88亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有3天上涨，近2天连续上涨了0.94%。期间整体下跌了0.40%，最高价为42.89元，最低价为41.91元，总成交量3.08亿。\"}, {\"text\": \"在近30个交易日中，招商银行有15天上涨。期间整体下跌了0.65%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了70.61亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的央企50板块共有48支个股，本交易日整体上涨1.59%，板块中领涨的个股是中远海控(涨幅9.96%)、中远海特(涨幅6.76%)、中远海发(涨幅5.26%)，跌幅较大的个股是中国太保(跌幅2.51%)、中国船舶(跌幅1.28%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨0.99%，报收于3396.56点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-25 18:02:42\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"前海概念\", \"超大盘\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-25\"}, {\"stock_code\": \"600036\", \"title\": \"收市简讯：「招商银行」今日股价微涨0.75%，报收43.04元\", \"content\": [{\"text\": \"北京时间12月28日，招商银行开盘报价42.75元，收盘于43.04元，上涨0.75%。当日最高价为43.32元，最低达42.23元，成交量8414.09万，总市值为10854.62亿元。\"}, {\"text\": \"最近两个交易日，招商银行连续上涨，涨幅分别为0.35%和0.75%，最高价达到43.04元，总市值增长了118.53亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有4天上涨，近2天连续上涨了1.10%。期间整体上涨了2.70%，最高价为43.04元，最低价为41.91元，总成交量3.30亿。\"}, {\"text\": \"在近30个交易日中，招商银行有15天上涨。期间整体下跌了1.82%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了201.76亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的前海概念板块共有31支个股，本交易日整体下跌1.88%，板块中领涨的个股是深华发Ａ(涨幅2.80%)、华侨城Ａ(涨幅1.19%)、万  科Ａ(涨幅1.14%)，跌幅较大的个股是泛海控股(跌幅10.08%)、爱施德(跌幅10.05%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨0.02%，报收于3397.29点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-28 15:23:24\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"前海概念\", \"超大盘\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-28\"}, {\"stock_code\": \"600036\", \"title\": \"收市简讯：「招商银行」今日股价阴跌0.49%，报收42.83元\", \"content\": [{\"text\": \"北京时间12月29日，招商银行开盘报价43.26元，收盘于42.83元，下跌0.49%。当日最高价为43.29元，最低达42.65元，成交量7271.48万，总市值为10801.66亿元。\"}, {\"text\": \"最近两个交易日，招商银行从涨至跌，涨跌幅分别为0.75%和-0.49%，当前最高价为42.83元，总市值增加了27.74亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有4天上涨，近2天涨幅波动不定。期间整体上涨了1.21%，最高价为43.04元，最低价为42.32元，总成交量3.24亿。\"}, {\"text\": \"在近30个交易日中，招商银行有14天上涨。期间整体下跌了4.29%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了484.22亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的央企50板块共有48支个股，本交易日整体下跌0.98%，板块中领涨的个股是光大证券(涨幅2.53%)、海油工程(涨幅1.62%)、航天电子(涨幅1.27%)，跌幅较大的个股是中国西电(跌幅5.53%)、保变电气(跌幅5.39%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数下跌0.54%，报收于3379.04点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-29 15:23:49\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"前海概念\", \"超大盘\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-29\"}, {\"stock_code\": \"600036\", \"title\": \"「招商银行」简讯：今日股价微涨0.51%，报收43.05元\", \"content\": [{\"text\": \"北京时间12月30日，招商银行开盘报价42.87元，收盘于43.05元，上涨0.51%。当日最高价为43.05元，最低达41.90元，成交量9334.96万，总市值为10857.14亿元。\"}, {\"text\": \"最近两个交易日，招商银行从跌到涨，涨跌幅分别为-0.49%和0.30%，当前最高价为42.96元，总市值缩减了20.17亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有4天上涨，近2天涨幅波动不定。期间整体上涨了0.92%，最高价为43.04元，最低价为42.57元，总成交量3.44亿。\"}, {\"text\": \"在近30个交易日中，招商银行有14天上涨。期间整体下跌了4.26%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了459.00亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的融资融券板块共有387支个股，本交易日整体上涨0.87%，板块中领涨的个股是大湖股份(涨幅10.04%)、方正证券(涨幅10.04%)、中闽能源(涨幅10.03%)，跌幅较大的个股是ST安泰(跌幅5.15%)、*ST永泰(跌幅5.15%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨1.05%，报收于3414.45点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-30 15:24:11\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"前海概念\", \"超大盘\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-30\"}, {\"stock_code\": \"600036\", \"title\": \"收市简讯：「招商银行」今日股价上涨2.09%，报收43.95元\", \"content\": [{\"text\": \"北京时间12月31日，招商银行开盘报价43.05元，收盘于43.95元，上涨2.09%。当日最高价为44.35元，最低达42.85元，成交量8911.99万，总市值为11084.12亿元。\"}, {\"text\": \"最近两个交易日，招商银行连续上涨，涨幅分别为0.51%和2.09%，最高价达到43.95元，总市值增长了282.46亿元。\"}, {\"text\": \"在近5个交易日中，招商银行有4天上涨，近2天连续上涨了2.60%。期间整体上涨了2.88%，最高价为43.95元，最低价为42.72元，总成交量3.87亿。\"}, {\"text\": \"在近30个交易日中，招商银行有14天上涨。期间整体下跌了2.44%，最高价为46.80元，最低价为41.91元。和30个交易日前相比，招商银行的市值下跌了277.42亿元。\"}, {\"text\": \"再来看下板块方面，招商银行所属的央企50板块共有48支个股，本交易日整体上涨1.48%，板块中领涨的个股是招商证券(涨幅9.99%)、三友化工(涨幅8.93%)、中国太保(涨幅6.02%)，跌幅较大的个股是国电电力(跌幅1.32%)、华能国际(跌幅1.10%)。\"}, {\"text\": \"招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨1.72%，报收于3473.07点。\"}], \"source\": \"http://m2.quote.eastmoney.com/h5stock/6000361.html?from=BaiduAladdin\", \"public_time\": \"2020-12-31 15:23:25\", \"tag\": {\"domain\": {\"lv1\": \"金融服务\", \"lv3\": \"银行Ⅲ\", \"lv2\": \"银行\"}, \"region\": [\"珠三角\"], \"stock_type\": \"沪深ab股\", \"location\": [{\"city\": \"深圳市\"}], \"block_list\": [\"融资融券\", \"央企50\", \"前海概念\", \"超大盘\", \"基金重仓\", \"深圳本地\", \"保险重仓\", \"货币金融服务\"], \"board\": \"主板\"}, \"date\": \"2020-12-31\"}]}}\n");
        return resultVo;
    }
}
