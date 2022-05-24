package com.hdumil.aiwriter.base.util;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * http 工具类
 */
public class HttpUtil {

    static HashMap<String, String> GetDefaultParm = new HashMap<>();
    static {
        GetDefaultParm.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        GetDefaultParm.put("Accept","*/*");
        GetDefaultParm.put("Accept-Encoding","gzip,deflate,br");
//        GetDefaultParm.put("Accept-Language","zh-CN,zh;q=0.9");
        GetDefaultParm.put("Connection","keep-alive");
//        GetDefaultParm.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        GetDefaultParm.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36 Edg/83.0.478.50");
        GetDefaultParm.put("Cookie","BAIDUID=9B223E80FD9BDFF40F493E730048491C:FG=1");
//        GetDefaultParm.put("Sec-Fetch-Dest","document");
//        GetDefaultParm.put("Sec-Fetch-Mode","navigate");
//        GetDefaultParm.put("Sec-Fetch-Site","none");
//        GetDefaultParm.put("Sec-Fetch-User","?1");
//        GetDefaultParm.put("Upgrade-Insecure-Requests","1");
//        GetDefaultParm.put("Host","baijiahao.baidu.com");
//        GetDefaultParm.put("Cache-Control","max-age=0");
    }

    public static void echoRequestHeaders(HttpURLConnection httpUrlCon){
        System.out.println("Request Headers:");
        System.out.println(" " + httpUrlCon.getRequestMethod() + " / "+ httpUrlCon.getURL());
        Map<String, List<String>> requestHeaders =httpUrlCon.getRequestProperties();
        for (String key : requestHeaders.keySet()) {
            System.err.println(key + "--->" + requestHeaders.get(key));
        }
//        System.out.println(" Host: " + httpUrlCon.getRequestProperty("Host"));
//        System.out.println(" Connection: " + httpUrlCon.getRequestProperty("Connection"));
//        System.out.println(" Accept: " + httpUrlCon.getRequestProperty("Accept"));
//        System.out.println(" User-Agent: " + httpUrlCon.getRequestProperty("User-Agent"));
//        System.out.println(" Accept-Encoding: " + httpUrlCon.getRequestProperty("Accept-Encoding"));
//        System.out.println(" Accept-Language: " + httpUrlCon.getRequestProperty("Accept-Language"));
//        System.out.println(" Cookie: " + httpUrlCon.getRequestProperty("Cookie"));
//        System.out.println(" Connection: " + httpUrlCon.getHeaderField("Connection"));//利用另一种读取HTTP头字段
//        System.out.println();
    }

