package com.hdumil.aiwriter.test;

import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.service.ArticleService;
import com.hdumil.aiwriter.back.service.impl.ArticleServiceImpl;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.controller.ReptileController;
import com.hdumil.aiwriter.base.controller.baidu.*;
import com.hdumil.aiwriter.base.controller.baidu.bean.Text2video1;
import com.hdumil.aiwriter.base.controller.shanda.EvaluateController;
import com.hdumil.aiwriter.base.service.impl.BaiduReptileServiceImpl;
import com.hdumil.aiwriter.base.service.impl.NationalEmergencyReptileServiceImpl;
import org.json.JSONException;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/14
 * Description :
 */
public class TestBlog {

    @Test
    public void test_change() {
        String str = "<p>深圳南山，今日晴，白天最高气温30℃，</p>\n" +
                "夜间最低温度23℃，\n" +
                "<p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004774841.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>\n" +
                "<p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004775032.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>\n" +
                "无持续风向3~4级，空气质量优。";
        Text2video1 tx = new Text2video1(str);
        System.out.println(tx.toString());
    }

    @Test
    public void test_NLPController() {
        NLPController NLPC = new NLPController();
        NLPC.setApiKey("d0ZmMHyBgj9S3FiWkGZEXCMV");
        NLPC.setSecretKey("4nPsqIPZIAPMntaF2BomhEGGdtiGSxwy");
        NLPC.setAccessToken();
//        ResultVo res = NLPC.news_summary("麻省理工学院为无人机配备RFID技术，进行仓库货物管理",
//                "麻省理工学院的研究团队为无人机在仓库中使用RFID技术进行库存查找等工作，创造了一种聪明的新方式。它允许公司使用更小，更安全的无人机在巨型建筑物中找到之前无法找到的东西。使用RFID标签更换仓库中的条形码，将帮助提升自动化并提高库存管理的准确性。与条形码不同，RFID标签不需要对准扫描，标签上包含的信息可以更广泛和更容易地更改。它们也可以很便宜，尽管有优点，但是它具有局限性，对于跟踪商品没有设定RFID标准，“标签冲突”可能会阻止读卡器同时从多个标签上拾取信号。扫描RFID标签的方式也会在大型仓库内引起尴尬的问题。固定的RFID阅读器和阅读器天线只能扫描通过设定阈值的标签，手持式读取器需要人员出去手动扫描物品。几家公司已经解决了无人机读取RFID的技术问题。配有RFID读卡器的无人机可以代替库存盘点的人物，并以更少的麻烦更快地完成工作。一个人需要梯子或电梯进入的高箱，可以通过无人机很容易地达到，无人机可以被编程为独立地导航空间，并且他们比执行大规模的重复任务的准确性和效率要比人类更好。目前市场上的RFID无人机需要庞大的读卡器才能连接到无人机的本身。这意味着它们必须足够大，以支持附加硬件的尺寸和重量，使其存在坠机风险。麻省理工学院的新解决方案，名为Rfly，允许无人机阅读RFID标签，而不用捆绑巨型读卡器。相反，无人机配备了一个微小的继电器，它像Wi-Fi中继器一样。无人机接收从远程RFID读取器发送的信号，然后转发它读取附近的标签。由于继电器很小，这意味着可以使用更小巧的无人机，可以使用塑料零件，可以适应较窄的空间，不会造成人身伤害的危险。麻省理工学院的Rfly系统本质上是对现有技术的一个聪明的补充，它不仅消除了额外的RFID读取器，而且由于它是一个更轻的解决方案，允许小型无人机与大型无人机做同样的工作。研究团队正在马萨诸塞州的零售商测试该系统。",
//                String.valueOf(200));
        ResultVo res = NLPC.titlepredictor("如果因为喝酒过量导致恶心、呕吐等，可以喝橘子茶，橘子能够快速醒酒，缓解人体不适。将100克鲜橘子皮加入500毫升水中煮开，加入少许盐摇匀，当茶水饮用，可以快速解酒。喝酒之前食用蜂蜜，对预防醉酒有一定的作用。在喝酒前的一个小时左右，蜂蜜进入人体后会减少人体各器官对酒精的吸收，从而起到预防醉酒的功效。喝醉后可以温水将蜂蜜进行稀释，然后多喝几杯。蜂蜜在进入人体时候，能够迅速的被吸收，增加人体的解毒功能，能帮助肝脏尽快的将酒精排出体外，这样也就达到了解酒的目的。食盐在大家的生活中非常常见，它也是一种很好解酒药，醉酒后使用食盐能够帮助降低胃中酒精浓度，降低血液中酒精浓度的作用，帮助解酒。 方法：取一杯白开水，加入少许食盐直接饮用即可。蒙古人多豪饮，酸奶正是他们的解酒秘方，一旦酒喝多了，便喝酸奶，酸奶能保护胃黏膜，延缓酒精吸收。由于酸奶中钙含量丰富，因此对缓解酒后烦躁症状尤其有效。拐枣，有些地区也叫它万寿果，用它煮水解酒的效果一流。拐枣含有大菌的葡萄糖、苹果酸钙，有较强的利尿作用，能促进乙醇的分解和排出，显著降低乙醇在血液中的浓度，并能消除乙醇在体内产生的自由基，阻止过氧化酯质的形成，从而减轻乙醇对肝组织的损伤，避免酒精中毒导致各种代谢异常。拐枣还能消化人体过多脂肪，可以减肥。梨能够消燥祛火，解酒毒，给醉酒者吃梨，一般2~3个梨子可以醒酒，如果醉酒者大醉伶仃，可以将梨子榨汁让其饮用，也有快速解酒的功效。 大量喝水，冲淡酒精，加快新成代谢，以便快速排出，再喝两支葡萄糖，也可以用蜂蜜泡茶喝，很快酒就醒了，酒后如头痛可以吃点葡萄。");
//        ResultVo res = NLPC.topic("麻省理工学院为无人机配备RFID技术，进行仓库货物管理",
//                "麻省理工学院的研究团队为无人机在仓库中使用RFID技术进行库存查找等工作，创造了一种聪明的新方式。它允许公司使用更小，更安全的无人机在巨型建筑物中找到之前无法找到的东西。使用RFID标签更换仓库中的条形码，将帮助提升自动化并提高库存管理的准确性。与条形码不同，RFID标签不需要对准扫描，标签上包含的信息可以更广泛和更容易地更改。它们也可以很便宜，尽管有优点，但是它具有局限性，对于跟踪商品没有设定RFID标准，“标签冲突”可能会阻止读卡器同时从多个标签上拾取信号。扫描RFID标签的方式也会在大型仓库内引起尴尬的问题。固定的RFID阅读器和阅读器天线只能扫描通过设定阈值的标签，手持式读取器需要人员出去手动扫描物品。几家公司已经解决了无人机读取RFID的技术问题。配有RFID读卡器的无人机可以代替库存盘点的人物，并以更少的麻烦更快地完成工作。一个人需要梯子或电梯进入的高箱，可以通过无人机很容易地达到，无人机可以被编程为独立地导航空间，并且他们比执行大规模的重复任务的准确性和效率要比人类更好。目前市场上的RFID无人机需要庞大的读卡器才能连接到无人机的本身。这意味着它们必须足够大，以支持附加硬件的尺寸和重量，使其存在坠机风险。麻省理工学院的新解决方案，名为Rfly，允许无人机阅读RFID标签，而不用捆绑巨型读卡器。相反，无人机配备了一个微小的继电器，它像Wi-Fi中继器一样。无人机接收从远程RFID读取器发送的信号，然后转发它读取附近的标签。由于继电器很小，这意味着可以使用更小巧的无人机，可以使用塑料零件，可以适应较窄的空间，不会造成人身伤害的危险。麻省理工学院的Rfly系统本质上是对现有技术的一个聪明的补充，它不仅消除了额外的RFID读取器，而且由于它是一个更轻的解决方案，允许小型无人机与大型无人机做同样的工作。研究团队正在马萨诸塞州的零售商测试该系统。");
//        ResultVo res = NLPC.titlepredictor("麻省理工学院的研究团队为无人机在仓库中使用RFID技术进行库存查找等工作，创造了一种聪明的新方式。它允许公司使用更小，更安全的无人机在巨型建筑物中找到之前无法找到的东西。使用RFID标签更换仓库中的条形码，将帮助提升自动化并提高库存管理的准确性。与条形码不同，RFID标签不需要对准扫描，标签上包含的信息可以更广泛和更容易地更改。它们也可以很便宜，尽管有优点，但是它具有局限性，对于跟踪商品没有设定RFID标准，“标签冲突”可能会阻止读卡器同时从多个标签上拾取信号。扫描RFID标签的方式也会在大型仓库内引起尴尬的问题。固定的RFID阅读器和阅读器天线只能扫描通过设定阈值的标签，手持式读取器需要人员出去手动扫描物品。几家公司已经解决了无人机读取RFID的技术问题。配有RFID读卡器的无人机可以代替库存盘点的人物，并以更少的麻烦更快地完成工作。一个人需要梯子或电梯进入的高箱，可以通过无人机很容易地达到，无人机可以被编程为独立地导航空间，并且他们比执行大规模的重复任务的准确性和效率要比人类更好。目前市场上的RFID无人机需要庞大的读卡器才能连接到无人机的本身。这意味着它们必须足够大，以支持附加硬件的尺寸和重量，使其存在坠机风险。麻省理工学院的新解决方案，名为Rfly，允许无人机阅读RFID标签，而不用捆绑巨型读卡器。相反，无人机配备了一个微小的继电器，它像Wi-Fi中继器一样。无人机接收从远程RFID读取器发送的信号，然后转发它读取附近的标签。由于继电器很小，这意味着可以使用更小巧的无人机，可以使用塑料零件，可以适应较窄的空间，不会造成人身伤害的危险。麻省理工学院的Rfly系统本质上是对现有技术的一个聪明的补充，它不仅消除了额外的RFID读取器，而且由于它是一个更轻的解决方案，允许小型无人机与大型无人机做同样的工作。研究团队正在马萨诸塞州的零售商测试该系统。");
//        ResultVo res = NLPC.ecnet("百度是一家人工只能公司，360是一家人工智能工司，腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工只能公司，腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司");
//        ResultVo res = NLPC.simnet("麻省理工学院为无人机配备RFID技术，进行仓库货物管理","配备RFID技术，进行仓库");

        if(res.isOk())
            System.out.println("result:" + res.getT());
        else
            System.out.println(res.getMess());
    }

