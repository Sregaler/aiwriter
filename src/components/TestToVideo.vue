<template>
  <div class="row pt" v-cloak>
    <div class="context-left col-md-2 col-xs-2"></div>
    <div class="context-center col-md-7 col-xs-7">
      <div class="context-center-top clearfix">
        <div class="b1">
          <b>视频输出设置</b>
        </div>
        <el-button type="primary" @click="generateVideo"><b>生成视频</b></el-button>
      </div>
      <div class="video-select">
        <div class="video-select-top">
          <el-tabs v-model="activeName" type="card">
            <el-tab-pane label="网页转视频" name="first">
              <div class="video-select-xq">
                <span class="video-select-xq-s1">请输入网页链接</span>
                <div class="linkSearch">
                  <input type="text" placeholder="只支持人民号、百家号" v-model="pageUrl" />
                  <!-- <el-button type="primary" icon="el-icon-search"></el-button> -->
                </div>
              </div>
            </el-tab-pane>
            <!-- <el-tab-pane label="Word转视频" name="second">
              <div class="video-select-xq">
                <input type="file" name="" id="docUp" style="display: none" @change="fileUpOver" accept=".doc,.docx" />
                <div class="video-select-xq-d">
                  <img
                    class="video-select-xq-img"
                    src="../assets/icon/上传.svg"
                    alt=""
                    @click="putDoc"
                  />
                </div>
                <span class="video-select-xq-s2">{{fileupname}}</span>
                <span class="video-select-xq-s3">点击上传Word文件</span>
                <span class="video-select-xq-s4"
                  >只支持上传一个Word文件，支持doc、docx格式，大小不可超过15MB</span
                >
              </div>
            </el-tab-pane> -->
            <el-tab-pane label="图文稿件转视频" name="third">
              <div class="video-select-xq">
                <div class="video-select-tw">
                  <el-radio-group v-model="writerModal">
                    <el-radio-button label="当前文稿"></el-radio-button>
                  </el-radio-group>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 选择 -->
        <div class="video-type">
          <div class="video-type-inner">
            <span class="video-type-inner-s1">视频时长</span>
            <div>
              <el-radio-group v-model="videoInfo.timetype">
                <el-radio :label="3">系统默认</el-radio>
                <el-radio :label="6">自定义</el-radio>
              </el-radio-group>
              <input
                type="text"
                :disabled="videoInfo.timetype != 6"
                name="videoTime"
                id="videoTime"
                v-model="videoInfo.time"
              />
            </div>
            <span class="video-type-inner-s2">视频比例</span>
            <div>
              <el-radio-group v-model="videoInfo.type">
                <el-radio :label="'640×360'">640×360</el-radio>
                <el-radio :label="'1280×720'">1280×720</el-radio>
                <el-radio :label="'1920×1080'">1920×1080</el-radio>
              </el-radio-group>
            </div>
            <span class="video-type-inner-s2">视频比例</span>
            <div>
              <el-radio-group v-model="videoInfo.sound">
                <el-radio :label="0">成熟女声</el-radio>
                <el-radio :label="1">温润男声</el-radio>
                <el-radio :label="3">磁性男声</el-radio>
                <el-radio :label="4">乖巧</el-radio>
              </el-radio-group>
            </div>
          </div>
        </div>
      </div>
      <!-- 通用遮罩 -->
      <div class="Cover" v-cloak v-show="videoShowBtn"></div>
      <!-- 视频展示 -->
      <div class="vide-show" v-show="videoShowBtn">
        <div class="videoTitle">{{videoTitle}}</div>
        <span class="videoQuit" @click="closeVideo">关闭</span>
        <div class="video-inner" v-loading="videoLoadding" element-loading-text="正在生成视频">
          <video :src="videosrc" controls v-show="!videoLoadding"></video>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TestToVideo",
  data() {
    return {
      activeName: "first",
      videoInfo: { time: 60, type: "640×360", timetype: 3, sound: 0 },
      videoShowBtn:false,
      pageUrl:"",
      docUrl:"",
      writerUrl:"",
      writerModal:"当前文稿",
      videoLoadding:true,
      videosrc:"",
      videoTitle:"视频生成",
      fileupname:""
    };
  },
  methods: {
    handleClick() {},
    putDoc(){
      document.getElementById("docUp").click();
    },
    closeVideo(){
      this.videoShowBtn = !this.videoShowBtn
      this.videosrc = ""
      this.videoLoadding = true
    },
    //生成视频
    generateVideo(){
      if(this.activeName=="first"){
          if(this.pageUrl.length<=0){
            this.$message.info("输入的网址不能为空")
            return
          }
          this.videosrc=""
          this.videoLoadding = true
          this.videoShowBtn = true
          axios.post("/baidu/retrieval/create_vidpress",{newsUrl:this.pageUrl,duration:this.videoInfo.time,resolution:this.videoInfo.type,ttsPer:this.videoInfo.sound}).then(res=>{
            if(res.data.ok){
              // console.log(res.data)
              var data = JSON.parse(res.data.t)
              console.log(data)
              if(data.msg=="success"){
                this.videosrc = data.data.videoAddr
                this.videoLoadding = false
                // this.videoTitle = 
              }else{
                this.$message.error("视频生成失败")
                this.videoShowBtn = false
              }
            }else{
              console.log(res.data)
              this.$message.error("视频生成失败")
              this.videoShowBtn = false
            }
          }).catch(e=>{
            this.$message.error("视频生成失败")
            this.videoShowBtn = false
            console.log(e)
          })
      }else if(this.activeName=="second"){
        if(this.docUrl.length<=0){
            this.$message.info("请先提交doc或者docx文件")
            return
          }
          this.videoShowBtn = true
          axios.post("/baidu/retrieval/create_vidpress_alignmen_doc",{docUrl:this.docUrl,duration:this.videoInfo.time,resolution:this.videoInfo.type,ttsPer:this.videoInfo.sound}).then(res=>{
            if(res.data.ok){
              // console.log(res.data)
              var data = JSON.parse(res.data.t)
              console.log(data)
              if(data.msg=="success"){
                this.videosrc = data.data.videoAddr
                this.videoLoadding = false
              }else{
                this.$message.error("视频生成失败")
                this.videoShowBtn = false
              }
            }else{
              console.log(res.data)
              this.$message.error("视频生成失败")
              this.videoShowBtn = false
            }
          }).catch(e=>{
            this.$message.error("视频生成失败")
            this.videoShowBtn = false
            console.log(e)
          })
      }else{
        if(this.writerUrl.length<=0){
            this.$message.info("请先生成图文稿件")
            return
          }
          this.videoShowBtn = true
          var formFile = new window.FormData()
          formFile.append("reqHtml",this.writerUrl)
          formFile.append("duration",this.videoInfo.time)
          formFile.append("resolution",this.videoInfo.type)
          formFile.append("ttsPer",this.videoInfo.sound)
          // axios.post("/baidu/retrieval/create_vidpress_alignmen_html",{reqHtml:this.writerUrl,duration:this.videoInfo.time,resolution:this.videoInfo.type,ttsPer:this.videoInfo.sound}).then(res=>{
          axios.post("/baidu/retrieval/create_vidpress_alignmen_html",formFile).then(res=>{
            if(res.data.ok){
              console.log(res.data)
              var data = JSON.parse(res.data.t)
              console.log(data)
              if(data.msg=="success"){
                this.videosrc = data.data.videoAddr
                this.videoLoadding = false
                // this.videoTitle = 
              }else{
                this.$message.error("视频生成失败")
                this.videoShowBtn = false
              }
            }else{
              console.log(res.data)
              this.$message.error("视频生成失败")
              this.videoShowBtn = false
            }
          }).catch(e=>{
            this.$message.error("视频生成失败")
            this.videoShowBtn = false
            console.log(e)
          })
      }
    },
    fileUpOver(event){
        var fileObj = event.target.files[0]; // js 获取文件对象
        if (typeof (fileObj) == "undefined") {
            return;
        }
        if(fileObj.size>(1024*1024*15) && (filename==".jpg" || filename==".png")){
            this.$message.warning("文档上传不能超过15M")
            return
        }
        var formFile = new window.FormData();
        formFile.append("files", fileObj); //加入文件对象
        axios.post("/filesUpload2CreateVidpressDoc",formFile,{headers: { "Content-Type": "multipart/form-data" },}).then(res =>{
          var data = res.data[0];
          if(data.success==1){
            this.docUrl = data.url
            console.log(this.docUrl)
            this.$alert('<strong>文件上传成功</strong>', '消息提示', {dangerouslyUseHTMLString: true})
            this.fileupname = fileObj.name
          }else{
            this.$message.error("文件上传失败")
          }
        }).catch(e=>{
          this.$message.error("文件上传失败")
          console.error(e)
        })
    },
  },
  mounted() {
    if(this.$route.params.content){
      this.writerUrl=this.$route.params.content
      this.activeName="third"
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
.video-select {
  width: 100%;
  height: 630px;
  background-color: white;
  margin-top: 50px;
  position: relative;
  padding: 0 10%;
}
.video-select-top {
  position: absolute;
  width: 80%;
  top: 50px;
  background-color: rgb(249, 249, 249);
}
.video-select-xq {
  width: 100%;
  height: 220px;
}
.video-select-xq-s1 {
  display: block;
  text-align: center;
  font-size: 22px;
  padding: 30px 0px;
}
.linkSearch {
  height: 36px;
  width: 60%;
  margin: 0 auto;
}
.linkSearch input {
  float: left;
  height: 44px;
  min-width: 100px;
  width: 100%;
  outline-style: none;
  border: solid 2px #dbdbdb;
  /* border-right: none; */
  text-indent: 10px;
}
.linkSearch button {
  float: left;
  width: 20%;
  height: 44px;
  border: none;
  color: white;
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
}
.video-select-xq-s2 {
  height: 18px;
  display: block;
  text-align: center;
  font-size: 16px;
  margin: 20px 0px 0px;
}
.video-select-xq-s3 {
  display: block;
  text-align: center;
  font-size: 16px;
  margin: 20px 0px 20px;
  font-weight: bolder;
}
.video-select-xq-s4 {
  display: block;
  text-align: center;
  font-size: 16px;
  margin: 20px 0px 0px;
  color: rgb(88, 88, 88);
}
.video-select-xq-d {
  text-align: center;
  padding-top: 40px;
}
.video-select-xq-img {
  width: 50px;
}
.video-select-xq-img:hover {
  cursor: pointer;
}
.video-type {
  padding-top: 350px;
}
.video-type-inner {
  position: relative;
}
#videoTime {
  width: 200px;
  height: 30px;
  margin-left: 20px;
}
.video-type-inner-s1,
.video-type-inner-s2 {
  display: block;
  font-size: 18px;
  margin: 20px 0px 20px;
  color: rgb(88, 88, 88);
}
/* 视频展示 */
.Cover {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: darkgrey;
    z-index: 500;
    opacity: 0.5;
}
.vide-show{
  width: 1100px;
  height: 600px;
  z-index: 1000;
  background-color: white;
  position: fixed;
  left: 50%;
  top: 50%;
  margin-left: -550px;
  margin-top: -300px;
  border-radius: 10px;
  padding: 30px;
}
.videoTitle{
    font-size:30px;
    font-weight: bolder;
    padding-bottom: 20px;
    border-bottom: 1px solid rgb(235,235,235);
}
.videoQuit{
    display: inline-block;
    width: 60px;
    height: 30px;
    font-size: 12px;
    text-align: center;
    line-height: 30px;
    position:absolute;
    right:30px;
    top: 30px;
    background-color: #015478;
    color:white;
    border-radius: 3px;
}
.videoQuit:hover{
  cursor: pointer;
}
.video-inner{
  padding-top: 20px;
  width: 100%;
  height: 460px;
  text-align: center;
}
.video-inner video{
  height: 460px;
  display: inline-block;
}
.video-select-tw{
  padding-top: 80px;
  padding-left: 50px;
}
</style>