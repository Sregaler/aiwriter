package com.hdumil.aiwriter.base.controller;
import com.hdumil.aiwriter.back.bean.ArticleTemplate;
import com.hdumil.aiwriter.back.bean.Material;
import com.hdumil.aiwriter.back.service.WriteService;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.util.FileUploadUtil;
import com.hdumil.aiwriter.base.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/write")
public class AiWriteController {

    @Autowired
    private WriteService writeService;

    @RequestMapping("/ai_template")
    @ResponseBody
    public ResultVo ai_write(
            @RequestParam(value = "contents", required = false) String contents,
            @RequestParam(value = "urls", required = false) String urls,
            ArticleTemplate article_template,
            HttpSession session){

        List<Material> materials = new ArrayList<>();
        // 将contents封装成materials
        contentsToMatertial(materials,contents);
        // 将urls封装成materials
        for(String url : urls.split(";")){
            materials.add(new Material(FileUtil.getFileTypeIndx(url), url));
        }
        String html_url = writeService.getAiWrite(materials, article_template);
        ResultVo resultVo = new ResultVo();
        resultVo.setMess("AI写稿完成");
        resultVo.setOk(true);
        resultVo.setT(html_url);
        return resultVo;
    }

    //解析contents中的内容
    public void contentsToMatertial(List<Material> materials, String contents) {

    }
}
