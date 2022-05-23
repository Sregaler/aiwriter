// 生成验证码
var verifyCode = new GVerify("v_container");
//校验用户名
//单词字符，长度8到20位
function checkUsername() {
    //1.获取用户名值
    var username = $("#username").val();
    //2.定义正则
    var reg_username = /^\w{3,16}$/;
    
    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if(flag){
        //用户名合法
        $("#username").css("border","");
    }else{
        //用户名非法,加一个红色边框
        $("#username").css("border","1px solid red");
    }
    return flag;
}

//校验密码
function checkPassword() {
    //1.获取密码值
    var password = $("#password").val();
    //2.定义正则
    var reg_password = /^\w{6,20}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    if(flag){
        //密码合法
        $("#password").css("border","");
    }else{
        //密码非法,加一个红色边框
        $("#password").css("border","1px solid red");
    }

    return flag;
}

// 重置验证码
function changeCheckCode(img) {
    img.src="checkCode?"+new Date().getTime();
}

$(function () {
    //当表单提交时，调用所有的校验方法
    $("#registerForm").submit(function(){
        //1.发送数据到服务器
        if(checkUsername() && checkPassword()){
            //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123
            var msg = {"username":$("#username").val(),"password":$("#password").val(),"code":$("#code").val()};
            $.ajax({
            type: "post",
            url: "/user/register",
            data: msg,//将对象转为json字符串
            success: function(msg) {
                if(msg.ok){
                    //注册成功，跳转成功页面
                    console.log("success")
                }else{
                    //注册失败,给errorMsg添加提示信息
                    $("#errorMsg").text(msg.mess);
                }
            }})
        }
        //2.不让页面跳转
        return false;
        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
    });

    //当某一个组件失去焦点是，调用对应的校验方法
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
});