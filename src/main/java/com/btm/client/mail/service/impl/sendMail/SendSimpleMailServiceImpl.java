package com.btm.client.mail.service.impl.sendMail;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.dao.CmiContent;
import com.btm.client.mail.entity.dao.CmiUserMailInfo;
import com.btm.client.mail.entity.dao.Mail;
import com.btm.client.mail.entity.dto.IntelligenceMailDTO;
import com.btm.client.mail.service.mail.CmiContentService;
import com.btm.client.mail.service.mail.CmiUserMailInfoService;
import com.btm.client.mail.service.sendMail.SendSimpleMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private CmiUserMailInfoService cmiUserMailInfoService;
    @Autowired
    private CmiContentService cmiContentService;

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
    public RestResult sendMailSingleton(Mail mail) {
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

    @Override
    public RestResult placeHolderGroupSendMail(IntelligenceMailDTO intelligenceMailDTO){
        CmiContent cmiContent = cmiContentService.getById(intelligenceMailDTO.getMailId());
        if (null == cmiContent){
            return RestResult.FAILURE("邮件ID错误，未查询到邮件",null);
        }
        cmiContent.replaceDescriptionPlaceHolder(intelligenceMailDTO.getMailDescription());
        List<CmiUserMailInfo> cmiUserMailInfoList = cmiUserMailInfoService.getUserInfoByUserIdBatch(intelligenceMailDTO.getUserLoginArray());
        List<String> mailAddressList = cmiUserMailInfoList.stream().map(CmiUserMailInfo::getUserMailAddress).collect(Collectors.toList());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String[] mailAddressArray = new String[mailAddressList.size()];
        simpleMailMessage.setTo(mailAddressList.toArray(mailAddressArray));
        simpleMailMessage.setSubject(cmiContent.getContentTitle());
        simpleMailMessage.setText(cmiContent.getContentDescription());
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
        if (mailAddressArray.length != intelligenceMailDTO.getUserLoginArray().length){
            List<String> parameterUserIdList = Arrays.asList(intelligenceMailDTO.getUserLoginArray());
            List<String> sendUserIdList = cmiUserMailInfoList.stream().map(CmiUserMailInfo::getUserLoginName).collect(Collectors.toList());
            parameterUserIdList.removeAll(sendUserIdList);
            return RestResult.SUCCESS("发送成功，但部分账号未找到用户邮箱地址。",parameterUserIdList);
        }else {
            return RestResult.SUCCESS("发送成功", null);
        }
    }
}
