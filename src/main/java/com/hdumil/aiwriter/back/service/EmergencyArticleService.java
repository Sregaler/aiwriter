package com.hdumil.aiwriter.back.service;

import com.hdumil.aiwriter.back.bean.EmergencyArticle;

import java.util.List;

public interface EmergencyArticleService {
    List<EmergencyArticle> list(String uid, String title);

    void isOpen(EmergencyArticle emergencyArticle);

    EmergencyArticle saveOrUpdate(EmergencyArticle emergencyArticle);

    EmergencyArticle queryById(String id);

    void deleteById(String id);
}
