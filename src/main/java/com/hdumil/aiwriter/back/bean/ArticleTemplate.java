package com.hdumil.aiwriter.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author :   Andy
 * Date : 2021/7/14
 * Description :
 */
@Data
@Table(name = "t_article_template")
@NameStyle(Style.normal)
public class ArticleTemplate {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String idat;//主键
    private String at_type;//模板类型，文本或视频
    private String title;//标题
    private String digest;//模板摘要
    private String content;//模板的html文本
    private String url;//模板的url
    private String create_time;//创建时间
    private String update_time;//更新时间
    private String isOpen;//是否公开
    private String cid;//模板所属标签(栏目)
    private String tagNames;//标签名称
    private String uid;//发布者外键
}
