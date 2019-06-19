package com.btm.client.mail.service.impl.mail;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btm.client.mail.entity.CmiContent;
import com.btm.client.mail.mapper.CmiContentMapper;
import com.btm.client.mail.service.mail.CmiContentService;
import org.springframework.stereotype.Service;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:18
 * @Version: 1.0
 */
@Service("cmiContentService")
public class CmiContentServiceImpl extends ServiceImpl<CmiContentMapper,CmiContent> implements CmiContentService {
}
