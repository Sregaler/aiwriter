package com.hdumil.aiwriter.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Description : 负责页面的统一跳转
 */
@CrossOrigin
@Controller
public class ViewController {

    //转跳到登录
//    @RequestMapping(value = "/")
//    public String login1(){
//        return "login";
//    }
    @RequestMapping(value = "/login")
    public void login2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request,response);  //转发
    }
    //转跳到首页
    @RequestMapping(value = "/")
    public void index1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request,response);  //转发
    }
    @RequestMapping(value = "/aiIndex")
    public String index2(){
        return "ai";
    }
    //转跳到注册页面
    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }

    //三级通用跳转功能
    //localhost:8080/blog/add?name=..&title=...
    //localhost:8080/blog/add/zhangsan/旅游  workbench/article/index
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(
            @PathVariable("firstView") String firstView,
            @PathVariable("secondView") String secondView ,
            @PathVariable("thirdView") String thirdView, HttpServletRequest request){
        System.out.printf(firstView);
        //获取前台所有参数名字
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getParameter(name);
            //设置到request域中
            request.setAttribute(name,value);
        }
        //File.separator:\
        return firstView + File.separator + secondView + File.separator + thirdView;
    }


    @RequestMapping(value = "/test")
    public String test3(){
        return "test";
    }
}
