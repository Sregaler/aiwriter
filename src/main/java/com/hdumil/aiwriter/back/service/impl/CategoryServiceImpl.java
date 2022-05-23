package com.hdumil.aiwriter.back.service.impl;


import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.Tag;
import com.hdumil.aiwriter.back.mapper.CategoryMapper;
import com.hdumil.aiwriter.back.mapper.TagMapper;
import com.hdumil.aiwriter.back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    private Map<String, Category> categorys = null;

    private Map<String, Tag> tags = null;

    @Override
    public List<Category> queryCategory() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Tag> queryTagsByCid(String cid) {
        Tag tag = new Tag();
        tag.setCid(cid);
        return tagMapper.select(tag);
    }

    @Override
    public List<Tag> queryTags() {
        return tagMapper.selectAll();
    }

    @Override
    public Map<String, Category>  getCategorysMap() {
        if(categorys==null){
            categorys = new HashMap<>();
            List<Category> category = categoryMapper.selectAll();
            for (Category cat:category) {
                assert cat != null;
                categorys.put(cat.getCid(),cat);
            }
        }
        return categorys;
    }
}
