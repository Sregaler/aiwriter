<template>
  <div>
    <div id="head-top" class="container-fluid">
      <div class="row">
        <div
          id="head-body"
          class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1"
        >
          <div id="head-body-top">
            <img id="head-logo" src="./assets/index/title.jpg" alt="" @click="toIndex"/>
            <span id="login" v-show="true">
              <img
                id="head-figure"
                src="./assets/index/头像.png"
                alt=""
              />
              <span class="login-s1" v-show="haveLogin">{{ username }}</span>
              <span class="login-s2" v-show="haveLogin">▼</span>
              <span class="login-s3" v-show="!haveLogin" @click="openlogin"
                >登录/注册</span
              >
            </span>
            <!-- <div class="quiteLogin" :class="{'notShow':quitebtn}">
                            退出登录
                        </div> -->
          </div>
        </div>
      </div>
    </div>

    <div v-show="haveEnter">
      <div id="head-buttom clearfix" class="container-fluid">
        <div class="row clearfix">
          <div
            id="head-buttom-img"
            class="col-md-1 col-md-offset-1 col-xs-1 col-xs-offset-1"
          >
            <!-- <img id="head-logo1" src="./assets/顶部菜单/插图.svg" alt="" /> -->
          </div>
          <div id="head-buttom-nav" class="col-md-10 col-xs-10">
            <ul v-cloak id="head-nav" class="">
              <a></a>
              <li
                v-for="item in navItems"
                :key="item.id"
                class="head-li"
                :class="{ 'head-li-active': headerNavActive == item }"
                @click="headerNavTag(item)"
              >
                <b>{{ item }}</b>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="Cover" v-cloak v-show="coverControl"></div>
    <div class="login-register" v-show="coverControl">
      <div class="login-inner">
        <span class="login-inner-c">登陆注册AI新闻写稿</span>
        <i
          class="el-icon-close close-modal"
          @click="coverControl = !coverControl"
        ></i>
        <div class="login-inner-r"></div>
        <form id="loginForm" method="post">
          <div class="loginForm-inner clearfix">
            <div class="login-input clearfix">
              <input
                class="login-input-i"
                type="text"
                id="username"
                placeholder="用户名"
                v-model="username"
              />
            </div>
            <div class="login-input clearfix">
              <input
                class="login-input-i"
                type="password"
                id="password"
                placeholder="密码"
                v-model="password"
              />
            </div>
            <div class="register-input clearfix">
              <div id="v_container">
                <img
                  id="imgcode"
                  src="/code/img"
                  alt=""
                  @click.self="getCode"
                />
              </div>
              <input
                type="text"
                name="code"
                id="code"
                placeholder="请输入验证码"
                v-model="code"
              />
            </div>
            <div class="login-input clearfix" style="margin-top: 60px">
              <input
                class="b3"
                type="submit"
                @click.prevent="putLogin"
                value="登陆"
              />
              <input
                class="b2"
                type="button"
                value="注册"
                @click.prevent="putRegister"
              />
            </div>
            <div id="errorMsg"></div>
          </div>
        </form>
      </div>
    </div>
    <div id="context" class="container-fluid">
      <keep-alive include="AiWrite,QuickWriter">
        <router-view></router-view>
      </keep-alive>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "App",
  data() {
    return {
      username: "",
      password: "",
      code: "",
      // navItems:["模板成稿","智能图文成稿","稿件转视频","手动写稿","素材库","稿件管理"],
      navItems: [
        "模板成稿",
        "智能图文成稿",
        "稿件转视频",
        "工具箱",
        "素材库",
        "稿件管理",
      ],
      headerNavActive: "模板成稿",
      haveLogin: false,
      coverControl: false,
      haveEnter: false,
    };
  },
  methods: {
    headerNavTag(name) {
      this.headerNavActive = name;
      switch (name) {
        case "模板成稿":
          this.$router.replace({
            name: "moban",
          });
          break;
        case "智能图文成稿":
          this.$router.replace({
            name: "aiwrite",
          });
          break;
        case "稿件转视频":
          this.$router.replace({
            name: "testtovideo",
          });
          break;
        case "工具箱":
          this.$router.replace({
            name: "tool",
          });
          break;
        case "素材库":
          this.$router.replace({
            name: "sucaiku",
          });
          break;
        case "稿件管理":
          this.$router.replace({
            name: "manager",
          });
      }
    },
    openlogin() {
      this.coverControl = true;
    },
    putLogin(event) {
      event.preventDefault();
      var formFile = new window.FormData();
      formFile.append("username", this.username);
      formFile.append("password", this.password);
      formFile.append("code", this.code);
      axios.post("/user/login", formFile).then((res) => {
        if (res.data.ok) {
          //注册成功，跳转成功页面
          // console.log(res.data)
          this.haveLogin = true;
          this.coverControl = false;
          this.$bus.$emit("islogin", true);
          this.$alert("<strong>登录成功</strong>", "登录信息", {
            dangerouslyUseHTMLString: true,
          });
        } else {
          //注册失败,给errorMsg添加提示信息
          document.getElementById("errorMsg").innerText = res.data.mess;
        }
      });
    },
    putRegister(event) {
      event.preventDefault();
      var formFile = new window.FormData();
      formFile.append("username", this.username);
      formFile.append("password", this.password);
      formFile.append("code", this.code);
      axios
        .post("/user/register", formFile)
        .then((res) => {
          if (res.data.ok) {
            //注册成功，跳转成功页面
            // this.$message.success("注册成功")
            this.coverControl = false;
            this.$alert("<strong>注册成功</strong>", "注册信息", {
              dangerouslyUseHTMLString: true,
            });
          } else {
            //注册失败,给errorMsg添加提示信息
            document.getElementById("errorMsg").innerText = res.data.mess;
            this.username = "";
            this.password = "";
            this.code = "";
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getCode(event) {
      event.target.src = "/code/img?" + new Date().getTime();
    },
    toIndex(){

      if(this.$route.name!="aiindex"){
        this.$router.replace({
                name: "aiindex",
                params: {
                logining: this.haveLogin,
            },
          });
        this.haveEnter = false
      }
    }
  },
  mounted() {
    this.$bus.$on("navTag", (data) => {
      this.headerNavActive = data;
    });
    this.$bus.$on("goLogin", (data) => {
      this.coverControl = data;
    });
    this.$bus.$on("enter", (data) => {
      this.haveEnter = data;
      this.haveLogin = data;
    });
  },
  beforeDestroy() {
    this.$bus.$off("navTag");
    this.$bus.$off("goLogin");
    this.$bus.$off("enter");
    this.haveEnter = false;
  },
};
</script>

<style
>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.pt {
  padding-top: 80px;
}
audio {
  outline: none;
}
[v-cloak] {
  display: none;
}
body {
  margin: 0px;
  padding: 0px;
  min-width: 1600px;
  overflow-x: auto;
}
::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 10px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 10px;
}
::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: rgb(214, 214, 214);
}
::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: white;
}
.el-header {
  background-color: #056aee;
  color: #333;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
}
/* 头部 */
.header-all {
  width: 80%;
  background-color: red;
  height: 200px;
  margin: 0 auto;
}
#head-top {
  background-color: #111313;
}
#head-body {
  position: relative;
  height: 60px;
}
#head-logo {
  margin-top: 10px;
  width: 210px;
  height: 36px;
}
#head-logo:hover{
  cursor: pointer;
}
#login {
  float: right;
  margin-top: 15px;
  font-size: 10px;
  color: white;
}
#login img {
  position: absolute;
  right: 100px;
}
.login-s1 {
  font-size: 12px;
  position: absolute;
  right: 60px;
  top: 22px;
}
.login-s2 {
  font-size: 12px;
  position: absolute;
  right: 35px;
  top: 22px;
}
.login-s3 {
  font-size: 12px;
  position: absolute;
  right: 40px;
  top: 22px;
}
.login-s2:hover,
.login-s3:hover {
  cursor: pointer;
}
#head-figure {
  width: 30px;
  height: 30px;
}
.quiteLogin {
  height: 20px;
  font-size: 18;
  background-color: rgb(233, 224, 224);
  position: absolute;
  right: 15px;
  top: 40px;
  line-height: 20px;
}
.quiteLogin:hover {
  cursor: pointer;
}
#login i:hover {
  cursor: pointer;
}

