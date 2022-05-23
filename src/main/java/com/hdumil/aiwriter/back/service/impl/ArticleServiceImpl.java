package com.hdumil.aiwriter.back.service.impl;

import com.hdumil.aiwriter.AdminConfig;
import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.Tag;
import com.hdumil.aiwriter.back.mapper.ArticleMapper;
import com.hdumil.aiwriter.back.mapper.CategoryMapper;
import com.hdumil.aiwriter.back.mapper.TagMapper;
import com.hdumil.aiwriter.back.service.ArticleService;
import com.hdumil.aiwriter.back.service.CategoryService;
import com.hdumil.aiwriter.base.exception.AiwriterEnum;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import com.hdumil.aiwriter.base.util.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Company :
 * Author :   Andy
 * Description :
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Article> list(String uid,String title) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        //查询当前登录用户的文章
        criteria.andEqualTo("uid",uid);
        if(title!=null && !title.equals("")){
            criteria.andLike("title","%" + title + "%");
        }
        List<Article> articles = articleMapper.selectByExample(example);
        //遍历所有文章
        for (Article article : articles) {
            //根据cid查询栏目表，查询栏目对象
            Category category=categoryService.getCategorysMap().get(article.getCid());
            if(category!=null)
                article.setCid(category.getCname());
        }
        return articles;
    }

    @Override
    public void isOpen(Article article) {
        int count = articleMapper.updateByPrimaryKeySelective(article);
        if(count == 0){
            throw new AiwriterException(AiwriterEnum.USER_LOGIN_CODE);
        }
    }


    @Transactional
    @Override
    public Article save(Article article) {
        //添加类型
        if(article.getType()==null) article.setType("0");
        //添加logo
        if(article.getLogo()==null) {
            Document document = Jsoup.parse(article.getContent());
            Elements pItems = document.getElementsByTag("img");
            if(pItems.isEmpty())
                article.setLogo("/img/other/article_def.png");
            else
                article.setLogo(pItems.get(0).attr("src"));
        }
        //点赞数
        article.setThumbsUp("0");
        //是否热门
        article.setIs_hot("0");
        //访问量
        article.setVisit_count("0");
        //发布时间
        String d_str = DateTimeUtil.getSysTime();
        article.setCreate_time(d_str);
        article.setUpdate_time(d_str);
        //是否被评论
        article.setIsCommented("0");
        int count = articleMapper.insertSelective(article);
        if(count == 0){
            //发布失败
            throw new AiwriterException(AiwriterEnum.ARTICLE_PUNISH);
        }
        return article;
    }

    @Transactional
    @Override
    public Article update(Article article) {
        article.setUpdate_time(DateTimeUtil.getSysTime());
        int count = articleMapper.updateByPrimaryKeySelective(article);
        if(count == 0){
            //修改失败
            throw new AiwriterException(AiwriterEnum.ARTICLE_UPDATE);
        }
        return article;
    }

    @Override
    public Article queryById(String aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        return article;
    }

    @Transactional
    @Override
    public List<String>[] deleteById(String aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        List<String>[] preMaterial = null;
        if(article!=null){
            articleMapper.delete(article);
            if(!article.getLogo().equals(AdminConfig.ARTICLE_DEFAULT_IMAGE)){
                String preContent = article.getContent() + "<img src=\""+article.getLogo()+"\">";
                preMaterial = compareMaterial(preContent,"");
            }
            else
                preMaterial = compareMaterial(article.getContent(),"");
        }
        else {
            throw new AiwriterException(AiwriterEnum.ARTICLE_UPDATE);
        }
        return preMaterial;
    }

    @Override
    public void deleteByHtml(String htmlText,String realPath) {
            FileUtil.deleteFilrByHtml(htmlText, realPath);
    }

    public Elements analysisMaterial(Document document){
        Elements materialList = document.getElementsByTag("img");
        materialList.addAll(document.getElementsByTag("video"));
        materialList.addAll(document.getElementsByTag("audio"));
        return materialList;
    }

    /**
     * 对比前文删除的文件素材
     * @param preContent  前文的内容
     * @param nowContent  当前文章的内容
     * @return  返回前文中存在，当前文章中不存在的素材文件列表
     */
    @Override
    public List<String>[] compareMaterial(String preContent, String nowContent) {
        Document document = Jsoup.parse(preContent);
        Elements MaterialList = analysisMaterial(document);
        List<String>[] preMaterial = new ArrayList[3];  // [0]前文有，当前文没有，[1]仅当前文有 [2]前文中的存在的临时文件
        preMaterial[0] = new ArrayList<>();
        preMaterial[1] = new ArrayList<>();
        preMaterial[2] = new ArrayList<>();
        for (Element Item : MaterialList) {
            String url = Item.attr("src");
            String[] str = url.split("/");
            if (str.length > 2 && str[2].equals("user")) {
                preMaterial[0].add(url);
            }
            else {
                preMaterial[2].add(url);
            }
        }
        document = Jsoup.parse(nowContent);
        MaterialList = analysisMaterial(document);
        for (Element Item : MaterialList) {
            String url = Item.attr("src");
            String[] str = url.split("/");
            if (str.length > 2 && str[2].equals("user")) {
                if(!preMaterial[0].remove(url)){
                    preMaterial[1].add(url);
                }
            }
            else {
                preMaterial[2].remove(url);
            }
        }
        return preMaterial;
    }
}
