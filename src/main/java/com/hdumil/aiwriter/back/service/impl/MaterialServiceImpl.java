package com.hdumil.aiwriter.back.service.impl;
import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.Material;
import com.hdumil.aiwriter.back.mapper.CategoryMapper;
import com.hdumil.aiwriter.back.mapper.MaterialMapper;
import com.hdumil.aiwriter.back.mapper.TagMapper;
import com.hdumil.aiwriter.back.service.CategoryService;
import com.hdumil.aiwriter.back.service.MaterialService;
import com.hdumil.aiwriter.base.exception.AiwriterEnum;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.hdumil.aiwriter.base.service.FileUploadToCloud;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import com.hdumil.aiwriter.base.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Company :
 * Author :   Andy
 * Description :
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileUploadToCloud fileUploadToCloud;

    @Override
    public List<Material> list(String uid, Integer mType, String m_name) {
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        //查询当前登录用户的文章
        criteria.andEqualTo("uid",uid);
        criteria.andEqualTo("is_delete","0");
        if(mType!=null){
            criteria.andEqualTo("m_type",mType);
        }
        if(m_name!=null && !m_name.equals("")){
            criteria.andLike("m_name","%" + m_name + "%");
        }
        List<Material> materials = materialMapper.selectByExample(example);
        //遍历所有文章
        for (Material material : materials) {
            Category category=categoryService.getCategorysMap().get(material.getCid());
            if(category!=null)
                material.setCid(category.getCname());
        }
        return materials;
    }

    @Transactional
    @Override
    public Material save(Material material) {
        String time_now = DateTimeUtil.getSysTime();
        material.setCreate_time(time_now);//发布时间
        material.setUpdate_time(time_now);//更改时间
        material.setUse_time(time_now);//使用时间
        material.setUse_count(0);//引用次数
        material.setIs_show("1");//是否隐藏
        material.setIs_delete("0");//是否已经删除
        int count = materialMapper.insertSelective(material);
        if(count == 0){
            throw new AiwriterException(AiwriterEnum.MATERIAL_SAVE);
        }
        return material;
    }

    @Transactional
    @Override
    public Material update(Material material) {
        material.setUpdate_time(DateTimeUtil.getSysTime());
        int count = materialMapper.updateByPrimaryKeySelective(material);
        if(count == 0){
            //修改失败
            throw new AiwriterException(AiwriterEnum.ARTICLE_UPDATE);
        }
        return material;
    }

    @Override
    public Material queryById(String mid) {
        return materialMapper.selectByPrimaryKey(mid);
    }

    @Transactional
    @Override
    public void deleteById(String mid, String realPath) {
        Material material = materialMapper.selectByPrimaryKey(mid);
        if(material!=null){
            if(material.getUse_count()>0){
                if(material.getIs_delete().equals("0")){
                    material.setIs_delete("1");
                    materialMapper.updateByPrimaryKey(material);
                }
            }
            else {
                delete(material, realPath);
            }
        }
        else {
            throw new AiwriterException(AiwriterEnum.ARTICLE_UPDATE);
        }
    }

    public void delete(Material material, String realPath) {
        materialMapper.delete(material);
        fileUploadToCloud.deleteFile(material.getContent());
        FileUtil.deleteFile(realPath + "/" + material.getContent());
    }

    @Override
    @Async
    public void updateUseCounts(List<String> contents, int change, String realPath){
        for (String str: contents) {
            updateUseCount(str,change,realPath);
        }
    }

    public void updateUseCount(String content, int change, String realPath){
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("content",content);

        List<Material> m_list = materialMapper.selectByExample(example);
        for (Material mat:m_list) {
            mat.setUse_count(mat.getUse_count()+change);
            if(mat.getIs_delete().equals("1")&&mat.getUse_count()<=0){
                delete(mat, realPath);
            }
            else
                materialMapper.updateByPrimaryKey(mat);
        }
    }
}
