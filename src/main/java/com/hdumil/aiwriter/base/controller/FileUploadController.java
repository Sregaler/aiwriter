package com.hdumil.aiwriter.base.controller;

import com.hdumil.aiwriter.back.bean.User;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.util.DateTimeUtil;
import com.hdumil.aiwriter.base.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Company :
 * Author :   Andy
 * Description :
 */
@CrossOrigin
@Controller
//@RequestMapping("/upload")
public class FileUploadController {


    //解决Editormd文件上传
    @Transactional
    @RequestMapping("/editorUpload")
    @ResponseBody
    public Map<String,Object> editorUpload(
            @RequestParam(value = "editormd-image-files", required = false)MultipartFile img,
            HttpSession session){
        Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
        return map;
    }

    //上传图片
    @Transactional
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile img, HttpSession session){
        Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
        return map;
    }

//    //多个文件上传到公共素材库
//    @Transactional
//    @RequestMapping("/filesUpload2PublicLib")
//    @ResponseBody
//    public Map<String,Object> filesUpload(
//            @RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
//            HttpSession session){
//        Map<String, Object> map = FileUploadUtil.Upload2Public_s(files, session);
//        return map;
//    }

    //多个文件上传到临时公共库，不做数据库记录
    @Transactional
    @RequestMapping("/filesUpload2TempPublicLib")
    @ResponseBody
    public List<Map<String, Object>> tempfilesUpload(
            @RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
            HttpSession session){
        List<Map<String, Object>> res = FileUploadUtil.Upload2TempPublic(files, session);
        return res;
    }


    @RequestMapping("/filesUpload2CreateVidpressDoc")
    @ResponseBody
    public List<Map<String, Object>> filesUpload2CreateVfilesUpload2CreateVidpressDocidpress(
            @RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
            HttpSession session){
        List<Map<String, Object>> res = FileUploadUtil.Upload2TempPublic(files, session);
        // 解析文件中是否存在文字和图片

//        map.put("success", 2);
//        map.put("message", "文档中没有图片");
        return res;
    }
}
