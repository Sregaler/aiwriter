package com.hdumil.aiwriter.test;

import com.hdumil.aiwriter.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author yyj
 * @create 2022-05-24 12:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RPC {

    @Autowired
    public RestTemplate restTemplate;

    @Test
    public void test1(){
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36 Edg/83.0.478.50");
        headers.add(HttpHeaders.ACCEPT,"*/*");
        headers.add(HttpHeaders.HOST,"");
        headers.add(HttpHeaders.COOKIE,"BAIDUID=8DEB5E88AFBAA7F8299E847531F7F5F6:FG=1");
        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        HttpEntity httpEntity = new HttpEntity(null, headers);
//        ParameterizedTypeReference<String> res = new ParameterizedTypeReference() {
//        };

        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> entity = restTemplate.exchange("https://baijiahao.baidu.com/s?id=1629023731257426183", HttpMethod.GET, httpEntity,String.class, map);
        System.out.println(entity.toString());
    }
}