package com.btm.client.mail.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/19 16:05
 * @Version: 1.0
 */
@Data
@ConfigurationProperties("cmail.attachment.access")
public class AttachmentAccessConfig {
    /**
     * 附件获取地址
     */
    private String address;
}
