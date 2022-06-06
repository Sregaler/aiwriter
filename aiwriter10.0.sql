-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: aiwriter
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_article` (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章标题',
  `digest` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '摘要',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '文章内容，一个html的url',
  `type` int DEFAULT NULL COMMENT '0:图文 1:视频',
  `cid` int DEFAULT '1' COMMENT '所属标签分类',
  `visit_count` int DEFAULT NULL COMMENT '访问量',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_hot` int DEFAULT NULL COMMENT '是否热门 0：不热门 1：热门',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章的logo,保存一个url',
  `uid` int DEFAULT NULL COMMENT '发布者',
  `isOpen` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '是否公开 0:不公开 1:公开',
  `thumbsUp` bigint DEFAULT NULL COMMENT '点赞数',
  `tagNames` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章标签',
  `isCommented` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否被评论',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='稿件信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article`
--

LOCK TABLES `t_article` WRITE;
/*!40000 ALTER TABLE `t_article` DISABLE KEYS */;
INSERT INTO `t_article` VALUES (76,'「深圳南山」10/11，晴，23~30℃，无持续风向3~4级，空气质量优',NULL,'<p>深圳南山，今日晴，白天最高气温30℃，夜间最低温度23℃，无持续风向3~4级，空气质量优。</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004774841.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004775032.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,0,0,'2022-03-06 12:14:13','2022-03-06 12:14:13',0,'/upload/temp_public\\2022-03-01\\16461004774841.jpg',1,'0',0,NULL,'0'),(77,'【雷雨大风预警】8月30日珠海市气象台发布雷雨大风黄色预警',NULL,'<p>珠海市气象台于08月30日12时55分发布珠海市雷雨大风黄色预警信号：</p><p>请注意防御。</p><p>（信息来源：国家预警发布中心）</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461005686764.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p></p><hr><p></p><p></p><h3>防御指南</h3><p></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004775043.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-06 12:17:41','2022-03-06 12:17:41',0,'/upload/temp_public\\2022-03-01\\16461005686764.jpg',1,'0',0,NULL,'0'),(81,'哪一刻，触动了你？值得铭记的冬奥会十大感人瞬间',NULL,'<p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">极目新闻记者</font> 徐平综合报道</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">抗癌斗士帕罗特、八战冬奥的</font>“速滑大妈”、历经四届冬奥终于圆梦的徐梦桃……自2月4日开幕的北京冬奥会，除了呈现一场又一场的精彩比赛外，我们还借此认识了这些特别的运动员，更目睹了赛场内外不少的感人瞬间。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><b><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">NO.1 夺冠之后，谷爱凌不忘抚慰失意人</font></span></b><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">8日，谷爱凌在自由式滑雪女子大跳台决赛中逆转夺冠，不过当看到与冠军失之交臂的法国选手泰丝·勒德失望落泪时，谷爱凌以及获得铜牌的瑞士选手玛蒂尔德·格雷莫德赶忙上前抚慰对方。这一幕，让全场的观众为之动容。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\"><br></font></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465469655591.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"center\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:center;\nvertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">谷爱凌抚慰失意的法国选手</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><b><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">NO.2 时隔34年，父子“接力”夺冠</font></span></b><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">2月10日，奥地利选手施特罗尔茨夺得高山滑雪男子全能冠军。而34年前，他的父亲休伯特在卡尔加里冬奥会上拿过同一项目的金牌。施特罗尔茨此前曾一度因成绩不佳退出国家队，冬奥会前赢得一站世界杯冠军，他才搭上北京冬奥会的末班车。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><b><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">NO.3 “速滑大妈”八战冬奥</font></span></b><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">在短道速滑女子</font>3000米的比赛中，49岁的“速滑大妈”克劳迪娅·佩希施泰因成绩垫底，但她却像胜利者一般，迎接属于自己的荣耀时刻——她是历史上年纪最大的女子冬奥选手，八次出战冬奥，总共获得5金2银2铜。佩希施泰因说：“成绩已不再重要，重要的是我来到了这里，滑到了终点。”</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><br></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465470048072.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"center\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:center;\nvertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">“速滑大妈”佩希施泰因</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p>',0,1,0,'2022-03-06 14:10:28','2022-03-06 14:12:39',0,'/upload/temp_public\\2022-03-06\\16465469655591.png',1,'0',0,NULL,'0'),(82,'「广联达」今日发布2020年年度财报',NULL,'<p>3月30日，广联达（002410）发布年度公告，公司在2020年（全年）实现营业收入39.47亿元，同比增长13.94%，资产总额同比增加54.80%达到95.47亿元。</p><p>归属于上市公司股东的净利润为3.30亿元，同比增长40.55%。</p><p>根据该财报，截止目前每股收益为0.2867元，同时，归属股东的净资产为64.02亿元，与上年度末相比增长95.83%。</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465475692684.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-06 14:19:49','2022-03-06 14:19:49',0,'/upload/temp_public\\2022-03-06\\16465475692684.png',1,'0',0,NULL,'0'),(83,'收市简讯：「招商银行」今日股价下跌0.49%，报收42.89元',NULL,'<p>北京时间12月21日，招商银行开盘报价43.04元，收盘于42.89元，下跌0.49%。当日最高价为43.15元，最低达42.31元，成交量62.39万手，总市值为10816.79亿元。</p><p>最近两个交易日，招商银行连续下跌，跌幅分别为-2.16%和-0.49%，最高价降到42.89元，总市值缩减了292.55亿元。</p><p>在近30个交易日中，招商银行有14天上涨。期间整体上涨了1.52%，最高价为46.80元，最低价为42.58元。和30个交易日前相比，招商银行的市值下跌了209.33亿元。</p><p>再来看下板块方面，招商银行所属的融资融券板块共有385支个股，本交易日整体上涨0.95%，板块中领涨的个股是华银电力(涨幅10.17%)、古越龙山(涨幅10.04%)、金枫酒业(涨幅10.04%)，跌幅较大的个股是*ST金钰(跌幅5.26%)、美邦服饰(跌幅4.87%)。</p><p>招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨0.76%，报收于3420.57点。</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\1646547636332stock4.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-06 14:20:46','2022-03-06 14:20:46',0,'/upload/temp_public\\2022-03-06\\1646547636332stock4.jpg',1,'0',0,NULL,'0'),(84,'【NBA战报】泰伦斯·罗斯20分难救主 魔术客场憾负马刺',NULL,'<p>北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。</p><p>【双方表现】</p><p>魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板</p><p>马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\1646547692349篮球2.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-06 14:21:36','2022-03-06 14:21:36',0,'/upload/temp_public\\2022-03-06\\1646547692349篮球2.jpg',1,'0',0,NULL,'0'),(85,'「深圳南山」10/11，晴，23~30℃，无持续风向3~4级，空气质量优',NULL,'<p>深圳南山，今日晴，白天最高气温30℃，夜间最低温度23℃，无持续风向3~4级，空气质量优。</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004774841.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004775032.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-06 14:22:06','2022-03-06 14:22:06',0,'/upload/temp_public\\2022-03-01\\16461004774841.jpg',1,'0',0,NULL,'0'),(86,'【雷雨大风预警】8月30日珠海市气象台发布雷雨大风黄色预警',NULL,'<p>珠海市气象台于08月30日12时55分发布珠海市雷雨大风黄色预警信号：</p><p>请注意防御。</p><p>（信息来源：国家预警发布中心）</p><p></p><hr><p></p><p></p><h3>防御指南</h3><p></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461005686764.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004775043.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-06 14:22:15','2022-03-06 14:22:15',0,'/upload/temp_public\\2022-03-01\\16461005686764.jpg',1,'0',0,NULL,'0'),(87,'“云流转”土地700多万亩 交易金额逾80亿元 看看昌吉州农村综合产权交易平台有',NULL,'<p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">天山网讯（记者</font> 盖有军 通讯员 王薇报道）<font face=\"inherit\">“</font><font face=\"宋体\">今年通过农村综合产权交易平台竞价交易，我们村</font><font face=\"inherit\">3000</font><font face=\"宋体\">亩耕地的土地流转费比过去翻了一番，每年多收入了</font><font face=\"inherit\">90</font><font face=\"宋体\">万元。</font><font face=\"inherit\">” 3</font><font face=\"宋体\">月</font><font face=\"inherit\">2</font><font face=\"宋体\">日，说起今年村里土地流转的事，奇台县西地镇西地村党委书记、村委会主任李炬军脸上挂满了笑容。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\"><br></font></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465478633725.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\">&nbsp; &nbsp;2022<font face=\"宋体\">年</font><font face=\"inherit\">3</font><font face=\"宋体\">月</font><font face=\"inherit\">2</font><font face=\"宋体\">日，工作人员在昌吉州农村综合产权交易平台上登记审核土地流转数据。王薇摄</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">　　今年</font><font face=\"inherit\">2</font><font face=\"宋体\">月</font><font face=\"inherit\">22</font><font face=\"宋体\">日，奇台县西地镇西地村</font><font face=\"inherit\">3000</font><font face=\"宋体\">亩耕地承包经营权转让项目，通过昌吉回族自治州农村综合产权交易平台竞价流转，最终成交价达到每亩每年</font><font face=\"inherit\">600</font><font face=\"宋体\">元，竞标人一次性支付</font><font face=\"inherit\">7</font><font face=\"宋体\">年土地流转费，共计</font><font face=\"inherit\">1260</font><font face=\"宋体\">万元。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">　　李炬军介绍，近几年不少村民外出务工，将土地流转给种植大户，每亩每年流转费大约</font><font face=\"inherit\">300</font><font face=\"宋体\">元左右，流转价格较低，且每次支付一年的费用，村民们无法进行大规模的生产投资。</font><font face=\"inherit\">2021</font><font face=\"宋体\">年</font><font face=\"inherit\">9</font><font face=\"宋体\">月，西地村将这</font><font face=\"inherit\">3000</font><font face=\"宋体\">亩耕地的流转信息上传至昌吉州农村综合产权交易平台后，吸引了</font><font face=\"inherit\">14</font><font face=\"宋体\">名竞标人参与竞拍。每亩最终成交的价格，比过去增加了一倍，村民们都很满意。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">　　西地村村民吴永清说：</font><font face=\"inherit\">“</font><font face=\"宋体\">今年我家的</font><font face=\"inherit\">20</font><font face=\"宋体\">多亩土地流转后，一年有</font><font face=\"inherit\">1</font><font face=\"宋体\">万多元的收入。土地流转后，我在村里的合作社打工，合计一年收入能有</font><font face=\"inherit\">5</font><font face=\"宋体\">万多元。</font><font face=\"inherit\">”</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">　　昌吉回族自治州农村综合产权交易中心总经理朱衷卫介绍说，昌吉州是农业大州。近年来，随着农村外出务工人员增多，农村土地流转数量不断增加，随之出现的土地流转供需不对称、交易不规范、无法实现溢价等的问题也日益突出，深化改革显得尤为迫切与重要。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">　　</font><font face=\"inherit\">“</font><font face=\"宋体\">自</font><font face=\"inherit\">2017</font><font face=\"宋体\">年底以来，随着昌吉州农村综合产权交易平台的建立和全面应用，用大数据盘活了人、地、钱三要素，促进了农村生产要素的合理配置，催生和培育了大量新型经营主体，推动农业生产从零散化向规模化、集约化、标准化转变，为乡村振兴注入了动力与活力。</font><font face=\"inherit\">” </font><font face=\"宋体\">朱衷卫说。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">　　据朱衷卫介绍，通过昌吉州农村综合产权交易平台，将全州农村土地基础信息、权属信息、流转信息、交易信息和综合服务融为一体，使全州农村土地交易行为全程处于平台监管之下，能够有效增强土地交易行为的便利性和规范性，减少纠纷隐患。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p>',0,1,0,'2022-03-06 14:24:40','2022-03-06 14:24:40',0,'/upload/temp_public\\2022-03-06\\16465478633725.png',1,'0',0,NULL,'0'),(88,'谈判要来了？俄称：俄罗斯代表团已经抵达白俄罗斯！多国向乌克兰提供军事援助',NULL,'<p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">27日，俄罗斯对乌克兰顿巴斯地区的</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">特别军事行动进入第四天</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">乌克兰多地战火持续</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">俄乌谈判有了最新消息</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">刚刚消息</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">俄总统新闻秘书：</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">俄罗斯谈判代表团已抵达白俄罗斯</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">泽连斯基：</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">已准备好与俄会谈，但不是在白俄罗斯</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">当地时间</font>2月27日，俄罗斯总统新闻秘书佩斯科夫表示，按照约定，由俄外交部、国防部和其他机构，</span><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">包括总统办公厅代表组成的俄罗斯代表团已经抵达白俄罗斯，准备与乌克兰方面进行会谈。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">佩斯科夫说，会谈计划在戈梅利州举行。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">对此，乌克兰总统泽连斯基表示，</font></span><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">他知晓俄方关于会谈的提议，乌克兰希望与俄罗斯会谈并达成和平，会谈地点可以在其他城市，但是不能在白俄罗斯</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">，华沙、布达佩斯或伊斯坦布尔都可以考虑。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">此外，据白俄罗斯外交部消息，白俄罗斯已为俄乌在戈梅利州的会谈做好一切必要准备。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><img src=\"http://localhost:8000/upload/temp_public/2022-03-06/16465479618266.png\" alt=\"\" style=\"text-align: center; width: 491.484px;\"><span style=\"text-align: center;\">&nbsp;</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\"><br></font></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">另据界面新闻：塔斯社</font>2月27日消息，</span><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">乌克兰总统泽连斯基向白俄罗斯人发表讲话，表示希望该国</font>“再次变得友善和安全”。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">泽连斯基表示，他知道有关俄方在白俄罗斯举行俄乌谈判的提议。根据泽连斯基的说法，如果没有敌对行动，乌克兰会同意在白俄罗斯举行谈判。泽连斯基称，其他地方可以成为谈判平台，但白俄罗斯不行。泽连斯基提出可以在华沙、布达佩斯、伊斯坦布尔和巴库与俄罗斯谈判。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">据乌克兰《真理报》报道，泽连斯基表示：</font>“</span><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">任何其他城市都适合我们</font>——在一个不向乌克兰发射导弹的国家</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">。这是诚实谈判并真正结束战争的唯一途径。</font>”</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">据央视新闻此前报道，当地时间</font>26日，俄总统新闻秘书佩斯科夫表示，乌克兰方面已拒绝和谈，俄方的军事行动将继续。佩斯科夫说，俄罗斯总统普京25日曾下令俄军停止前进，但是乌克兰方面拒绝谈判，俄军恢复军事行动计划，主力部队26日下午继续推进。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><br></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465479618266.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">多地战火持续</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">乌克兰战略通讯中心：俄军已进入哈尔科夫市中心</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">当地时间</font>27日，乌克兰第二大城市哈尔科夫地区发生炮战，为俄乌开战以来最猛烈的一次。</span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">当地时间</font>27日，乌克兰战略通讯中心消息说，俄军已进入哈尔科夫市中心。目前俄方暂无回应。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">俄罗斯国防部当地时间</font>2月27日表示，乌克兰军队第302防空导弹团自愿放下武器投降，471人被俘。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">目前乌克兰方面尚无回应。</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">乌克兰首都基辅街上发生猛烈交火</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">基辅当地时间</font>26日21时左右，在洛巴诺夫斯基大街，长时间防空警报后，发生猛烈交火。此外，当地时间26日夜间，基辅基础设施部附近的第聂伯河右岸发生战斗。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">当地时间</font>27日，基辅市中心可以清楚听到远处传来的射击声。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span class=\"15\" style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">位于基辅市西南方向的两地遭打击</font></span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><font face=\"微软雅黑\">当地时间</font>27日，位于基辅西南方向的瓦西里科夫市遭导弹攻击，并引发大火。瓦西里科夫市长确认，该市一座油库遭到了导弹袭击。同样位于基辅市西南方向的格列瓦哈镇也遭到打击。</span><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:0.0000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: 微软雅黑; font-size: 13.5pt;\"><br></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465479859507.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p style=\"text-align:center;\">&nbsp;<video controls=\"\" src=\"/upload/temp_public\\2022-03-06\\1646548183922561.mp4\" style=\"width:60%;\">&nbsp;</video></p>',0,1,0,'2022-03-06 14:29:53','2022-03-06 14:29:53',0,'/upload/temp_public/2022-03-06/16465479618266.png',1,'0',0,NULL,'0'),(89,'勠力同心 和衷共济——香港社会各界积极行动共战疫情',NULL,'<p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465482792778.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　</font><font face=\"inherit\">2</font><font face=\"微软雅黑\">月</font><font face=\"inherit\">26</font><font face=\"微软雅黑\">日，工作人员在青衣方舱医院施工。 新华社记者 吕小炜 摄</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　新华社香港</font><font face=\"inherit\">3</font><font face=\"微软雅黑\">月</font><font face=\"inherit\">1</font><font face=\"微软雅黑\">日电（记者黄茜恬）香港第五波新冠肺炎疫情持续，每日确诊病例居高不下，牵动着每一位市民的心。连日来，香港社会各界纷纷表达对前线抗疫人员的感激与敬意，并用实际行动抗击疫情。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　因应严峻疫情，全港学校将提前放假以避开疫情高峰。年幼的学生虽然未能走上抗疫前线，但抗疫之心同样坚定。他们通过写信、画画和拍摄短片的形式为前线抗疫工作者加油打气。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">尊敬的抗疫英雄，你们好！我知道我每天能够安心上网课，是因为我的身后有你们。我不知道你们的样子，但我知道那一定是勇敢的样子。因为你们从一个家庭中出来，去守护千千万万的家庭</font><font face=\"inherit\">……</font><font face=\"微软雅黑\">我想谢谢你们守护着我。在我心里，你们是最棒的！</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">圣士提反堂中学学生谢聪霞在信中写道。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　连日来，香港市民期盼的内地援港医务人员陆续抵港，马不停蹄投入到工作中。许多市民纷纷在网上留言表示感谢中央支援。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">来的全部都是精英，香港一定赢！</font><font face=\"inherit\">”“</font><font face=\"微软雅黑\">衷心感谢国家照顾援手，感谢每位支援的医护。你们离开家人到港支援，辛苦你们了！这情谊铭记于心！</font><font face=\"inherit\">”“</font><font face=\"微软雅黑\">感谢国家，感谢内地医护团队！感谢你们在寒冷天气下为我们采样。</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">各种留言饱含感情。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　排队等待检测的市民对内地医护来港支援的行动表示感动，并说</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">检测速度明显变快</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">。有市民表示，若没有内地加派人手支援香港，香港难以应付这次疫情暴发带来的医疗压力。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\"><br></font></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465482934749.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\">3<font face=\"微软雅黑\">月</font><font face=\"inherit\">1</font><font face=\"微软雅黑\">日，在香港中环，市民佩戴口罩出行。 新华社记者 卢炳辉 摄</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　在爱国爱港力量带动下，香港社会各界纷纷行动，出人出力出钱投入抗疫。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　香港大学派出</font><font face=\"inherit\">24</font><font face=\"微软雅黑\">名医护人员及员工，到彩荣路体育馆的暂托中心，照顾由香港医院管理局转介而等候入院的染疫长者，以及康复中病情稳定但需要照料的长者。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　香港再出发大联盟发起</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">感谢内地援建设施、慰问辛劳建设者</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">活动，呼吁社会各界一同向内地援建香港隔离治疗设施团队致谢，并向所有前线工友致敬。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　大联盟</font><font face=\"inherit\">27</font><font face=\"微软雅黑\">日率先到青衣隔离设施工地慰问施工人员，并由香港立法会议员、香港再出发大联盟副秘书长黄英豪作为代表，捐赠</font><font face=\"inherit\">2</font><font face=\"微软雅黑\">万份快速抗原检测包及</font><font face=\"inherit\">5000</font><font face=\"微软雅黑\">盒连花清瘟胶囊等防疫物资。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　交通银行香港分行及交银（香港）向香港保良局提供</font><font face=\"inherit\">1.5</font><font face=\"微软雅黑\">万套新冠病毒快速测试套装、</font><font face=\"inherit\">20</font><font face=\"微软雅黑\">万个医学外科口罩及</font><font face=\"inherit\">2000</font><font face=\"微软雅黑\">支消毒搓手液等防疫物资。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　香港红十字会设立</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">香港红十字会抗疫同行热线</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">，一站式解答抗疫及最新防疫措施的问题，并提供心理支援及其他社区服务信息，并按需要提供食物包、防疫物资及快速检测剂等。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　香港圣约翰救护机构表示，将为建设方舱医院的工作人员提供全方位的紧急医疗支援。全国政协委员、香港圣约翰救伤会总监及理事会副主席李国栋在接受采访时表示，圣约翰救护机构将会派医生、护士和急救员驻守</font><font face=\"inherit\">8</font><font face=\"微软雅黑\">个工地，为工友们提供</font><font face=\"inherit\">24</font><font face=\"微软雅黑\">小时的医疗服务。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　他表示，目前机构正在加紧策划，制定全方位应变方案，希望能够贡献机构的专业能力，上下一心，同心抗疫。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"MsoNormal\"><span style=\"mso-spacerun:\'yes\';font-family:等线;mso-bidi-font-family:\'Times New Roman\';\nfont-size:10.5000pt;mso-font-kerning:1.0000pt;\"><o:p>&nbsp;</o:p></span></p>',0,1,0,'2022-03-06 14:31:52','2022-03-06 14:31:52',0,'/upload/temp_public\\2022-03-06\\16465482792778.png',1,'0',0,NULL,'0'),(90,'【雷雨大风预警】8月30日珠海市气象台发布雷雨大风黄色预警',NULL,'<p>珠海市气象台于08月30日12时55分发布珠海市雷雨大风黄色预警信号：</p><p>请注意防御。</p><p>（信息来源：国家预警发布中心）</p><p></p><hr><p></p><p></p><h3>防御指南</h3><p></p>',0,1,0,'2022-03-06 14:49:48','2022-03-06 14:49:48',0,'/img/other/article_def.png',1,'0',0,NULL,'0'),(91,'【雷雨大风预警】8月30日珠海市气象台发布雷雨大风黄色预警',NULL,'<p>珠海市气象台于08月30日12时55分发布珠海市雷雨大风黄色预警信号：</p><p>请注意防御。</p><p>（信息来源：国家预警发布中心）</p><p></p><hr><p></p><p></p><h3>防御指南</h3><p></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461005686764.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-01\\16461004775043.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p>中央纪委国家监委网站讯 据山东省纪委监委消息：山东省煤田地质局党委书记、局长李关宾涉嫌严重违纪违法，目前正接受山东省纪委监委纪律审查和监察调查。</p>',0,1,0,'2022-03-06 14:50:45','2022-03-06 14:50:45',0,'/upload/temp_public\\2022-03-01\\16461005686764.jpg',1,'0',0,NULL,'0'),(92,'交易金额逾80亿元 ',NULL,'<p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\">天山网讯（记者</font> 盖有军 通讯员 王薇报道）<font face=\"inherit\">“</font><font face=\"宋体\">今年通过农村综合产权交易平台竞价交易，我们村</font><font face=\"inherit\">3000</font><font face=\"宋体\">亩耕地的土地流转费比过去翻了一番，每年多收入了</font><font face=\"inherit\">90</font><font face=\"宋体\">万元。</font><font face=\"inherit\">” 3</font><font face=\"宋体\">月</font><font face=\"inherit\">2</font><font face=\"宋体\">日，说起今年村里土地流转的事，奇台县西地镇西地村党委书记、村委会主任李炬军脸上挂满了笑容。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"宋体\"><br></font></span></p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465495645225.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p style=\"text-align:center;\">&nbsp;<video controls=\"\" src=\"/upload/temp_public\\2022-03-06\\1646549577026561.mp4\" style=\"width:60%;\">&nbsp;</video></p>',0,1,0,'2022-03-06 14:53:09','2022-03-06 14:53:09',0,'/upload/temp_public\\2022-03-06\\16465495645225.png',1,'0',0,NULL,'0'),(93,'勠力同心 和衷共济——香港社会各界积极行动共战疫情',NULL,'<p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public\\2022-03-06\\16465496697858.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\">2<font face=\"微软雅黑\">月</font><font face=\"inherit\">26</font><font face=\"微软雅黑\">日，工作人员在青衣方舱医院施工。 新华社记者 吕小炜 摄</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　新华社香港</font><font face=\"inherit\">3</font><font face=\"微软雅黑\">月</font><font face=\"inherit\">1</font><font face=\"微软雅黑\">日电（记者黄茜恬）香港第五波新冠肺炎疫情持续，每日确诊病例居高不下，牵动着每一位市民的心。连日来，香港社会各界纷纷表达对前线抗疫人员的感激与敬意，并用实际行动抗击疫情。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　因应严峻疫情，全港学校将提前放假以避开疫情高峰。年幼的学生虽然未能走上抗疫前线，但抗疫之心同样坚定。他们通过写信、画画和拍摄短片的形式为前线抗疫工作者加油打气。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">尊敬的抗疫英雄，你们好！我知道我每天能够安心上网课，是因为我的身后有你们。我不知道你们的样子，但我知道那一定是勇敢的样子。因为你们从一个家庭中出来，去守护千千万万的家庭</font><font face=\"inherit\">……</font><font face=\"微软雅黑\">我想谢谢你们守护着我。在我心里，你们是最棒的！</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">圣士提反堂中学学生谢聪霞在信中写道。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　连日来，香港市民期盼的内地援港医务人员陆续抵港，马不停蹄投入到工作中。许多市民纷纷在网上留言表示感谢中央支援。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">来的全部都是精英，香港一定赢！</font><font face=\"inherit\">”“</font><font face=\"微软雅黑\">衷心感谢国家照顾援手，感谢每位支援的医护。你们离开家人到港支援，辛苦你们了！这情谊铭记于心！</font><font face=\"inherit\">”“</font><font face=\"微软雅黑\">感谢国家，感谢内地医护团队！感谢你们在寒冷天气下为我们采样。</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">各种留言饱含感情。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p class=\"p\" align=\"justify\" style=\"margin-top:0.0000pt;margin-bottom:22.5000pt;text-align:justify;\ntext-justify:inter-ideograph;vertical-align:baseline;line-height:22.5000pt;\"><span style=\"font-family: inherit; font-size: 12pt;\"><font face=\"微软雅黑\">　　排队等待检测的市民对内地医护来港支援的行动表示感动，并说</font><font face=\"inherit\">“</font><font face=\"微软雅黑\">检测速度明显变快</font><font face=\"inherit\">”</font><font face=\"微软雅黑\">。有市民表示，若没有内地加派人手支援香港，香港难以应付这次疫情暴发带来的医疗压力。</font></span><span style=\"font-family: inherit; font-size: 12pt;\"><o:p></o:p></span></p><p>3月2日上午，全市城乡治理标准化推进会召开，部署2022年度城乡治理标准化工作任务，调度安排当前重点工作。市委常委、市委秘书长、市标准化办主任尹培国出席会议并讲话。</p>',0,1,0,'2022-03-06 14:54:59','2022-03-06 14:54:59',0,'/upload/temp_public\\2022-03-06\\16465496697858.png',1,'0',0,NULL,'0'),(95,'分难救主 魔术客场憾负马刺',NULL,'<p>北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。</p><p>【双方表现】</p><p>魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板</p><p>马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板</p>',0,1,0,'2022-03-08 09:40:54','2022-03-08 09:40:54',0,'/img/other/article_def.png',1,'0',0,NULL,'0'),(96,'ceshiash',NULL,'<p>北京时间2021年10月11日，NBA战报：马刺主场作战，101比100击败魔术。泰伦斯·罗斯拿下全场最高的20分。</p><p>【双方表现】</p><p>魔术方面：泰伦斯·罗斯得到20分、2助攻和1个篮板；穆罕默德·班巴得到16分和10个篮板；温德尔·卡特得到14分、3助攻和7个篮板；R·J 汉普顿得到12分、3助攻和8个篮板；伊托万·摩尔得到10分和2个篮板</p><p>马刺方面：德章泰·穆雷得到18分、1助攻和5个篮板；朗尼·沃克得到16分、5助攻和1个篮板；凯尔登·约翰逊得到12分、3助攻和5个篮板</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public/2022-03-08/1646703692110￥篮球2.jpg\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-08 09:42:01','2022-03-08 09:42:54',0,'/upload/temp_public/2022-03-08/1646703692110￥篮球2.jpg',1,'0',0,NULL,'0'),(97,'无持续风向3~4级，空气质量优',NULL,'<p>深圳南山，今日晴，白天最高气温30℃，夜间最低温度23℃，无持续风向3~4级，空气质量优。</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public/2022-03-08/1646704818721￥6.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-08 10:00:25','2022-03-08 10:00:25',0,'/upload/temp_public/2022-03-08/1646704818721￥6.png',1,'0',0,NULL,'0'),(98,'价下跌0.49%，报收42.89元',NULL,'<p>北京时间12月21日，招商银行开盘报价43.04元，收盘于42.89元，下跌0.49%。当日最高价为43.15元，最低达42.31元，成交量62.39万手，总市值为10816.79亿元。</p><p>最近两个交易日，招商银行连续下跌，跌幅分别为-2.16%和-0.49%，最高价降到42.89元，总市值缩减了292.55亿元。</p><p>在近30个交易日中，招商银行有14天上涨。期间整体上涨了1.52%，最高价为46.80元，最低价为42.58元。和30个交易日前相比，招商银行的市值下跌了209.33亿元。</p><p>再来看下板块方面，招商银行所属的融资融券板块共有385支个股，本交易日整体上涨0.95%，板块中领涨的个股是华银电力(涨幅10.17%)、古越龙山(涨幅10.04%)、金枫酒业(涨幅10.04%)，跌幅较大的个股是*ST金钰(跌幅5.26%)、美邦服饰(跌幅4.87%)。</p><p>招商银行（股票代码：600036）在A股上市，本交易日上证指数上涨0.76%，报收于3420.57点。</p><p style=\"text-align:center;\">&nbsp;<img src=\"/upload/temp_public/2022-03-08/1646704857262￥8.png\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-03-08 10:01:15','2022-03-08 10:01:15',0,'/upload/temp_public/2022-03-08/1646704857262￥8.png',1,'0',0,NULL,'0'),(100,'王森浩同志遗体送别仪式在京举行',NULL,'<p></p><p>中国共产党的优秀党员、忠诚的共产主义战士，原煤炭工业部部长、党组书记王森浩同志的遗体送别仪式，4月14日上午在北京八宝山革命公墓举行。</p><p>王森浩同志因病于2022年4月10日在北京逝世，享年89岁。</p><p>王森浩同志逝世后，中央有关领导同志以不同方式表示哀悼并向其亲属表示慰问。</p><p>14日上午的北京八宝山革命公墓礼堂庄严肃穆，哀乐低回。正厅上方悬挂着黑底白字的横幅“沉痛悼念王森浩同志”，横幅下方是王森浩同志的遗像。王森浩同志的遗体安卧在鲜花翠柏丛中，身上覆盖着鲜红的中国共产党党旗。</p><p>9时许，应急管理部党委书记、部长黄明，黄玉治、宋元明、张永利等部领导，在哀乐声中缓步来到王森浩同志遗体前肃立默哀，向王森浩同志遗体三鞠躬，并向亲属表示深切慰问。国家矿山安全监察局，中国煤炭工业协会，应急管理系统老领导、老同志代表，部机关和部直属事业单位干部职工代表等前往送别。</p><p>王森浩同志生前好友和曾经工作地方单位代表等，也前往送别。</p><p>王森浩同志，1933年1月生，浙江慈溪人。1956年8月参加工作，1955年11月加入中国共产党，教授级高级工程师。1956年8月至1957年8月在北京俄语学院留苏预备班学习。1957年8月至1970年10月在山西大同矿务局工作，先后任永定庄矿实习生、技术员，同家梁矿、白洞矿技术员，局生产技术处技术员、工程师，局总调度室副主任，局长办公室副主任，四老沟矿总工程师。1970年10月至1982年3月先后任山西潞安矿务局王庄矿总工程师、矿长，潞安矿务局党委常委、副局长兼石圪节矿党委书记、矿长。1982年3月，王森浩同志任煤炭工业部总工程师。1983年3月，任中共山西省委副书记、山西省省长。1992年8月，王森浩同志任中国统配煤矿总公司总经理、党组书记。1993年3月，任煤炭工业部部长、党组书记。</p><p>王森浩同志是中国共产党第十二届、十三届、十四届中央委员会委员、第十五届全国代表大会代表，政协第九届全国委员会常务委员、社会和法制委员会主任，第六届、第七届全国人民代表大会代表。&nbsp;&nbsp;</p><p style=\"text-align:center;\">&nbsp;<img src=\"https://img1.baidu.com/it/u=2095125529,137293863&amp;fm=253&amp;fmt=auto&amp;app=138&amp;f=JPEG?w=250&amp;h=365\" style=\"width:60%;\" alt=\"\">&nbsp;</p>',0,1,0,'2022-04-27 11:32:59','2022-04-27 11:32:59',0,'https://img1.baidu.com/it/u=2095125529,137293863&fm=253&fmt=auto&app=138&f=JPEG?w=250&h=365',1,'0',0,NULL,'0');
/*!40000 ALTER TABLE `t_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_article_template`
--

DROP TABLE IF EXISTS `t_article_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_article_template` (
  `idat` int NOT NULL,
  `at_type` int NOT NULL DEFAULT '0' COMMENT '模板类型：图文，视频',
  `title` varchar(255) DEFAULT NULL,
  `digest` longtext COMMENT '模板的摘要，介绍',
  `content` longtext COMMENT '模板的保存地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `isOpen` char(1) DEFAULT NULL COMMENT '是否公开',
  `cid` int DEFAULT NULL COMMENT '所属类型',
  `tagNames` varchar(255) DEFAULT NULL COMMENT '标签',
  `uid` int DEFAULT NULL COMMENT '发布者',
  PRIMARY KEY (`idat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模板信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article_template`
--

LOCK TABLES `t_article_template` WRITE;
/*!40000 ALTER TABLE `t_article_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_article_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_category` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='栏目标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
INSERT INTO `t_category` VALUES (1,'其他'),(2,'科技'),(3,'娱乐'),(4,'时尚'),(5,'军事'),(6,'历史'),(7,'旅游'),(8,'国际'),(9,'美食'),(10,'游戏'),(11,'直播'),(12,'财经'),(13,'体育');
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_emergency`
--

