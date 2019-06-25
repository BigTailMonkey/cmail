package com.btm.client.mail.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:10
 * @Version: 1.0
 */

@Data
@ToString
@EqualsAndHashCode
public class CmiContent {

    @TableId(type = IdType.AUTO)
    private Long contentId;
    private String contentName;
    private String contentDescription;
    private String contentGroupId;
    private String contentTitle;

    /**
     * 将邮件正文中的占位符替换为实际内容
     * @param map
     */
    public void replaceDescriptionPlaceHolder(Map<String,String> map){
        if (null != map){
            for (Map.Entry<String,String> entry : map.entrySet()){
                this.contentDescription = this.contentDescription.replace("{"+entry.getKey()+"}",entry.getValue());
            }
        }
    }

}
