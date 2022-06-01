package com.hdumil.aiwriter.base.service.impl;

import com.google.gson.Gson;
import com.hdumil.aiwriter.AdminConfig;
import com.hdumil.aiwriter.base.service.FileUploadToCloud;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
@ConfigurationProperties(prefix = "qiniu")
public class FileCloudImpl implements FileUploadToCloud {
    private final Logger logger = LoggerFactory.getLogger(FileUploadToCloud.class);

    private String url;
    private String bucket;
    private String accessKey;
    private String secretKey;
    private Configuration cfg = new Configuration(Region.region2());

    @Override
    public String uploadFile(InputStream inputStream, long size, String extName) {
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, extName, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return url + putRet.key;
        } catch (QiniuException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public String uploadFile(InputStream inputStream, long size, String extName, int days) {
        String fileUrl = uploadFile(inputStream,size,extName);
        if(fileUrl!=null){
            Configuration cfg = new Configuration(Region.region0());
            Auth auth = Auth.create(accessKey, secretKey);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            try {
                bucketManager.deleteAfterDays(bucket, extName, days);
            } catch (QiniuException ex) {
                logger.error("fileDeleteAfterError:"+fileUrl, ex);
                System.err.println(ex.response.toString());
            }
        }
        return fileUrl;
    }

    @Override
    public List<String> deleteFiles(List<String> fileName) {
        List<String> errorRes = new ArrayList<>();
        for (String fileUrl:fileName) {
            if(!deleteFile(fileUrl))
                errorRes.add(fileUrl);
        }
        return errorRes;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        String key = fileUrl.substring(url.length());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return false;
        }
        return true;
    }


    @Override
    public Map<String, Object> Upload2Public(CommonsMultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<>();
        long attachSize = multipartFile.getSize();
        String imgName = AdminConfig.FILE_SAVE_PATH_PUBLIC + "/" + System.currentTimeMillis() + multipartFile.getOriginalFilename();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            String filePath = uploadFile(inputStream, attachSize, imgName);
            map.put("url", filePath);
            map.put("success", 1);
            map.put("message", "上传成功");
        } catch (IOException e) {
            map.put("url", multipartFile.getOriginalFilename());
            map.put("success", 0);
            map.put("message", "上传失败");
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> Upload2TempPublic(CommonsMultipartFile[] files) {
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> map;
        for (CommonsMultipartFile multipartFile : files) {
            map = new HashMap<>();
            long attachSize = multipartFile.getSize();
            String imgName = AdminConfig.FILE_SAVE_PATH_TEMPPUBLIC + "/" + System.currentTimeMillis() + multipartFile.getOriginalFilename();
            try (InputStream inputStream = multipartFile.getInputStream()) {
                String filePath = uploadFile(inputStream, attachSize, imgName);
                map.put("url", filePath);
                map.put("success", 1);
                map.put("message", "上传成功");
            } catch (IOException e) {
                map.put("url", multipartFile.getOriginalFilename());
                map.put("success", 0);
                map.put("message", "上传失败");
            }
            res.add(map);
        }
        return res;
    }

    @Override
    public Map<String, Object> Upload2User(CommonsMultipartFile multipartFile, String userName) {
        Map<String, Object> map = new HashMap<>();
        long attachSize = multipartFile.getSize();
        String imgName = AdminConfig.FILE_SAVE_PATH_USER + "/" + userName + "/" + System.currentTimeMillis() + multipartFile.getOriginalFilename();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            String filePath = uploadFile(inputStream, attachSize, imgName);
            map.put("url", filePath);
            map.put("success", 1);
            map.put("message", "上传成功");
        } catch (IOException e) {
            map.put("url", multipartFile.getOriginalFilename());
            map.put("success", 0);
            map.put("message", "上传失败");
        }
        return map;
    }

}
