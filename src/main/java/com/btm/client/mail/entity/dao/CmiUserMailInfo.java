package com.btm.client.mail.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.btm.client.mail.entity.dto.CmiUserMailInfoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class CmiUserMailInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userLoginName;
    private String userName;
    private String userMailAddress;

    /**
     * 由CmiUserMailInfoDTO类型转为CmiUserMailInfo类型
     *
     * @param cmiUserMailInfoDTO
     * @return
     */
    public static CmiUserMailInfo createFromDAO(CmiUserMailInfoDTO cmiUserMailInfoDTO){
        CmiUserMailInfo cmiUserMailInfo = new CmiUserMailInfo();
        cmiUserMailInfo.setUserLoginName(cmiUserMailInfoDTO.getUserLoginName());
        cmiUserMailInfo.setUserMailAddress(cmiUserMailInfoDTO.getUserMailAddress());
        cmiUserMailInfo.setUserName(cmiUserMailInfoDTO.getUserName());
        return cmiUserMailInfo;
    }
}