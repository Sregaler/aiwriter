package com.hdumil.aiwriter.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_material")
@NameStyle(Style.normal)
public class Material{
    @Id
    @KeySql(useGeneratedKeys = true)
    private String mid;//主键
    private String m_type;//素材类型
    private String m_name;//素材名称
    private String content;//素材的url路径
    private String uid;//发布者外键
    private String cid;//素材所属标签(栏目)
    private String create_time;//创建时间
    private String update_time;//更新时间
    private String use_time;//最近使用时间
    private Integer use_count;//使用次数
    private String tagNames;//标签名称
    private String is_show;//是否展示给用户
    private String is_delete;//素材是否已经被用户指示删除
    public Material(){}
    public Material(String m_type,String content){
        this.m_type = m_type;
        this.content = content;
    }
}
