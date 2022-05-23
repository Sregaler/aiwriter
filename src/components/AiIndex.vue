<template>
  <div>
      <div class="bgimg">
          <img src="../assets/index/background.png" alt="">
          <button class="imgbtn" id="location1" @click="goMoBan">立即体验</button>
      </div>
      <div class="row" style="background: rgb(247,247,247);">
        <div class="col-md-2 col-xs-1">
        </div>
        <div class="content-left col-md-7 col-xs-7">
            <ul class="context-left-ul">
                <a href="#location1" class="left-li1" :class="{'context-left-li':curpage=='1'}">智能写稿</a>
                <a href="#location2" class="left-li1" :class="{'context-left-li':curpage=='2'}">视频生成</a>
                <a href="#location3" class="left-li1" :class="{'context-left-li':curpage=='3'}">全能工具</a>
            </ul>
            <div class="context-inner">
                <!-- 第一部分 -->
                <div class="clearfix">
                    <div class="context-inner-l clearfix">
                        <div class="context-inner-l-d1">全流程辅助新闻写稿</div>
                        <div class="context-inner-l-d2">模版成稿，八大模版帮助用户在最短时间快速轻松创作相关新闻稿件，还可根据需求自定义数据来源。智能图文成稿，AI辅助用户根据关键词配图，全网热点资讯素材一键引用，帮助用户精修稿件。更有智能审校、原创度识别等能力，帮助用户在多领域提升稿件质量</div> 
                    </div>
                    <div class="context-inner-r">
                        <img src="../assets/index/写稿.png" alt="">
                    </div>
                </div>
                <div class="context-inner-b">
                    <ul class="moban-select-ul clearfix" id="location2">
                        <li
                        class="moban-select-item"
                        :class="{'moban-select-item-a':item.isMouseOver}"
                        v-for="(item, index) in writerInfo"
                        :key="index"
                        @mouseenter="item.isMouseOver = true"
                        @mouseleave="item.isMouseOver = false"
                        @click="goMoBan"
                        >
                        <i class="el-icon-arrow-right moban-select-item-i" :class="{ 'moban-select-item-d-a': item.isMouseOver }"></i>
                        <span class="moban-select-item-s1"
                            ><b>{{ item.name }}</b></span
                        >
                        <span class="moban-select-item-s2" :class="{'moban-select-item-s2-a':item.isMouseOver}">{{ item.msg }}</span>
                        </li>
                    </ul>
                    <div class="lookmore">
                        <span @click="goMoBan">查看更多></span>
                    </div>
                </div>
                <!-- 第二部分 -->
                <div class="clearfix" style="margin-top:150px;">
                    <div class="context-inner-l clearfix">
                        <div class="context-inner-l-d1">视频稿件快速生成</div>
                        <div class="context-inner-l-d2">图文稿件、网页链接智能生成视频，自定义视频时长、分辨率，个性化添加片头、片尾、背景音乐，AI智能朗读配音。帮助用户实现零门槛视频稿件制作</div> 
                    </div>
                    <div class="context-inner-r">
                        <img src="../assets/index/视频.png" alt="">
                    </div>
                </div>
                <div class="context-inner-b">
                    <ul class="moban-select-ul clearfix" id="location3">
                        <li
                        class="moban-select-item"
                        :class="{'moban-select-item-a':item.isMouseOver}"
                        v-for="(item, index) in videoInfo"
                        :key="index"
                        @mouseenter="item.isMouseOver = true"
                        @mouseleave="item.isMouseOver = false"
                        @click="goVideo"
                        >
                        <i class="el-icon-arrow-right moban-select-item-i" :class="{ 'moban-select-item-d-a': item.isMouseOver }"></i>
                        <span class="moban-select-item-s1"
                            ><b>{{ item.name }}</b></span
                        >
                        <span class="moban-select-item-s2" :class="{'moban-select-item-s2-a':item.isMouseOver}">{{ item.msg }}</span>
                        </li>
                    </ul>
                    <div class="lookmore">
                        <span @click="goVideo">查看更多></span>
                    </div>
                </div>
                <!-- 第三部分 -->
                <div class="clearfix" style="margin-top:150px;">
                    <div class="context-inner-l clearfix">
                        <div class="context-inner-l-d1">工具库全方位助力创作</div>
                        <div class="context-inner-l-d2">事件脉络梳理新闻线索，边写边推荐智能分析全网高关联内容，热词图谱分析网罗全网热点趋势。全能工具库全方位助力用户提升创作效率。</div> 
                    </div>
                    <div class="context-inner-r">
                        <img src="../assets/index/工具.png" alt="">
                    </div>
                </div>
                <div class="context-inner-b">
                    <ul class="moban-select-ul clearfix">
                        <li
                        class="moban-select-item"
                        :class="{'moban-select-item-a':item.isMouseOver}"
                        v-for="(item, index) in toolInfo"
                        :key="index"
                        @mouseenter="item.isMouseOver = true"
                        @mouseleave="item.isMouseOver = false"
                        @click="popModal(item.name)"
                        >
                        <i class="el-icon-arrow-right moban-select-item-i" :class="{ 'moban-select-item-d-a': item.isMouseOver }"></i>
                        <span class="moban-select-item-s1"
                            ><b>{{ item.name }}</b></span
                        >
                        <span class="moban-select-item-s2" :class="{'moban-select-item-s2-a':item.isMouseOver}">{{ item.msg }}</span>
                        </li>
                    </ul>
                    <div class="lookmore">
                        <span>查看更多></span>
                    </div>
                </div>
                <div style="height:500px;"></div>
            </div>
        </div>
      </div>
        
  </div>
