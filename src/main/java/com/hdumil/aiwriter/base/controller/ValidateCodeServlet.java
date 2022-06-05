package com.hdumil.aiwriter.base.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/1/9
 * Description :
 */
@CrossOrigin
@RestController
@RequestMapping("/code")
public class ValidateCodeServlet {

    @GetMapping("/str")
    public String stringCode(HttpSession session){
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(140, 35, 4, 4);

        //把正确的验证码保存在session中
        String code = captcha.getCode();
        session.setAttribute("code",code);
        //返回验证码字符串
        return code;
    }

    @GetMapping("/img")
    public void imgCode(HttpSession session, HttpServletResponse response){
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(140, 35, 4, 4);
        //把正确的验证码保存在session中
        String code = captcha.getCode();
        session.setAttribute("code",code);
        response.setContentType("image/jpg");
        try {
            // 输出到页面
            captcha.write(response.getOutputStream());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
