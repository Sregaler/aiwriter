<template>
  <div class="row pt" v-cloak>
    <div class="col-md-2 col-xs-2"></div>
    <div class="col-md-9 col-xs-9">
      <div class="hotfind-title">
        <b>{{ title }}</b>
      </div>
      <div class="hotfind-line"></div>
      <div class="hot-select clearfix">
        <div id="hot-div">选择行业</div>
        <div
          :class="{ 'hot-div-active': hotType == item }"
          v-for="(item, index) in hotList"
          :key="index"
          @click="navHot(item)"
        >
          {{ item }}
        </div>
      </div>

      <!-- 展示 -->
      <div class="hot-item-box">
        <div class="QWhotContent">
          <div
            class="QWhotContentItem"
            v-for="(item, index) in QWhotContentMsg"
            :key="item.id"
          >
            <div class="QWhotContentItemTop" @click="item.show = !item.show">
              <span
                class="QWhotContentItems1"
                :class="{
                  hot1: index == 0,
                  hot2: index == 1,
                  hot3: index == 2,
                }"
                >{{ index + 1 }}</span
              >
              <span class="QWhotContentItems2" :title="item.name">{{
                item.name
              }}</span>
              <span class="QWhotContentItems3">{{ item.ctime }}</span>
              <i class="QWhotContentItems4 el-icon-arrow-right"></i>
            </div>
            <div class="QWhotContentItemd1 clearfix" v-show="item.show">
              <p>{{ item.summary }}</p>
              <a
                href="http://k.sina.com.cn/article_2814131830_a7bc3e7600100nvb9.html"
                target="_blank"
                >更多资讯</a
              >
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
  name: "HotFind",
  data() {
    return {
      title: "热点发现",
      hotType: "国际",
      hotList: [
        "国际",
        "国内",
        "军事",
        "财经",
        "科技",
        "房产",
        "娱乐",
        "教育",
        "社会",
        "旅游",
        "体育",
        "汽车",
        "游戏",
      ],
      QWhotContentMsg: [],
    };
  },
  methods: {
    navHot(name){
      this.hotType = name
      this.doQWhotSelect(name)
    },
    // 发送快速成稿的ajax
    doQWhotSelect(title) {
      var formFile = new window.FormData();
      formFile.append("domain", title);
      axios.post("/baidu/retrieval/hot_list", formFile).then((res) => {
        this.QWhotContentMsg = [];
        var data = JSON.parse(res.data.t);
        for (let i of data.content) {
          i["show"] = false;
          i["nid"] = nanoid();
          this.QWhotContentMsg.push(i);
        }
      });
    },
  },
  mounted() {
    this.doQWhotSelect(this.hotType);
  },
};
</script>

<style scoped>
.hotfind-title {
  font-size: 30px;
  height: 40px;
  line-height: 40px;
}
.hotfind-line {
  border-top: 2px solid rgb(238, 238, 238);
  margin-top: 20px;
  max-width: 1500px;
}
.hot-select {
  margin-top: 20px;
}
.hot-select div {
  float: left;
  width: 60px;
  height: 30px;
  margin-right: 20px;
  font-size: 16px;
  line-height: 30px;
  text-align: center;
}
.hot-select div:hover{
  cursor: pointer;
}
#hot-div {
  width: 80px;
  color: rgb(135, 135, 135);
}
.hot-div-active {
  background-color: rgb(36, 104, 241);
  color: white;
}
.hot-item-box {
  width: 80%;
  height: 1600px;
  margin-top: 40px;
  overflow-y: auto;
}
.hot-link-a {
  float: right;
  margin-left: 10px;
}

/* 热点搜索 */
.QWhotContent {
  height: 600px;
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
.QWhotContentItems1 {
  position: absolute;
  left: 10px;
  top: 50%;
  width: 20px;
  height: 20px;
  margin-top: -10px;
  background-color: rgb(240, 240, 240);
  color: rgb(166, 166, 166);
  text-align: center;
}
.QWhotContentItems2 {
  font-size: 12px;
  position: absolute;
  left: 40px;
  top: 50%;
  height: 20px;
  margin-top: -10px;
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
.hot1{
  background-color: rgb(252,77,70);
  color: white !important;
}
.hot2{
  background-color: rgb(255,146,1);
  color: white !important;
}
.hot3{
  background-color: rgb(255,191,0);
  color: white !important;
}
</style>