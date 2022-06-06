package com.hdumil.aiwriter.base.controller.baidu;
import com.google.gson.JsonObject;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.controller.baidu.bean.Text2video1;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import com.hdumil.aiwriter.base.util.GsonUtils;
import com.hdumil.aiwriter.base.util.HttpUtil;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@Controller
@Data
@ConfigurationProperties(prefix = "baiduAPI.Retrieval")
@RequestMapping("/baidu/retrieval")
public class RetrievalController {
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

    @RequestMapping("/hot_list")
    @ResponseBody
    public ResultVo hot_list(String domain){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/creation/v1/hot_list/domain";
        Map<String, Object> map = new HashMap<>();
        map.put("domain", domain);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("热点发现", url, accessToken, map,null);

        ResultVo resultVo = new ResultVo();
        resultVo.setMess("热点发现");
        resultVo.setOk(true);
        resultVo.setT("{\"content\":[{\"pv\":48,\"ctime\":\"20220606 10:49:36\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734852217472057451&wfr=spider&for=pc\",\"content\":[\"新京报讯 据中央纪委国家监委驻自然资源部纪检监察组、内蒙古自治区包头市监察委员会消息：日前，中央纪委国家监委驻自然资源部纪检监察组、内蒙古自治区包头市监察委员会对国家林业和草原局原林产工业规划设计院党委副书记、院长周岩严重违纪违法问题进行了纪律审查和监察调查。\",\"经查，周岩丧失理想信念，背弃初心使命，对党不忠诚、不老实，销毁证据，转移违纪违法所得，处心积虑对抗组织审查，不信马列信鬼神，烧香拜佛进行迷信活动；无视中央八项规定精神，肆无忌惮收受可能影响公正执行公务的礼品、礼金、消费卡，接受可能影响公正执行公务的宴请，公款吃喝，用公款购买礼品赠送他人或个人使用，公车私用，在购买住房中侵犯国家利益；组织观念淡薄，瞒报个人有关事项，由他人代持多处房产车位，不如实申报，在组织函询时不如实说明问题，在毕业生招聘中利用职权为他人谋取利益，甚至泄露考题；违规从事营利活动，违规经商办企业，利用职权将本应由个人支付的费用由他人支付，大搞钱色交易，利用职务影响索要他人财物；生活奢靡享乐、追求低级趣味，与多名女性发生不正当性关系；大搞权钱交易，利用职权或职务便利，侵吞、套取公共资产，在房地产开发、工程款结算、干部任用、毕业生招聘、项目安排等方面为他人谋取利益并收受巨额财物，涉嫌贪污和受贿犯罪。\",\"周岩严重违反党的政治纪律、中央八项规定精神、组织纪律、廉洁纪律、生活纪律，构成严重职务违法并涉嫌贪污、受贿犯罪，其违纪违法时间长、次数多，涉案金额大，在党的十八大甚至十九大后仍不收敛不收手，性质严重，情节恶劣，应予严肃处理。依据《中国共产党纪律处分条例》《中华人民共和国监察法》《中华人民共和国公职人员政务处分法》等有关规定，经国家林业和草原局党组研究决定，给予周岩开除党籍处分；经中央纪委国家监委驻自然资源部纪检监察组研究决定，给予周岩开除公职处分；收缴其违纪违法所得；将其涉嫌犯罪问题移送检察机关依法审查起诉，所涉财物一并移送。\",\"编辑 孙琳智\"],\"publish_time\":\"20220606 10:53:00\"}],\"summary\":\"据中央纪委国家监委驻自然资源部纪检监察组、内蒙古自治区包头市监察委员会消息：日前，中央纪委国家监委驻自然资源部纪检监察组、内蒙古自治区包头市监察委员会对国家林业和草原局原林产工业规划设计院党委副书记、院长周岩严重违纪违法问题进行了纪律审查和监察调查。\",\"mtime\":\"20220606 14:02:59\",\"keywords\":[\"周岩\",\"双开\",\"林产工业\"],\"id\":\"44eee7e4402c6523bc5facbccc4e23c7\",\"name\":\"国家林草局原林产工业规划设计院院长周岩被双开\"},{\"pv\":29,\"ctime\":\"20220606 09:19:59\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734845799938180548&wfr=spider&for=pc\",\"content\":[\"中央纪委国家监委网站6月6日消息，据中央纪委国家监委驻海关总署纪检监察组、海南省纪委监委：江门海关原副巡视员王洪涉嫌严重违纪违法，目前正在接受中央纪委国家监委驻海关总署纪检监察组纪律审查和海南省三亚市监察委员会监察调查。\",\"王洪简历\",\"王洪，男，汉族，1962年6月出生，山东诸城人，大学学历，1988年6月加入中国共产党，1981年8月参加工作。\",\"1981年8月至1988年5月，江门海关人事科、调查科干部；\",\"1988年5月至1999年1月，江门海关调查科副科长、科长、副处长、处长；\",\"1999年1月至2006年11月，江门海关走私犯罪侦查分局局长、江门海关副关长兼走私犯罪侦查分局局长（正处级）；\",\"2006年11月至2007年9月，江门海关副关长兼缉私局局长（副厅局级）；\",\"2007年9月至2014年12月，海口海关副关长兼缉私局局长（副厅局级）；\",\"2014年12月至2019年3月，湛江海关副巡视员（副厅局级）；\",\"2019年3月至2019年5月，江门海关副巡视员（副厅局级）；\",\"2019年5月，提前退休。\"],\"publish_time\":\"20220606 09:09:00\"}],\"summary\":\"中央纪委国家监委网站6月6日消息，据中央纪委国家监委驻海关总署纪检监察组、海南省纪委监委：江门海关原副巡视员王洪涉嫌严重违纪违法，目前正在接受中央纪委国家监委驻海关总署纪检监察组纪律审查和海南省三亚市监察委员会监察调查。1981年8月至1988年5月，江门海关人事科、调查科干部；1988年5月至1999年1月，江门海关调查科副科长、科长、副处长、处长；\",\"mtime\":\"20220606 13:06:25\",\"keywords\":[\"王洪\",\"海关\",\"巡视员\"],\"id\":\"24b850a2d4f9a8af09c24bde97266d25\",\"name\":\"江门海关原副巡视员王洪接受纪律审查和监察调查\"},{\"pv\":28,\"ctime\":\"20220606 08:10:42\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734842120803465658&wfr=spider&for=pc\",\"content\":[\"【辽宁新增13例本土无症状感染者】财联社6月6日电，6月5日0-24时，辽宁省无新增新冠肺炎确诊病例；新增13例本土无症状感染者，均为丹东市报告。\"],\"publish_time\":\"20220606 08:12:00\"}],\"summary\":\"据辽宁卫健委网站，6月5日0-24时，辽宁省无新增新冠肺炎确诊病例；新增13例本土无症状感染者，均为丹东市报告。解除医学观察本土无症状感染者4例。截至6月5日24时，全省累计报告确诊病例1675例，治愈出院1671例，死亡2例，在院治疗2例。\",\"mtime\":\"20220606 12:03:13\",\"keywords\":[\"辽宁\",\"感染者\",\"丹东\"],\"id\":\"b8f0ef645752312871f13824f59be108\",\"name\":\"辽宁丹东新增13例本土无症状感染者\"},{\"pv\":28,\"ctime\":\"20220606 10:04:13\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734850077952104663&wfr=spider&for=pc\",\"content\":[\"6月6日上午，总台记者从有关部门获悉，山东省泰安市所辖新泰市泉沟镇羊泉矿业发生事故，有人员被困。（总台记者 张明）\",\"【来源：央视网_新闻频道】\"],\"publish_time\":\"20220606 10:19:00\"}],\"summary\":\"财联社6月6日电，记者6月6日上午从有关部门获悉，山东省泰安市所辖新泰市泉沟镇羊泉矿业发生事故，有人员被困。\",\"mtime\":\"20220606 12:03:13\",\"keywords\":[\"煤矿\",\"山东\",\"事故\"],\"id\":\"62050504608c8e9461bebdb7948ea027\",\"name\":\"山东一煤矿发生事故有人员被困\"},{\"pv\":23,\"ctime\":\"20220606 09:29:56\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734846753417191276&wfr=spider&for=pc\",\"content\":[\"郧西县气象台2022年06月06日09时19分发布高温橙色预警信号：\",\"预计今天白天我县大部乡镇最高气温将升至37℃以上，请注意防范。\",\"（信息来源：国家预警发布中心）\"],\"publish_time\":\"20220606 09:26:00\"}],\"summary\":\"郧西县气象台2022年06月06日09时19分发布高温橙色预警信号：预计今天白天我县大部乡镇最高气温将升至37℃以上，请注意防范。\",\"mtime\":\"20220606 13:06:25\",\"keywords\":[\"丹江口市\",\"气象台\",\"高温\"],\"id\":\"b9bc3f27d4248214dbb8f76685780652\",\"name\":\"丹江口市气象台发布高温橙色预警\"},{\"pv\":21,\"ctime\":\"20220606 09:42:24\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734854072111931171&wfr=spider&for=pc\",\"content\":[\"6月5日0—24时，内蒙古自治区报告新增本土确诊病例16例（均在锡林郭勒盟）。本土无症状感染者33例，其中呼和浩特市1例、锡林郭勒盟32例。\",\"截至6月5日24时，内蒙古自治区现有本土确诊病例41例，其中通辽市1例、锡林郭勒盟40例。本土无症状感染者49例，其中呼和浩特市3例、通辽市2例、锡林郭勒盟42例、乌兰察布市2例。均在定点医院隔离治疗，所有密切接触者均在指定场所集中隔离医学观察，严防疫情扩散蔓延。（皇利华 供稿）\",\"【来源：内蒙古卫健委】\"],\"publish_time\":\"20220606 11:22:00\"}],\"summary\":\"本土无症状感染者33例，其中呼和浩特市1例、锡林郭勒盟32例。截至6月5日24时，内蒙古自治区现有本土确诊病例41例，其中通辽市1例、锡林郭勒盟40例。均在定点医院隔离治疗，所有密切接触者均在指定场所集中隔离医学观察，严防疫情扩散蔓延。\",\"mtime\":\"20220606 13:06:25\",\"keywords\":[\"确诊病例\",\"本土\",\"内蒙古\"],\"id\":\"501c7ba405b84dac582d860864070c48\",\"name\":\"内蒙古昨日新增16例本土确诊病例\"},{\"pv\":21,\"ctime\":\"20220606 12:57:23\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734860049309052604&wfr=spider&for=pc\",\"content\":[\"据中国载人航天工程办公室消息，神舟十四号航天员顺利进入天舟四号。\",\"【来源：央视新闻】\"],\"publish_time\":\"20220606 12:56:00\"}],\"summary\":\"中国载人航天工程办公室消息，神舟十四号航天员顺利进入天舟四号。重要提示文章部分内容及图片来源于网络，我们尊重作者版权，若有疑问可与我们联系。\",\"mtime\":\"20220606 14:02:59\",\"keywords\":[\"十四\",\"航天员\",\"神舟\"],\"id\":\"cf05c33b6c10c9962ce1ffdbce4864a2\",\"name\":\"神舟十四号航天员顺利进入天舟四号\"},{\"pv\":17,\"ctime\":\"20220606 09:22:09\",\"related_urls\":[{\"url\":\"http://www.163.com/news/article/H95TO84200019K82.html\",\"content\":[\"（原标题：成都市新增3例本土新冠病毒无症状感染者，为省外返蓉闭环管理人员，在成都市无社会活动轨迹）\",\"成都市新冠肺炎疫情日常通报\",\"6月5日0-24时，我市无新增本土确诊病例，新增本土无症状感染者3例（为省外返蓉闭环管理人员）。无新增境外输入确诊病例，新增境外输入无症状感染者15例。新增确诊病例出院5例，4例无症状感染者解除集中隔离医学观察。\",\"截至6月5日24时，我市累计报告确诊病例1681例（其中境外输入1360例），累计出院1653例，死亡3例，其余25例确诊病例正在定点医院隔离治疗。全市现有60例无症状感染者（本土7例，境外输入53例）正在接受集中隔离医学观察。\",\"新增本土无症状感染者3例\",\"无症状感染者1：省外返蓉人员，6月2日抵蓉后即闭环转运，纳入集中隔离，无社会活动轨迹。6月5日核酸检测阳性，诊断为无症状感染者。\",\"无症状感染者2：省外返蓉人员，6月2日抵蓉后即闭环转运，纳入集中隔离，无社会活动轨迹。6月5日核酸检测阳性，诊断为无症状感染者。\",\"无症状感染者3：省外返蓉人员，6月2日抵蓉后即闭环转运，纳入集中隔离，无社会活动轨迹。6月5日核酸检测阳性，诊断为无症状感染者。\",\"新增境外输入无症状感染者15例\",\"无症状感染者1：中国籍，从加拿大出发，于5月26日飞抵成都入境。\",\"无症状感染者2：中国籍，从加拿大出发，于5月26日飞抵成都入境。\",\"无症状感染者3：中国籍，从韩国出发，于5月12日自烟台入境，在当地集中隔离。28日抵蓉后继续纳入隔离管控，无社会活动轨迹。6月5日核酸检测阳性。\",\"无症状感染者4：中国籍，从阿根廷出发，经中国香港于5月29日飞抵成都入境。\",\"无症状感染者5：中国籍，从英国出发，经中国香港于5月29日飞抵成都入境。\",\"无症状感染者6：中国籍，从柬埔寨出发，经中国香港于5月29日飞抵成都入境。\",\"无症状感染者7：中国籍，从英国出发，经中国香港于5月29日飞抵成都入境。\",\"无症状感染者8：中国籍，从新加坡出发，于6月1日飞抵成都入境。\",\"无症状感染者9：中国籍，从新加坡出发，于6月1日飞抵成都入境。\",\"无症状感染者10：中国籍，从新加坡出发，于6月1日飞抵成都入境。\",\"无症状感染者11：中国籍，从新加坡出发，于6月1日飞抵成都入境。\",\"无症状感染者12：中国籍，从新加坡出发，于6月1日飞抵成都入境。\",\"无症状感染者13：德国籍，从德国出发，于6月1日飞抵成都入境。\",\"无症状感染者14：中国籍，从英国出发，经中国香港于6月1日飞抵成都入境。\",\"无症状感染者15：中国籍，从埃及出发，于6月4日飞抵成都入境。\",\"上述无症状感染者居住的隔离点环境已进行消毒，密接人员均处于隔离观察状态。\"],\"publish_time\":\"20220606 09:15:43\"}],\"summary\":\"6月5日0-24时，我市无新增本土确诊病例，新增本土无症状感染者3例。无新增境外输入确诊病例，新增境外输入无症状感染者15例。新增确诊病例出院5例，4例无症状感染者解除集中隔离医学观察。6月5日核酸检测阳性，诊断为无症状感染者。无症状感染者2：中国籍，从加拿大出发，于5月26日飞抵成都入境。\",\"mtime\":\"20220606 13:06:25\",\"keywords\":[\"本土\",\"感染者\",\"症状\"],\"id\":\"ed2c48f4bb42e0bfd7bfbb11f88777f0\",\"name\":\"成都6月5日新增3例本土无症状感染者\"},{\"pv\":16,\"ctime\":\"20220606 07:58:57\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734840203792295631&wfr=spider&for=pc\",\"content\":[\"2022年6月5日0—24时，新增本土新冠肺炎确诊病例4例和无症状感染者4例，其中2例确诊病例和3例无症状感染者在隔离管控中发现。新增境外输入性无症状感染者3例，均在闭环管控中发现。\",\"本土病例情况\",\"2022年6月5日0—24时，新增本土新冠肺炎确诊病例4例。新增治愈出院173例。\",\"病例1、病例2，居住于浦东新区，\",\"均为本市闭环隔离管控人员，其间新冠病毒核酸检测结果异常，经疾控中心复核结果为阳性。经市级专家会诊，综合流行病学史、临床症状、实验室检测和影像学检查结果等，诊断为确诊病例。\",\"病例3，居住于虹口区，\",\"病例4，居住于宝山区，\",\"在风险人群筛查中发现新冠病毒核酸检测结果异常，即被隔离管控。经疾控中心复核结果为阳性。经市级专家会诊，综合流行病学史、临床症状、实验室检测和影像学检查结果等，诊断为确诊病例。（6月5日已通报）\",\"本土无症状感染者情况\",\"2022年6月5日0—24时，新增本土无症状感染者4例。\",\"无症状感染者1，居住于黄浦区，\",\"无症状感染者2、无症状感染者3，居住于闵行区，\",\"均为本市闭环隔离管控人员，其间新冠病毒核酸检测结果异常，经疾控中心复核结果为阳性，诊断为无症状感染者。\",\"无症状感染者4，居住于虹口区，\",\"在风险人群筛查中发现新冠病毒核酸检测结果异常，即被隔离管控。经疾控中心复核结果为阳性，诊断为无症状感染者。（6月5日已通报）\",\"境外输入病例情况\",\"2022年6月5日0—24时，无新增境外输入性新冠肺炎确诊病例。治愈出院1例，来自新加坡。\",\"境外输入性无症状感染者情况\",\"2022年6月5日0—24时，新增境外输入性无症状感染者3例。\",\"无症状感染者1为中国籍，在美国留学，自美国出发，于2022年5月29日抵达上海浦东国际机场，入关后即被集中隔离观察，其间例行核酸检测异常。经排查，区疾控中心新冠病毒核酸检测结果为阳性。综合流行病学史、临床症状、实验室检测和影像学检查结果等，诊断为无症状感染者。\",\"无症状感染者2为中国籍，在美国生活，自美国出发，于2022年6月2日抵达上海浦东国际机场，入关后即被集中隔离观察，其间例行核酸检测异常。经排查，区疾控中心新冠病毒核酸检测结果为阳性。综合流行病学史、临床症状、实验室检测和影像学检查结果等，诊断为无症状感染者。\",\"无症状感染者3为中国籍，在加拿大留学，自加拿大出发，于2022年6月3日抵达上海浦东国际机场，入关后即被集中隔离观察，其间例行核酸检测异常。经排查，区疾控中心新冠病毒核酸检测结果为阳性。综合流行病学史、临床症状、实验室检测和影像学检查结果等，诊断为无症状感染者。\",\"3例境外输入性无症状感染者已转至定点医疗机构医学观察，已追踪同航班密切接触者55人，均已落实集中隔离观察。\",\"2022年6月5日0—24时，解除医学观察无症状感染者1126例，其中本土无症状感染者1125例，境外输入性无症状感染者1例。\",\"2022年2月26日0时至2022年6月5日24时，累计本土确诊58028例，治愈出院56917例，在院治疗523例（其中重型28例，危重型20例），死亡588。现有待排查的疑似病例0例。\",\"截至2022年6月5日24时，累计境外输入性确诊病例4624例，出院4610例，在院治疗14例。现有待排查的疑似病例0例。\"],\"publish_time\":\"20220606 07:56:00\"}],\"summary\":\"2022年6月5日0—24时，新增本土新冠肺炎确诊病例4例和无症状感染者4例，其中2例确诊病例和3例无症状感染者在隔离管控中发现。病例1、病例2，居住于浦东新区，病例4，居住于宝山区，截至2022年6月5日24时，累计境外输入性确诊病例4624例，出院4610例，在院治疗14例。现有待排查的疑似病例0例。\",\"mtime\":\"20220606 11:02:26\",\"keywords\":[\"本土\",\"上海\",\"病例\"],\"id\":\"ac55fb33be636263b34abac0d110c0f8\",\"name\":\"上海6月5日新增本土病例“4+4”例\"},{\"pv\":16,\"ctime\":\"20220606 06:37:55\",\"related_urls\":[{\"url\":\"http://new.qq.com/omn/20220606/20220606A00QZT00.html\",\"content\":[\"06:28\",\"二三里客户端\",\"企鹅号\",\"发布于\",\"据国家突发事件预警信息发布网：\",\"文山市气象局2022年06月06日03时38分37秒解除2022年06月05日20时50分01秒发布的雷电黄色预警。\"],\"publish_time\":\"20220606 06:28:00\"}],\"summary\":\"文山市气象局2022年06月06日03时38分37秒解除2022年06月05日20时50分01秒发布的雷电黄色预警。\",\"mtime\":\"20220606 10:03:37\",\"keywords\":[\"黄色\",\"雷电\",\"气象台\"],\"id\":\"fea0ff806e864ebf2da175ed14fd1aa0\",\"name\":\"南安市气象台解除雷电黄色预警\"},{\"pv\":16,\"ctime\":\"20220606 07:46:03\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734841998213444499&wfr=spider&for=pc\",\"content\":[\"【预警解除】祁门县气象台2022年06月06日08时04分解除大雾橙色预警信号。\",\"（信息来源：国家预警发布中心）\",\"预警原文：「大雾预警」6月6日祁门县气象局发布大雾橙色预警「II级/严重」\"],\"publish_time\":\"20220606 08:10:00\"}],\"summary\":\"东至县气象台2022年06月06日07时36分解除大雾橙色预警信号。\",\"mtime\":\"20220606 11:02:26\",\"keywords\":[\"大雾\",\"气象局\",\"东至县\"],\"id\":\"c71ef89646e13f45666ffd22266a309b\",\"name\":\"东至县气象局解除大雾橙色预警\"},{\"pv\":16,\"ctime\":\"20220606 12:17:11\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1734857085505030035&wfr=spider&for=pc\",\"content\":[\"博罗县气象台于06月06日12时03分发布博罗县雷雨大风黄色预警信号：\",\"请注意防御。\",\"（信息来源：国家预警发布中心）\",\"防御指南\"],\"publish_time\":\"20220606 12:10:00\"}],\"summary\":\"博罗县气象台于06月06日12时03分发布博罗县雷雨大风黄色预警信号，请注意防御。今日惠州天气概述：惠州市，多云转中雨,24℃~32℃,西南风1级，当前温度32℃。\",\"mtime\":\"20220606 14:02:59\",\"keywords\":[\"黄色\",\"气象台\",\"雷雨\"],\"id\":\"86c98d0dca0ab808686934a7b2dc7a79\",\"name\":\"化州市气象台发布雷雨大风黄色预警\"}],\"log_id\":1533704504602938702}");
        return resultVo;
    }

