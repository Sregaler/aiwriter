<template>
  <div class="row pt" v-cloak>
    <div class="col-md-2 col-xs-2"></div>
    <div class="col-md-8 col-xs-8">
      <div class="moban-select">新闻热点</div>
      <ul class="moban-select-ul clearfix">
        <li
          class="moban-select-item"
          v-for="(item, index) in hotThing"
          :key="index"
          @mouseenter="item.isMouseOver = !item.isMouseOver"
          @mouseleave="item.isMouseOver = !item.isMouseOver"
          @click="popModal(item.name)"
        >
          <img class="moban-select-item-img" :src="item.img" alt="" />
          <i class="el-icon-arrow-right moban-select-item-i" :class="{ 'moban-select-item-d-a': item.isMouseOver }"></i>
          <span class="moban-select-item-s1"
            ><b>{{ item.name }}</b></span
          >
          <span class="moban-select-item-s2">{{ item.msg }}</span>
          <div
            class="moban-select-item-d"
            :class="{ 'moban-select-item-d-a': item.isMouseOver }"
          >
            <span class="moban-select-item-s3">{{ item.detial }}</span>
          </div>
        </li>
      </ul>

      <div class="moban-select">重大突发事件</div>

      <ul class="moban-select-ul">
        <li
          class="moban-select-item"
          v-for="(item, index) in tuFaThing"
          :key="index"
          @mouseenter="item.isMouseOver = !item.isMouseOver"
          @mouseleave="item.isMouseOver = !item.isMouseOver"
          @click="popModal(item.name)"
        >
          <img class="moban-select-item-img" :src="item.img" alt="" />
          <i class="el-icon-arrow-right moban-select-item-i" :class="{ 'moban-select-item-d-a': item.isMouseOver }"></i>
          <span class="moban-select-item-s1"
            ><b>{{ item.name }}</b></span
          >
          <span class="moban-select-item-s2">{{ item.msg }}</span>
          <div
            class="moban-select-item-d"
            :class="{ 'moban-select-item-d-a': item.isMouseOver }"
          >
            <span class="moban-select-item-s3">{{ item.detial }}</span>
          </div>
        </li>
      </ul>
    </div>

    <!-- 通用遮罩 -->
    <div class="Cover" v-cloak v-show="coverControl"></div>
    <!-- 天气预报 -->
    <div class="TQYB" v-show="modalControl['天气预报']">
      <span class="TQYB-s1">天气预报</span>
      <i class="el-icon-close close-modal" @click="closeModal('天气预报')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['天气预报'].title" class="TQYB-radio">
        <el-radio-button label="今日天气"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.城市天气</span>
      <div class="TQYB-cascader">
        <el-cascader
          v-model="selectInfo['天气预报'].address"
          :options="selectInfo['天气预报'].option"
          :props="{ expandTrigger: 'hover' }"></el-cascader>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['天气预报'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('天气预报')">取消</el-button>
    </div>
    <!-- 天气预警 -->
    <div class="TQYB" v-show="modalControl['天气预警']">
      <span class="TQYB-s1">天气预警</span>
      <i class="el-icon-close close-modal" @click="closeModal('天气预警')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['天气预警'].title" class="TQYB-radio">
        <el-radio-button label="天气预警"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.相关城市</span>
      <div class="TQYB-cascader">
        <el-cascader
          v-model="selectInfo['天气预警'].address"
          :options="selectInfo['天气预警'].option"
          :props="{ expandTrigger: 'hover' }"></el-cascader>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['天气预警'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('天气预警')">取消</el-button>
    </div>
    <!-- 体育 -->
    <div class="TQYB" v-show="modalControl['体育赛事']">
      <span class="TQYB-s1">体育赛事</span>
      <i class="el-icon-close close-modal" @click="closeModal('体育赛事')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group @change="getTYInfo('体育赛事')" v-model="selectInfo['体育赛事'].title" class="TQYB-radio">
        <el-radio-button label="篮球战报"></el-radio-button>
        <!-- <el-radio-button label="足球战报"></el-radio-button> -->
      </el-radio-group>
      <span class="TQYB-s4">2.赛事选择</span>
      <div class="TQYB-cascader">
        <el-cascader
          v-model="selectInfo['体育赛事'].address"
          :options="selectInfo['体育赛事'].option"
          :props="{ expandTrigger: 'hover' }"></el-cascader>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['体育赛事'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('体育赛事')">取消</el-button>
    </div>

    <!-- 股市收盘 -->
    <div class="TQYB" v-show="modalControl['股市收盘']">
      <span class="TQYB-s1">股市收盘</span>
      <i class="el-icon-close close-modal" @click="closeModal('股市收盘')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['股市收盘'].title" class="TQYB-radio">
        <el-radio-button label="股市收盘"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.输入相关股票</span>
      <div class="TQYB-cascader">
        <el-input
          placeholder="请输入股票编号"
          v-model="selectInfo['股市收盘'].address"
          clearable>
        </el-input>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['股市收盘'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('股市收盘')">取消</el-button>
    </div>

    <!-- 公司财报 -->
    <div class="TQYB" v-show="modalControl['公司财报']">
      <span class="TQYB-s1">公司财报</span>
      <i class="el-icon-close close-modal" @click="closeModal('公司财报')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['公司财报'].title" class="TQYB-radio">
        <el-radio-button label="公司财报"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.选择年度</span>
      <div class="TQYB-cascader">
        <el-select v-model="selectInfo['公司财报'].address.year" placeholder="请选择">
          <el-option
            v-for="item in years"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <span class="TQYB-s5">3.选择相关股票</span>
      <div class="TQYB-cascader1">
        <el-input
          placeholder="请输入股票编号"
          v-model="selectInfo['公司财报'].address.id"
          clearable>
        </el-input>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['公司财报'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('公司财报')">取消</el-button>
    </div>

    <!-- 突发事件 -->
    <div class="TQYB" v-show="modalControl['突发事件']">
      <span class="TQYB-s1">突发事件</span>
      <i class="el-icon-close close-modal" @click="closeModal('突发事件')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['突发事件'].title" class="TQYB-radio">
        <el-radio-button label="突发事件"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.选择类别</span>
      <div class="TQYB-cascader">
        <el-select v-model="selectInfo['突发事件'].address.type" placeholder="请选择" @change="getTFTitle">
          <el-option
            v-for="item in tuFaTag"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <span class="TQYB-s5">3.选择相关标题</span>
      <div class="TQYB-cascader1">
        <el-select v-model="selectInfo['突发事件'].address.title" placeholder="请选择">
          <el-option
            v-for="item in selectInfo['突发事件'].option"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['突发事件'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('突发事件')">取消</el-button>
    </div>

    <!-- 地震 -->
    <div class="TQYB" v-show="modalControl['地震速报']">
      <span class="TQYB-s1">地震速报</span>
      <i class="el-icon-close close-modal" @click="closeModal('地震速报')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['地震速报'].title" class="TQYB-radio">
        <el-radio-button label="地震速报"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.选择相关标题</span>
      <div class="TQYB-cascader">
        <el-select v-model="selectInfo['地震速报'].address" placeholder="请选择">
          <el-option
            v-for="item in selectInfo['地震速报'].option"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['地震速报'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('地震速报')">取消</el-button>
    </div>

    <!-- 爆炸 -->
    <div class="TQYB" v-show="modalControl['爆炸事件']">
      <span class="TQYB-s1">爆炸事件</span>
      <i class="el-icon-close close-modal" @click="closeModal('爆炸事件')"></i>
      <span class="TQYB-s2">请选择要创建的&nbsp;<b>模版</b>&nbsp;及&nbsp;<b>数据</b></span>
      <span class="TQYB-s3">1.文章模板</span>
      <el-radio-group v-model="selectInfo['爆炸事件'].title" class="TQYB-radio">
        <el-radio-button label="爆炸事件"></el-radio-button>
      </el-radio-group>
      <span class="TQYB-s4">2.选择相关标题</span>
      <div class="TQYB-cascader">
        <el-select v-model="selectInfo['爆炸事件'].address" placeholder="请选择">
          <el-option
            v-for="item in selectInfo['爆炸事件'].option"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <el-button type="primary" class="btn1-left" @click="generateTxt(selectInfo['爆炸事件'].title)">成稿</el-button>
      <el-button class="btn1-right" @click="closeModal('爆炸事件')">取消</el-button>
    </div>

    <div id="infoHander" style="display: none;"></div>  
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "MoBan",
  data() {
    return {
      qwrite:"",
      coverControl:false,
      modalControl:{
        "天气预报":false,
        "天气预警":false,
        "体育赛事":false,
        "股市收盘":false,
        "公司财报":false,
        "地震速报":false,
        "突发事件":false,
        "爆炸事件":false,
      },
      currentActive:"天气预报",
      currentModal:"今日天气",
      hotThing: [
        {
          name: "天气预报",
          msg: "获取全国城市天气数据，自动生成文章",
          img: require("../assets/icon/天气1.svg"),
          detial: "预置全国每周/每天实时数据，可自定义数据源",
          isMouseOver: false,
        },
        {
          name: "体育赛事",
          msg: "获取CBA/NBA赛事相关信息，自动生成文章。",
          img: require("../assets/icon/体育.svg"),
          detial: "预置赛事实时数据，可自定义数据源",
          isMouseOver: false,
        },
        {
          name: "股市收盘",
          msg: "获取沪深股票每日数据，自动生成文章",
          img: require("../assets/icon/股市收盘.svg"),
          detial: "预置沪深股市收盘数据，可自定义数据源",
          isMouseOver: false,
        },
        {
          name: "公司财报",
          msg: "公司财报，自动出稿",
          img: require("../assets/icon/公司财报.svg"),
          detial: "预置沪深上市公司财报数据，可自定义数据源",
          isMouseOver: false,
        },
      ],

      tuFaThing: [
        {
          name: "天气预警",
          msg: "气象预警，自动出稿",
          img: require("../assets/icon/台风.svg"),
          detial: "预置全国每周/每天实时数据，可自定义数据源",
          isMouseOver: false,
        },
        {
          name: "地震速报",
          msg: "获取地震台实时数据，自动生成文章",
          img: require("../assets/icon/地震.png"),
          detial: "获取地震台实时数据，自动生成文章",
          isMouseOver: false,
        },
        {
          name: "突发事件",
          msg: "事故、车祸等突发事件快速成稿",
          img: require("../assets/icon/感叹号.svg"),
          detial: "事故、车祸等突发事件快速成稿",
          isMouseOver: false,
        },
        {
          name: "爆炸事件",
          msg: "爆炸等恶性事件快速成稿",
          img: require("../assets/icon/爆炸.png"),
          detial: "爆炸等恶性事件快速成稿",
          isMouseOver: false,
        },
      ],
      requestInfo:{"事件脉络":"",
      "体育赛事":["/baidu/newTag/getBasketballTeam","/baidu/customized/sports_events","/baidu/newTag/getFootballTeam"],
      "股市收盘":["","/baidu/customized/shares"],
      "公司财报":["","/baidu/customized/company_financial_report"],
      "天气预报":["/baidu/newTag/getProvince","/baidu/customized/weather"],
      "天气预警":["/baidu/newTag/getProvince","/baidu/customized/weather_warning"],
      "突发事件":["/reptile/emergency_news/","/reptile/emergency_news_details"]},

      selectInfo:{"事件脉络":"",
      "体育赛事":{title:"篮球战报",address:[],option:[]},
      "股市收盘":{title:"股市收盘",address:""},
      "公司财报":{title:"公司财报",address:{"year":"年度","id":""},option:[]},
      "天气预报":{title:"今日天气",address:[],option:[]},
      "天气预警":{title:"天气预警",address:[],option:[]},
      "突发事件":{title:"突发事件",address:{"type":"yjyw","title":""},option:[]},
      "地震速报":{title:"地震速报",address:"",option:[
          {
          "label":"【天然地震预警】9月28日河北张家口市蔚县发生3.1级地震",
          "value":"中国地震台网正式测定： 9月28日11时27分在河北张家口市蔚县（北纬39.894度,东经114.565度）发生3.1级地震，震源深度19.0千米。 （信息来源：国家预警发布中心）"
          },
          {
          "label":"【天然地震预警】12月23日天津蓟州区发生3.3级地震",
          "value":"中国地震台网正式测定： 12月23日2时44分在天津蓟州区（北纬39.8452度,东经117.326度）发生3.3级地震，震源深度10.0千米。 （信息来源：国家预警发布中心）"
          },
          {
          "label":"【天然地震预警】12月23日地震速报(正式报):台湾海峡南部发生3.3级地震",
          "value":"中国地震台网正式测定:2019年12月23日01时43分16秒在台湾海峡南部(东经117.80度,北纬22.96度)发生3.3级地震,震源深度11千米。 （信息来源：国家预警发布中心）"
          },
          {
          "label":"【天然地震预警】12月5日河北唐山市丰南区发生4.5级地震",
          "value":"中国地震台网正式测定： 12月5日8时2分在河北唐山市丰南区（北纬39.31度,东经118.04度）发生4.5级地震，震源深度10.0千米。 （信息来源：国家预警发布中心）"
          }
        ]},
      "爆炸事件":{title:"爆炸事件",address:"",option:[{
        "label":"2022年6月1日突发：长沙早餐店爆燃事故致1名消防员牺牲",
        "value":"2022年6月1日(农历2022年5月3日)，长沙早餐店起火后爆炸致1名消防员牺牲13人受伤，零星爆炸后发生一声巨响。长沙一早餐店火灾引发爆炸致1死13伤，伤者中有多名消防员。北京时间2022年6月1日，据长沙市“长沙县融媒体中心”通报，2022年6月1日早上6:30左右，长沙县星沙街道一早餐店发生火灾并引发燃爆事故，消防、公安、卫健、应急等部门第一时间赶赴现场处置。经初步了解，目前事故造成1人死亡，13人受伤，伤者已紧急送医院救治，事故原因正在调查之中。“早上一声巨响，我以为地震了。”熊先生家住事发地附近，6月1日上午，他告诉上游新闻记者，发生爆炸的早餐店系长沙一家连锁餐饮店，位于长沙市长沙县幸福里润城小区。家住幸福里润城小区高层的居民肖先生介绍，早上6时15分许，他先是被一阵类似烟花爆炸的声音吵醒，“大概过了15分钟，听到一声巨大的爆炸声，比打雷的声音还要大，我起来到窗户边看了一下，接着消防车、救护车赶过来”。记者在现场看到，事发地附近各个出入口已拉起警戒线，官方对现场周边居民进行了疏散，临近午间，有不少居民在警戒线外等待返回家中。事发现场。图片来源/视频截图记者获取的多段现场视频显示，爆炸发生在小区底楼商铺，门面前后被贯穿，现场有消防员受伤。爆炸还击穿了地下室楼板，门店前后洒满爆炸碎片。“消防员先去救火，后发生了爆炸。”记者从多个信源处了解到，13名伤者中含多名消防员。"},
        {"label":"2022年5月25日常州爆炸事故亲历者:咚一声像炸弹",
        "value":"2022年5月25日(农历2022年4月25日)，常州爆炸事故亲历者:咚一声像炸弹。江苏常州一居民楼坍塌致1死5伤，亲历者：咚的一声像炸弹一样。北京时间2022年5月25日，江苏常州湖塘花园街发生爆炸，现场救援正在进行5月24日晚，江苏常州湖塘花园街内发生爆炸，现场有房屋倒塌，附近商户受损严重，现场救援正在继续。常州武进区花园街附近发生爆炸，消防已赶赴现场救援另据北京青年报消息：5月24日晚，多位网友发帖称，常州市武进区花园街发生爆炸，多个店铺受损严重。一位附近商户告诉北京青年报记者，爆炸大约发生于24日晚8点多，“好像是一个饭店爆炸，我和这个店隔着一条街，店里受影响不算大。”另一位附近住户告诉北青报记者，她听到一声巨响，“刚开始我还以为门被砸了，直到听到火警才知道发生了爆炸。”现场目击者发布的视频显示，多个周边商户、房屋受损严重，一民房疑似坍塌。北青报记者从常州市消防救援支队获悉，消防人员已赶赴现场处理，目前伤亡情况仍在核实中。5月24日20时45分左右，江苏省常州市武进区湖塘镇花园新村小区182—183号两层半居民楼因瓶装液化气泄漏引发爆炸造成坍塌。当晚，记者在现场看到，当地消防、公安等多个部门已经开展救援，有伤员陆续从废墟中被搜救出送往医院救治。有目击者告诉记者，爆炸发生时声音巨大，冲击波导致马路对面的商家也有不同程度受损，周边有行人受伤。目前，救援工作已经接近尾声。据新华社南京5月25日电（记者刘兆权、李雨泽、毛俊）5月24日20时45分左右，江苏省常州市武进区湖塘镇花园新村小区182—183号两层半居民楼，因瓶装液化气泄漏引发爆炸造成坍塌，致6人受困。其中，1人遇难、2人重伤、3人轻伤。事发后，消防、应急、公安、卫健等救援力量及时赶到救援。记者在现场见到，25日凌晨，现场被困人员救援结束，事故具体原因正在调查当中。"},
        {"label":"2022 4.19刚刚绵阳欧家坝爆炸火灾最新消息情况",
        "value":"一名学生告诉记者，8点40分左右，她下了晚自习走在路上听到几声像爆炸声的巨响，第一声巨响之后就听到了救护车的声音，随后又两声巨响之后，听到了消防车陆陆续续的声音。在学校操场也能看到有明显的烟雾，能闻到东西焚烧的气味，在学校寝室也能看到距离几百米的地方起火。4月19日晚，多位网友反映，四川绵阳游仙区欧家坝附近听到巨响，疑似有工厂发生爆炸。据多位网友上传的视频和照片显示，现场腾起蘑菇云，火光冲天。"}]}
      },
      years:[{value: '半年度',label: '半年度'}, {
          value: '年度',
          label: '年度'
        }, {
          value: '第一季度',
          label: '第一季度'
        }, {
          value: '第二季度',
          label: '第二季度'
        }, {
          value: '第四季度',
          label: '第四季度'
        }],
      tuFaTag:[
        {value: 'yjyw',label: '应急要闻'},
        {value: 'yjglbgzdt',label: '工作动态'},
        {value: 'gdyj',label: '地方应急'},
        {value: 'jyll',label: '救援力量'},
        {value: 'zhsgxx',label: '灾害事故信息'},
        {value: 'mtxx',label: '媒体信息'},
      ],
    };
  },
  methods: {
    closeModal(name){
      this.modalControl[name] = false
      this.coverControl = false
    },
    popModal(name){
      this.currentActive = name
      this.modalControl[name] = true
      this.coverControl = true
      if(name=="地震速报" ||name=="爆炸事件"){
        // axios.get("/baidu/customized/explosion").then(res=>{
        //   console.log(res.data.t)
        // })
        return
      }
      if(this.currentActive == "体育赛事"||this.currentActive == "天气预报"|| this.currentActive == "天气预警"){
          if(this.selectInfo[this.currentActive].option.length!=0){
            return
          }
          axios.get(this.requestInfo[name][0]).then(res=>{
          for(let item of res.data){
            for(let k in item){
              let city = {"value": k,"label": k,"children": []}
              for(let c of item[k]){
                let tmp = {"value": c,"label": c}
                city.children.push(tmp)
              }
              this.selectInfo[this.currentActive].option.push(city)
            }
          }
        })
      }else if(this.currentActive == "突发事件"){
        axios.get(this.requestInfo[name][0]+this.selectInfo[this.currentActive].address.type+'/1').then(res=>{
          if(res.data.ok){
            this.selectInfo[this.currentActive].option = []
            for(let item of res.data.t.news_list){
                let msg = {"value": item.url,"label": item.title+"-"+item.time}
                this.selectInfo[this.currentActive].option.push(msg)
              }
          } 
        })
      }
    },
    //获取突发事件标题
    getTFTitle(){
      this.selectInfo[this.currentActive].address.title = ""
        axios.get(this.requestInfo[this.currentActive][0]+this.selectInfo[this.currentActive].address.type+'/1').then(res=>{
          if(res.data.ok){
            this.selectInfo[this.currentActive].option = []
            for(let item of res.data.t.news_list){
                let msg = {"value": item.url,"label": item.title+"-"+item.time}
                this.selectInfo[this.currentActive].option.push(msg)
              }
          } 
        })
    },
    getTYInfo(name){
      var opt=[]
      this.selectInfo[this.currentActive].address = []
      if(this.selectInfo[this.currentActive].title=="篮球战报"){
        axios.get(this.requestInfo[name][0]).then(res=>{
          for(let item of res.data){
            for(let k in item){
              let city = {"value": k,"label": k,"children": []}
              for(let c of item[k]){
                let tmp = {"value": c,"label": c}
                city.children.push(tmp)
              }
              opt.push(city)
            }
          }
        })
      }else{
        axios.get(this.requestInfo[name][2]).then(res=>{
          for(let item of res.data){
            for(let k in item){
              let city = {"value": k,"label": k,"children": []}
              for(let c of item[k]){
                let tmp = {"value": c,"label": c}
                city.children.push(tmp)
              }
              opt.push(city)
            }
          }
        })
      }
      this.$nextTick(()=>{
        this.selectInfo[this.currentActive].option = opt
      })
    },
    generateTxt(name){
      var formFile = new window.FormData();
      if(this.selectInfo[this.currentActive].address.length == 0){
          this.$message.warning("选择不能为空")
          return
        }
      if(this.currentActive == "天气预报" || this.currentActive == "天气预警"){
        formFile.append("province", this.selectInfo[this.currentActive].address[0]);
        formFile.append("city", this.selectInfo[this.currentActive].address[1]);
        // axios.post(this.requestInfo[this.currentActive][1],{province:this.selectInfo[this.currentActive].address[1]}).then(res=>{
        axios.post(this.requestInfo[this.currentActive][1],formFile).then(res=>{
          if(res.data.ok){
            var msg = JSON.parse(res.data.t) 
            this.$router.push({
              name:'quickwriter',
              params:{
                qwtext:msg,
                modal:name,
              }
            })
          }else{
            console.log(res.data.t)
          }
        }).catch(err=>{console.log(err.data)})
      }else if(this.currentActive == "股市收盘"){
        formFile.append("stock_code_list", this.selectInfo[this.currentActive].address);
        axios.post(this.requestInfo[this.currentActive][1],formFile).then(res=>{
          if(res.data.ok){
            var msg = JSON.parse(res.data.t)
            this.$router.push({
              name:'quickwriter',
              params:{
                qwtext:msg,
                modal:name,
              }
            })
          }else{
            console.log(res.data.t)
          }
        }).catch(err=>{console.log(err.data)})
      }else if(this.currentActive == "体育赛事"){
        formFile.append("match_name", this.selectInfo[this.currentActive].address[0]);
        formFile.append("team_name", this.selectInfo[this.currentActive].address[1]);
        axios.post(this.requestInfo[this.currentActive][1],formFile).then(res=>{
          if(res.data.ok){
            var msg = JSON.parse(res.data.t) 
            this.$router.push({
              name:'quickwriter',
              params:{
                qwtext:msg,
                modal:name,
              }
            })
          }else{
            console.log(res.data.t)
          }
        }).catch(err=>{console.log(err.data)})
      }else if(this.currentActive == "公司财报"){
        if(this.selectInfo[this.currentActive].address.id == "" || this.selectInfo[this.currentActive].address.year == "" ){
          this.$message.warning("选择不能为空")
          return
        }
        formFile.append("article_type", this.selectInfo[this.currentActive].address.year);
        formFile.append("stock_code_list", this.selectInfo[this.currentActive].address.id);
        axios.post(this.requestInfo[this.currentActive][1],formFile).then(res=>{
          if(res.data.ok){
            var msg = JSON.parse(res.data.t) 
            this.$router.push({
              name:'quickwriter',
              params:{
                qwtext:msg,
                modal:name,
              }
            })
          }else{
            console.log(res.data.t)
          }
        }).catch(err=>{console.log(err.data)})
      }else if(this.currentActive == "突发事件"){
        if(this.selectInfo[this.currentActive].address.title == ""){
          this.$message.warning("选择不能为空")
          return
        }
        formFile.append("url", this.selectInfo[this.currentActive].address.title);
        axios.post(this.requestInfo[this.currentActive][1],formFile).then(res=>{
          if(res.data.ok){
            var msg = res.data.t
            console.log("msg",msg)
            if(msg.title==""){
              document.getElementById("infoHander").innerHTML = msg.news_content
              msg.title = document.getElementById("infoHander").getElementsByTagName("p")[0].innerText
              document.getElementById("infoHander").getElementsByTagName("p")[0].remove()
              msg.news_content = document.getElementById("infoHander").innerHTML
            }
            console.log(msg)
            this.$router.push({
              name:'quickwriter',
              params:{
                qwtext:msg,
                modal:name,
              }
            })
          }else{
            console.log(res.data.t)
          }
        }).catch(err=>{console.log(err.data)})
      }else if(this.currentActive == "地震速报" || this.currentActive == "爆炸事件"){
        if(this.selectInfo[this.currentActive].address.title == ""){
          this.$message.warning("选择不能为空")
          return
        }
        console.log(this.currentActive)
        var msg = {}
        msg.news_content = this.selectInfo[this.currentActive].address
        for(let tt of this.selectInfo[this.currentActive].option){
          if(msg.news_content==tt.value){
            msg.title = tt.label
            msg.news_content = "<p>" + msg.news_content + "</p>";
            break
          }
        }
        console.log(msg)
        this.$router.push({
          name:'quickwriter',
          params:{
            qwtext:msg,
            modal:name,
          }
        })
      }
    }
  },
};
</script>

<style scoped>
.moban-select-item-i{
  position: absolute;
  top:20px;
  right: 10px;
  display: none !important;
}
.moban-select-item-d{
  z-index: 800;
}
.moban-select-item-d-a {
  display: block !important;
}
.moban-select-item-img {
  width: 36px;
  height: 36px;
  position: absolute;
  top: 20px;
  left: 20px;
}
.moban-select {
  font-size: 30px;
  height: 40px;
  line-height: 40px;
}
.moban-select-ul {
  margin-bottom: 40px;
}
.moban-select-item {
  width: 236px;
  min-height: 88px;
  position: relative;
  float: left;
  margin-right: 30px;
  background-color: white;
  margin-top: 30px;
}
.moban-select-item:hover {
  cursor: pointer;
}
.moban-select-item-d {
  width: 236px;
  display: none;
  margin-top: 88px;
  height: 80px;
  border-bottom: 2px solid rgb(1, 84, 120);
  position:absolute;
  background-color: white;
}
.moban-select-item-s3 {
  color: rgb(112, 112, 112);
  font-size: 12px;
  display: block;
  margin:20px auto 0px;
  width:200px
}
.moban-select-item-s2 {
  color: rgb(112, 112, 112);
  font-size: 12px;
  position: absolute;
  top: 48px;
  left: 70px;
}
.moban-select-item-s1 {
  font-size: 15px;
  font-weight: border;
  position: absolute;
  top: 20px;
  left: 70px;
}

.Cover {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: darkgrey;
    z-index: 999;
    opacity: 0.5;
}
/* 天气预报 */
.TQYB{
    width: 800px;
    height: 540px;
    background-color: white;
    position: fixed;
    left: 50%;
    top: 50%;
    margin-left: -400px;
    margin-top: -270px;
    z-index: 1000;
    border-radius: 5px;
    padding: 30px;
    font-size:18px;
}
.TQYB-s1{
  position: absolute;
}
.TQYB-s2{
  position: absolute;
  top:80px;
  font-size:22px;
}
.TQYB-s3{
  position: absolute;
  top:140px;
}
.TQYB-radio{
  position: absolute;
  top:180px;
}
.TQYB-s4{
  position: absolute;
  top:250px;
}
.TQYB-cascader{
  position: absolute;
  top:290px;
}
.TQYB-s5{
  position: absolute;
  top:350px;
}
.TQYB-cascader1{
  position: absolute;
  top:390px;
}
.btn1-right{
  position: absolute;
  bottom:20px;
  right: 40%;
}
.btn1-left{
  position: absolute;
  bottom:20px;
  left: 40%;
}
.close-modal{
  position: absolute;
  right:20px;
}
.close-modal:hover{
  cursor: pointer;
}

</style>



