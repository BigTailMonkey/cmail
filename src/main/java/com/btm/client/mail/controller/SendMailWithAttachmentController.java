package com.btm.client.mail.controller;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.Mail;
import com.btm.client.mail.service.sendMail.SendMailWithAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 9:48
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("sendMail/attachment")
public class SendMailWithAttachmentController {

    @Autowired
    private SendMailWithAttachmentService sendMailWithAttachmentService;


    /**
     * 发送带附件的邮件
     * 多收件人时群发
     * 附件直接从minio客户端读取
     * 需要配置并提供基于minio的对象存储服务
     * {"attachements":{"文件1-id":"文件1-bucket","文件2-id":"文件2-bucket"},
     * "content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}
     *
     * @param mailStr
     * @return
     */
    @PostMapping("minio/group")
    public RestResult attachmentfromMinioGroup(@RequestBody String mailStr) {
        Mail mail = new Mail();
        mail = mail.jsonToMail(mailStr);
        if (!mail.hasRecipient()) {
            log.error("收件人不能为空");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        return sendMailWithAttachmentService.sendAllRecipient(mail);
    }
    /**
     * 发送带附件的邮件
     * 多收件人时单发
     * 附件直接从minio客户端读取
     * 需要配置并提供基于minio的对象存储服务
     * {"attachements":{"文件1-id":"文件1-bucket","文件2-id":"文件2-bucket"},
     * "content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}
     *
     * @param mailStr
     * @return
     */
    @PostMapping("minio/singleton")
    public RestResult attachmentfromMinioSingleton(@RequestBody String mailStr) {
        Mail mail = new Mail();
        mail = mail.jsonToMail(mailStr);
        if (!mail.hasRecipient()) {
            log.error("收件人不能为空。");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        List<String> recipients = mail.getRecipients();
        List<RestResult> result = new ArrayList<>(recipients.size());
        for (String recipient : recipients){
            List<String> oneRecipient = new ArrayList<>(1);
            oneRecipient.add(recipient);
            mail.setRecipients(oneRecipient);
            result.add(sendMailWithAttachmentService.sendAllRecipient(mail));
        }
        return RestResult.SUCCESS("发送完成。",result);
    }
}
