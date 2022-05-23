<template>
  <div class="row pt" v-cloak>
    <div class="context-left col-md-2 col-xs-2">
      <div class="context-left-nav">
        <ul class="context-left-ul">
          <li class="left-li1 context-left-li">
            <img src="../assets/icon/修改.svg" alt="" />内容修改
          </li>
          <li class="left-li2">
            <img src="../assets/其他/审核发布-未选中.svg" alt="" />审核评分
          </li>
        </ul>
      </div>
    </div>
    <div class="context-center col-md-7 col-xs-7">
      <div class="context-center-top clearfix">
        <div class="b1">
          <b>模板成稿</b>
        </div>
        <el-button type="primary" @click="nextStep"><b>下一步</b></el-button>
        <el-button type="info" @click="preStep"><b>返回</b></el-button>
      </div>
      <div class="qwrite-info">
        <span class="qwrite-info-s1"><b>当前模板</b></span>
        <span class="qwrite-info-s2" v-cloak>{{ modal }}</span>
      </div>
      <div class="formwork-button">
        <!-- 标题 -->
        <div class="formwork-button-inner">
          <div class="context-center-input" style="width: 100%">
            <input
              type="text"
              maxlength="64"
              placeholder="请输入文章标题"
              v-model="qwtitle"
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
          spellcheck="true"
        ></div>
      </div>
      <!-- 通用遮罩 -->
      <div class="Cover" v-cloak v-show="isPut"></div>
      <div class="Modal" v-cloak v-show="isPut">
        <div class="modal-top">
          <ul class="clearfix">
            <li :class="{'modal-select':putOrSearch==0}" @click="putOrSearch=0">上传素材</li>
            <li :class="{'modal-select':putOrSearch==1}"  @click="showSuCai">本地素材库</li>
            <span @click="cancelFilePut">×</span>
          </ul>
        </div>
        <div class="modal-center" v-show="putOrSearch==0">
          <ul class="clearfix">
            <li>
              <div class="fileItem" @click="filesUp(putId)">+</div>
              <span class="filetitle">本地上传</span>
            </li>
            <li v-for="(item,index) in currentPutFile" :key="item.id">
              <div class="fileItem">
                <input class="filechecked" type="checkbox" v-model="indexList" :value="index"/>
                <img
                  class="fileImg"
                  :src="item.imgurl"
                  alt=""
                />
              </div>
              <span class="filetitle" :title="item.name" >{{item.nickname}}</span>
            </li>
          </ul>
        </div>
        <div class="modal-center" v-show="putOrSearch==1">
          <ul class="clearfix">
            <li v-for="(item,index) in suCaiList" :key="item.mid">
              <div class="fileItem">
                <input class="filechecked" type="checkbox" v-model="indexSucai" :value="index"/>
                <img
                  class="fileImg"
                  :src="item.imgurl"
                  alt=""
                />
              </div>
              <span class="filetitle" :title="item.m_name" >{{item.nickname}}</span>
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
  </div>
</template>