    @Test
    public void test_RetrievalController() throws JSONException {
        RetrievalController NLPC = new RetrievalController();
        NLPC.setApiKey("eHltBclr7s1kbGiSRQlvz1PY");
        NLPC.setSecretKey("gDWzGQljWFsa5uuTzgd3TGxVp0GyLlUg");
        NLPC.setAccessToken();
//        ResultVo res = NLPC.create_vidpress("https://www.sohu.com/a/519497144_121106994", 60, "1280x720", 4);
//        ResultVo res = NLPC.create_vidpress_alignmen_doc("https://www.sohu.com/a/519497144_121106994", 60, "1280x720", 4,null);
//        ResultVo res = NLPC.query_vidpress("81660");  ///75921  75924  75925  76141  77003  81660
//        ResultVo res = NLPC.delete_vidpress("75921");  ///75921  75924  75925  76141
//        ResultVo res = NLPC.hot_list("国内");
        ResultVo res = NLPC.vein_list("",1);
//        ResultVo res = NLPC.hot_list_advanced("国内",null,null,null);
//        ResultVo res = NLPC.news_summary(null,null,null);
        //下面都是图文素材转视频的测试
//        Text2video1 t2v = new Text2video1();
//        Text2video1.TracksTexts t1 = new Text2video1.TracksTexts();
//        t1.setText("近日《快乐大本营》主持人收礼物一事引起了网友们的热议，有网友发帖翻出近两年何炅与沈梦辰两个人在节目上的对话跟主持人收礼的文章，让何炅等人被置于舆论风口浪尖。网友们质疑何炅等人收取嘉宾粉丝跟后援会礼物的行为，何炅的人设变得岌岌可危");
//        Text2video1.Track track = new Text2video1.Track();
//        track.setUrl("https://pics2.baidu.com/feed/d50735fae6cd7b893eba201f416885a0d8330e2f.jpeg?token=a48334672ef195d05f6b478811ca2b26&s=5590139D5842D2CE5E0802F20300A036");
//        track.setType("image");
//        track.setExpectedDuration("10");
//        t1.getTracks().add(track);
//        t2v.getTrackTexts().add(t1);
//        t1 = new Text2video1.TracksTexts();
//        t1.setText("在节目上沈梦辰提到跟海涛交往的时候，海涛到自己家拜访爸妈的时候就会拿应援品过去，连上面的标都没有拆。何炅也讲述了自己的遭遇，因为收到的礼物上有不干胶，根本无法转送，自己有二十多个保温杯跟五十多支笔，还表示彭昱畅粉丝送的按摩椅好用。随着事情发酵，何炅收礼的画面也被曝光");
//        track = new Text2video1.Track();
//        track.setUrl("https://pics0.baidu.com/feed/0dd7912397dda144a7411780fffb17a50cf4860f.jpeg?token=1e7b26c31e10629c19e9b007b8993ca7&s=B5B11D99548340E80609B6FB0300F024");
//        track.setType("image");
//        track.setExpectedDuration("10");
//        t1.getTracks().add(track);
//        t2v.getTrackTexts().add(t1);
//        t2v.setVideoTitle("(测试)《快乐大本营》主持人收礼物事件");
//        t2v.setTotalDuration("60");
//        t2v.setResolution("1280x720");
//        t2v.setTtsPer("0");
//        ResultVo res = NLPC.create_vidpress_alignmen(t2v);

//        System.out.println((String) res.getT());
//        JSONObject jo = new JSONObject((String) res.getT());
//        try {
//            FileWriter fw = new FileWriter("E:\\JAVA_workspace\\t1.txt");
//            fw.write((String) res.getT());
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if(res.isOk())
            System.out.println("result::" + res.getT());
        else
            System.out.println(res.getMess());
    }

