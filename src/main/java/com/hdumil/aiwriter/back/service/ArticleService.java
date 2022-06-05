package com.hdumil.aiwriter.back.service;

import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.Tag;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface ArticleService {
    List<Article> list(String uid,String title);

    void isOpen(Article article);


    Article save(Article article);

    Article update(Article article);

    Article queryById(String id);

    List<String>[] deleteById(String id);

    void deleteByHtml(String htmlText, String realPath);

    List<String>[] compareMaterial(String preContent, String nowContent);
}
