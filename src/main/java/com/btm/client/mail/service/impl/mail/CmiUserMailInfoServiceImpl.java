package com.btm.client.mail.service.impl.mail;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btm.client.mail.entity.dao.CmiUserMailInfo;
import com.btm.client.mail.mapper.CmiUserMailInfoMapper;
import com.btm.client.mail.service.mail.CmiUserMailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/24 17:32
 * @Version: 1.0
 */
@Service("cmiUserMailInfoService")
public class CmiUserMailInfoServiceImpl extends ServiceImpl<CmiUserMailInfoMapper,CmiUserMailInfo> implements CmiUserMailInfoService{

    @Autowired
    private CmiUserMailInfoMapper cmiUserMailInfoMapper;

    @Override
    public List<CmiUserMailInfo> getUserInfoByUserIdBatch(String[] userIdArray) {
        return cmiUserMailInfoMapper.getUserInfoByUserIdBatch(userIdArray);
    }
}