    @Test
    public void test_ReviewController() {
        ReviewController NLPC = new ReviewController();
        NLPC.setApiKey("rWKKOKTAcov73GpTRZkGu9zF");
        NLPC.setSecretKey("Omas1jvYGTsQYOd45nETzpZmqlwNHEdx");
        NLPC.setAccessToken();
        System.out.println(NLPC.getAccessToken());
        String[] str = new String[2];
        str[0]="百度是一家人工只能公司，360是一家人工智能工司，腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工只能公司，腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司腾讯是一家人工智能公司";
        str[1]="谷歌是一家人工智能公司";
        ResultVo res = NLPC.ai_review(str);
//        ResultVo res = NLPC.news_summary(null,null,null);
        if(res.isOk())
            System.out.println("result:" + res.getT());
//        else
//            System.out.println(res.getMess());
    }

    @Test
    public void test_CustomizedController() throws JSONException {
        CustomizedController NLPC = new CustomizedController();
        NLPC.setApiKey("eHltBclr7s1kbGiSRQlvz1PY");
        NLPC.setSecretKey("gDWzGQljWFsa5uuTzgd3TGxVp0GyLlUg");
        NLPC.setAccessToken();
//        ResultVo res = NLPC.weather("day","广东省","深圳市","南山区","2021-10-11","2021-10-11");
//        ResultVo res = NLPC.weather_warning(null,"广东省",null,null,null,null,null);
//        ResultVo res = NLPC.weather_warning("预警","广东省","珠海市",null,"2021-08-30","2021-08-30",null);
//        ResultVo res = NLPC.sports_events("战报","NBA","马刺","2021-10-11","2021-10-11");
//        stock_code_list.add("600036");
//        stock_code_list.add("006606");
//        stock_code_list.add("003185");
//        ResultVo res = NLPC.company_financial_report("年度", stock_code_list,"2020-01-01","2021-01-02");
        ResultVo res = NLPC.shares("收盘","600036","2020-01-01","2021-01-02");
//        List<String> lis = new ArrayList<>();
//        lis.add("002410");
//        ResultVo res = NLPC.company_financial_report("年度", lis,"2020","2020");
        if(res.isOk())
            System.out.println("result::" + res.getT());
        else
            System.out.println(res.getMess());

//        JSONObject jsonObject = new JSONObject((String) res.getT());
//        System.out.println(jsonObject.getString("status"));
    }

