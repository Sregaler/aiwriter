<template>
  <div>
        <div class="Content">
            <div class="login-inner">
                <div class="login-inner-l"></div>
                <span class="login-inner-c">登陆</span>
                <div class="login-inner-r"></div>
                <form id="loginForm" method="post">
                    <div class="loginForm-inner clearfix">
                        <div class="login-input clearfix">
                            <label for="username">用户名</label>
                            <input class="login-input-i" type="text" id="username" placeholder="用户名" v-model="username"> 
                        </div>
                        <div class="login-input clearfix">
                            <label for="password">密码</label>
                            <input class="login-input-i" type="password" id="password" placeholder="密码" v-model="password">
                        </div>
                        <div class="register-input clearfix">
                            <label for="checkcode">验证码</label>
                            <div id="v_container">
                                <img src="/code/img" alt="" @click.self="getCode">
                            </div>
                            <input type="text" name="code" id="code" placeholder="请输入验证码" v-model="code">
                        </div>
                        <div class="login-input clearfix">
                            <input class="b2" type="button" value="注册" @click.prevent="toRegister">
                            <input class="b1" type="submit" @click.prevent="putLogin" value="登陆">
                            <!-- <button class="b2">注册</button>
                            <button class="b1">登陆</button> -->
                        </div>
                        <div id="errorMsg"></div>
                    </div>
                </form>
            </div>
        </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
    name: 'Login',
    data() {
        return {
            username:"",
            password:"",
            code:""
        }
    },
    methods:{
        putLogin(event){
            event.preventDefault();
            var formFile = new window.FormData();
            formFile.append("username",this.username)
            formFile.append("password",this.password)
            formFile.append("code",this.code)
            axios.post("/user/login",formFile).then(res=>{
                if(res.data.ok){
                    //注册成功，跳转成功页面
                    this.$bus.$emit("islogin",true)
                    this.$router.push({path:"/moban"})
                }else{
                    //注册失败,给errorMsg添加提示信息
                    document.getElementById("errorMsg").innerText=res.data.mess;
                }
            })
        },
        getCode(event){
            event.target.src = "/code/img?"+new Date().getTime();
        },
        toRegister(){
            this.$router.push({
                path:"/register"
            })
        }
    },
    mounted() {
    },
}
</script>

<style scoped>
/* 头部-顶部 */
#head-body{
    position: relative;
    height: 60px;
}
#head-logo{
    margin-top: 10px;
    width: 210px;
    height: 36px;
}
#login{
    float: right;
    margin-top: 10px;
    font-size: 10px;
}
#login b{
    font-size: 12px;
}
#login b:hover{
    cursor: pointer;
}
#head-figure{
    width: 30px;
    height: 30px;
}

/* 头部-底部 */
#head-buttom-img,#head-buttom-nav{
    padding: 0px;
    position: relative;
    height: 140px;
}

#head-logo1{
    position:absolute;
    bottom: 5px;
    left: 10px;
    width: 96px;
    height: 96px;
}

.Content{
    width: 100%;
    height: 800px;
    padding-top: 50px;
    margin-top: 40px;
    background-color: rgb(249,249,249);
}
.login-inner{
    margin: auto;
    width: 1100px;
    height: 500px;
    position: relative;
    padding-top: 100px;
}
.loginForm-inner{
    width: 600px;
    margin:auto;
}



.login-inner-l{
    border: 1px solid rgb(191,191,191);
    width: 480px;
    position: absolute;
    top: 26px;
    left: 0;
}
.login-inner-r{
    border: 1px solid rgb(191,191,191);
    width: 480px;
    position: absolute;
    top: 26px;
    right: 0px;
}
.login-inner-c{
    width: 100px;
    font-size:42px;
    font-weight:bold;
    position:absolute;
    top:0px;
    left: 50%;
    margin-left: -50px;
}
.login-input{
    text-align: center;
    line-height: 44px;
    margin-top: 40px;
}
#checkcode{
    width: 220px;
}
.login-input .login-input-i{
    float: right;
    width: 420px;
    height: 44px;
    outline: none;
    border-radius: 5px;
    text-indent: 10px;
}
.login-input label{
    font-size: 20px;
    font-weight:500;
    margin-bottom: 0px;
}
#errorMsg{
    text-align: center;
    color: red;
    margin-top: 20px;
}
.b1,.b2{
    float: right;
    width: 200px;
    height: 44px;
    outline: none;
    border-radius: 5px;
    border: none;
}
.b1{
    background-color: #015478;
    color:white;
}
.b2{
    margin-left: 20px;
}
/* 验证码 */
.register-input{
    text-align: center;
    line-height: 44px;
    margin-top: 40px;
}
#v_container{
    float: right;
    width: 180px;
    height: 44px;
    margin-left: 20px;
}
#v_container img{
    width: 100%;
    height: 44px;
}
#v_container:hover{
    cursor: pointer;
}
#code{
    width: 220px;
    text-indent: 10px;
}
.register-input input{
    float: right;
    width: 420px;
    height: 44px;
    outline: none;
    border-radius: 5px;
    text-indent: 10px;
}
.register-input label{
    font-size: 20px;
    font-weight:500;
    margin-bottom: 0px;
}
</style>