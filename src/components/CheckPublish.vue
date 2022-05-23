<template>
  <div class="row pt" v-cloak>
    <div class="context-left col-md-2 col-xs-2">
      <div class="context-left-nav">
        <ul class="context-left-ul">
          <li class="left-li1">
            <img src="../assets/icon/修改1.svg" alt="" />内容修改
          </li>
          <li class="left-li2 context-left-li">
            <img src="../assets/其他/审核发布-选中.svg" alt="" />审核评分
          </li>
        </ul>
      </div>

      <!-- 实时评分 -->
      <div class="ZN-Score">
        <span class="ZN-Score-s1">智能评分:</span>
        <img class="ZN-Score-i1" src="../assets/icon/生成评分.svg" alt="" @click="getScore">
        <div class="ZN-Score-loading" v-loading="Loadding" element-loading-text="正在评分中">
          <span class="ZN-Score-s2">{{agvSocre}}</span>
          <div class="ZN-Score-d">
              <p v-for="(item,index) in socreList" :key="index">{{item.key}}：<b>{{item.value}}</b></p>
          </div>
        </div>
      </div>
    </div>
    <div class="context-center col-md-7 col-xs-7">
      <!-- 头部 -->
      <div class="context-center-top clearfix">
        <div class="b1">
          <b>审核评分</b>
        </div>
        <el-button type="primary" @click="coverControl = true"
          ><b>保存</b></el-button
        >
        <el-button type="info" @click="preStep"><b>返回</b></el-button>
      </div>

      <!-- 文章 -->
      <div class="formwork-button">
        <!-- <div class="textScore textScoreFix">
          <span>实时评分:85</span>
        </div> -->
        <!-- 标题 -->
        <div class="formwork-button-inner">
          <div class="context-center-input" style="width: 100%">
            <input
              type="text"
              maxlength="64"
              placeholder="请输入文章标题"
              v-model="title"
            />
          </div>
        </div>
        <!-- 工具条 -->
        <div class="formwork-toolbar">
          <ul>
            <li @click="editChange('undo', null)">
              <img src="../assets/工具栏/撤销.svg" alt="" />
            </li>
            <li @click="editChange('redo', null)">
              <img src="../assets/工具栏/重做.svg" alt="" />
            </li>
            <li @click="editChange('removeFormat', null)">
              <img src="../assets/工具栏/清除格式.svg" alt="" />
            </li>
            <li @click="editChange('bold', null)">
              <img src="../assets/工具栏/加粗.svg" alt="" />
            </li>
            <li @click="editChange('italic', null)">
              <img src="../assets/工具栏/斜体.svg" alt="" />
            </li>
            <li @click="editChange('underline', null)">
              <img src="../assets/工具栏/下划线.svg" alt="" />
            </li>
            <li @click="editChange('foreColor', '#ff0000')">
              <img src="../assets/工具栏/字体颜色.svg" alt="" />
            </li>
            <li @click="editChange('justifyLeft', null)">
              <img src="../assets/工具栏/居左对齐.svg" alt="" />
            </li>
            <li @click="editChange('justifyCenter', null)">
              <img src="../assets/工具栏/居中对齐.svg" alt="" />
            </li>
            <li @click="editChange('justifyRight', null)">
              <img src="../assets/工具栏/居右对齐.svg" alt="" />
            </li>
            <li @click="editChange('justifyFull', null)">
              <img src="../assets/工具栏/两端对齐.svg" alt="" />
            </li>
            <li @click="goPutFile(1)">
              <img src="../assets/工具栏/图片.svg" alt="" />
            </li>
            <li @click="goPutFile(2)">
              <img src="../assets/工具栏/视频.svg" alt="" />
            </li>
            <li @click="goPutFile(3)">
              <img src="../assets/工具栏/音频.svg" alt="" />
            </li>
            <input
              id="imgUp"
              type="file"
              name="files"
              style="display: none"
              accept=".png,.jpg"
              @change="fileUpOver"
            />
            <input
              id="videoUp"
              type="file"
              name="files"
              style="display: none"
              accept=".avi,.mp4"
              @change="fileUpOver"
            />
            <input
              id="soundUp"
              type="file"
              name="files"
              style="display: none"
              accept=".mp3,.wav"
              @change="fileUpOver"
            />
          </ul>
        </div>

        <div
          id="QWFormworkTextAi"
          class="formwork-text"
          contenteditable="true"
          spellcheck="false"
          v-html="qwtext"
        ></div>
      </div>
    </div>
    <div class="context-right col-md-3 col-xs-3">
      <div class="check-box">
        <div class="check-nav clearfix">
          <div
            class="check-item"
            :class="{ 'check-checked': checkInfoBtn == name }"
            v-for="(info, name) in checkInfo"
            :key="name"
            @click="checkInfoNav(name)"
          >
            <span class="check-item-s1">{{ name }}</span>
            <span class="check-item-s2"
              ><b>{{ checkInfo[name].length }}</b></span
            >
          </div>
        </div>
        <div class="check-content">
          <div class="check-title">{{ checkInfoBtn }}</div>
          <div class="check-select clearfix">
            <span @click="sendCheckMsg">重新检测</span>
            <span @click="removeAllCheck">全部忽略</span>
            <span @click="modifyAllCheck">全部纠错</span>
          </div>
          <div class="check-show">
            <div
              class="check-show-item"
              v-for="item in checkInfo[checkInfoBtn]"
              :key="item.id"
            >
              <span class="check-show-item-s1">{{ item.msg }}</span>
              <span class="check-show-item-s2"
                ><font color="#D5AB3F">{{ item.frag_ori }}</font
                >&nbsp;->&nbsp;{{ item.frag_fixed }}</span
              >
              <img
                class="check-show-item-i1"
                @click="modifyCheck(item.frag_ori, item.frag_fixed)"
                src="../assets/其他/对.svg"
                alt=""
              />
              <img
                class="check-show-item-i2"
                @click="removeCheckById(item)"
                src="../assets/其他/错.svg"
                alt=""
              />
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 遮罩 -->
    <div class="Cover" v-cloak v-show="coverControl"></div>
    <div class="GaoJanSelect" v-show="coverControl">
      <span class="GaoJanSelect-s">保存当前稿件</span>
      <div class="GaoJanSelect-nav">
        <el-radio v-model="gaoJan" label="1">直接保存</el-radio>
        <br />
        <el-radio v-model="gaoJan" label="2">智能图文成稿</el-radio>
        <br />
        <el-radio v-model="gaoJan" label="3">稿件转视频</el-radio>
      </div>
      <div class="infoPut">注:稿件转视频必须要有图文且不包含视频</div>
      <el-button type="primary" class="btn1-left" @click="generateTxt"
        >成稿</el-button
      >
      <el-button class="btn1-right" @click="coverControl = false"
        >取消</el-button
      >
    </div>

    <!-- 通用遮罩 -->
    <div class="Cover" v-cloak v-show="isPut"></div>
    <div class="Modal" v-cloak v-show="isPut">
      <div class="modal-top">
        <ul class="clearfix">
          <li
            :class="{ 'modal-select': putOrSearch == 0 }"
            @click="putOrSearch = 0"
          >
            上传素材
          </li>
          <li :class="{ 'modal-select': putOrSearch == 1 }" @click="showSuCai">
            本地素材库
          </li>
          <span @click="cancelFilePut">×</span>
        </ul>
      </div>
      <div class="modal-center" v-show="putOrSearch == 0">
        <ul class="clearfix">
          <li>
            <div class="fileItem" @click="filesUp(putId)">+</div>
            <span class="filetitle">本地上传</span>
          </li>
          <li v-for="(item, index) in currentPutFile" :key="item.id">
            <div class="fileItem">
              <input
                class="filechecked"
                type="checkbox"
                v-model="indexList"
                :value="index"
              />
              <img class="fileImg" :src="item.imgurl" alt="" />
            </div>
            <span class="filetitle" :title="item.name">{{
              item.nickname
            }}</span>
          </li>
        </ul>
      </div>
      <div class="modal-center" v-show="putOrSearch == 1">
        <ul class="clearfix">
          <li v-for="(item, index) in suCaiList" :key="item.mid">
            <div class="fileItem">
              <input
                class="filechecked"
                type="checkbox"
                v-model="indexSucai"
                :value="index"
              />
              <img class="fileImg" :src="item.imgurl" alt="" />
            </div>
            <span class="filetitle" :title="item.m_name">{{
              item.nickname
            }}</span>
          </li>
        </ul>
      </div>
      <div class="modal-button">
        <!-- <input class="modal-button-i1" type="checkbox" v-model="isSave" /> -->
        <!-- <span class="modal-button-s1">保存至本地素材库</span> -->
        <button class="filequit" @click="cancelFilePut">取消</button>
        <button class="fileput" @click="addSuCai">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { nanoid } from "nanoid";
