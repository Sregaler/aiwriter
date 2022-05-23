package com.hdumil.aiwriter.back.bean;

import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/1/12
 * Description :
 */
@Data
@ToString
@Table(name = "t_category")
@NameStyle(Style.normal)
public class Category {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String cid;
    private String cname;

}
