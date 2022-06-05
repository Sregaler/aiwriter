package com.hdumil.aiwriter.back.controller;

import com.hdumil.aiwriter.back.bean.Article;
import com.hdumil.aiwriter.back.bean.Category;
import com.hdumil.aiwriter.back.bean.Tag;
import com.hdumil.aiwriter.back.bean.User;
import com.hdumil.aiwriter.back.service.ArticleService;
import com.hdumil.aiwriter.back.service.CategoryService;
import com.hdumil.aiwriter.back.service.MaterialService;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdumil.aiwriter.base.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Company :
 * Author :   Andy
 * Description :
 */
@CrossOrigin
@Controller
@RequestMapping("/article")
@EnableAsync
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private CategoryService categoryService;

    //异步查询所有栏目
    @RequestMapping("/queryCategory")
    @ResponseBody
    public List<Category> queryCategory(){
        List<Category> categories = categoryService.queryCategory();
        return categories;
    }

    //查询栏目下的标签
    @RequestMapping("/queryTags")
    @ResponseBody
    public List<Tag> queryTags(String cid){
        List<Tag> tags = categoryService.queryTagsByCid(cid);
        return tags;
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Article> list(int page, int pageSize, String title, String orderByColumn, String sort, HttpSession session){
        //获取当前登录用户
        User user = (User) session.getAttribute("user");
        //参数1:当前页码 参数2:每页记录数 pageSize 该方法等同于 limit a,b
        if(orderByColumn!=null){
            if ("0".equals(sort))
                sort = "desc";
            else if ("1".equals(sort))
                sort = "asc";
            else sort = "desc";
            String orderBy = orderByColumn + " " + sort;//按照（数据库）排序字段 倒序 排序
            PageHelper.startPage(page, pageSize, orderBy);
        }
        else PageHelper.startPage(page, pageSize);
        List<Article> articles = articleService.list(user.getUid(),title);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }

    //异步修改文章是否公开
    @RequestMapping("/isOpen")
    @ResponseBody
    public ResultVo isOpen(@RequestBody Article article){
        ResultVo resultVo = new ResultVo();
        try {
            articleService.isOpen(article);
            resultVo.setOk(true);
            if(article.getIsOpen().equals("0")){
                resultVo.setMess("文章已私密");
            }else{
                resultVo.setMess("文章已公开");
            }
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //异步发布文章
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultVo saveOrUpdate(@RequestBody Article article, HttpSession session, HttpServletResponse resp){
        ResultVo resultVo = new ResultVo();
        try {
            //获取登录用户
            String realPath = session.getServletContext().getRealPath("");
//            if(user==null) {
//                resp.sendRedirect("/login");
//                return resultVo;
//            }
            if(article.getAid() == null){  //save
                User user = (User) session.getAttribute("user");
                article.setUid(user.getUid());
                List<String>[] preMaterial = articleService.compareMaterial("",article.getContent());
                materialService.updateUseCounts(preMaterial[1],1,realPath);
                article = articleService.save(article); //保存
                resultVo.setOk(true);
                resultVo.setMess("发布文章成功");
            }else{  //update
                String preArticle = articleService.queryById(article.getAid()).getContent(); // 修改前的文章内容
                article = articleService.update(article); //更新
                List<String>[] preMaterial = articleService.compareMaterial(preArticle,article.getContent());
                materialService.updateUseCounts(preMaterial[0],-1,realPath);
                materialService.updateUseCounts(preMaterial[1],1,realPath);
                FileUtil.deleteFiles(preMaterial[2],realPath);  // 删除前文中存在当前文中不存在的临时素材文件
                resultVo.setOk(true);
                resultVo.setMess("修改文章文章成功");
            }
            resultVo.setT(article);
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }


    //异步查询文章信息
    @RequestMapping("/queryById")
    @ResponseBody
    public Article queryById(String id){
       Article article = articleService.queryById(id);
       return article;
    }

    //异步删除文章
    @RequestMapping("/deleteById")
    @ResponseBody
    public ResultVo deleteById(String aid, HttpSession session){
        ResultVo resultVo = new ResultVo();
        String realPath = session.getServletContext().getRealPath("");
        try {
            List<String>[] preMaterial = articleService.deleteById(aid);
            materialService.updateUseCounts(preMaterial[0],-1,realPath);
            FileUtil.deleteFiles(preMaterial[2],realPath);  // 删除前文中存在当前文中不存在的临时素材文件
            resultVo.setOk(true);
            resultVo.setMess("删除文章成功");
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //异步删除未保存的文章
    @RequestMapping("/deleteFileByHtml")
    @ResponseBody
    public ResultVo deleteFileByHtml(String htmlText, HttpSession session){
        ResultVo resultVo = new ResultVo();
        try {
            articleService.deleteByHtml(htmlText, session.getServletContext().getRealPath(""));
            resultVo.setOk(true);
            resultVo.setMess("删除临时文件成功");
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }
}
