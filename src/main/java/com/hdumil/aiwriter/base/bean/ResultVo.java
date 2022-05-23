package com.hdumil.aiwriter.base.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/19
 * Description : 给客户端返回消息的Bean对象，返回数据
 */
@Data
@NoArgsConstructor
public class ResultVo<T> {
    private String mess;//给客户端的消息
    private boolean isOk;//用户操作是否成功
    private T t;//返回对象数据

    public ResultVo(String _mess,boolean _isOk,T _t){
        mess = _mess;
        isOk = _isOk;
        t = _t;
    }
}