<script>
import axios from "axios";
import {nanoid} from 'nanoid'
export default {
  name: "QuickWriter",
  props: ["qwtext", "city", "modal"],
  data() {
    return {
      stepFont: [true, false],
      qwtitle: "",
      innerdata: "",
      currentPutFile: [],
      indexList: [],
      indexSucai:[],
      isPut: false,
      putOrSearch:0,
      putId:1,
      suCaiList:[],
    };
  },
  methods: {
    // 富文本工具栏字体变化
    editChange(com, value) {
      document.execCommand(com, false, value);
    },
    preStep() {
      this.$router.replace({
        name: "moban",
      });
    },
    nextStep() {
      this.$router.push({
        name: "checkpublish",
        params: {
          qtitle: this.qwtitle,
          qwtext: document.getElementById("QWFormworkTextAi").innerHTML,
          innerdata: this.innerdata,
        },
      });
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
    //素材库
    selectAll(page){
        var formFile = new window.FormData();
        formFile.append("page",page+"")
        formFile.append("pageSize","1000000")
        formFile.append("m_type",this.putId+"")
        axios.post("/material/list",formFile).then(res=>{
            this.suCaiList = []
            for(let info of res.data.list){
                info.url = info.content
                if(info.m_name.length>10){
                    info.nickname = info.m_name.substring(0,7) + "..."
                }else{
                    info.nickname = info.m_name
                }
                if(info.m_type==1){
                    info["imgurl"] = info.content
                }else if(info.m_type==2){
                    info["imgurl"] = require("../assets/video.jpg")
                }else{
                    info["imgurl"] = require("../assets/audio.jpg")
                }
                this.suCaiList.push(info)
            }
            // this.totalPage = res.data.total
        }).catch(e=>{
            console.log(e)
        })
    },
    showSuCai(){
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
    appendEle(data){
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
      formFile.append("m_name", fileObj.name.substring(fileObj.name.lastIndexOf("/")+1)); //加入文件对象
      axios
        .post("/material/save", formFile, {
          //设置请求头
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          var data = res.data;
          if (data.ok) {
            data = data.t[0]
            if(this.putId == 1){
              var obj ={name:data.m_name,url:data.content,id:nanoid(),imgurl:data.content}
            }else if(this.putId == 2){
              var obj ={name:data.m_name,url:data.content,id:nanoid(),imgurl:require("../assets/video.jpg")}
            }else{
              var obj ={name:data.m_name,url:data.content,id:nanoid(),imgurl:require("../assets/audio.jpg")}
            }
            if(obj.name.length>10){
                obj.nickname = obj.name.substring(0,7) + "..."
            }else{
                obj.nickname = obj.name
            }
            this.currentPutFile.push(obj)
            document.getElementById("imgUp").value=""
            document.getElementById("videoUp").value=""
            document.getElementById("soundUp").value=""
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
        this.putId=id
        this.isPut = true
    },
    cancelFilePut(){
        this.isPut = false
        this.putOrSearch = 0
        this.currentPutFile = []
        this.indexList = []
        this.suCaiList = []
        this.indexSucai = []
    },
    addSuCai(){
        for(let item of this.indexList){
            this.appendEle(this.currentPutFile[item])
        }
        for(let item of this.indexSucai){
            this.appendEle(this.suCaiList[item])
        }
        this.cancelFilePut()
    }
  },
  activated() {
    if(this.modal=='突发事件' && this.qwtext){
      console.log(this.qwtext)
      this.qwtitle = this.qwtext.title;
      document.getElementById("QWFormworkTextAi").innerHTML = this.qwtext.news_content
      this.innerdata = this.qwtext.news_content
    }
    if (this.qwtext) {
      if (this.qwtext.status == 0) {
        let num = this.qwtext.content.num
        for(let i = 0;i<num;i++){
          let msg = this.qwtext.content.list[i].content;
          this.innerdata = msg;
          var textinfo = "";
          for (let item of msg) {
            for (let k in item) {
              if (k == "text") {
                textinfo += "<p>" + item[k] + "</p>";
              } else if (k == "image") {
                //  textinfo += `<p style="text-align:center;">&nbsp;<img src=${item[k]} style="width:60%;" alt="">&nbsp;</p>`
              }
            }
          }
        }
        document.getElementById("QWFormworkTextAi").innerHTML = textinfo
        this.qwtitle = this.qwtext.content.list[0].title;
      }
    }
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
  margin: 20px 0px 100px;
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
.qwrite-info {
  height: 84px;
  line-height: 84px;
  font-size: 20px;
  margin-top: 50px;
  position: relative;
}
.qwrite-info-s2 {
  position: absolute;
  left: 90px;
}
.qwrite-info-s3 {
  position: absolute;
  left: 500px;
}
.qwrite-info-s4 {
  position: absolute;
  left: 600px;
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
</style>