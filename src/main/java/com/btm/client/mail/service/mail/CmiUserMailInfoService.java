package com.btm.client.mail.service.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btm.client.mail.entity.dao.CmiUserMailInfo;

import java.util.List;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/24 17:31
 * @Version: 1.0
 */
public interface CmiUserMailInfoService extends IService<CmiUserMailInfo> {

    /**
     *  使用USER_ID批量查询用户邮箱地址
     *
     * @param userIdArray
     * @return
     */
    List<CmiUserMailInfo> getUserInfoByUserIdBatch(String[] userIdArray);

}
