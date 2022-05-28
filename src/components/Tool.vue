<template>
  <div class="row pt" v-cloak>t
    <div class="col-md-2 col-xs-2"></div>
    <div class="col-md-8 col-xs-8">
      <div class="moban-select"><b>智能写稿</b></div>
      <ul class="moban-select-ul clearfix">
        <li
          class="moban-select-item"
          v-for="(item, index) in writerInfo"
          :key="index"
          @mouseenter="item.isMouseOver = !item.isMouseOver"
          @mouseleave="item.isMouseOver = !item.isMouseOver"
          @click="toMoBan"
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

      <div class="moban-select"><b>视频创作</b></div>
      <ul class="moban-select-ul clearfix">
        <li
          class="moban-select-item"
          v-for="(item, index) in videoInfo"
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

      <div class="moban-select"><b>全能工具</b></div>
      <ul class="moban-select-ul clearfix">
        <li
          class="moban-select-item"
          v-for="(item, index) in toolInfo"
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
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "Tool",
  data() {
    return {
      qwrite:"",
      coverControl:false,
      modalControl:{
        "天气预报":false,
        "天气预警":false,
        "篮球赛事":false,
        "股市收盘":false,
        "公司财报":false,
        "事件脉络":false,
        "突发事件":false,
      },
      currentActive:"天气预报",
      currentModal:"今日天气",
      writerInfo: [
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
      videoInfo:[
        {
          name: "稿件转视频",
          msg: "无需剪辑，视频一键生成",
          img: require("../assets/icon/文本转视频.png"),
          detial: "无需剪辑，视频一键生成",
          isMouseOver: false,
        },
        {
          name: "视频转GIF",
          msg: "视频一键转GIF",
          img: require("../assets/icon/视频转gif.png"),
          detial: "视频一键转GIF",
          isMouseOver: false,
        },
        {
          name: "动态图表",
          msg: "数据一键生成图表动画",
          img: require("../assets/icon/图表.png"),
          detial: "数据一键生成图表动画",
          isMouseOver: false,
        },
        {
          name: "语音播报",
          msg: "AI智能配音读稿",
          img: require("../assets/icon/娱乐.svg"),
          detial: "AI智能配音读稿",
          isMouseOver: false,
        },
        {
          name: "清晰度提升",
          msg: "一键提升视频清晰度",
          img: require("../assets/icon/清晰度.png"),
          detial: "一键提升视频清晰度",
          isMouseOver: false,
        },
      ],
      toolInfo:[
        {
          name: "热点发现",
          msg: "全网最新热点发现",
          img: require("../assets/icon/热点3.png"),
          detial: "全网最新热点发现",
          isMouseOver: false,
        },
        {
          name: "事件脉络",
          msg: "热点新闻时间线索梳理",
          img: require("../assets/icon/专题培训.svg"),
          detial: "热点新闻时间线索梳理",
          isMouseOver: false,
        },
        // {
        //   name: "审校助手",
        //   msg: "一键分析稿件可能额错误",
        //   img: require("../assets/icon/审核.png"),
        //   detial: "一键分析稿件可能额错误",
        //   isMouseOver: false,
        // },
      ]
    };
  },
  methods: {
    toMoBan(){
      this.$bus.$emit('navTag',"模板成稿")
      this.$router.replace({name:"moban"});
    },
    popModal(name){
      if(name=='稿件转视频'){
        this.$bus.$emit('navTag',"稿件转视频")
        this.$router.replace({name:"testtovideo"});
        return
      }
      switch (name) {
        case "热点发现":
          this.$router.replace({
            name: "hotfind",
          });
          break;
        case "事件脉络":
          this.$router.replace({
            name: "thingdetial",
          });
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
