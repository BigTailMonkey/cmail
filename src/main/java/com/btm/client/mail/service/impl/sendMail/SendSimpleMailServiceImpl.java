package com.btm.client.mail.service.impl.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.Mail;
import com.btm.client.mail.service.sendMail.SendSimpleMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 14:34
 * @Version: 1.0
 */
@Slf4j
@Service("sendMailService")
public class SendSimpleMailServiceImpl implements SendSimpleMailService {

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public RestResult sendMailGroup(Mail mail) {
        RestResult restResult;
        SimpleMailMessage message = new SimpleMailMessage();
        String[] recipients = new String[mail.getRecipients().size()];
        message.setFrom(from);
        message.setTo(mail.getRecipients().toArray(recipients));
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            mailSender.send(message);
            log.info("简单邮件发送成功");
            restResult = RestResult.SUCCESS("简单邮件发送成功。",null);
        } catch (Exception e) {
            log.error("简单邮件发送失败："+e.getMessage(), e);
            restResult = RestResult.FAILURE("简单邮件发送失败："+e.getMessage(),null);
        }
        return restResult;
    }

    @Override
    public RestResult sendMailSinleton(Mail mail) {
        RestResult restResult;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            for (String recipient : mail.getRecipients()) {
                message.setTo(recipient);
                mailSender.send(message);
            }
            log.info("简单邮件发送成功");
            restResult = RestResult.SUCCESS("简单邮件发送成功。",null);
        } catch (Exception e) {
            log.error("简单邮件发送失败："+e.getMessage(), e);
            restResult = RestResult.FAILURE("简单邮件发送失败："+e.getMessage(),null);
        }
        return restResult;
    }
}
