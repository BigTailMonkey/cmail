package com.btm.client.mail.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:59
 * @Version: 1.0
 */
@Data
@ConfigurationProperties("cmi.mail.send")
public class MailServiceConfig {

    private String address;
    private String password;

}
