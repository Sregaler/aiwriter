<template>
  <div class="row" v-cloak>
    <div class="col-md-3 col-xs-3">
      <div class="t-left">
        <div class="t-title">
          <span class="t-title-s1">热点新闻</span>
          <span class="t-title-s2">追溯本源、展示热点事件的时间发展脉络</span>
        </div>
        <div class="t-show">
          <div class="t-item" v-for="(item,index) in titleList" :key="index" @click="navTitle(index,item.title)" :class="{'t-item-active': index == titleid}">
            <span class="t-item-s1" :class="{
                  'hot1': index == 0,
                  'hot2': index == 1,
                  'hot3': index == 2,
                }">{{index+1}}</span>
            <span class="t-item-s2" :class="{'t-item-s2-active': index == titleid}" >{{item.title}}</span>
            <div class="t-item-d">
              <span class="t-item-s3" v-for="(t,i) in item.tag" :key="i">{{t}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-xs-6">
      <div class="t-detail">
        <span class="t-detail-s1">{{title}}</span>
        <span class="t-detail-s2">更新时间：{{findtime}}</span>
        <span class="t-detail-s3">事件脉络</span>
        <div class="t-detail-d">
          <div class="QWhotContent">
            <div
              class="QWhotContentItem"
              v-for="(item,index) in titleItem[title]"
              :key="index"
            >
              <div class="QWhotContentItemTop" @click="nav(item)">
                <span class="QWhotContentItems2" :title="item.title">{{
                  item.title
                }}</span>
                <span class="QWhotContentItems3">{{ item.time }}</span>
                <i class="QWhotContentItems4 el-icon-arrow-right"></i>
              </div>
              <div class="QWhotContentItemd1 clearfix" v-show="item.show">
                <p>{{ item.detail }}</p>
                <a
                  :href="item.url"
                  target="_blank"
                  >更多资讯</a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { nanoid } from "nanoid";
export default {
  name: "ThingDetail",
  data() {
    return {
      title: "",
      findtime:"",
      titleid:0,
      titleList:[],
      // titleItem:{"乌克兰局势":[{title:"纽约地铁枪击被啥",time:"20210205 11:20:56",detail:"的发射东方红反倒是的发挥的方式速式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活式发挥的方式速度放缓的方式飞书的身份和的身份和的方式活度放缓的方式飞书的身份和的身份和的方式活动方式和第三方和第三方还是短发和",id:1,show:false},
      //   {title:"纽约地铁枪击被啥",time:"20210205 11:20:56",detail:"的发射东方红反倒是的发挥的方式速度放缓的方式飞书的身份和的身份和的方式活动方式和第三方和第三方还是短发和",id:2,show:false},
      //   {title:"纽约地铁枪击被啥",time:"20210205 11:20:56",detail:"的发射东方红反倒是的发挥的方式速度放缓的方式飞书的身份和的身份和的方式活动方式和第三方和第三方还是短发和",id:3,show:false}]},
      titleItem:{},
    };
  },
  methods: {
    navTitle(index,name){
      this.titleid= index
      this.title = name
    },
    // 发送的ajax
    doQWhotSelect() {
      var formFile = new window.FormData();
      formFile.append("num", 10);
      axios.post("/baidu/retrieval/vein_list", formFile).then((res) => {
        this.titleList = [];
        this.titleItem = {};
        var data = JSON.parse(res.data.t);
        console.log(data)
        for (let item of data.content) {
          var tmp = {}
          tmp.id = item.event_id
          tmp.time = item.first_vein_time
          tmp.title = item.event_name
          tmp.tag = item.vein_tag
          var msg = item.vein
          this.titleList.push(tmp)
          this.titleItem[tmp.title] = []
          for(let k of msg){
            var tmp1 = {}
            tmp1.id = k.node_id
            tmp1.show = false
            tmp1.title = k.name
            tmp1.time = k.create_time.replace("T"," ")
            tmp1.detail = k.key_doc.summary
            tmp1.url = k.key_doc.url
            this.titleItem[tmp.title].push(tmp1)
          }
        }
      });
    },
    nav(item){
      // var m = this.titleItem[this.title][index]
      // console.log(m)
      // m.show = !m.show
      // console.log(m)
      // this.titleItem[this.title].splice(index,1,m)
      item.show = !item.show
      this.$forceUpdate()
    }
  },
  mounted() {
    this.doQWhotSelect()
    this.title="乌克兰局势"
    this.findtime = "2020-05-06 11:56:32"
  },
};
</script>

<style scoped>
.hot1{
  background-color: rgb(252,77,70) !important;
  color: white !important;
}
.hot2{
  background-color: rgb(255,146,1) !important;
  color: white !important;
}
.hot3{
  background-color: rgb(255,191,0) !important;
  color: white !important;
}
.t-left{
  /* background-color: yellow; */
  height: 1000px;
}
.t-title{
  height: 56px;
  position: relative;
  margin-top: 20px;
  /* background: red; */
}
.t-title-s1{
  position: absolute;
  font-weight: 900;
  font-size: 20px;
  left: 5%;
}
.t-title-s2{
  position: absolute;
  font-weight: 900;
  font-size: 14px;
  left: 5%;
  top: 30px;
  color: rgb(170,170,170);
}
.t-show{
  max-height: 1000px;
  overflow-y: auto ;
}
.t-item{
  margin: 20px 20px 0px;
  height: 100px;
  /* background-color: green; */
  position: relative;
  border: 1px solid rgb(200,200,200);
}
.t-item-s1{
  width: 20px;
  height: 20px;
  position: absolute;
  background-color: rgb(223,223,223);
  line-height: 20px;
  text-align: center;
  left: 20px;
  top: 25px;
}
.t-item-s2{
  position: absolute;
  left: 50px;
  top: 25px;
  font-size: 16px;
  font-weight: 600;
}
.t-item-s3{
  color: rgb(123,123,123);
  height: 20px;
  line-height: 20px;
  padding: 0px 4px;
  margin-right: 10px;
  border: 1px solid rgb(123,123,123);
}
.t-item-d{
  position: absolute;
  top: 55px;
  left: 20px;
}
.t-item:hover{
  cursor: pointer;
}
.t-item-active{
  border-left: 3px solid rgb(28,115,232) !important;
}
.t-item-s2-active{
  color: rgb(28,115,232) !important;;
}

/*主体 */
.t-detail{
  background-color: white;
  height: 1000px;
  margin-left: 50px;
  margin-top: 50px;
  position: relative;
}
.t-detail-s1{
  font-size: 28px;
  position: absolute;
  top: 30px;
  left: 5%;
  font-weight: 600;
}
.t-detail-s2{
  position: absolute;
  top: 70px;
  left: 5%;
  color: rgb(170,170,170);
}
.t-detail-s3{
  font-size: 20px;
  position: absolute;
  top: 120px;
  left: 5%;
  font-weight: 600;
}
.t-detail-d{
  border: 1px solid rgb(170,170,170);
  /* background-color: yellow; */
  /* height: 800px; */
  width: 90%;
  position: absolute;
  top: 160px;
  left: 5%;
  overflow-y: auto;
}



.QWhotContent {
  max-height: 800px;
  overflow-y: auto;
}
.QWhotContentItem {
  border: 1px solid rgb(235, 235, 235);
}
.QWhotContentItemTop {
  height: 50px;
  position: relative;
}
.QWhotContentItemTop:hover {
  cursor: pointer;
}

.QWhotContentItems2 {
  font-size: 14px;
  position: absolute;
  left: 10px;
  top: 50%;
  height: 20px;
  margin-top: -10px;
  font-weight: 600;
}
.QWhotContentItems3 {
  position: absolute;
  right: 20px;
  top: 50%;
  height: 20px;
  margin-top: -10px;
  font-size: 10px;
}
.QWhotContentItems4 {
  position: absolute;
  right: 5px;
  top: 50%;
  height: 20px;
  margin-top: -10px;
}
.QWhotContentItemd1 {
  padding: 10px;
  background-color: rgb(246, 246, 246);
}
.QWhotContentItemd1 a {
  float: right;
  margin-left: 10px;
}
</style>