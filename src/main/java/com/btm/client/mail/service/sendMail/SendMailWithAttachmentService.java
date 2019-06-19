package com.btm.client.mail.service.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.Mail;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 14:37
 * @Version: 1.0
 */
public interface SendMailWithAttachmentService {

    /**
     * 给所有收件人群发一份带有附件的邮件
     *
     * @param mail
     * @return
     */
    RestResult sendAllRecipient(Mail mail);
}
