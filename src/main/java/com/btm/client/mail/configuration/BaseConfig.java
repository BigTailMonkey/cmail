package com.btm.client.mail.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 9:55
 * @Version: 1.0
 */
@Data
@ConfigurationProperties("cmi.mail.send")
public class BaseConfig {

    private String sendUserName;

}
