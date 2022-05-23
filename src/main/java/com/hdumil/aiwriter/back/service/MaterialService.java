package com.hdumil.aiwriter.back.service;

import com.hdumil.aiwriter.back.bean.Material;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MaterialService {
    List<Material> list(String uid, Integer mType, String m_name);

    Material save(Material material);

    Material update(Material material);

    Material queryById(String mid);

    void deleteById(String mid, String realPath);

    void updateUseCounts(List<String> contents, int change, String realPath);
}
