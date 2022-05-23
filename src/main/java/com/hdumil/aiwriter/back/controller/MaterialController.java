package com.hdumil.aiwriter.back.controller;

import com.hdumil.aiwriter.back.service.MaterialService;
import com.hdumil.aiwriter.base.bean.ResultVo;
import com.hdumil.aiwriter.base.exception.AiwriterEnum;
import com.hdumil.aiwriter.base.exception.AiwriterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdumil.aiwriter.back.bean.Material;
import com.hdumil.aiwriter.back.bean.User;
import com.hdumil.aiwriter.base.util.FileUploadUtil;
import com.hdumil.aiwriter.base.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Company :
 * Author :   Andy
 * Description :
 */
@CrossOrigin
@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Material> list(int page, int pageSize,
                                   @RequestParam(value = "m_type", required = false) Integer mType,
                                   String m_name,
                                   String orderByColumn, String sort,
                                   HttpSession session){
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
        List<Material> materials = materialService.list(user.getUid(),mType,m_name);
        PageInfo<Material> pageInfo = new PageInfo<>(materials);
        return pageInfo;
    }

    //异步保存素材
    @RequestMapping("/save")
    @ResponseBody
    public ResultVo save(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                         Material material, HttpSession session){
        ResultVo resultVo = new ResultVo();
        List<Material> materials = new ArrayList<>();
        User user = (User) session.getAttribute("user");//获取登录用户
        material.setUid(user.getUid());
        for(CommonsMultipartFile file : files){  // 一个一个素材添加
            Material material_copy = new Material();
            BeanUtils.copyProperties(material, material_copy);
            //保存到本地
            try {
                Map<String,Object> map = FileUploadUtil.Upload2User(file, session);
                if((int)map.get("success")==1) {
                    if(material_copy.getM_type()==null)  //设置文件类型
                        material_copy.setM_type(FileUploadUtil.getFileTypeIndx(file.getOriginalFilename()));
                    if(material_copy.getM_name()==null)  // 根据文件名字设置素材名字
                        material_copy.setM_name(file.getOriginalFilename());
                    material_copy.setContent((String) map.get("url"));
                    material_copy = materialService.save(material_copy);
                    materials.add(material_copy);
                }
                else{
                    FileUtil.deleteFile((String) map.get("url"));
                    throw new AiwriterException(AiwriterEnum.MATERIAL_SAVE);
                }
            }catch (AiwriterException e){
                //添加失败
                FileUtil.deleteFile(material_copy.getContent());
                resultVo.setMess(e.getMessage());
            }
        }
        resultVo.setOk(true);
        if (materials.size() == files.length) {
            resultVo.setMess("全部保存成功");
        } else {
            resultVo.setMess("部分成功");
        }
        resultVo.setT(materials);
        return resultVo;
    }

    //异步保存公共素材
    @RequestMapping("/savePublic")
    @ResponseBody
    public ResultVo savePublic(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                         Material material, HttpSession session){
        ResultVo resultVo = new ResultVo();
        List<Material> materials = new ArrayList<>();
        material.setUid("1");  // 保存到公共库下的文件都表示由管理员管理
        for(CommonsMultipartFile file : files){  // 一个一个素材添加
            Material material_copy = new Material();
            BeanUtils.copyProperties(material, material_copy);
            //保存到本地
            try {
                Map<String,Object> map = FileUploadUtil.Upload2Public(file, session);
                if((int)map.get("success")==1) {
                    material_copy.setContent((String) map.get("url"));
                    material_copy = materialService.save(material_copy);
                    materials.add(material_copy);
                }
                else{
                    FileUtil.deleteFile((String) map.get("url"));
                    throw new AiwriterException(AiwriterEnum.MATERIAL_SAVE);
                }
            }catch (AiwriterException e){
                FileUtil.deleteFile(material_copy.getContent());
                resultVo.setMess(e.getMessage());
            }
        }
        if (materials.size() == files.length) {
            resultVo.setOk(true);
            resultVo.setMess("全部保存成功");
        } else {
            resultVo.setOk(false);
            resultVo.setMess("存在保存失败的文件");
        }
        resultVo.setT(materials);
        return resultVo;
    }

    //异步查询素材信息
    @RequestMapping("/queryById")
    @ResponseBody
    public Material queryById(String id){
        Material material = materialService.queryById(id);
       return material;
    }

    @RequestMapping("/queryByName")
    @ResponseBody
    public Material queryByName(String name){
//        Material material = materialService.queryById(name);
        return new Material();
    }

    //异步删除素材
    @RequestMapping("/deleteById")
    @ResponseBody
    public ResultVo deleteById(String mid, HttpSession session){
        ResultVo resultVo = new ResultVo();
        try {
            materialService.deleteById(mid, session.getServletContext().getRealPath(""));
            resultVo.setOk(true);
            resultVo.setMess("删除素材成功");
        }catch (AiwriterException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

}