    public static void echoResponseHeaders(HttpURLConnection httpUrlCon) throws IOException{
        System.out.println("响应头的内容");
        Map<String, List<String>> headers = httpUrlCon.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            System.err.println(key + "--->" + headers.get(key));
        }
//        System.out.println("Response Headers:");
//        System.out.println(" " + "HTTP/1.1 " + httpUrlCon.getResponseCode() + " " + httpUrlCon.getResponseMessage());
//        System.out.println(" status: " + httpUrlCon.getResponseCode() + " " + httpUrlCon.getResponseMessage());
//        System.out.println(" content-encoding: " + httpUrlCon.getContentEncoding());
//        System.out.println(" content-length : " + httpUrlCon.getContentLength());
//        System.out.println(" content-type: " + httpUrlCon.getContentType());
//        System.out.println(" Date: " + httpUrlCon.getDate());
//        System.out.println(" ConnectTimeout: " + httpUrlCon.getConnectTimeout());
//        System.out.println(" expires: " + httpUrlCon.getExpiration());
//        System.out.println(" content-type: " + httpUrlCon.getHeaderField("content-type"));//利用另一种读取HTTP头字段
//        System.out.println();
    }

    public static String post(String requestUrl, String accessToken, String params)
            throws Exception {
        String contentType = "application/x-www-form-urlencoded";
        return HttpUtil.post(requestUrl, accessToken, contentType, params);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params)
            throws Exception {
        String encoding = "UTF-8";
        if (requestUrl.contains("nlp")) {
            encoding = "GBK";
        }
        return HttpUtil.post(requestUrl, accessToken, contentType, params, encoding);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params, String encoding)
            throws Exception {
        String url = requestUrl + "?access_token=" + accessToken;
        return HttpUtil.postGeneralUrl(url, contentType, params, encoding);
    }

    public static String postGeneralUrl(String generalUrl, String contentType, String params, String encoding){
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(generalUrl);
            System.out.println("url:" + generalUrl);
            System.out.println("contentType:" + contentType);
            System.out.println("params:" + params);
            System.out.println("encoding:" + encoding);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 得到请求的输出流对象
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(params.getBytes(encoding));
            out.flush();
            out.close();

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> headers = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.err.println(key + "--->" + headers.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = null;
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), encoding));
            String getLine;
            while ((getLine = in.readLine()) != null) {
                result.append(getLine);
            }
            in.close();
            System.err.println("result:" + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }


    /**
     * 在指定url后追加参数
     * @param url
     * @param data 参数集合 key = value
     * @return
     */
    public static String appendUrl(String url, Map<String, String> data) {
        String newUrl = url;
        StringBuffer param = new StringBuffer();
        for (String key : data.keySet()) {
            param.append(key + "=" + data.get(key) + "&");
        }
        String paramStr = param.toString();
        paramStr = paramStr.substring(0, paramStr.length() - 1);
        if (newUrl.indexOf("?") >= 0) {
            newUrl += "&" + paramStr;
        } else {
            newUrl += "?" + paramStr;
        }
        return newUrl;
    }

    public static String httpGet(String httpUrl, HashMap<String, String> headerMap){
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if(headerMap == null) headerMap = GetDefaultParm;
            for (Map.Entry item : headerMap.entrySet()) {
                connection.setRequestProperty(item.getKey().toString(),item.getValue().toString()); //设置header
            }
            echoRequestHeaders(connection);// 查看请求头的内容
            // 建立实际的连接
            connection.connect();
//            echoResponseHeaders(connection);// 获取所有响应头字段
            if(connection.getContentEncoding()!=null && connection.getContentEncoding().equals("gzip")){
                GZIPInputStream gzip = new GZIPInputStream(connection.getInputStream());
                byte[] b = new byte[4096];
                for (int n; (n = gzip.read(b)) != -1;) {
                    result.append(new String(b, 0, n));
                }
            }
            else {
                // 定义 BufferedReader输入流来读取URL的响应
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String getLine;
                while ((getLine = in.readLine()) != null) {
                    result.append(getLine);
                }
                in.close();
            }
//            System.err.println("result:" + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

    public static String httpGetTrust(String httpUrl, HashMap<String, String> headerMap) {
        BufferedReader input = null;
        StringBuilder strb = null;
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(httpUrl);
            try {
                // trust all hosts
                trustAllHosts();
                if (url.getProtocol().toLowerCase().equals("https")) {
                    HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
                    https.setHostnameVerifier(DO_NOT_VERIFY);
                    con = https;
                } else {
                    con = (HttpURLConnection)url.openConnection();
                }
                if(headerMap != null){
                    for (Map.Entry item : headerMap.entrySet()) {
                        con.setRequestProperty(item.getKey().toString(),item.getValue().toString()); //设置header
                    }
                }
//                else{
//                    for (Map.Entry item : GetDefaultParm.entrySet()) {
//                        con.setRequestProperty(item.getKey().toString(),item.getValue().toString()); //设置header
//                    }
//                }
                input = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                strb = new StringBuilder();
                String s;
                while ((s = input.readLine()) != null) {
                    strb.append(s).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } finally {
            // close buffered
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}

            // disconnecting releases the resources held by a connection so they may be closed or reused
            if (con != null) {
                con.disconnect();
            }
        }
        return strb == null ? null : strb.toString();
    }

    public static String httpGetByJsoup(String httpUrl, HashMap<String, String> headerMap){
        String xmlSource = "";
        try {
            if(headerMap == null) headerMap = GetDefaultParm;
            Connection connection = Jsoup.connect(httpUrl).headers(headerMap).timeout(5000);
            Document document = connection.get();
            xmlSource = document.toString();
//            System.out.println(xmlSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return xmlSource;
    }

    public static String httpGetBySpring(String httpUrl, HashMap<String, String> headerMap) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36 Edg/83.0.478.50");
        headers.add(HttpHeaders.ACCEPT,"*/*");
        headers.add(HttpHeaders.HOST,"");
        headers.add(HttpHeaders.COOKIE,"BAIDUID=8DEB5E88AFBAA7F8299E847531F7F5F6:FG=1");
        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        HttpEntity httpEntity = new HttpEntity(null, headers);
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> entity = restTemplate.exchange(httpUrl, HttpMethod.GET, httpEntity,String.class, map);
        String reualt = entity.toString();
        return reualt;
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                Log.i(TAG, "checkClientTrusted");
            }
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                Log.i(TAG, "checkServerTrusted");
            }
        } };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getFileBytes(String generalUrl){
        URL urlConnection = null;
        HttpURLConnection httpURLConnection = null;
        try {
            urlConnection = new URL(generalUrl);
            httpURLConnection = (HttpURLConnection) urlConnection.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((len = in.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            in.close();
            out.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        return null;
    }
}
