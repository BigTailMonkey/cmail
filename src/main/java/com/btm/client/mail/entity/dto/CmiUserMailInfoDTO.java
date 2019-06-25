package com.btm.client.mail.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class CmiUserMailInfoDTO implements Serializable {

    @TableId(type = IdType.AUTO)
    private String userLoginName;
    private String userName;
    private String userMailAddress;
}