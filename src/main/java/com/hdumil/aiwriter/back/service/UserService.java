package com.hdumil.aiwriter.back.service;

import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.User;

public interface UserService {
    User login(User user);

    void verifyOldPwd(String oldPwd,User user);

    void updateUser(User user);

    void registerUser(User user);

    User queryById(String id);

    User queryByUserName(String id);
}
