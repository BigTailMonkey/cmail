package com.btm.client.mail.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 11:34
 * @Version: 1.0
 */
@Data
public class RestResult {

    /**
     * 结果消息码
     */
    private Integer code;
    /**
     * 结果消息内容
     */
    private String message;
    /**
     * 结果消息体
     */
    private Object object;

    /**
     * 默认成功对象
     * @param message
     * @param o
     * @return
     */
    public static RestResult SUCCESS(String message,Object o){
        RestResult restResult = new RestResult();
        restResult.setCode(200);
        if(StringUtils.isNotBlank(message)){
            restResult.setMessage(message);
        }
        if (null != o){
            restResult.setObject(o);
        }
        return restResult;
    }

    /**
     * 默认失败对象
     * @param message
     * @param o
     * @return
     */
    public static RestResult FAILURE(String message,Object o){
        RestResult restResult = new RestResult();
        restResult.setCode(400);
        if(StringUtils.isNotBlank(message)){
            restResult.setMessage(message);
        }
        if (null != o){
            restResult.setObject(o);
        }
        return restResult;
    }

}
