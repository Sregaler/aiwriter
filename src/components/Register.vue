<template>
  <div>
      <div class="Content">
            <div class="register-inner">
                <div class="register-inner-l"></div>
                <span class="register-inner-c">注册</span>
                <div class="register-inner-r"></div>
                <form id="registerForm" action="http://10.3.73.14:8080/aiwriter/user/register" method="post">
                    <div class="registerForm-inner clearfix">
                        <div class="register-input clearfix">
                            <label for="username">用户名</label>
                            <input type="text" name="username" id="username" placeholder="用户名(3-16位数字字母)" v-model="username"> 
                        </div>
                        <div class="register-input clearfix">
                            <label for="password">密码</label>
                            <input type="password" name="password" id="password" placeholder="密码(6-20位数字字母)" v-model="password">
                        </div>
                        <div class="register-input clearfix">
                            <label for="checkcode">验证码</label>
                            <div id="v_container">
                                <img src="/code/img" alt="" @click.self="getCode">
                            </div>
                            <input type="text" name="code" id="code" placeholder="请输入验证码" v-model="code">
                        </div>
                        <div class="clearfix">
                            <input type="submit" class="register-submit" value="注册" @click.self="putRegister" >
                        </div>
                        <div class="clearfix">
                            <a class="tologin" @click.prevent="toLogin">已有账号,直接登陆></a>
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
            codeImg:"",
            code:""
        }
    },
    methods:{
        putRegister(event){
            event.preventDefault();
            var formFile = new window.FormData();
            formFile.append("username",this.username)
            formFile.append("password",this.password)
            formFile.append("code",this.code)
            axios.post("/user/register",formFile).then(res=>{
                if(res.data.ok){
                    //注册成功，跳转成功页面
                    // this.$message.success("注册成功")
                    this.$alert('<strong>注册成功</strong>', '注册信息', {dangerouslyUseHTMLString: true})
                    setTimeout(()=>{
                        this.$router.push({path:"/"})
                    },2000)
                }else{
                    //注册失败,给errorMsg添加提示信息
                    document.getElementById("errorMsg").innerText=res.data.mess;
                }
            }).catch(e=>{
                console.log(e)
            })
        },
        getCode(event){
            event.target.src = "/code/img?"+new Date().getTime();
        },
        toLogin(){
            this.$router.push({
                path:"/"
            })
        }
    },
    mounted() {
    },
}
</script>

<style scoped>
.Content{
    width: 100%;
    height: 800px;
    padding-top: 50px;
    margin-top: 40px;
    background-color: rgb(249,249,249);
}
.register-inner{
    margin: auto;
    width: 1100px;
    height: 500px;
    position: relative;
    padding-top: 100px;
}
.registerForm-inner{
    width: 600px;
    margin:auto;
}



.register-inner-l{
    border: 1px solid rgb(191,191,191);
    width: 480px;
    position: absolute;
    top: 26px;
    left: 0;
}
.register-inner-r{
    border: 1px solid rgb(191,191,191);
    width: 480px;
    position: absolute;
    top: 26px;
    right: 0px;
}
.register-inner-c{
    width: 100px;
    font-size:42px;
    font-weight:bold;
    position:absolute;
    top:0px;
    left: 50%;
    margin-left: -50px;
}
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
a:hover{cursor: pointer;}
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
.register-submit{
    width: 220px;
    height: 44px;
    background-color: #015478;
    outline: none;
    border-radius: 5px;
    border:none;
    float:right;
    margin-right: 200px;
    margin-top: 40px;
    color: white;
}
.tologin{
    float: right;
    margin-top: 10px;
}
#errorMsg{
    text-align: center;
    color: red;
}
</style>