DROP TABLE IF EXISTS `t_emergency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_emergency` (
  `eaid` int NOT NULL COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `digest` longtext COMMENT '摘要',
  `content` longtext COMMENT '内容，可以是一个路径',
  `cid` int DEFAULT NULL COMMENT '所属栏目',
  `tagNames` varchar(255) DEFAULT NULL COMMENT '标签',
  `visit_count` int DEFAULT NULL COMMENT '访问量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `logo` varchar(255) DEFAULT NULL COMMENT '文章封面logo',
  `isOpen` char(1) DEFAULT NULL COMMENT '是否公开',
  `level` int DEFAULT NULL COMMENT '事件等级',
  PRIMARY KEY (`eaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='自动生成稿件、突发事件爬取信息管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_emergency`
--

LOCK TABLES `t_emergency` WRITE;
/*!40000 ALTER TABLE `t_emergency` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_emergency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_link`
--

DROP TABLE IF EXISTS `t_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_link` (
  `id` int NOT NULL AUTO_INCREMENT,
  `site_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点名',
  `site_url` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点地址',
  `site_desc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点描述  简单备注下 ',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='合作新闻网址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_link`
--

LOCK TABLES `t_link` WRITE;
/*!40000 ALTER TABLE `t_link` DISABLE KEYS */;
INSERT INTO `t_link` VALUES (1,'微博','www.weibo.com','热点',1),(2,'百度新闻','https://news.baidu.com/','新闻',0),(3,'今日头条','https://www.toutiao.com/','新闻',0),(4,'澎湃新闻','https://www.thepaper.cn/','新闻',0);
/*!40000 ALTER TABLE `t_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material`
--

DROP TABLE IF EXISTS `t_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_material` (
  `mid` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `m_type` int NOT NULL DEFAULT '0' COMMENT '素材类型，0是文本，1是图片，2是视频，3是音频，4是html',
  `m_name` varchar(45) DEFAULT NULL,
  `content` longtext COMMENT '文本内容、或者url路径（图片、音频、视频）',
  `uid` int DEFAULT NULL COMMENT '用户id',
  `cid` int DEFAULT '1' COMMENT '类型id',
  `tagNames` varchar(45) DEFAULT NULL COMMENT '标签',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `use_time` char(19) DEFAULT NULL COMMENT '最近使用时间',
  `use_count` int NOT NULL DEFAULT '0',
  `is_show` int NOT NULL DEFAULT '1' COMMENT '表示文章内素材，0不显示给用户，1显示给用户选择',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '是否已经删除， 0 未删除，1是已经删除，但依旧被引用，在用户素材库中不可见',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='素材仓库，保存个人素材';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material`
--

LOCK TABLES `t_material` WRITE;
/*!40000 ALTER TABLE `t_material` DISABLE KEYS */;
INSERT INTO `t_material` VALUES (28,1,'stock1.jpg','/upload/user/admin/1646635114718stock1.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',3,1,1),(29,1,'stock2.jpg','/upload/user/admin/1646635114734stock2.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',2,1,0),(30,1,'stock3.jpg','/upload/user/admin/1646635114749stock3.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(31,1,'stock4.jpg','/upload/user/admin/1646635114761stock4.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(32,1,'stock5.jpg','/upload/user/admin/1646635114793stock5.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(33,1,'篮球1.jpg','/upload/user/admin/1646635114811篮球1.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',1,1,1),(34,1,'篮球2.jpg','/upload/user/admin/1646635114821篮球2.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(35,1,'篮球3.jpg','/upload/user/admin/1646635114829篮球3.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(36,1,'篮球4.jpg','/upload/user/admin/1646635114840篮球4.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(37,1,'篮球5.jpg','/upload/user/admin/1646635114852篮球5.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(38,1,'篮球6.jpg','/upload/user/admin/1646635114863篮球6.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(39,1,'足球1.jpg','/upload/user/admin/1646635114874足球1.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',3,1,1),(40,1,'足球2.jpg','/upload/user/admin/1646635114885足球2.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(41,1,'足球3.jpg','/upload/user/admin/1646635114893足球3.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(42,1,'足球4.jpg','/upload/user/admin/1646635114905足球4.jpg',1,1,NULL,'2022-03-07 14:38:34','2022-03-07 14:38:34','2022-03-07 14:38:34',0,1,0),(43,1,'106点.png','/upload/user/admin/1649153531370￥106点.png',1,1,NULL,'2022-04-05 18:12:11','2022-04-05 18:12:11','2022-04-05 18:12:11',0,1,0);
/*!40000 ALTER TABLE `t_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `rid` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `data_scope` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tag` (
  `tid` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签名称',
  `cid` int DEFAULT NULL COMMENT '所属栏目',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='属性标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
INSERT INTO `t_tag` VALUES (1,'户外运动',1),(2,'赛艇',1),(3,'羽毛球',1),(4,'垒球',1),(5,'自驾游',2),(6,'徒步旅游',2),(7,'飞机旅游',2),(8,'女装、男装',3),(9,'家电',3),(10,'手机、数码',3),(11,'厨具',3),(12,'Java',4),(13,'PHP',4),(14,'JavaScript',4),(15,'Dubbo',4);
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码  采取MD5加密',
  `role` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '0:管理员 1:普通用户',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号是否被锁定 0:锁定 1:未锁定',
  `loginIp` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录ip',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `loginCount` int DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','管理员','21232f297a57a5a743894a0e4a801fc3','1','2021-04-20 10:01:21','2021-04-20 10:01:21','1','0:0:0:0:0:0:0:1','13739242980',NULL,73),(4,'yyj','云杰','2c8d0ad33af28e261b0944bdad1cfb44','1','2022-01-11 16:01:18','2022-01-11 16:01:18','1','0:0:0:0:0:0:0:1','13754831564',NULL,1),(5,'gyx','宇轩','b96b6fc5ce1461a9171bc7b7db26a261','1','2022-01-11 16:02:08','2022-01-11 16:02:08','1','0:0:0:0:0:0:0:1','13932543247',NULL,1),(6,'syj','Yingjie','4686898bee4c039f12d0b0f934b7a072','1','2022-01-11 16:06:12',NULL,'1',NULL,'18724571325',NULL,NULL),(9,'superbaby',NULL,'25d55ad283aa400af464c76d713c07ad','1','2022-01-13 10:41:01',NULL,'1',NULL,NULL,NULL,NULL),(10,'test','y','test','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_roles`
--

DROP TABLE IF EXISTS `t_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_roles`
--

LOCK TABLES `t_user_roles` WRITE;
/*!40000 ALTER TABLE `t_user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-05  9:55:54