    @Test
    public void test_NationalEmergencyReptileServiceImpl() throws Exception {
        String Word = "{\"title\":\"【NBA战报】泰伦斯·罗斯20分难救主 魔术客场憾负马刺\",\"paragraphs\":[\"北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。\",\"【双方表现】\",\"魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板\",\"马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板\"]}";
//        BaiduReptileImpl bri = new BaiduReptileImpl();
//        List<Map<String, String>> res = bri.imageReptile(Word,10); //1代表下载一页，一页一般有30张图片
//        for (Map<String, String> map : res) {
//            System.out.println(map.get("url"));
//        }
        NationalEmergencyReptileServiceImpl nationalEmergencyReptile = new NationalEmergencyReptileServiceImpl();
//        Map<String, Object> res = nationalEmergencyReptile.news_xw("szzl",1);
//        List<Map<String, String>> ttt = (List<Map<String, String>>)res.get("news_list");
//        System.out.printf("页数：%d,总条数：%d\n",Integer.parseInt((String) res.get("countPage")),Integer.parseInt((String) res.get("dataCount")));
//        for (Map<String, String> t : ttt
//             ) {
//            System.out.println(t.get("title")+t.get("time")+t.get("url"));
//        }

        Map<String, Object> res = nationalEmergencyReptile.analysisHtmlContent("https://www.mem.gov.cn/xw/mtxx/202203/t20220311_409427.shtml");
//        Map<String, Object> res = nationalEmergencyReptile.analysisHtmlContent("http://www.gov.cn/xinwen/2022-04/13/content_5684960.htm");
//        Map<String, Object> res = nationalEmergencyReptile.analysisHtmlContent("https://www.mem.gov.cn/xw/yjyw/202204/t20220411_411436.shtml");
        String news_list = (String)res.get("news_content");
        System.out.println(news_list);
        String s2 = (String)res.get("title");
        System.out.println(s2);
        s2 = (String)res.get("time_laiy");
        System.out.println(s2);
    }

