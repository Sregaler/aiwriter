package com.hdumil.aiwriter.base.exception;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/7/19
 * Description :
 */
public class AiwriterException extends RuntimeException {

    private AiwriterEnum aiwriterEnum;

    public AiwriterException(AiwriterEnum aiwriterEnum) {
        //想从异常堆栈中获取异常信息的话
        super(aiwriterEnum.getMessage());
        this.aiwriterEnum = aiwriterEnum;
    }
}

