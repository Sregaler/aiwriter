<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI智能写稿</title>
    <link rel="stylesheet" href="/aiwriter/css/bootstrap.css">
    <link rel="stylesheet" href="/aiwriter/css/register.css">
    <script src="/aiwriter/js/JQuery3.6.js"></script>
    <script src="/aiwriter/js/JqGetCode.js"></script>
</head>
<body>
<div id="#root">
    <div id="head-top" class="container-fluid">
        <div class="row">
            <div id="head-body" class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">
                <div id="head-body-top">
                    <img id="head-logo" src="/aiwriter/img/顶部菜单/logo.png" alt="">
                    <span id="login">
                            <img id="head-figure" src="/aiwriter/img/顶部菜单/默认头像.svg" alt="">
                            <b>登陆/注册</b>
                        </span>
                </div>
            </div>
        </div>
    </div>
    <div class="Content">
        <div class="register-inner">
            <div class="register-inner-l"></div>
            <span class="register-inner-c">注册</span>
            <div class="register-inner-r"></div>
            <form id="registerForm" action="/aiwriter/user/register" method="post">
                <div class="registerForm-inner clearfix">
                    <div class="register-input clearfix">
                        <label for="username">用户名</label>
                        <input type="text" name="username" id="username" placeholder="用户名(3-16位数字字母)">
                    </div>
                    <div class="register-input clearfix">
                        <label for="password">密码</label>
                        <input type="password" name="password" id="password" placeholder="密码(6-20位数字字母)">
                    </div>
                    <div class="register-input clearfix">
                        <label for="checkcode">验证码</label>
                        <div id="v_container"></div>
                        <input type="text" name="code" id="code" placeholder="请输入验证码">
                    </div>
                    <div class="clearfix">
                        <input type="submit" class="register-submit" value="注册">
                    </div>
                    <div class="clearfix">
                        <a class="tologin" href="login.html">已有账号,直接登陆></a>
                    </div>
                    <div id="errorMsg"></div>
                </div>
            </form>
        </div>
    </div>

</div>
<script src="/aiwriter/js/register.js">
</script>
</body>
</html>