    @Test
    public void test_BaiduReptileServiceImpl() throws Exception {
//        String Word = "4月24日，应急管理部党委书记、部长黄明主持召开部党委会和部务会，深入学习贯彻习近平总书记重要指示精神，分析近期全国安全生产形势，研究部署防控重大安全风险工作。黄明强调，要坚决贯彻落实习近平总书记重要指示精神和党中央、国务院决策部署，以强烈的政治责任感使命感抓紧抓实重大安全风险防控工作，精准研判、创新方式、完善机制，更加专业高效做好常态化疫情防控条件下安全监管工作，牢牢守住不发生重特大事故的红线底线，为党的二十大胜利召开营造安全稳定环境。";
//        String Word = "青藏铁路，应急管理部党委书记、部长黄明主持召开部党委会和部务会";
        String Word = "iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下，iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下,iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下";
//        String Word = "";
//        BaiduReptileServiceImpl baiduReptileService = new BaiduReptileServiceImpl();
//        List<Map<String, String>> res = baiduReptileService.imageReptile(Word,6,true);
//        System.out.println(res.size());
//        for (int i = 0; i < res.size(); i++) {
//            System.out.println(res.get(i).get("url"));
//        }

        NLPController NLPC = new NLPController();
        NLPC.setApiKey("d0ZmMHyBgj9S3FiWkGZEXCMV");
        NLPC.setSecretKey("4nPsqIPZIAPMntaF2BomhEGGdtiGSxwy");
        NLPC.setAccessToken();
        ReptileController reptileController = new ReptileController();
        reptileController.setNlpController(NLPC);
        System.out.println(reptileController.keyWordByNLP(Word));
    }

    @Test
    public void test_ArticleServiceImpl() throws Exception {
        Article article = new Article();
        ArticleService articleService = new ArticleServiceImpl();
        String realPath = "E:\\JAVA_workspace\\AIwriter\\src\\main\\webapp\\";
//        article.setTitle("【NBA战报】泰伦斯·罗斯20分难救主 魔术客场憾负马刺");
//        article.setContent("<p>北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。</p><p>【双方表现】</p><p>魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板</p><p>马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\1646547692349篮球2.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>");
//        ReviewController reviewController = new ReviewController();
//        reviewController.text_score(article);

//        article.setTitle("【NBA战报】泰伦斯·罗斯20分难救主 魔术客场憾负马刺");
//        article.setContent("<p><img src=\"/upload/user/admin/1646635114718stock1.jpg\" style=\"width:60%;\" alt=\"\"></p><p>【双方表现】</p><p></p><p>马刺方面：德章泰·穆雷得</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\1646547692349篮球2.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>");
//        List<String>[] file_name =articleService.checkMaterial(article,realPath);
//        System.out.println(file_name[0]);
//        System.out.println(file_name[1]);
//        articleService.copyMaterial(file_name);

//        String preContent = "<p><img src=\"/upload/user/2022-04-05/1646635114718stock1.jpg\" style=\"width:60%;\" alt=\"\"><img src=\"/upload/temp_public/2022-04-05/164stock1.jpg\" style=\"width:60%;\" alt=\"\"></p><p>【双方表现】</p><p></p><p>马刺方面：德章泰·穆雷得</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/user/2022-03-06\\1646547692349篮球2.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>";
        String preContent = "";
        String nowContent = "<p><img src=\"/upload/user/admin/16466351stock1.jpg\" style=\"width:60%;\" alt=\"\"></p><p>【双方表现】</p><p></p><p>马刺方面：德章泰·穆雷得</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/user/2022-03-06\\1646547692349篮球2.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>";
        List<String>[] preMaterial =articleService.compareMaterial(preContent,nowContent);
        for (List<String> mat:preMaterial) {
            System.out.println(mat);
        }
    }

