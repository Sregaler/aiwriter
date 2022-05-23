package com.hdumil.aiwriter.base.util;

import com.hdumil.aiwriter.back.bean.User;

import javax.servlet.http.HttpSession;

public class VerifyUtil {

    public static boolean verifyCode(String rightCode, String code){
        //校验验证码
        if(code==null)
            return false;
        return rightCode.equals(code);
    }

    public static boolean verifyUser(String uid, User user, HttpSession session){
        if(user==null&&session.getAttribute("user")==null)
            return false;
        String v_uid;
        if(user==null){
            User session_user = (User) session.getAttribute("user");
            v_uid = session_user.getUid();
        }
        else {
            v_uid = user.getUid();
        }
        return uid.equals(v_uid);
    }
}
