package com.hdumil.aiwriter.back.service;

import com.hdumil.aiwriter.back.bean.*;
import com.hdumil.aiwriter.back.bean.ArticleTemplate;
import com.hdumil.aiwriter.back.bean.Material;

import java.util.List;

public interface WriteService {

    String getAiWrite(List<Material> materials, ArticleTemplate article_template);

    String getTextImage(List<Material> materials, ArticleTemplate article_template);

    String getVideo(List<Material> materials, ArticleTemplate article_template);

    String getAudio(List<Material> materials, ArticleTemplate article_template);
}
