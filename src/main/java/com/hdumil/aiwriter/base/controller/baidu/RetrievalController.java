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
        resultVo.setT("{\"content\":[{\"pv\":5,\"ctime\":\"20220303 13:16:27\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726244121191074800&wfr=spider&for=pc\",\"content\":[\"中央纪委国家监委网站讯 据山东省纪委监委消息：山东省煤田地质局党委书记、局长李关宾涉嫌严重违纪违法，目前正接受山东省纪委监委纪律审查和监察调查。\"],\"publish_time\":\"20220303 10:30:00\"}],\"summary\":\"中央纪委国家监委网站讯 据山东省纪委监委消息：山东省煤田地质局党委书记、局长李关宾涉嫌严重违纪违法，目前正接受山东省纪委监委纪律审查和监察调查。\",\"mtime\":\"20220303 14:00:45\",\"keywords\":[\"地质局\",\"党委书记\",\"李关宾\"],\"id\":\"25f4fc68fb7e354b0a329d61c89a7191\",\"name\":\"山东省煤田地质局党委书记、局长李关宾接受审查调查\"},{\"pv\":5,\"ctime\":\"20220303 11:38:09\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726241252499434258&wfr=spider&for=pc\",\"content\":[\"本文转自【央视新闻客户端】；\",\"今天（3月3日），葫芦岛市召开疫情防控新闻发布会，葫芦岛市卫健委副主任周娇阳在发布会上表示：3月1日0时—24时，葫芦岛市新增确诊病例2例，在隔离场所中发现，临床分型为普通型。3月2日0时—24时，葫芦岛市无新增确诊病例。2月8日—3月2日24时，葫芦岛市累计报告本土确诊病例204例。2月8日—3月2日24时，葫芦岛市已对3333名密接者、3076名次密接者按要求落实管控措施。\",\"3月2日0时—24时，葫芦岛市又有1名新冠肺炎确诊病例在省、市专家、医务人员的精准治疗和细心护理下，体温恢复正常3天以上，呼吸道症状明显好转，肺部影像学显示病灶较前减少，连续两次呼吸道标本核酸检测阴性。依据国家卫健委《新型冠状病毒肺炎诊疗方案（试行第八版修订版）》及专家会诊意见，临床治愈，符合出院标准，将在葫芦岛市定点康复医院继续进行康复治疗及医学观察。\",\"截至目前，葫芦岛市204例确诊病例中，新冠肺炎临床治愈15例，其中14例已在葫芦岛市定点康复医院继续进行康复治疗及医学观察，1例继续在辽宁省新冠肺炎集中救治锦州中心治疗其他基础性疾病。\",\"（总台记者 李承泽）\"],\"publish_time\":\"20220303 09:42:00\"}],\"summary\":\"2月8日—3月2日24时，葫芦岛市累计报告本土确诊病例204例。截至目前，葫芦岛市204例确诊病例中，新冠肺炎临床治愈15例，其中14例已在葫芦岛市定点康复医院继续进行康复治疗及医学观察，1例继续在辽宁省新冠肺炎集中救治锦州中心治疗其他基础性疾病。\",\"mtime\":\"20220303 13:01:00\",\"keywords\":[\"确诊病例\",\"辽宁\",\"葫芦岛市\"],\"id\":\"59b0775dfc81c3f5165a2d097a8ce064\",\"name\":\"辽宁葫芦岛市累计报告本土确诊病例204例\"},{\"pv\":2,\"ctime\":\"20220303 14:18:59\",\"related_urls\":[{\"url\":\"http://www.thepaper.cn/newsDetail_forward_16936304\",\"content\":[\"澎湃新闻记者从东北师范大学地理科学学院方面获悉，中国共产党党员，我国著名水文地理学家、计量地理学家、东北师范大学教授杨秉赓先生，于2022年3月2日在长春逝世，享年95岁。\",\"公开资料显示，杨秉赓1927年4月4日出生于辽宁省锦县（现凌海市），1949年2月至7月，东北大学政治学习；1949年9月至1953年7月，东北大学地理系本科；1953年9月起在华东师范大学地理系普通自然地理研究班，1955年毕业后留东北师范大学地理系任教。1979年任副教授，1985年任教授。1988年6月加入中国共产党。\",\"杨秉赓专长于流域自然地理和水文学研究，1957年参与中苏合作黑龙江流域考察研究；1958年带队开展辉发河流域、饮马河、东辽河水利规划，完成了《辉发河流域水利调查报告》等三部调查报告。1959年，他参与吉林省自然区划工作，完成了《吉林省水文区划》报告，带队进行大架山水土保持考察，完成《大架山水土保持》。\",\"改革开放之后，杨秉赓积极投身于科学研究，先后完成了第二松花江源头区水文特征、松辽水系的变迁、吉林省河流的主成分分析、长白山地区特产资源考察及其总体规划、图们江流域生态系统研究等科研任务，分别在《地理研究》《地理科学》等主流学术期刊上发表论文20余篇。\"],\"publish_time\":\"20220303 11:03:00\"}],\"summary\":\"澎湃新闻记者从东北师范大学地理科学学院方面获悉，中国共产党党员，我国著名水文地理学家、计量地理学家、东北师范大学教授杨秉赓先生，于2022年3月2日在长春逝世，享年95岁。1988年6月加入中国共产党。\",\"mtime\":\"20220303 15:00:18\",\"keywords\":[\"水文地理\",\"杨秉赓\",\"计量地理学\"],\"id\":\"7dde5564c0bc866b6f20a976e312da7f\",\"name\":\"我国著名水文地理学家、计量地理学家杨秉赓逝世\"},{\"pv\":2,\"ctime\":\"20220303 11:34:49\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726241445727390871&wfr=spider&for=pc\",\"content\":[\"本文转自：参考消息\",\"参考消息网3月3日报道据彭博新闻社网站3月2日报道，中国年度盛会全国人大会议将于周六在北京开幕。预计中国届时将公布多项政策，旨在应对该国面临的严重挑战，如重建因新冠疫情放缓的经济增长之势。\",\"报道称，中国领导层在2021年12月召开的中央经济工作会议上表示，有必要解决“需求收缩、供给冲击、预期转弱”等问题。据法国巴黎银行分析师称，促进消费、放宽信贷供应、加快基础设施投资、解决经济增长不平衡等是提振经济的主要方式。\",\"报道指出，由于新冠疫情管控措施，今年的两会将是连续第三年没有邀请境外记者临时来京采访，一些中国记者和外国驻华记者参加两会。\"],\"publish_time\":\"20220303 09:41:00\"}],\"summary\":\"参考消息网3月3日报道据彭博新闻社网站3月2日报道，中国年度盛会全国人大会议将于周六在北京开幕。预计中国届时将公布多项政策，旨在应对该国面临的严重挑战，如重建因新冠疫情放缓的经济增长之势。报道指出，由于新冠疫情管控措施，今年的两会将是连续第三年没有邀请境外记者临时来京采访，一些中国记者和外国驻华记者参加两会。\",\"mtime\":\"20220303 13:01:00\",\"keywords\":[\"目光\",\"盛会\",\"吸引\"],\"id\":\"a6c2b886b0a61c5261d3c4abd1609469\",\"name\":\"美媒：中国年度盛会吸引世界目光\"},{\"pv\":2,\"ctime\":\"20220303 11:07:32\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726240127480326377&wfr=spider&for=pc\",\"content\":[\"2018年，湖南省高级人民法院认定原中国移动湖南有限公司原党组书记王建根受贿1000多万元，判处他11年有期徒刑，并罚金200万。\",\"王建根在一审时表示自己从未收受一分钱贿赂，后来又在案发前退回情人收受的300多万，无论如何也不该是11年的铁窗生涯。\",\"对此，省高院的回复是，王建根本人虽然没有收受贿款，但行贿人员是按照他的指示，把贿款交给其情人和朋友。\",\"按照我国法律，王建根可以完全支配这笔钱的去向，综合其他证据认定，王建根仍然是受贿罪的主犯。\",\"国企高管遇上“灰姑娘”：感觉像极了“爱情”\",\"王建根出生于五十年代，在湖南省湘阴县长大，24岁加入中国共产党，在中国移动湖南有限公司工作近15年，积累下无数人脉。\",\"这为他后来托关系找人办事埋下了伏笔。\",\"王建根算得上中国电信的早期从业员，1971年他参加工作后，在湘阴县邮电局担任机务员，使用的是当时最传统的老式交换机设备。\",\"十年动乱期结束后，王建根由于在工作中表现优秀，被批准加入中国共产党。\",\"与此同时，电信局重新开始运转，但八十年代的中国百姓家里很少有人能装得起电话，即便能安装，“跑关系”也是一项考验做人能力的技术活儿。\",\"随着九十年代中国移动、联通、电信三大巨头的崛起，王建根的官职也跟着水涨船高，从湘阴县的机务员变成邵阳市邮电局的副局长、局长、书记。\",\"在出任湖南移动的董事长之前，王建根已经做到了省邮电管理局副局长，还是党组成员之一。\",\"2000年，移动通信集团为了顺应市场化潮流，跟上国际运营商的改革步伐，宣布重组。王建根幸运地赶上了这趟顺风车，出任移动通信湖南有限公司的董事长兼党组书记。\",\"这一年，王建根大权在握，成了无数人争相巴结的对象。\",\"4月，志得意满的他和当年邮电局的好友龙某，以及另一位公司老总，三人去了KTV包间唱歌，休息放松。\",\"这位老总有意到湖南省开展业务，因此提前找人托关系，认识了湖南移动的党组书记王建根。\",\"但他与王建根没有交情，为了确保业务顺利，老总又请龙某帮忙，表示事成之后，愿意给“好处费”。\",\"龙某心动了。\",\"龙某与王建根认识已有十年，两人知根知底，见他喝高了，龙某便“贴心”地叫了服务员来陪他们喝酒。\",\"KTV老板与龙某也是旧相识，他投其所好，让年仅19岁的一位农村姑娘去服侍这桌人。\",\"王建根见到年轻漂亮的服务员，顿时心里大起好感，拉着她就在饭桌上谈天说地，高谈阔论。\",\"服务员十分尴尬，但她知道王建根是一位高级官员，自己得罪不起，只好忍耐。\",\"然而令她没想到的是，已经49岁的王建根竟然看上了自己。\",\"龙某察言观色，聚会结束后调查了服务员的身世，向她暗示，只要服从安排，荣华富贵唾手可得。\",\"出身贫寒、每月需要寄钱养家的女服务员左思右想，最终默认了龙某的安排。\",\"三个月后，王建根为这位女服务员改名张茜，两人正式确立情人关系。\",\"得益于龙某的引荐，那位公司老总也从王建根那里拿到了湖南移动的一部分项目，按照约定，他给了龙某一笔“好处费”。\",\"王建根得到张茜后，喜不自胜，把家中妻儿都丢在脑后，一心一意讨好这位性格内向的情人。\",\"张茜告诉王建根，由于家境贫寒，她很早就放弃了学业出来打工，没有上过大学，也没有足够的学识修养。\",\"王建根一听，当场表示自己可以圆她的梦想。\",\"这一年，王建根为张茜重新办理了身份证，落户绥宁县，并把她送到湖南涉外经济学院读书。\",\"2004年，张茜大学毕业，听从王建根的安排，进入一家私营企业工作，工资每月近3000元。\",\"为了让情人安心，王建根又给了这家公司许多项目。\",\"私企老总感恩之余，又主动给张茜开了双倍工资。\",\"2007年，张茜从公司离职后自主创业，老总又忙不迭为她办理了第三张工资卡，每月7000多元，同样发到2009年。\",\"直到案发，三张不同身份的工资卡为张茜带来了大约70万元的非法收入。\",\"十年受贿不取分毫，情人与兄弟才是最大受益者\",\"就在王建根绞尽脑汁讨好张茜时，深圳一家从事礼品业务的公司找到了他。\",\"老总姚某与四川移动董事长相识，经过董事长撮合，姚某见到了王建根。\",\"听闻王建根有位知性美丽的情人，他又设法通过龙某找到张茜，请他们“夫妻二人”一起吃个饭。\",\"龙某欣然同意。张茜听后也是默认了这场饭局。\",\"三人在饭桌上谈起姚某的生意，王建根表示，姚某完全可以在湖南成立一家代理公司，接手湖南移动的礼品业务。\",\"至于好处费，王建根大度地表示，龙某跟随自己多年，是时候带着他“挣点小钱”了。\",\"姚某点头同意，又问好处费怎么分？\",\"王建根伸手指着张茜和龙某，表示他们两人平分就行。自己现在还是党组书记，不宜受贿。\",\"事情商量好后，王建根春风得意，自觉既给了小情人美好的未来，也让自己的兄弟赚了钱，这叫双喜临门。\",\"2006年，姚某在长沙成立办事处，聘用张茜为负责人，王建根以开展业务为由给予了许多“关照”。\",\"作为回报，姚某把业务量成交额的3％到5％当成“佣金”，汇款给龙某，再由龙某和张茜平分这一笔钱。\",\"从2006年到2009年，张茜和龙某累计受贿550万多。\",\"2010年，移动集团内部掀起反贪风暴，四川移动董事长被刑事拘留，不久被立案调查。\",\"一时间，移动内部高级官员人人自危，纷纷检查自己坐的椅子是不是端正。\",\"王建根也受到了这股情绪的影响。\",\"比起其他人，他和四川移动董事长的关系要近得多。\",\"当初姚某千里迢迢从深圳到湖南，就是托了这位董事长的关系，才能和王建根做生意。\",\"且姚某作为一家礼品公司的老总，拥有大量移动客户，这其中就包括四川移动董事长。\",\"王建根担心四川那位扛不住压力，在警方面前卖了姚某，到时候姚某再招供，下一个被顺藤摸瓜的就是自己了。\",\"身为湖南移动的一把手，王建根十分担心这把火会烧到自己身上，连忙给张茜和龙某打电话，要他们把从姚某那里拿到的钱退回去。\",\"这时他与张茜已经分手，但两人还保持着一定联系。接通张茜的电话后，王建根直奔主题，提出退钱。\",\"张茜十分不情愿，那笔钱她已经花了不少，短时间内要凑齐275万人民币，对张茜来说这太难了。\",\"然而王建根严厉警告她，这笔钱如果不退回去，他们所有人都要受牵连，到时候王建根乌纱帽不保，哪里还有张茜龙某等人的好日子过？\",\"张茜无奈，只好取出剩下的钱，又让老家姐姐汇了80多万，凑齐275万之后，她列出清单，写好收条，拿着银行卡和一袋子钱找到龙某，要龙某也尽快集齐钱款，一并退给姚某。\",\"与此同时，王建根也通知了龙某退钱。\",\"龙某知道事情严重性，却不以为意，他认为王建根在党组书记的位子上坐久了，胆子也变小了。\",\"龙某在宦海浮沉多年，心眼和头脑都远超出张茜，他收下了275万的现金，在张茜列出的清单和收条上签了名。\",\"张茜回去后告知王建根事情已经办妥，龙某能凑齐多少就看王建根自己了。\",\"王建根几次催促龙某尽快凑齐钱款，均被龙某打哈哈糊弄过去。\",\"拖了一段时间后，王建根无奈，只能让龙某先把张茜的275万返还，余下的能凑多少凑多少。\",\"这正合龙某的心思。\",\"在王建根的高压催促下，他很快就办理了两张银行卡，把张茜的275万和自己的25万汇入其中。\",\"2012年，龙某找到姚某，把两张用不同身份证办理的银行卡交给他，告诉他这是退款。\",\"但龙某并没有告诉他银行卡密码，也没有告知办理时所用的身份证号，这是两张只能看不能取也不能用的储蓄卡，纯属摆设。\",\"姚某收到退款后，自以为明白了王建根和龙某的意思，他分别给两人发短信，称钱已收到，请放心。\",\"王建根这才松了口气，又接着查自己和张茜名下的收入，并询问她前公司那三张工资卡一共多少钱。\",\"张茜核算后确定是70万元，王建根又从自己存款里提出一部分给她，凑齐70万返还给当年的公司老总，并拿到了老总的收据。\",\"退钱之后，张茜账面上的钱款已经所剩无几，王建根为表安慰，自掏腰包给了她35万，并告诉她等这段时间过去了，他们依然还会有钱。\",\"张茜满心的不乐意，但事已至此，她也只能接过这笔钱。\",\"王建根收到姚某的短信，又听了龙某的汇报，自以为万事大吉，不会再有任何问题了，于是安安心心做自己的事去了。\",\"整个退钱过程中，王建根并不知道龙某的心思，也不了解他在银行卡上做的手脚。\",\"姚某在案发后曾对警方供述，自己理解的意思是，王建根只是出于一时艰难，不得已才把钱款交给自己，风声一过，这笔已经花出去的钱自然还会被要回去。\",\"他给王建根和龙某发送的那条短信言外之意是：放心，钱我先保管着，您二位有需要，随时可以拿回去。\",\"而龙某的供词核心内容和姚某一样。\",\"法庭喊冤自认清廉，天网恢恢疏而不漏\",\"2012年，中国移动内部反贪风暴接近尾声，但仍有官员陆续落马。\",\"此时的王建根在还清钱款后，长出一口气，因为他终于正式退休了。\",\"退休时他还很高兴地认为，这一生总算清清白白“落地”了。\",\"但王建根的违法行为并没有逃过中纪委的火眼金睛。\",\"在家安然度过三年后，中国移动内部又曝出反腐大案，湖南移动副总经理周云和郭强因为贪污受贿，被中央调查组拿到铁证立案调查。\",\"湖南省纪委在案件办理过程中，发现前中国移动湖南有限公司董事长、原党组书记王建根存在违法受贿事实。\",\"2015年8月，从姚某、龙某、张茜等人那里拿到确切证据后，省人民检察院对王建根发出逮捕令，月底王建根即被立案调查。\",\"被抓后的王建根很不服气，同时也十分诧异，他明明没有收受一分钱贿赂，为什么会被当成罪犯抓起来？\",\"四个月后，湖南省宣布开除王建根党籍，监察网站上同步更新了相关情况介绍。\",\"在2016年的湖南省郴州市人民检察院一审公诉法庭上，判决书解答了王建根的困惑。\",\"根据我国受贿罪的法律定义，王建根虽然没有直接收受贿赂，但他利用职权为自己的情人和朋友牟取非法利益，属于“单独或者共同非法收受他人财物”。\",\"且王建根对受贿得来的钱款又有绝对支配权，从法理上来讲，他毫无疑问是受贿罪的真正主犯。\",\"张茜与龙某都听命于王建根，负责瓜分赃款，是受贿罪的从犯，犯罪情节相对轻微。\",\"判决文书宣布之后，王建根心灰意冷，但他仍不放弃，提出上诉。\",\"他的情人张茜也感觉十分冤枉，同样要求上诉。\",\"2018年，湖南省最高人民法院二审此案，认定王建根犯下受贿罪，金额达1019万元，属于“数额特别巨大”，量刑加重，处11年有期徒刑。\",\"同时没收缴全部违法所得，退回受贿钱款，罚金200百万。\",\"情人张茜被判处有期徒刑3年零六个月，退回受贿钱款，罚金20万。\",\"其他犯罪人员也依法给予处置。\",\"由于二审即为终审，法庭宣判后，王建根默然无言，张茜痛不欲生。\",\"王建根落网后，他的家人们终于知道了事情的原委。原配妻子痛苦至极，丈夫犯罪意味着自己的儿子无法通过政审，失去了从政的希望。\",\"而至今还在监狱服刑的王建根知道自己愧对家人，他不敢奢求家人原谅，只能努力参加改造，希望早日出狱重新做人。\",\"情人张茜由于认判刑较轻，因此早早出狱，但也已经是年过40的中年女子了。\",\"重新变成普通人的她低调生活，绝口不提当年那段不光彩的往事。王建根一事后，她再也没有结婚，也失去了重组家庭、养育自己的孩子的愿望。\",\"参考文献\",\"1、澎湃新闻：《中移动湖南公司原董事长王建根涉嫌受贿罪被提起公诉》\",\"2、法制现场播报：《国企高管王建根：为博红颜一笑，扶持坐台小姐成高管，获刑11年》\"],\"publish_time\":\"20220303 09:27:00\"}],\"summary\":\"2018年，湖南省高级人民法院认定原中国移动湖南有限公司原党组书记王建根受贿1000多万元，判处他11年有期徒刑，并罚金200万。王建根在一审时表示自己从未收受一分钱贿赂，后来又在案发前退回情人收受的300多万，无论如何也不该是11年的铁窗生涯。对此，省高院的回复是，王建根本人虽然没有收受贿款，但行贿人员是按照他的指示，把贿款交给其情人和朋友。\",\"mtime\":\"20220303 13:01:00\",\"keywords\":[\"高管\",\"中国移动\",\"被捕\"],\"id\":\"9a8ef65075a0ac2f289b0cacfe045953\",\"name\":\"中国移动前高管被捕\"},{\"pv\":2,\"ctime\":\"20220303 12:17:49\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726243273046837111&wfr=spider&for=pc\",\"content\":[\"3月4日，北京2022年冬奥会火炬将在北京市残疾人文化体育指导中心、中国残疾人体育运动管理中心、北京冬奥组委进行传递。3月4日下午，全国政协十三届五次会议开幕会将在人民大会堂举行。\",\"为保障活动安全、顺利进行，北京市公安局发布通告，3月4日当天，以下路段将分时、分段采取交通管制措施：\",\"(1)3月4日上午，早高峰城区环路、联络线以及部分桥区节点通行压力较为突出，部分学校、医院周边车流集中。上午时段，西五环、北五环、西二环、莲石路、长安街、前三门大街、京承高速、机场高速将适时采取临时交通管理措施。\",\"(2)3月4日中午至晚间，西五环、北五环、东三环、西二环、北二环、东二环、阜石路、长安街、前三门大街、京新高速、京藏高速、京礼高速、京沪高速、机场高速将分时、分段采取临时交通管理措施。预计晚高峰将延长，市区环路、联络线、高速公路交通流量大。\",\"请市民朋友关注北京市公安局发布的交通管制通告(jtgl.beijing.gov.cn/jgj/jgxx/gsgg/jttg/bjsgajtg/21237317/index.html)和“北京交警”微博发布的实时交通提示信息，尽量减少出行，或选择错峰出行、公交出行、绿色出行，合理安排出行时间、方式、路线。\",\"特别提示，冬残奥会奥林匹克专用车道已于2月26日正式启用，市民朋友出行过程中，请严格遵守奥林匹克专用车道“通告”相关规定。在设置奥林匹克专用车道的道路上，同时设有公交专用车道的，在奥林匹克专用车道使用时段，机动车仍然可以借用公交专用车道通行。\",\"希望市民朋友们在冬残奥会即将开幕之际，继续礼让冬残奥专用车辆，争做文明交通践行者，充分展示“双奥之城”热情好客、文明有礼的东道主风采。(总台央视记者 赵学荣)\",\"（央视新闻客户端）\"],\"publish_time\":\"20220303 10:17:00\"}],\"summary\":\"为保障活动安全、顺利进行，北京市公安局发布通告，3月4日当天，以下路段将分时、分段采取交通管制措施： 3月4日上午，早高峰城区环路、联络线以及部分桥区节点通行压力较为突出，部分学校、医院周边车流集中。上午时段，西五环、北五环、西二环、莲石路、长安街、前三门大街、京承高速、机场高速将适时采取临时交通管理措施。\",\"mtime\":\"20220303 13:01:00\",\"keywords\":[\"临时\",\"管制\",\"明日部分路段\"],\"id\":\"a7d310f5cfffed6f8d991456fdb4723b\",\"name\":\"北京明日部分路段将临时管制\"},{\"pv\":2,\"ctime\":\"20220303 11:37:46\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726241100478772751&wfr=spider&for=pc\",\"content\":[\"3月1日，莘县副县长贾国顺对水污染防治工作进行现场调研，县环委办、生态环境、水务集团及相关镇街负责人陪同活动。\",\"贾国顺先后来到徐庄镇驻地污水处理站、朝城镇白井社区污水处理站及入河排污口、金线河仁义闸、朝城污水处理厂等地进行实地调研，听取了相关单位、镇街情况汇报，了解存在的问题和困难，并对今后水污染防治工作提出了指导意见和要求。\",\"贾国顺强调， 水污染防治工作事关经济社会发展和人民群众身体健康，一定要充分认识到水污染对环境带来的长期危害性和治理的艰巨性，并予以高度重视。要进一步强化责任落实，细化工作举措，相关部门与镇街要互相配合，形成工作合力，协调联动，把水污染治理工作落到实处，切实改善莘县河流生态环境质量。（王珊珊）\"],\"publish_time\":\"20220303 09:43:00\"}],\"summary\":\"3月1日，莘县副县长贾国顺对水污染防治工作进行现场调研，县环委办、生态环境、水务集团及相关镇街负责人陪同活动。贾国顺强调， 水污染防治工作事关经济社会发展和人民群众身体健康，一定要充分认识到水污染对环境带来的长期危害性和治理的艰巨性，并予以高度重视。\",\"mtime\":\"20220303 13:01:00\",\"keywords\":[\"贾国顺\",\"水污染防治\",\"莘县\"],\"id\":\"69c94b3c6d1c9f3f3cb373d8e2ee0ca3\",\"name\":\"莘县副县长贾国顺调研水污染防治工作\"},{\"pv\":2,\"ctime\":\"20220303 11:17:23\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726240415822080083&wfr=spider&for=pc\",\"content\":[\"衡阳日报3月3日讯（全媒体记者姚永军）\",\"3月2日上午，全市城乡治理标准化推进会召开，部署2022年度城乡治理标准化工作任务，调度安排当前重点工作。市委常委、市委秘书长、市标准化办主任尹培国出席会议并讲话。\",\"2022年，市标准化办将按照市委、市政府总体部署，瞄准“全市城乡面貌彻底改善，城乡居民标准化成为遵循和习惯，干净衡阳、美丽衡阳、活力衡阳成为品牌”这一总体目标，对标对表《实施意见》目标任务，聚焦第一批标准内容全市全域施行、达标以及第二批标准内容的建标、达标工作，进一步创新手段，压实责任，全面攻坚城乡治理标准化工作。会议对2022年度城乡治理标准化工作计划、考核方案及相关重点工作进行了解读。\",\"尹培国强调，要从党中央和国务院的重大决策部署、市委市政府的决策要求、提升各级干部治理能力和水平、满足人民群众对美好生活的向往四个方面切实提升对城乡治理标准化工作的思想认识；抓紧全面推行第一批标准内容、尽快完成第二批建标工作、破解瓶颈突破重点难点、推进行政职能标准化、加强路域标准化治理、加强门前“三包”、深入发动群众等方面的重点工作；要加大工作保障力度，责任分解到人，强化资金保障，浓厚宣传氛围，统筹联动创新，突出考核触动。\"],\"publish_time\":\"20220303 09:20:00\"}],\"summary\":\"3月2日上午，全市城乡治理标准化推进会召开，部署2022年度城乡治理标准化工作任务，调度安排当前重点工作。市委常委、市委秘书长、市标准化办主任尹培国出席会议并讲话。\",\"mtime\":\"20220303 13:01:00\",\"keywords\":[\"部署\",\"工作任务\",\"2022\"],\"id\":\"b05472af21ec0d4ffb5e024ab33b0f8b\",\"name\":\"衡阳市部署2022年度城乡治理标准化工作任务\"},{\"pv\":2,\"ctime\":\"20220303 13:19:34\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726244441470354928&wfr=spider&for=pc\",\"content\":[\"参考消息网3月3日报道泰国《民意报》网站2月21日报道发表题为《北京冬奥会：丰富的奥运遗产惠及大众》的报道，专访国际奥委会文化和奥林匹克遗产委员会主席帕塔玛。帕塔玛认为，中国应保护好此次冬奥会留下来的奥林匹克遗产，并作为冬奥会历史的重要篇章传承下去。全文摘编如下：\",\"近日，泰国国际奥委会委员帕塔玛表示，北京2022年冬奥会的成功举办，代表着大型体育赛事在与新冠肺炎疫情的较量中获胜，这与严格的防疫安排、全体北京冬奥会参与人员的齐心协力密不可分。作为东道主，中国在多个方面将冬奥会推向了更高水准，而本届冬奥会各项体育基础设施也将惠及当地民众。\",\"帕塔玛表示，自2月4日开幕以来，中国始终坚持高标准、高效率举办各项冬奥赛事，详细的计划和周全的准备令人印象深刻。特别是高标准的闭环管理措施，使北京冬奥会疫情防控成效显著。\",\"北京冬奥会大力倡导国际合作，这向全世界表明：只要我们团结一心，就一定能够克服新冠肺炎疫情带来的各种困难。这次盛会真正践行了奥林匹克精神，将博爱、友谊和希望传递给全世界。\",\"举办奥林匹克盛会是让全世界人民摒弃嫌隙、和平聚会的有效方式。这次盛会达到了这个目标，是构建人类命运共同体的一次重要实践。同时，北京冬奥会通过将高科技融入体育竞赛，向人们展示了冬奥会的进步。中国通过举办这次冬奥会向世界表明，中国是世界上科技实力位居前列的国家之一。\",\"另外值得肯定的是，中国政府和北京冬奥组委为保护环境，在减少碳排放方面作出了积极努力，如将北京2008年夏季奥运会场馆改造后作为冬奥会场馆，以及广泛使用节能车辆、清洁能源等，这些举措可最大限度减少能源消耗。帕塔玛曾多次到访冬奥村，发现中方为运动员提供了专门的服务和配套设施，在此居住的泰国运动员和工作人员均对北京冬奥组委周到的安排感到非常满意。\",\"帕塔玛还对北京冬奥会高效的交通系统和舒适的住宿安排、特别是对运动员卫生健康的重视印象深刻。在本届冬奥会，每个活动区域的容纳人数都有严格限制，确保不会出现拥挤；免洗手消毒凝胶随处可见；电梯清洁频率很高；冬奥村的志愿者和工作人员都非常友善并乐于助人；餐食种类丰富并兼顾营养均衡。\",\"帕塔玛表示，她作为国际奥委会文化和奥林匹克遗产委员会主席，认为中国应保护好此次冬奥会留下来的奥林匹克遗产，并作为冬奥会历史的重要篇章传承下去。\",\"北京冬奥会在促进经济发展方面也发挥了积极作用，中国国内外与冬奥会相关的产业均得到了提振，这具有重要意义。北京冬奥场馆是重要的奥林匹克遗产，中国作为东道主可以继续发挥其作用，长期来看这将对中国经济和旅游业带来利好。至于冬奥会期间使用的基础设施，将为中国民众的出行带来更多便利。\"],\"publish_time\":\"20220303 10:29:00\"}],\"summary\":\"帕塔玛认为，中国应保护好此次冬奥会留下来的奥林匹克遗产，并作为冬奥会历史的重要篇章传承下去。近日，泰国国际奥委会委员帕塔玛表示，北京2022年冬奥会的成功举办，代表着大型体育赛事在与新冠肺炎疫情的较量中获胜，这与严格的防疫安排、全体北京冬奥会参与人员的齐心协力密不可分。\",\"mtime\":\"20220303 14:00:45\",\"keywords\":[\"国际奥委会\",\"帕塔玛\",\"遗产\"],\"id\":\"1affab637e4395e3641b9f2c4833e8de\",\"name\":\"国际奥委会委员帕塔玛：冬奥会为中国留下丰富遗产\"},{\"pv\":2,\"ctime\":\"20220303 12:26:56\",\"related_urls\":[{\"url\":\"http://baijiahao.baidu.com/s?id=1726242619297054836&wfr=spider&for=pc\",\"content\":[\"红网时刻3月2日讯（通讯员 贺美玲）3月2日上午，新宁县乡村振兴局党支部召开2021年度组织生活会暨民主评议党员会。\",\"会上，全体党员都严肃认真地就各自思想、工作、学习情况及自身存在的问题进行深刻的剖析，并诚恳、客观地提出了批评与自我批评，也虚心地接受了其他同志的批评。\",\"会议指出，这些批评和建议，句句都是同志们的心里话，体现了该局党员同志的思想觉悟和综合素质，反映了大家对乡村振兴工作的殷切期望。下一步，全体党员同志要把此次组织生活会作为新的开端，新的起点，以更加积极的态度、饱满的精神和踏实的工作作风来抓好问题的整改和落实。\",\"会议要求，要继续加强学习，深化思想认识。要坚持把学习放在首位，特别是要学好省、市、县乡村振兴有关会议、文件等精神；要继续加强支部的自身建设，增强解决班子自身问题的能力，吸收新鲜血液，壮大党员队伍；全体党员要进一步增强责任意识，在各自岗位有所作为，始终保持干事创业的热情，始终保持争先创优的激情，共同努力将新宁县乡村振兴工作推向新的高度。\"],\"publish_time\":\"20220303 10:04:00\"}],\"summary\":\"红网时刻3月2日讯 3月2日上午，新宁县乡村振兴局党支部召开2021年度组织生活会暨民主评议党员会。下一步，全体党员同志要把此次组织生活会作为新的开端，新的起点，以更加积极的态度、饱满的精神和踏实的工作作风来抓好问题的整改和落实。\",\"mtime\":\"20220303 14:00:45\",\"keywords\":[\"党支部\",\"乡村\",\"新宁县\"],\"id\":\"11ec4d721c4fb719a6b6aa8228432363\",\"name\":\"新宁县乡村振兴局党支部召开2021年度组织生活会\"}],\"log_id\":1499343728269198938}");
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
//        Map<String, Object> map = new HashMap<>();
//        map.put("event_id",event_id);
        //调用接口获取信息
        ResultVo resultVo = new ResultVo();
        JSONArray res = new JSONArray();
        try {
            FileReader fr = new FileReader("E:\\JAVA_workspace\\AIwriter\\src\\main\\webapp\\text\\vein_list_1.txt");
            BufferedReader bf = new BufferedReader(fr);
            String meg = bf.readLine();
//            ResultVo resultVo = AuthService.jsonPostUrl("事件脉络", url, accessToken, map,null);
//            String meg = (String) resultVo.getT();
            JSONArray jsonArray = new JSONObject(meg).getJSONArray("content");
            if(num==null) num = 1;
            for (int i = 0; i < num; i++) {
                res.put(jsonArray.get(i));
            }
        } catch (IOException | JSONException e) {
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
//            result = HttpUtil.post(url, accessToken, "application/json", param);
//            resultVo.setT(result);
            resultVo.setT("{" +
                    "\"code\":0," +
                    "\"msg\":\"success\"," +
                    "\"data\":{\"jobId\":76141," +
                    "\"estimateStartTime\":\"2022-02-04T13:33:50.261+08:00\"," +
                    "\"estimateFinishTime\":\"2022-02-04T14:03:50.261+08:00\"}" +
                    "}");
            resultVo.setMess("图文素材成稿");
            resultVo.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setMess("调用发生错误。");
            resultVo.setOk(false);
            resultVo.setT(result);
        }
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
        tv.setTtsPer(String.valueOf(ttsPer));
        tv.setTotalDuration(String.valueOf(totalDuration));
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
                "\"estimateStartTime\":\"2022-02-04T13:33:50.261+08:00\"," +
                "\"estimateFinishTime\":\"2022-02-04T14:03:50.261+08:00\"}" +
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
                "\"data\":{\"jobId\":75921," +
                "\"status\":4," +
                "\"expireTime\":\"2022-02-09T11:59:18.212\"," +
                "\"expire\":2," +
                "\"videoAddr\":\"/upload/temp_public/2022-03-01/1646100477509新闻生成视频-冬奥.mp4\"," +
                "\"videoCoverAddr\":\"/upload/temp_public/2022-03-01/1646635384.jpg\"," +
                "\"videoDuration\":75}" +
                "}");
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
