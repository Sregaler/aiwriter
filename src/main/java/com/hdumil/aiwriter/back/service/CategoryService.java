package com.hdumil.aiwriter.back.service;


import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.Tag;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> queryCategory();

    List<Tag> queryTags();

    List<Tag> queryTagsByCid(String cid);

    Map<String, Category> getCategorysMap();
}
