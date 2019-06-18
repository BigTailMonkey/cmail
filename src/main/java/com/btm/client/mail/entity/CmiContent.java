package com.btm.client.mail.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:10
 * @Version: 1.0
 */

@Data
public class CmiContent {

    @TableId(type = IdType.AUTO)
    private Long contentId;
    private String contentName;
    private String contentDescription;
    private String contentGroupId;

}
