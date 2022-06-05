package com.hdumil.aiwriter.back.service.impl;

import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.EmergencyArticle;
import com.hdumil.aiwriter.back.mapper.CategoryMapper;
import com.hdumil.aiwriter.back.mapper.EmergencyArticleMapper;
import com.hdumil.aiwriter.back.mapper.TagMapper;
import com.hdumil.aiwriter.back.service.EmergencyArticleService;
import com.hdumil.aiwriter.base.exception.AiwriterEnum;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Company :
 * Author :   Andy
 * Description :
 */
@Service
public class EmergencyArticleServiceImpl implements EmergencyArticleService {

    @Autowired
    private EmergencyArticleMapper emergencyArticleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<EmergencyArticle> list(String uid,String title) {
        Example example = new Example(EmergencyArticle.class);
        Example.Criteria criteria = example.createCriteria();
        //查询当前登录用户的文章
        criteria.andEqualTo("uid",uid);
        if(title!=null && !title.equals("")){
            criteria.andLike("title","%" + title + "%");
        }
        List<EmergencyArticle> emergencyArticles = emergencyArticleMapper.selectByExample(example);
        //遍历所有文章
        for (EmergencyArticle emergencyArticle : emergencyArticles) {
            String cid = emergencyArticle.getCid();
            //根据cid查询栏目表，查询栏目对象
            Category category = categoryMapper.selectByPrimaryKey(cid);
            emergencyArticle.setCid(category.getCname());
        }
        return emergencyArticles;
    }

    @Override
    public void isOpen(EmergencyArticle emergencyArticle) {
        int count = emergencyArticleMapper.updateByPrimaryKeySelective(emergencyArticle);
        if(count == 0){
            throw new AiwriterException(AiwriterEnum.USER_LOGIN_CODE);
        }
    }

    @Transactional
    @Override
    public EmergencyArticle saveOrUpdate(EmergencyArticle emergencyArticle) {
        if(emergencyArticle.getEaid() == null){
            //添加
            //访问量
            emergencyArticle.setVisit_count("0");
            //发布时间
            emergencyArticle.setCreate_time(DateTimeUtil.getSysTime());
            int count = emergencyArticleMapper.insertSelective(emergencyArticle);
            if(count == 0){
                //发布失败
                throw new AiwriterException(AiwriterEnum.ARTICLE_PUNISH);
            }
        }else{
            int count = emergencyArticleMapper.updateByPrimaryKeySelective(emergencyArticle);
            if(count == 0){
                //修改失败
                throw new AiwriterException(AiwriterEnum.ARTICLE_UPDATE);
            }
        }
        return emergencyArticle;
    }

    @Override
    public EmergencyArticle queryById(String id) {
        EmergencyArticle emergencyArticle = emergencyArticleMapper.selectByPrimaryKey(id);
        return emergencyArticle;
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        int count = emergencyArticleMapper.deleteByPrimaryKey(id);
        if(count == 0){
            throw new AiwriterException(AiwriterEnum.ARTICLE_UPDATE);
        }
    }
}
