package com.btm.client.mail.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.Mail;
import com.btm.client.mail.minu.Client;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.xmlpull.v1.XmlPullParserException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 11:42
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("sendMail")
public class SendMailController {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired(required = false)
    private Client client;

    /**
     * 发送一条简单邮件
     * 仅包含收件人、邮件标题、邮件正文
     *
     * @return
     */
    @PostMapping("simple")
    public RestResult simple(Mail mail) {
        if (!mail.hasRecipient()) {
            log.trace("收件人不能为空");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        String[] recipients = new String[mail.getRecipients().size()];
        message.setFrom(from);
        message.setTo(mail.getRecipients().toArray(recipients));
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            mailSender.send(message);
            log.trace("简单邮件发送成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return RestResult.SUCCESS("简单邮件发送成功。", null);
    }

    /**
     * 发送带附件的邮件
     * 附件直接从minio客户端读取
     * 需要配置并提供基于minio的对象存储服务
     * {"attachements":{"文件1-id":"文件1-bucket","文件2-id":"文件2-bucket"},
     * "content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}
     *
     * @param mailStr
     * @return
     */
    @PostMapping("attachement/minio")
    public RestResult attachementfromMinio(@RequestBody String mailStr) {
        if (null == client){
            return RestResult.FAILURE("请添加minio依赖，及相关配置项。",null);
        }
        Mail mail = new Mail();
        mail = mail.jsonToMail(mailStr);
        if (!mail.hasRecipient()) {
            log.trace("收件人不能为空");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String[] recipients = new String[mail.getRecipients().size()];
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getRecipients().toArray(recipients));
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent());
            if (mail.hasAttachemet()) {
                for (Map.Entry<String, String> entry :
                        mail.getAttachements().entrySet()) {
                    InputStreamSource inputStreamSource = new InputStreamSource() {
                        @Override
                        public InputStream getInputStream() throws IOException {
                            InputStream inputStream = null;
                            try {
                                inputStream = client.getObject(entry.getValue(),entry.getKey());
                            } catch (InvalidBucketNameException e) {
                                e.printStackTrace();
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InsufficientDataException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (NoResponseException e) {
                                e.printStackTrace();
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            } catch (ErrorResponseException e) {
                                e.printStackTrace();
                            } catch (InternalException e) {
                                e.printStackTrace();
                            } catch (RegionConflictException e) {
                                e.printStackTrace();
                            } catch (InvalidArgumentException e) {
                                e.printStackTrace();
                            }
                            return inputStream;
                        }
                    };
                    helper.addAttachment(entry.getKey(),inputStreamSource);
                }
            }
            mailSender.send(message);
            log.trace("附件邮件发送成功");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return RestResult.SUCCESS("附件邮件发送成功。",null);
    }
}
