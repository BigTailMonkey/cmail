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

    /**
     * 发送一条简单邮件
     * 群发
     * 仅包含收件人、邮件标题、邮件正文
     *
     * @return
     */
    @PostMapping("simple/group")
    public RestResult simpleGroup(Mail mail) {
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
     * 发送一条简单邮件
     * 单发
     * 仅包含收件人、邮件标题、邮件正文
     *
     * @param mail
     * @return
     */
    @PostMapping("simple/singleton")
    public RestResult simpleSimgleton(Mail mail){
        if (!mail.hasRecipient()) {
            log.trace("收件人不能为空");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mail.getRecipients().stream().forEach(to -> {
            message.setTo(to);
            mailSender.send(message);
        });
        return RestResult.SUCCESS("简单邮件发送成功。", null);
    }
}
