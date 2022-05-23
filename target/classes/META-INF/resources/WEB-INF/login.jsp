<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="referrer" content="no-referrer">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI智能写稿</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/login.css">
    <script src="/js/JQuery3.6.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
    <div id="#root">
        <div id="head-top" class="container-fluid">
            <div class="row">
                <div id="head-body" class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">
                    <div id="head-body-top">
                        <img id="head-logo" src="/img/顶部菜单/logo.png" alt="">
                        <span id="login">
                            <img id="head-figure" src="/img/顶部菜单/默认头像.svg" alt="">
                            <b>登陆/注册</b>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="Content">
            <div class="login-inner">
                <div class="login-inner-l"></div>
                <span class="login-inner-c">登陆</span>
                <div class="login-inner-r"></div>
                <form id="loginForm" method="post">
                    <div class="loginForm-inner clearfix">
                        <div class="login-input clearfix">
                            <label for="username">用户名</label>
                            <input class="login-input-i" type="text" name="username" id="username" placeholder="用户名"> 
                        </div>
                        <div class="login-input clearfix">
                            <label for="password">密码</label>
                            <input class="login-input-i" type="password" name="password" id="password" placeholder="密码">
                        </div>
                        <div class="login-input clearfix">
                            <input class="b2" type="button" value="注册">
                            <input class="b1" type="submit" value="登陆">
                        </div>
                        <div id="errorMsg"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/js/login.js"></script>
</body>
</html>