package com.hdumil.aiwriter.base.controller.baidu.bean;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/14
 * Description :
 */
@Data
public class Text2video1 {
    @Data
    public static class Track {
        String url;  //素材的url
        String type;  //素材的类型
        String expectedDuration;  //时长
    }
    @Data
    public static class TracksTexts {
        String text;  //文本信息
        List<Track> tracks = new ArrayList<>();  //素材信息
    }
    String videoTitle;
    String totalDuration;
    String resolution;
    String ttsPer;
    List<TracksTexts> trackTexts = new ArrayList<>();

    public Text2video1() {}

    public Text2video1(String htmlInput) {
        //6.Jsoup解析html
        Document document = Jsoup.parse(htmlInput);
        //像js一样，通过id 获取文章列表元素对象
//        Element postList = document.getElementById("post_list");
        //像js一样，通过class 获取列表下的所有博客
        Elements pItems = document.getElementsByTag("p");
        for (Element pItem : pItems) {
//            System.out.println(pItem);
            Elements pItem_img = pItem.select("img");
            Elements pItem_video = pItem.select("video");
            Elements pItem_audio = pItem.select("audio");
            if(!pItem_img.isEmpty()||!pItem_video.isEmpty()||!pItem_audio.isEmpty()){
                if(trackTexts.isEmpty()){
                    TracksTexts temp = new TracksTexts();
                    trackTexts.add(temp);
                }
                TracksTexts temp = trackTexts.get(trackTexts.size()-1);
                Track tempTrack = new Track();
                if (!pItem_img.isEmpty()){
                    tempTrack.setUrl(pItem.select("img").attr("src"));
                    tempTrack.setType("image");
                }
                else if(!pItem_video.isEmpty()){
                    tempTrack.setUrl(pItem.select("video").attr("src"));
                    tempTrack.setType("video");
                }
                else if(!pItem_audio.isEmpty()){
                    tempTrack.setUrl(pItem.select("audio").attr("src"));
                    tempTrack.setType("audio");
                }
                temp.tracks.add(tempTrack);
            }
            else {
                TracksTexts temp = new TracksTexts();
                temp.text = pItem.text().trim();
                trackTexts.add(temp);
            }
        }
    }

    public Text2video1(String htmlInput, int x) {
        //6.Jsoup解析html
        Document document = Jsoup.parse(htmlInput);
        //像js一样，通过标签获取title
//        videoTitle = document.getElementsByTag("title").first().toString();
//        System.out.println(videoTitle);
        //像js一样，通过id 获取文章列表元素对象
//        Element postList = document.getElementById("post_list");
        //像js一样，通过class 获取列表下的所有博客
        Elements pItems = document.getElementsByTag("p");
        //循环处理每篇博客
        for (Element pItem : pItems) {
            Elements pItem_img = pItem.select("img");
            Elements pItem_video = pItem.select("video");
            Elements pItem_audio = pItem.select("audio");
            if(!pItem_img.isEmpty()||!pItem_video.isEmpty()||!pItem_audio.isEmpty()){
                if(trackTexts.isEmpty()){
                    TracksTexts temp = new TracksTexts();
                    trackTexts.add(temp);
                }
                TracksTexts temp = trackTexts.get(trackTexts.size()-1);
                Track tempTrack = new Track();
                if (!pItem_img.isEmpty()){
                    tempTrack.setUrl(pItem.select("img").attr("src"));
                    tempTrack.setType("image");
                }
                else if(!pItem_video.isEmpty()){
                    tempTrack.setUrl(pItem.select("video").attr("src"));
                    tempTrack.setType("video");
                }
                else if(!pItem_audio.isEmpty()){
                    tempTrack.setUrl(pItem.select("audio").attr("src"));
                    tempTrack.setType("audio");
                }
                temp.tracks.add(tempTrack);
            }
            else {
                TracksTexts temp = new TracksTexts();
                temp.text = pItem.text().trim();
                trackTexts.add(temp);
            }
        }
    }

    @Override
    public String toString() {
        return "Text2video1{" +
                "videoTitle='" + videoTitle + '\'' +
                ", totalDuration='" + totalDuration + '\'' +
                ", resolution='" + resolution + '\'' +
                ", ttsPer='" + ttsPer + '\'' +
                ", trackTexts=" + trackTexts +
                '}';
    }
}
