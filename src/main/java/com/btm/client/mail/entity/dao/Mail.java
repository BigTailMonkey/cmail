package com.btm.client.mail.entity.dao;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 13:56
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable,Cloneable {

    private List<String> recipients;
    private String subject;
    private String content;
    private Map<String,String> attachments;

    /**
     * 验证是否有收件人
     * @return
     */
    public boolean hasRecipient(){
        if (null != recipients && 0<recipients.size()){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasAttachment(){
        if (null != attachments && 0<attachments.size()){
            return true;
        }else{
            return false;
        }
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

    public Mail jsonToMail(String json){
        return JSON.parseObject(json,Mail.class);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