    @RequestMapping("/hot_list_advanced")
    @ResponseBody
    public ResultVo hot_list_advanced(String domain,
                                      String date,
                                      Integer time_spend,
                                      Integer num){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/creation/v1/hot_list_advanced/domain";
        Map<String, Object> map = new HashMap<>();
        map.put("domain", domain);
        map.put("date", date);
        map.put("time_spend", time_spend);
        map.put("num", num);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("热点发现-行业", url, accessToken, map,null);
        return resultVo;
    }

    @RequestMapping("/vein_list")
    @ResponseBody
    public ResultVo vein_list(String event_id,Integer num){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/creation/v1/event/vein_list";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id",event_id);
        //调用接口获取信息
        ResultVo resultVo = new ResultVo();
        JSONArray res = new JSONArray();
        try {
//            FileReader fr = new FileReader("E:\\JAVA_workspace\\AIwriter\\src\\main\\webapp\\text\\vein_list_1.txt");
//            BufferedReader bf = new BufferedReader(fr);
//            String meg = bf.readLine();
            resultVo = AuthService.jsonPostUrl("事件脉络", url, accessToken, map,null);
            String meg = (String) resultVo.getT();
            JSONArray jsonArray = new JSONObject(meg).getJSONArray("content");
            if(num==null) num = 1;
            for (int i = 0; i < num; i++) {
                res.put(jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resultVo.setT("{\"content\":"+res.toString()+"}");
        resultVo.setMess("事件脉络");
        resultVo.setOk(true);
        return resultVo;
    }

    @RequestMapping("/create_vidpress_alignmen")
    @ResponseBody
    public ResultVo create_vidpress_alignmen(@RequestBody Text2video1 text2video){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/brain/vidpress/v1/create_vidpress_alignment";
        //调用接口获取信息
        ResultVo resultVo = new ResultVo();
        String result = "获取失败。";
        try {
            String param = GsonUtils.toJson(text2video);
            result = HttpUtil.post(url, accessToken, "application/json", param);
            resultVo.setT(result);
//            resultVo.setT("{" +
//                    "\"code\":0," +
//                    "\"msg\":\"success\"," +
//                    "\"data\":{\"jobId\":76141," +
//                    "\"estimateStartTime\":\"2022-02-04T13:33:50.261+08:00\"," +
//                    "\"estimateFinishTime\":\"2022-02-04T14:03:50.261+08:00\"}" +
//                    "}");
            resultVo.setMess("图文素材成稿");
            resultVo.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setMess("调用发生错误。");
            resultVo.setOk(false);
            resultVo.setT(result);
        }
        System.out.println((String)resultVo.getT());
        return wait_query(resultVo);
    }

    @RequestMapping("/create_vidpress_alignmen_html")
    @ResponseBody
    public ResultVo create_vidpress_alignmen_html(String reqHtml,
                                                  Integer totalDuration,
                                                  String resolution,
                                                  Integer ttsPer,
                                                  String videoTitle){
        Text2video1 tv = new Text2video1(reqHtml);
        tv.setResolution(resolution);
        if(ttsPer!=null) tv.setTtsPer(String.valueOf(ttsPer));
        if(totalDuration!=null) tv.setTotalDuration(String.valueOf(totalDuration));
        tv.setVideoTitle(videoTitle);
        return create_vidpress_alignmen(tv);
    }

    @RequestMapping("/create_vidpress_alignmen_doc")
    @ResponseBody
    public ResultVo create_vidpress_alignmen_doc(String docUrl,
                                                 Integer totalDuration,
                                                 String resolution,
                                                 Integer ttsPer,
                                                 String videoTitle){
        try {
//            FileReader file = new FileReader(docUrl);
            //解析文件
            Text2video1 tv = new Text2video1();
            tv.setResolution(resolution);
            tv.setTtsPer(String.valueOf(ttsPer));
            tv.setTotalDuration(String.valueOf(totalDuration));
            tv.setVideoTitle(videoTitle);
            return create_vidpress_alignmen(tv);
        }
        catch (Exception ex){
            ResultVo res = new ResultVo();
            res.setMess("文件解析出错,可能不是doc文件");
            return res;
        }
    }

    @RequestMapping("/create_vidpress")
    @ResponseBody
    public ResultVo create_vidpress(String newsUrl,
                                    Integer duration,
                                    String resolution,
                                    Integer ttsPer){
        //获取token
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/brain/vidpress/v1/create_vidpress";
        //设置json参数
        Map<String, Object> map = new HashMap<>();
        map.put("newsUrl", newsUrl);
        map.put("duration", duration);
        map.put("resolution", resolution);
        map.put("ttsPer", ttsPer);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("图文转视频-网页转视频", url, accessToken, map,null);

        ResultVo resultVo = new ResultVo();
        resultVo.setOk(true);
        resultVo.setT("{" +
                "\"code\":0," +
                "\"msg\":\"success\"," +
                "\"data\":{\"jobId\":76141," +
                "\"estimateStartTime\":\"2022-06-06T13:33:50.261+08:00\"," +
                "\"estimateFinishTime\":\"2022-06-06T14:03:50.261+08:00\"}" +
                "}");
        return wait_query(resultVo);
    }

    public ResultVo wait_query(ResultVo resultVo){
        if (!resultVo.isOk()) return resultVo;
        try {
            JSONObject jo = new JSONObject((String) resultVo.getT());
            if(jo.getInt("code") != 0) return resultVo;
            String start_s = jo.getJSONObject("data").getString("estimateStartTime");
            String end_s = jo.getJSONObject("data").getString("estimateFinishTime");
            Date d1 = DateTimeUtil.parseDate(start_s,"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Date d2 = DateTimeUtil.parseDate(end_s,"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//            Thread.sleep(DateTimeUtil.testBetweenMillis(d1,d2)+1000);
            Thread.sleep(5000);
            resultVo = query_vidpress(jo.getJSONObject("data").getString("jobId"));
        } catch (Exception ex) {
            resultVo.setOk(false);
            resultVo.setMess("JSON解析错误(W)");
        }
        if (resultVo.isOk())
            resultVo.setMess("网址生成视频");
        return resultVo;
    }

    @RequestMapping("/query_vidpress")
    @ResponseBody
    public ResultVo query_vidpress(String jobId){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/brain/vidpress/v1/query";
        Map<String, Object> map = new HashMap<>();
        map.put("jobId", jobId);
        //调用接口获取信息
//        ResultVo resultVo = AuthService.jsonPostUrl("图文转视频-视频任务查询", url, accessToken, map,null);
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("生成任务查询");
        resultVo.setOk(true);

        resultVo.setT("{" +
                "\"code\":0," +
                "\"msg\":\"success\"," +
                "\"data\":{\"jobId\":76141," +
                "\"status\":4," +
                "\"expireTime\":\"2022-02-09T11:59:18.212\"," +
                "\"expire\":2," +
                "\"videoAddr\":\"/upload/temp_public/2022-03-01/1646100477509新闻生成视频-冬奥.mp4\"," +
                "\"videoCoverAddr\":\"/upload/temp_public/2022-03-01/1646635384.jpg\"," +
                "\"videoDuration\":75}" +
                "}");
        return resultVo;
    }
    public ResultVo query_vidpress_1(String jobId){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/brain/vidpress/v1/query";
        Map<String, Object> map = new HashMap<>();
        map.put("jobId", jobId);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("图文转视频-视频任务查询", url, accessToken, map,null);
        return resultVo;
    }

    @RequestMapping("/delete_vidpress")
    @ResponseBody
    public ResultVo delete_vidpress(String jobId){
        setAccessToken();
        String url = "https://aip.baidubce.com/rpc/2.0/brain/vidpress/v1/delete";
        Map<String, Object> map = new HashMap<>();
        map.put("jobId", jobId);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("图文转视频-视频任务中止与删除", url, accessToken, map,null);
        return resultVo;
    }

    @RequestMapping("/gen_article")
    @ResponseBody
    public ResultVo gen_article(String title){
        setAccessToken();
        String url = "https://aip.baidubce.com/rest/2.0/nlp/v1/gen_article";
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        //调用接口获取信息
        ResultVo resultVo = AuthService.jsonPostUrl("结构化写作", url, accessToken, map,null);
        return resultVo;
    }
}
