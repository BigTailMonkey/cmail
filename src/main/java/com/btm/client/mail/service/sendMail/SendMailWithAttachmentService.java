package com.btm.client.mail.service.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.dao.Mail;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 14:37
 * @Version: 1.0
 */
public interface SendMailWithAttachmentService {

    /**
     * 给所有收件人群发一份带有附件的邮件
     * 附件内容从minio中获取
     * 需要提供minio客户端
     *
     * @param mail
     * @return
     */
    RestResult attachmentfromMinioGroup(Mail mail);

    /**
     * 给所有收件人群发一份带有附件的邮件
     * 附件内容从web接口中获取
     * 需要提供web接口地址
     *
     * @param mail
     * @return
     */
    RestResult attachmentFromServiceGroup(Mail mail);
}
