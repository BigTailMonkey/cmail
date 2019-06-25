package com.btm.client.mail.controller;

import com.btm.client.mail.common.RestResult;
import com.btm.client.mail.entity.dao.Mail;
import com.btm.client.mail.entity.dto.IntelligenceMailDTO;
import com.btm.client.mail.service.sendMail.SendSimpleMailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SendSimpleMailService sendSimpleMailService;

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
            log.error("收件人不能为空");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        return sendSimpleMailService.sendMailGroup(mail);
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
            log.error("收件人不能为空");
            return RestResult.FAILURE("收件人不能为空。", null);
        }
        return sendSimpleMailService.sendMailSingleton(mail);
    }

    /**
     * 接收用户登录ID列表，自动匹配用户邮箱地址
     * 使用占位符，替换邮件内容
     * 群发邮件
     *
     * @param intelligenceMailDTO
     * @return
     */
    @PostMapping("noAuthentication/placeholder/group")
    public RestResult placeHolderGroupSendMail(IntelligenceMailDTO intelligenceMailDTO) {
        if (null != intelligenceMailDTO &&
                null != intelligenceMailDTO.getMailDescription() &&
                0 < intelligenceMailDTO.getMailDescription().size() &&
                null != intelligenceMailDTO.getUserLoginArray() &&
                0 < intelligenceMailDTO.getUserLoginArray().length &&
                StringUtils.isNotBlank(intelligenceMailDTO.getMailId())) {
            return sendSimpleMailService.placeHolderGroupSendMail(intelligenceMailDTO);
        } else {
            return RestResult.FAILURE("参数不完备。", intelligenceMailDTO);
        }
    }
}