    @Test
    public void test_EvaluateController() throws Exception {
        EvaluateController evaluateController = new EvaluateController();
        String title = "《求是》杂志发表习近平总书记重要文章在中央政协工作会议暨庆祝中国人民政治协商会议成立70周年大会上的讲话";
        String content = "<p>新华社北京3月15日电 03月16日出版的第6期《求是》杂志将发表中共中央总书记、国家主席、中央军委主席习近平的重要文章《在中央政协工作会议暨庆祝中国人民政治协商会议成立70周年大会上的讲话》。</p><p>文章强调，新时代加强和改进人民政协工作，要以新时代中国特色社会主义思想为指导，增强“四个意识”、坚定“四个自信”、做到“两个维护”，把坚持和发展中国特色社会主义作为巩固共同思想政治基础的主轴，把服务实现“两个一百年”奋斗目标作为工作主线，把加强思想政治引领、广泛凝聚共识作为中心环节，坚持团结和民主两大主题，提高政治协商、民主监督、参政议政水平，更好凝聚共识，担负起把党中央决策部署和对人民政协工作要求落实下去、把海内外中华儿女智慧和力量凝聚起来的政治责任，为全面建设社会主义现代化强国作出贡献。</p><p>文章指出，人民政协是中国共产党把马克思列宁主义统一战线理论、政党理论、民主政治理论同中国实际相结合的伟大成果，是中国共产党领导各民主党派、无党派人士、人民团体和各族各界人士在政治制度上进行的伟大创造。70年来，在中国共产党领导下，人民政协坚持团结和民主两大主题，服务党和国家中心任务，在建立新中国和社会主义革命、建设、改革各个历史时期发挥了十分重要的作用。</p><p>文章指出，党的十八大以来，我们总结经验，对人民政协工作提出了一系列新要求。一是加强党对人民政协工作的领导。二是准确把握人民政协性质定位。三是发挥好人民政协专门协商机构作用。四是坚持和完善我国新型政党制度。五是广泛凝聚人心和力量。六是聚焦党和国家中心任务履职尽责。七是坚持人民政协为人民。八是以改革创新精神推进履职能力建设。实践证明，人民政协制度具有多方面的独特优势。</p><p>文章指出，新形势下，我们必须把人民政协制度坚持好、把人民政协事业发展好，增强开展统一战线工作的责任担当，把更多的人团结在党的周围。当前和今后一个时期，人民政协尤其要抓好以下工作。第一，发挥人民政协专门协商机构作用。在中国社会主义制度下，有事好商量、众人的事情由众人商量，找到全社会意愿和要求的最大公约数，是人民民主的真谛。发展社会主义协商民主，要把民主集中制的优势运用好，发扬“团结—批评—团结”的优良传统，广开言路，集思广益，促进不同思想观点的充分表达和深入交流，做到相互尊重、平等协商而不强加于人，遵循规则、有序协商而不各说各话，体谅包容、真诚协商而不偏激偏执，形成既畅所欲言、各抒己见，又理性有度、合法依章的良好协商氛围。第二，加强思想政治引领、广泛凝聚共识。人民政协要通过有效工作，努力成为坚持和加强党对各项工作领导的重要阵地、用党的创新理论团结教育引导各族各界代表人士的重要平台、在共同思想政治基础上化解矛盾和凝聚共识的重要渠道。要正确处理一致性和多样性的关系。第三，强化委员责任担当。广大政协委员要坚持为国履职、为民尽责的情怀，把事业放在心上，把责任扛在肩上，认真履行委员职责，政治立场不能含糊、政治原则不能动摇。</p>";
        ResultVo res = evaluateController.news_score(title,content);

        if(res.isOk())
            System.out.println("result_vo:" + res.getT());
        else
            System.out.println(res.getMess());
    }

    @Test
    public void test_SearchController() throws Exception {
        SearchController evaluateController = new SearchController();
        evaluateController.setSearchAuthorization("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwidXNlcl9pZCI6OSwianRpIjoiODUxNThjMzIyNzU0NDQyM2FhMWEyZmE3YWQzNjAxNzciLCJleHAiOjE4Njc2NDc0NzN9.w71rTW_aBb7G4B-Xq4q5YiFlIH4oqWpYjq93gtAclZU");
        ResultVo res = evaluateController.correlation_list("浙江",null,null);
        if(res.isOk())
            System.out.println("result_vo:" + res.getT());
        else
            System.out.println("请求出错:" + res.getMess());
    }
}

