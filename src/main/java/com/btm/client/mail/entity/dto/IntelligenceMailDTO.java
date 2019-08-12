package com.btm.client.mail.entity.dto;

import lombok.Data;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/25 15:34
 * @Version: 1.0
 */
@Data
public class IntelligenceMailDTO {

    /**
     * 用户账号列别
     */
    private String[] userLoginArray;
    /**
     * 发送邮件内容编号
     */
    private String mailId;
    /**
     * 邮件模板中关键内容替换
     * key：替换关键字
     * value：替换后的值
     */
    private Map<String,String> mailDescription;

    public void setUserLoginArray(String[] userLoginArray){
        if (userLoginArray != null){
            this.userLoginArray = userLoginArray.clone();
        }
    }

    public String[] getUserLoginArray(){
        if (this.userLoginArray != null){
            return this.userLoginArray.clone();
        }else{
            return null;
        }
    }
}
