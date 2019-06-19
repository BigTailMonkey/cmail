package com.btm.client.mail.service.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.Mail;

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
    RestResult sendMailSinleton(Mail mail);
}