</template>

<script>
export default {
    name:"AiIndex",
    data() {
        return {
            curpage:"1",
            loginInfo:this.$route.params.logining,
            writerInfo: [
                {
                name: "天气预警模版成稿",
                msg: "全国天气预警，模版自动成稿",
                isMouseOver: false,
                },
                {
                name: "股市收盘模版成稿",
                msg: "沪深股市收盘信息，模版自动成稿",
                isMouseOver: false,
                },
                {
                name: "全网热点引用",
                msg: "热点资讯，一键引用",
                isMouseOver: false,
                },
                {
                name: "稿件自动审校",
                msg: "稿件语义、敏感信息自动审校",
                isMouseOver: false,
                },
            ],
            videoInfo: [
                {
                name: "图文稿件、网页链接转视频",
                msg: "全流程智能生成视频稿件",
                isMouseOver: false,
                },
                {
                name: "智能朗读",
                msg: "稿件AI播报零口误",
                isMouseOver: false,
                },
                {
                name: "图表动画",
                msg: "各类数据一键生成动态图表",
                isMouseOver: false,
                },
                {
                name: "视频转动图",
                msg: "一键转换方便各种场景",
                isMouseOver: false,
                },
            ],
            toolInfo: [
                {
                name: "事件脉络",
                msg: "全网热点事件脉络分析",
                isMouseOver: false,
                },
                {
                name: "视频提升清晰度",
                msg: "一键提升视频清晰度",
                isMouseOver: false,
                },
                {
                name: "边写边推荐",
                msg: "智能推荐全网高关联内容",
                isMouseOver: false,
                },
                {
                name: "热词分析",
                msg: "全网热词趋势图谱分析",
                isMouseOver: false,
                },
            ],
        }
    },
    methods:{
        windowScroll(e){
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
        if(scrollTop<915){
            this.curpage = "1";
        }else if(scrollTop<1570){
            this.curpage = "2";
        }else{
            this.curpage = "3";
        }
        },
        goMoBan(){
            if(this.loginInfo){
                this.$bus.$emit('enter',true)
                this.$router.replace({name:"moban"})
            }else{
                this.$bus.$emit('goLogin',true)
            }
        },
        goVideo(){
            if(this.loginInfo){
                this.$bus.$emit('enter',true)
                this.$bus.$emit('navTag',"稿件转视频")
                this.$router.replace({name:"testtovideo"})
            }else{
                this.$bus.$emit('goLogin',true)
            }
        }
    },
    mounted() {
        window.addEventListener('scroll', this.windowScroll)
        this.$bus.$on('islogin',(data)=>{
				this.loginInfo = data
		})
    }
}
</script>

<style scoped>
.bgimg{
    width: 100%;
    position: relative;
}
.bgimg img{
    width: 100%;
}
.imgbtn{
    width: 150px;
    height: 50px;
    text-align: center;
    background-color: rgb(30,126,240);
    font-size: 20px;
    font-weight: bold;
    color: white;
    position: absolute;
    top: 65%;
    left: 50%;
    margin-left: -75px;
    border:none;
    outline:none;
    list-style: none;
}
.context-left-ul{
    align-items: start;
    align-self: flex-start;
    color: #666;
    display: flex;
    flex: 0 0 84px;
    flex-direction: column;
    margin: 100px 112px 0 0;
    position: sticky;
    top: 30%;
    user-select: none;
    float: left;
}
.context-left-ul a {
    padding: 15px 0px;
    color:black;
    text-decoration: none;
}
.content-left{
    margin-top:100px;
}
.context-left-li {
  color: rgb(30,126,240) !important;
}
.context-inner{
    width: 100%;
    margin-left: 170px;
    padding: absolute;
    top:0px
}
.context-inner-l{
    width: 40%;
    float: left;
    position: relative;
}
.context-inner-r{
    width: 60%;
    float: right;
}
.context-inner-r img{
    width: 100%;
}
.context-inner-l-d1{
    font-size: 30px;
    font-weight: bolder;
    text-align: center;
    margin-top: 60px;
    width: 100%;
    
}
.context-inner-l-d2{
    font-size: 18px;
    width: 100%;
    padding:0 30px;
    margin-top: 40px;
}
.moban-select-item-i{
  position: absolute;
  top:20px;
  right: 10px;
  display: none !important;
}
.moban-select-item-d-a {
  display: block !important;
}
.moban-select-item {
  width: 200px;
  min-height: 80px;
  position: relative;
  float: left;
  margin-right: 10px;
  background-color: white;
  margin-top: 30px;
  border-radius: 3px;
}
.moban-select-item:hover {
  cursor: pointer;
}
.moban-select-item-s2 {
  color: rgb(112, 112, 112);
  font-size: 12px;
  position: absolute;
  top: 43px;
  left: 10px;
}
.moban-select-item-s1 {
  font-size: 15px;
  font-weight: border;
  position: absolute;
  top: 15px;
  left: 10px;
}
.moban-select-item-a{
    background-color: rgb(30,126,240) !important;
    color: white !important;
}
.moban-select-item-s2-a{
    color: white !important;
}
span:hover{
    cursor: pointer;
}
.lookmore{
    padding-left:10px;
}
</style>