export default {
  name: "CheckPublish",
  props: ["qwtext", "qtitle", "innerdata"],
  data() {
    return {
      Loadding:false,
      gaoJan: "1",
      coverControl: false,
      title: this.qtitle,
      //审核发布收起展开
      checkToolFixer: {
        flag: false,
        link: require("../assets/其他/浮窗-收回.svg"),
      },
      // 快速写稿模板选择、上传素材、敏感词查找
      formworkSelectButton: [true, false, false],
      // 审核信息
      // checkInfo:{
      //     "全部建议":[{"frag_ori":"人工只能","frag_fixed":"人工智能","explain":"建议用“人工智能”替换“人工只能”","label":"010200","msg":"疑似别词","msg1":"字词纠错","id":"fdsagf"},
      //     {"frag_ori":"工司","frag_fixed":"公司","explain":"建议用“人工智能”替换“人工只能”","label":"010200","msg":"疑似别词","msg1":"字词纠错","id":"gfgdh"},
      //     {"frag_ori":"。。","frag_fixed":"。","explain":"建议用“人工智能”替换“人工只能”","label":"010200","msg":"疑似标点误用","msg1":"标点纠错","id":"fgdtth"}],
      //     "字词纠错":[{"frag_ori":"人工只能","frag_fixed":"人工智能","explain":"建议用“人工智能”替换“人工只能”","label":"010200","msg":"疑似别词","msg1":"字词纠错","id":"fdsagf"},
      //     {"frag_ori":"工司","frag_fixed":"公司","explain":"建议用“人工智能”替换“人工只能”","label":"010200","msg":"疑似别词","msg1":"字词纠错","id":"gfgdh"}],
      //     "标点纠错":[{"frag_ori":"。。","frag_fixed":"。","explain":"建议用“人工智能”替换“人工只能”","label":"010200","msg":"疑似标点误用","msg1":"标点纠错","id":"fgdtth"}],
      //     "敏感审核":[],
      // },
      checkInfo: {
        全部建议: [],
        字词纠错: [],
        标点纠错: [],
        敏感审核: [],
      },
      checkInfoBtn: "全部建议",
      aid: null,

      currentPutFile: [],
      indexList: [],
      indexSucai: [],
      isPut: false,
      putOrSearch: 0,
      putId: 1,
      suCaiList: [],
      agvSocre:0,
      socreList:[],
    };
  },
  methods: {
    //获取评分
    getScore(){
      var formFile = new window.FormData();
      formFile.append("title", this.title); //加入文件对象
      formFile.append("content", document.getElementById("QWFormworkTextAi").innerHTML); //加入文件对象
      this.Loadding = true
      axios.post("/shanda/evaluate/news_score",formFile).then(res=>{
        if(res.data.ok){
          this.socreList = []
          var data = JSON.parse(res.data.t)
          var avg = 0
          for(let i in data){
            if(data[i] instanceof Array){
              continue
            }else{
              var tmp = {}
              tmp.key = i;
              avg += (data[i]*100)
              tmp.value = (data[i]*100).toFixed(1)
              this.socreList.push(tmp)
            }
          }
          this.agvSocre = (avg/this.socreList.length).toFixed(1)
          this.Loadding = false
        }
      })
    },
    // 富文本工具栏字体变化
    editChange(com, value) {
      document.execCommand(com, false, value);
    },
    // 返回
    preStep() {
      this.$router.back();
    },
    // 固定布局
    windowScroll(e) {
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      if (scrollTop >= 800) {
        this.fixSocre = true;
      } else {
        this.fixSocre = false;
      }
    },
    // 切换审核卡
    checkInfoNav(name) {
      this.checkInfoBtn = name;
    },
    //审核发布快速写稿收起展开
    checkToolFix() {
      if (this.checkToolFixer.flag == false) {
        this.checkToolFixer.link = require("../assets/其他/浮窗-展开.svg");
      } else {
        this.checkToolFixer.link = require("../assets/其他/浮窗-收回.svg");
      }
      this.checkToolFixer.flag = !this.checkToolFixer.flag;
    },
    // 审核发布模板选择
    toggleType(index) {
      if (index == 0) {
        this.formworkSelectButton.splice(0, 3, false, false, false);
        this.formworkSelectButton.splice(0, 1, true);
      } else if (index == 1) {
        this.formworkSelectButton.splice(0, 3, false, false, false);
        this.formworkSelectButton.splice(1, 1, true);
      } else {
        this.formworkSelectButton.splice(0, 3, false, false, false);
        this.formworkSelectButton.splice(2, 1, true);
      }
    },
    // 审核请求
    sendCheckMsg() {
      var aP = document
        .getElementById("QWFormworkTextAi")
        .getElementsByTagName("p");
      var innermsg = "";
      for (var i = 0; i < aP.length; i++) {
        var msg = aP[i].innerText;
        innermsg = innermsg + msg;
      }
      var formFile = new window.FormData();
      formFile.append("data", innermsg);
      axios
        .post("/baidu/review/ai_review", formFile)
        .then((res) => {
          if (res.data.ok) {
            for (let k in this.checkInfo) {
              this.checkInfo[k] = [];
            }
            let msg = JSON.parse(res.data.t);
            let dmap = {
              "010100": ["字词纠错", "疑似别词"],
              "010200": ["字词纠错", "疑似别字"],
              "020100": ["标点纠错", "标点误用"],
              "020200": ["标点纠错", "标点缺失"],
              "040100": ["敏感审核", "疑似敏感人名"],
              "040200": ["敏感审核", "疑似敏感词"],
            };
            for (let item of msg.fragments) {
              let tag = item.label;
              item["msg"] = dmap[tag][1];
              item["id"] = nanoid();
              item["msg1"] = dmap[tag][0];
              this.checkInfo["全部建议"].push(item);
              this.checkInfo[dmap[tag][0]].push(item);
              this.chargeCheckColor(item.frag_ori);
            }
          } else {
            console.log(res.data.t);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 改变错误字体颜色
    chargeCheckColor(name) {
      var str = '<span style="color:red">' + name + "</span>";
      var aP = document
        .getElementById("QWFormworkTextAi")
        .getElementsByTagName("p");
      for (var i = 0; i < aP.length; i++) {
        var innermsg = aP[i].innerHTML;
        if (innermsg.includes(name)) {
          aP[i].innerHTML = innermsg.replace(name, str);
        }
      }
    },
    // 修改错误
    modifyCheck(orname, newname) {
      var str = '<span style="color:red">' + orname + "</span>";
      var aP = document
        .getElementById("QWFormworkTextAi")
        .getElementsByTagName("p");
      for (var i = 0; i < aP.length; i++) {
        var innermsg = aP[i].innerHTML;
        if (innermsg.includes(orname)) {
          aP[i].innerHTML = innermsg.replace(str, newname);
        }
      }
    },
    // 忽略所有
    removeAllCheck() {
      if (this.checkInfoBtn == "全部建议") {
        for (let k in this.checkInfo) {
          this.checkInfo[k] = [];
        }
      } else {
        for (let k of this.checkInfo[this.checkInfoBtn]) {
          for (let v of this.checkInfo["全部建议"]) {
            if (v.id == k.id) {
              let index1 = this.checkInfo["全部建议"].indexOf(v);
              this.checkInfo["全部建议"].splice(index1, index1 + 1);
            }
          }
        }
        this.checkInfo[this.checkInfoBtn] = [];
      }
    },
    modifyAllCheck() {
      for (let item of this.checkInfo[this.checkInfoBtn]) {
        this.modifyCheck(item.frag_ori, item.frag_fixed);
      }
      this.removeAllCheck();
    },
    removeCheckById(item) {
      var index1;
      for (let k of this.checkInfo["全部建议"]) {
        if (k.id == item.id) {
          index1 = this.checkInfo["全部建议"].indexOf(k);
          break;
        }
      }
      this.checkInfo["全部建议"].splice(index1, 1);
      for (let v of this.checkInfo[item.msg1]) {
        if (v.id == item.id) {
          index1 = this.checkInfo[item.msg1].indexOf(v);
          break;
        }
      }
      this.checkInfo[item.msg1].splice(index1, 1);
    },
    //选择成稿方式
    generateTxt() {
      if (this.gaoJan == 1) {
        axios
          .post("/article/saveOrUpdate", {
            title: this.title,
            content: document.getElementById("QWFormworkTextAi").innerHTML,
            aid: this.aid,
            cid: "1",
          })
          .then((res) => {
            if (res.data.ok) {
              this.aid = res.data.t.uid;
              this.coverControl = false;
              this.$alert("<strong>保存成功</strong>", "保存信息", {
                dangerouslyUseHTMLString: true,
              });
            } else {
              this.$message.error("保存失败");
            }
          })
          .catch((err) => {
            this.$message.error("保存失败");
          });
      } else if (this.gaoJan == 2) {
        this.$bus.$emit("navTag", "智能图文成稿");
        this.$router.push({
          name: "aiwrite",
          params: {
            qtitle: this.title,
            qwtext: document.getElementById("QWFormworkTextAi").innerHTML,
            innerdata: this.innerdata,
          },
        });
      } else {
        let myimg = document
          .getElementById("QWFormworkTextAi")
          .getElementsByTagName("img");
        var myvideo = document
          .getElementById("QWFormworkTextAi")
          .getElementsByTagName("video");
        if (myimg.length > 0 && myvideo.length == 0) {
          let content = document.getElementById("QWFormworkTextAi").innerHTML;
          this.$bus.$emit("navTag", "稿件转视频");
          this.$router.push({
            name: "testtovideo",
            params: {
              content: content,
            },
          });
        } else if (myimg.length <= 0) {
          this.$message.warning("视频成稿必须包含文本与图片");
        } else {
          this.$message.warning("视频成稿不可包含视频");
        }
      }
    },
    // 插入各种素材
    insertHtmlAtCaret(html) {
      document.getElementById("QWFormworkTextAi").focus();
      var sel, range;
      if (window.getSelection) {
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
          range = sel.getRangeAt(0);
          range.deleteContents();
          var el = document.createElement("div");
          el.innerHTML = html;
          var frag = document.createDocumentFragment(),
            node,
            lastNode;
          while ((node = el.firstChild)) {
            lastNode = frag.appendChild(node);
          }
          range.insertNode(frag);
          if (lastNode) {
            range = range.cloneRange();
            range.setStartAfter(lastNode);
            range.collapse(true);
            sel.removeAllRanges();
            sel.addRange(range);
          }
        }
      }
    },
    goPutFile(id) {
      this.putId = id;
      this.isPut = true;
    },
    //素材库
    selectAll(page) {
      var formFile = new window.FormData();
      formFile.append("page", page + "");
      formFile.append("pageSize", "1000000");
      formFile.append("m_type", this.putId + "");
      axios
        .post("/material/list", formFile)
        .then((res) => {
          this.suCaiList = [];
          for (let info of res.data.list) {
            info.url = info.content;
            if (info.m_name.length > 10) {
              info.nickname = info.m_name.substring(0, 7) + "...";
            } else {
              info.nickname = info.m_name;
            }
            if (info.m_type == 1) {
              info["imgurl"] = info.content;
            } else if (info.m_type == 2) {
              info["imgurl"] = require("../assets/video.jpg");
            } else {
              info["imgurl"] = require("../assets/audio.jpg");
            }
            this.suCaiList.push(info);
          }
          // this.totalPage = res.data.total
        })
        .catch((e) => {
          console.log(e);
        });
    },
    showSuCai() {
      this.putOrSearch = 1;
      this.selectAll(1);
    },
    // 文件上传
    filesUp(index) {
      if (index == 1) {
        document.getElementById("imgUp").click();
      } else if (index == 2) {
        document.getElementById("videoUp").click();
      } else {
        document.getElementById("soundUp").click();
      }
    },
    appendEle(data) {
      var filename = data.url.substring(data.url.lastIndexOf("."));
      if (filename == ".jpg" || filename == ".png") {
        var ele = `<p style="text-align:center;">&nbsp;<img src=${data.url} style="width:60%;" alt="">&nbsp;</p>`;
      } else if (filename == ".mp4" || filename == ".avi") {
        var ele = `<p style="text-align:center;">&nbsp;<video controls src=${data.url} style="width:60%;">&nbsp;</p>`;
      } else {
        var ele = `<p style="text-align:center;">&nbsp;<audio controls src=${data.url} style="width:60%;">&nbsp;</p>`;
      }
      var tmp = document.getElementById("QWFormworkTextAi").innerHTML;
      var tmp = tmp + ele;
      document.getElementById("QWFormworkTextAi").innerHTML = tmp;
    },
    fileUpOver(event) {
      var fileObj = event.target.files[0]; // js 获取文件对象
      var ty;
      if (typeof fileObj == "undefined") {
        return;
      }
      var filename = fileObj.name.substring(fileObj.name.lastIndexOf("."));
      if (
        fileObj.size > 1024 * 1024 * 5 &&
        (filename == ".jpg" || filename == ".png")
      ) {
        this.$message.warning("图片上传不能超过5M");
        return;
      }
      if (
        fileObj.size > 1024 * 1024 * 1024 &&
        (filename == ".mp4" || filename == ".avi")
      ) {
        this.$message.warning("视频上传不能超过1G");
        return;
      }
      if (
        fileObj.size > 20 * 1024 * 1024 &&
        (filename == ".mp3" || filename == ".wam")
      ) {
        this.$message.warning("音频上传不能超过20M");
        return;
      }
      var formFile = new window.FormData();
      formFile.append("files", fileObj); //加入文件对象
      formFile.append("m_type", this.putId); //加入文件对象
      formFile.append(
        "m_name",
        fileObj.name.substring(fileObj.name.lastIndexOf("/") + 1)
      ); //加入文件对象
      axios
        .post("/material/save", formFile, {
          //设置请求头
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          var data = res.data;
          if (data.ok) {
            data = data.t[0];
            if (this.putId == 1) {
              var obj = {
                name: data.m_name,
                url: data.content,
                id: nanoid(),
                imgurl: data.content,
              };
            } else if (this.putId == 2) {
              var obj = {
                name: data.m_name,
                url: data.content,
                id: nanoid(),
                imgurl: require("../assets/video.jpg"),
              };
            } else {
              var obj = {
                name: data.m_name,
                url: data.content,
                id: nanoid(),
                imgurl: require("../assets/audio.jpg"),
              };
            }
            if (obj.name.length > 10) {
              obj.nickname = obj.name.substring(0, 7) + "...";
            } else {
              obj.nickname = obj.name;
            }
            this.currentPutFile.push(obj);
            document.getElementById("imgUp").value = "";
            document.getElementById("videoUp").value = "";
            document.getElementById("soundUp").value = "";
          } else {
            this.$message.warning("文件上传失败");
          }
        })
        .catch((e) => {
          this.$message.error("文件上传失败");
          console.error(e);
        });
    },
    goPutFile(id) {
      this.putId = id;
      this.isPut = true;
    },
    cancelFilePut() {
      this.isPut = false;
      this.putOrSearch = 0;
      this.currentPutFile = [];
      this.indexList = [];
      this.suCaiList = [];
      this.indexSucai = [];
    },
    addSuCai() {
      for (let item of this.indexList) {
        this.appendEle(this.currentPutFile[item]);
      }
      for (let item of this.indexSucai) {
        this.appendEle(this.suCaiList[item]);
      }
      this.cancelFilePut();
    },
  },
  mounted() {
    window.addEventListener("scroll", this.windowScroll);
    this.sendCheckMsg();
  },
};
</script>

<style scoped>
input::-webkit-input-placeholder {
  color: #dbdbdb;
}

.context-center-input input:focus {
  outline: none;
}

/* 主体左侧 */
.context-left {
  padding-right: 0px;
  position: relative;
}
.context-left-nav {
  border-right: 2px solid rgb(236, 236, 236);
  position: absolute;
  height: 110px;
  right: 0px;
  top: 50px;
}
.context-left-ul li {
  margin-bottom: 15px;
  padding: 8px 20px;
  font-size: 20px;
  color: #dbdbdb;
}
.context-left-li {
  color: #2c2c2c !important;
}
.left-li1 img {
  width: 20px;
  height: 20px;
  margin-right: 8px;
  margin-left: 2px;
}
.left-li2 img,
.left-li3 img {
  width: 25px;
  height: 25px;
  margin-right: 8px;
}
/* 主体 */
.context-center {
  padding-left: 80px;
}
.context-center-top button {
  margin-top: 5px;
  font-size: 18px;
  width: 120px;
  height: 50px;
  float: right;
  margin-left: 30px;
}
.b1 {
  float: left;
  padding-top: 10px;
  font-size: 30px;
}

/* 正文 */
.context-center-input {
  width: 90%;
  height: 140px;
  margin: auto;
}
.context-center-input input {
  /* display: block; */
  width: 100%;
  height: 70px;
  font-size: 40px;
  border: none;
  border-bottom: 3px solid rgb(236, 236, 236);
  margin-top: 50px;
  padding-bottom: 20px;
}
li:hover {
  cursor: pointer;
}
.formwork-button {
  /* border: solid 2px #dbdbdb; */
  border: none;
  padding: 70px 100px;
  margin: 100px 0px 100px;
  background-color: white;
  position: relative;
}
.formwork-toolbar {
  width: 80%;
  margin: auto;
  height: 50px;
  background-color: rgb(243, 247, 249);
  border-radius: 5px;
}
.formwork-toolbar li {
  float: left;
  height: 50px;
  width: 7.14%;
  text-align: center;
  line-height: 48px;
}
.formwork-toolbar li:hover {
  background-color: #c2b6b6;
}
.formwork-toolbar img {
  width: 25px;
}
.formwork-text {
  width: 100%;
  min-height: 800px;
  font-size: 18px;
  margin-top: 30px;
  outline: none;
  border: none;
}

/* 评分 */
.textScore {
  width: 180px;
  height: 60px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  background-color: white;
  text-align: center;
  line-height: 60px;
  z-index: 200;
  font-size: 20px;
  position: absolute;
  top: 300px;
  left: -150px;
}

/* 审核卡 */
.context-right {
  padding: 0px;
}
.check-box {
  background-color: white;
  margin-top: 155px;
}
.check-nav {
  border-bottom: 1px solid rgb(240, 240, 240);
  padding-bottom: 20px;
}
.check-item {
  width: 100px;
  height: 74px;
  background-color: rgb(250, 252, 252);
  float: left;
  margin-left: 10px;
  margin-top: 10px;
  position: relative;
}
.check-item:hover {
  cursor: pointer;
}
.check-item-s1 {
  text-align: center;
  width: 80px;
  font-size: 15px;
  position: absolute;
  top: 10px;
  left: 50%;
  margin-left: -40px;
}
.check-item-s2 {
  text-align: center;
  width: 50px;
  font-size: 22px;
  position: absolute;
  top: 35px;
  left: 50%;
  margin-left: -25px;
}
.check-content {
  padding: 10px 20px;
}
.check-title {
  font-size: 27px;
  height: 50px;
  line-height: 50px;
}
.check-checked {
  background-color: #e1ebef;
}
.check-select span {
  float: right;
  margin-left: 10px;
  font-size: 14px;
  color: rgb(1, 84, 120);
  font-size: 14px;
}
.check-select {
  margin: 10px 0px;
}
.check-select span:hover {
  cursor: pointer;
}
.check-show {
  height: 1000px;
  overflow-y: auto;
}
.check-show-item {
  position: relative;
  height: 90px;
  margin-bottom: 20px;
  box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.1);
  border-radius: 1px;
  background-color: white;
  font-size: 14px;
}
.check-show-item-s1 {
  position: absolute;
  top: 20px;
  left: 30px;
  color: rgb(166, 166, 166);
}
.check-show-item-s2 {
  position: absolute;
  top: 50px;
  left: 30px;
}
.check-show-item-i1 {
  position: absolute;
  top: 50%;
  margin-top: -10px;
  right: 50px;
  width: 20px;
  height: 20px;
}
.check-show-item-i2 {
  position: absolute;
  top: 50%;
  margin-top: -10px;
  right: 20px;
  width: 20px;
  height: 20px;
}
.check-show-item img {
  cursor: pointer;
}
/* 成稿 */
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
.GaoJanSelect {
  width: 500px;
  height: 250px;
  background-color: white;
  position: fixed;
  left: 50%;
  top: 50%;
  margin-left: -250px;
  margin-top: -125px;
  z-index: 1000;
  border-radius: 5px;
  padding: 30px;
  font-size: 18px;
}
.GaoJanSelect-s {
  font-size: 21px;
  position: absolute;
  width: 200px;
  text-align: center;
  top: 20xp;
  left: 50%;
  margin-left: -100px;
}
.GaoJanSelect-nav {
  width: 100px;
  position: absolute;
  top: 75px;
  left: 50%;
  margin-left: -50px;
}
.btn1-right {
  position: absolute;
  bottom: 20px;
  right: 32%;
}
.btn1-left {
  position: absolute;
  bottom: 20px;
  left: 32%;
}
.infoPut {
  width: 300px;
  text-align: center;
  position: absolute;
  bottom: 70px;
  font-size: 14px;
  left: 50%;
  margin-left: -150px;
}

/*文件上传框*/
.Modal {
  width: 800px;
  height: 540px;
  background-color: white;
  position: fixed;
  left: 50%;
  top: 50%;
  margin-left: -400px;
  margin-top: -270px;
  z-index: 1000;
  border-radius: 10px;
}
.modal-top {
  height: 55px;
  border-bottom: solid 1px #dbdbdb;
}
.modal-top ul li {
  margin-top: 20px;
  margin-left: 20px;
  display: block;
  width: 80px;
  height: 35px;
  float: left;
  text-align: center;
  line-height: 35px;
}
.modal-top ul li:hover {
  background-color: rgb(240, 240, 240);
  cursor: pointer;
}
.modal-select {
  color: #015478;
  border-bottom: solid #015478 2px;
}
.modal-top span {
  float: right;
  font-size: 20px;
  margin-top: 10px;
  margin-right: 10px;
}
.modal-top span:hover {
  cursor: pointer;
}
.modal-center {
  height: 420px;
  border-bottom: solid 1px #dbdbdb;
  overflow-y: auto;
}
.modal-center ul {
  padding-left: 0px;
  margin-bottom: 0px;
}
.modal-center ul li {
  display: block;
  width: 140px;
  height: 100px;
  float: left;
  text-align: center;
  margin-left: 17px;
  margin-top: 10px;
}
.fileItem {
  width: 140px;
  height: 76px;
  font-size: 30px;
  line-height: 76px;
  border: solid 1px #dbdbdb;
  border-radius: 5px;
  color: #c5c3c3;
  margin-bottom: 3px;
  position: relative;
  overflow: hidden;
}
.filechecked {
  position: absolute;
  top: 5px;
  left: 8px;
}
.fileImg {
  width: 100%;
}
.modal-center ul li:hover {
  cursor: pointer;
}
.modal-button {
  width: 100%;
  height: 100%;
  position: relative;
}
.modal-button-i1 {
  position: absolute;
  top: 20px;
  left: 30px;
}
.modal-button-s1 {
  position: absolute;
  top: 20px;
  left: 50px;
}
.modal-button-i2 {
  position: absolute;
  top: 30px;
  left: 30px;
}
.modal-button-s2 {
  position: absolute;
  top: 30px;
  left: 50px;
}
.filequit,
.fileput {
  width: 90px;
  height: 32px;
  border: none;
  position: absolute;
  top: 15px;
}
.filequit {
  left: 300px;
}
.fileput {
  right: 300px;
  background-color: #015478;
  color: white;
}
.filequit:hover {
  background-color: #c5c3c3;
}
.fileput:hover {
  background-color: #04364b;
}


.ZN-Score{
  height: 500px;
  background-color: white;
  margin-top: 330px;
  position: relative;
}
.ZN-Score-s1{
  font-size: 24px;
  font-weight: 900;
  position: absolute;
  left: 20px;
  top: 10px;
}
.ZN-Score-i1{
  width: 20px;
  height: 20px;
  position: absolute;
  top: 18px;
  right: 20px;
}
.ZN-Score-i1:hover{
  cursor: pointer;
}
.ZN-Score-s2{
  font-size: 48px;
  font-weight: 900;
  color: rgb(53,130,184);
  position: absolute;
  left: 20px;
  top: 0px;
}
.ZN-Score-d{
  position: absolute;
  left: 20px;
  top: 80px;
}
.ZN-Score-d p{
  font-size: 16px;
}
.ZN-Score-loading{
  height: 450px;
  width: 100%;
  position: absolute;
  top: 50px;
}
</style>