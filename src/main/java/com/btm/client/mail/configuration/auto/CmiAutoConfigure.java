package com.btm.client.mail.configuration.auto;

import com.btm.client.mail.configuration.BaseConfig;
import com.btm.client.mail.configuration.MailServiceConfig;
import com.btm.client.mail.entity.dao.CmiContent;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/17 10:00
 * @Version: 1.0
 */
@Slf4j
@Configuration
@ConditionalOnClass(CmiContent.class)
@EnableConfigurationProperties({BaseConfig.class, MailServiceConfig.class})
@MapperScan({"com.btm.client.mail.mapper"})
@ComponentScan({"com.btm.client.mail"})
public class CmiAutoConfigure {

    static {
        log.info("start auto configure cmi.");
    }

}
