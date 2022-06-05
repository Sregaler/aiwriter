package com.hdumil.aiwriter.back.service.impl;
import com.hdumil.aiwriter.back.bean.ArticleTemplate;
import com.hdumil.aiwriter.back.bean.Material;
import com.hdumil.aiwriter.back.bean.WriteAlgorithm;
import com.hdumil.aiwriter.back.service.WriteService;
import com.hdumil.aiwriter.base.util.MakeHtmlTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WriteServiceImpl implements WriteService {

    @Autowired
    private WriteAlgorithm WA;

    @Override
    public String getAiWrite(List<Material> materials, ArticleTemplate article_template){
        //根据模板套用ai
        if(article_template.getAt_type() == "0"){
            return getTextImage(materials, article_template);
        }
        else if(article_template.getAt_type() == "1"){
            return getVideo(materials, article_template);
        }
        else if(article_template.getAt_type() == "2"){
            return getAudio(materials, article_template);
        }
        return null;
    }

    @Override
    public String getTextImage(List<Material> materials, ArticleTemplate article_template) {
        //1.调用算法整合素材
        List<String> result_ai = new ArrayList<>();
        //2.调用模板合成html文件并保存

        //根据ai结果生成html
        String html_url = MakeHtmlTemplateUtil.stringToHtml(result_ai, article_template);

        //3.返回html的url
        return null;
    }

    @Override
    public String getVideo(List<Material> materials, ArticleTemplate article_template) {
        return null;
    }

    @Override
    public String getAudio(List<Material> materials, ArticleTemplate article_template) {
        return null;
    }
}
