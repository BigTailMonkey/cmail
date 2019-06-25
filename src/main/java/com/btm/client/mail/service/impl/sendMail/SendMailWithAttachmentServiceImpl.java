package com.btm.client.mail.service.impl.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.dao.Mail;
import com.btm.client.mail.minu.Client;
import com.btm.client.mail.service.sendMail.SendMailWithAttachmentService;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 14:37
 * @Version: 1.0
 */
@Slf4j
@Service("sendMailWithAttachmentService")
public class SendMailWithAttachmentServiceImpl implements SendMailWithAttachmentService {

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired(required = false)
    private Client client;

    /**
     * 给所有收件人群发一份带有附件的邮件
     *
     * @param mail
     * @return
     */
    @Override
    public RestResult attachmentfromMinioGroup(Mail mail) {
        if (null == client) {
            return RestResult.FAILURE("请添加minio依赖，及相关配置项。", null);
        }
        RestResult restResult = new RestResult();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String[] recipients = new String[mail.getRecipients().size()];
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getRecipients().toArray(recipients));
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent());
            if (mail.hasAttachment()) {
                for (Map.Entry<String, String> entry :
                        mail.getAttachments().entrySet()) {
                    InputStreamSource inputStreamSource = new InputStreamSource() {
                        @Override
                        public InputStream getInputStream() throws IOException {
                            InputStream inputStream = null;
                            try {
                                inputStream = client.getObject(entry.getValue(), entry.getKey());
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
                    helper.addAttachment(entry.getKey(), inputStreamSource);
                }
            }
            mailSender.send(message);
            log.info("附件邮件发送成功");
            restResult = RestResult.SUCCESS("邮件发送成功。", null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            restResult = RestResult.FAILURE("邮件发送失败",e.getMessage());
        }
            return restResult;
    }

    @Override
    public RestResult attachmentFromServiceGroup(Mail mail) {
        return RestResult.FAILURE("功能暂未开放。",null);
    }
}
