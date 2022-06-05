package com.hdumil.aiwriter.back.service.impl;

import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.User;
import com.hdumil.aiwriter.back.mapper.UserMapper;
import com.hdumil.aiwriter.back.service.UserService;
import com.hdumil.aiwriter.base.exception.AiwriterEnum;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import com.hdumil.aiwriter.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/19
 * Description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        //把用户输入的密码进行加密
        String password = user.getPassword();
        password = MD5Util.getMD5(password);
        user.setPassword(password);
        //校验用户名和密码是否正确
        List<User> users = userMapper.select(user);
        if(users.size() == 0){
            throw new AiwriterException(AiwriterEnum.USER_LOGIN_ACCOUNT);
        }
        return users.get(0);
    }

    @Override
    public void verifyOldPwd(String oldPwd, User user) {
        oldPwd = MD5Util.getMD5(oldPwd);
        String password = user.getPassword();
        if(!password.equals(oldPwd)){
            throw new AiwriterException(AiwriterEnum.USER_VERIFY_PASS);
        }
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        //给用户输入的新密码加密
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        //count:影响记录数
        int count = userMapper.updateByPrimaryKeySelective(user);
        if(count == 0){
            throw new AiwriterException(AiwriterEnum.USER_VERIFY_PASS);
        }
    }

    @Override
    public void registerUser(User user) {
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        user.setCreateTime(DateTimeUtil.getSysTime());  //注册时间
        user.setRole("1");  //角色
        user.setState("1");  //状态
        //count:影响记录数
        try {
            userMapper.insertSelective(user);
        }
        catch (Exception e){
            throw new AiwriterException(AiwriterEnum.USER_REGISTER);
        }
    }

    @Override
    public User queryById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User queryByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        List<User> users = userMapper.select(user);
        return users.get(0);
    }
}
