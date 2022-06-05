package com.hdumil.aiwriter.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/14
 * Description :
 */
@Data
@Table(name = "t_emergency")
@NameStyle(Style.normal)
public class EmergencyArticle {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String eaid;//主键
    private String title;//标题
    private String digest;//文章摘要
    private String content;//具体内容
    private String cid;//文章所属标签(栏目)
    private String tagNames;//标签名称
    private String visit_count;//访问量
    private String create_time;//创建时间
    private String logo;//文章封面logo
    private String isOpen;//是否公开
    private String level;//事件等级
}
