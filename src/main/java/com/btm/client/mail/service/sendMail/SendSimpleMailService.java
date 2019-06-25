package com.btm.client.mail.service.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.dao.Mail;
import com.btm.client.mail.entity.dto.IntelligenceMailDTO;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 14:36
 * @Version: 1.0
 */
public interface SendSimpleMailService {

    /**
     * 给所有收件人群发一份简单邮件
     *
     * @param mail
     * @return
     */
    RestResult sendMailGroup(Mail mail);

    /**
     * 给所有收件人群发一份简单邮件
     *
     * @param mail
     * @return
     */
    RestResult sendMailSingleton(Mail mail);

    /**
     * 使用数据库配置的邮件模板，发送邮件
     * 邮件内容中函数有{}包裹的占位符
     *
     * @param intelligenceMailDTO
     * @return
     */
    RestResult placeHolderGroupSendMail(IntelligenceMailDTO intelligenceMailDTO);
}
