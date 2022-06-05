package com.hdumil.aiwriter.back.controller;

import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.Tag;
import com.hdumil.aiwriter.back.bean.User;
import com.hdumil.aiwriter.back.service.UserService;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.exception.AiwriterEnum;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.hdumil.aiwriter.base.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/19
 * Description :
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过自定义异常来定义系统中出现异常的情况
     * 使用枚举实现用户操作失败的定义情况
     * 使用ResultVo给客户端返回具体操作的结果信息
     * @param user
     * @param code
     * @param session
     */
    //注册功能
    @RequestMapping("/register")
    @ResponseBody
    public ResultVo register(User user, String code, HttpSession session){
        ResultVo resultVo = new ResultVo();
        //从session获取正确的验证码
        String rightCode = (String) session.getAttribute("code");
        try{
            if(!VerifyUtil.verifyCode(rightCode, code)) throw new AiwriterException(AiwriterEnum.USER_LOGIN_CODE);
            userService.registerUser(user);
            resultVo.setOk(true);
            //把登录用户存放到session中
            session.setAttribute("user",user);
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //登录功能
    @RequestMapping("/login")
    @ResponseBody
    public ResultVo login(User user, String code, HttpSession session){
        ResultVo resultVo = new ResultVo();
        //从session获取正确的验证码
        String rightCode = (String) session.getAttribute("code");
        try{
//            if(!VerifyUtil.verifyCode(rightCode, code)) throw new AiwriterException(AiwriterEnum.USER_LOGIN_CODE);
            user = userService.login(user);
            resultVo.setOk(true);
            //把登录用户存放到session中
            session.setAttribute("user",user);
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //登出功能
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        //清除session中的用户
        session.removeAttribute("user");
        //重定向到登录页面
        return "/login.jsp";
    }

    //异步校验用户输入的原始密码是否正确
    @RequestMapping("/verifyOldPwd")
    @ResponseBody
    public ResultVo verifyOldPwd(String oldPwd, HttpSession session){
        ResultVo resultVo = new ResultVo();
        try{
            //获取当前登录用户
            User user = (User) session.getAttribute("user");
            userService.verifyOldPwd(oldPwd,user);
            resultVo.setOk(true);
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //异步修改用户信息
    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultVo updateUser(User user){
        ResultVo resultVo = new ResultVo();
        try{
            userService.updateUser(user);
            resultVo.setOk(true);
            resultVo.setMess("修改用户信息成功");
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //id异步查询用户信息
    @RequestMapping("/queryById")
    @ResponseBody
    public User queryById(String id){
        User user = userService.queryById(id);
        return user;
    }

    //UserName异步查询用户信息
    @RequestMapping("/queryByUserName")
    @ResponseBody
    public User queryByUserName(String userName){
        User user = userService.queryByUserName(userName);
        return user;
    }

    //查询session中的用户信息
    @RequestMapping("/queryBySession")
    @ResponseBody
    public User queryBySession(HttpSession session){
        return (User) session.getAttribute("user");
    }
}
