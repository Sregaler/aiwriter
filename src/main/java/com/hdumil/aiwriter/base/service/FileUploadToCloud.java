package com.hdumil.aiwriter.base.service;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface FileUploadToCloud {

    String uploadFile(InputStream inputStream, long size, String extName);

    String uploadFile(InputStream inputStream, long size, String extName, int days);

    List<Map<String, Object>> Upload2TempPublic(CommonsMultipartFile[] files);

    Map<String, Object> Upload2User(CommonsMultipartFile multipartFile, String userName);

    List<String> deleteFiles(List<String> fileName);

    boolean deleteFile(String fileName);

    Map<String, Object> Upload2Public(CommonsMultipartFile multipartFile);
}
