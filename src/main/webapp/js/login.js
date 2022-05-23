$(".b2").click(function(event) {
    event.preventDefault();
    location.href = "/register"
})

$(function () {
    // 当表单提交时，调用所有的校验方法
    $("#loginForm").submit(function(){
        //1.发送数据到服务器
        $.ajax({
            type: "post",
            url: "/user/login",
            data: {"username":$("#username").val(),"password":$("#password").val()},
            success: function(msg) {
                if(msg.ok){
                    location.href="/test";
                }else{
                    //注册失败,给errorMsg添加提示信息
                    $("#errorMsg").text(msg.mess);
                }
            }})
        //2.不让页面跳转
        return false;
        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
    });
});