/* 头部-底部 */
#head-buttom-img,
#head-buttom-nav {
  padding: 0px;
  position: relative;
  height: 140px;
}

#head-logo1 {
  position: absolute;
  bottom: 5px;
  left: 10px;
  width: 96px;
  height: 96px;
}
#head-nav {
  position: absolute;
  margin-bottom: 0px;
  left: 0px;
  bottom: 0px;
  font-size: 20px;
}
#head-nav li {
  display: block;
  width: 140px;
  height: 40px;
  line-height: 40px;
  float: left;
  padding: 0px 5px;
  text-align: center;
  color: rgb(134, 134, 134);
}
#head-nav li:hover {
  background-color: rgb(240, 240, 240);
  cursor: pointer;
}
.head-li-active {
  border-bottom: 3px solid #015478;
  color: #2c2c2c !important;
}
.li-active {
  border-bottom: 1px solid #015478;
  color: #2c2c2c !important;
}
#context {
  background-color: rgb(249, 249, 249);
  min-height: 1200px;
  padding: 0px;
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
/* 登陆月注册 */
.login-register {
  position: fixed;
  width: 400px;
  height: 420px;
  top: 50%;
  left: 50%;
  margin-left: -200px;
  margin-top: -250px;
  background-color: white;
  z-index: 1000;
  border-radius: 5px;
}
.login-inner {
  margin: auto;
  width: 100%;
  position: relative;
  padding-top: 80px;
}
.login-inner-r {
  border-bottom: 1px solid rgb(191, 191, 191);
  width: 100%;
  position: absolute;
  top: 60px;
  right: 0px;
}
.login-inner-c {
  width: 300px;
  font-size: 28px;
  font-weight: bold;
  position: absolute;
  text-align: center;
  top: 10px;
  left: 50%;
  margin-left: -150px;
}
.login-input {
  text-align: center;
  line-height: 40px;
  margin-top: 30px;
}
#checkcode {
  width: 100px;
}
.login-input .login-input-i {
  width: 260px;
  height: 40px;
  outline: none;
  border-radius: 1px;
  text-indent: 10px;
}
#errorMsg {
  text-align: center;
  color: red;
  margin-top: 20px;
}
.b3,
.b2 {
  width: 80px;
  height: 38px;
  outline: none;
  border-radius: 5px;
  border: none;
  line-height: 30px;
}
.b3 {
  background-color: #015478;
  color: white;
}
.b2 {
  margin-left: 20px;
}
/* 验证码 */
.register-input {
  text-align: center;
  line-height: 40px;
  margin-top: 30px;
  position: relative;
}
#v_container {
  position: absolute;
  width: 120px;
  height: 40px;
  margin-left: 20px;
  right: 70px;
}
#v_container img {
  width: 100%;
  height: 40px;
  border: 1px solid rgb(240, 240, 240);
}
#v_container:hover {
  cursor: pointer;
}
#code {
  width: 120px;
  text-indent: 10px;
  width: 120px;
  height: 40px;
  outline: none;
  border-radius: 1px;
  position: absolute;
  left: 70px;
}
.close-modal {
  position: absolute;
  right: 15px;
  top: 10px;
}
.close-modal:hover {
  cursor: pointer;
}
</style>
