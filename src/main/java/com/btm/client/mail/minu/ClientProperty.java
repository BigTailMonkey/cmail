package com.btm.client.mail.minu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 17:22
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties("cmail.minio")
public class ClientProperty {

    private String url;
    private String userName;
    private String password;
}
