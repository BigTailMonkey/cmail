package com.btm.client.mail.minu;

import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * s3 minu客户端
 */
@Slf4j
@Component
@ConditionalOnBean(ClientProperty.class)
@ConditionalOnClass(MinioClient.class)
public class Client {

    private MinioClient minioClient;

    @Bean
    @ConditionalOnClass(MinioClient.class)
    public MinioClient getClient(ClientProperty clientProperty) {
        try {
            log.info("初始化 Minio Client ...");
            if (StringUtils.isBlank(clientProperty.getUrl()) ||
                    StringUtils.isBlank(clientProperty.getUserName()) ||
                    StringUtils.isBlank(clientProperty.getPassword())){
                throw new IllegalArgumentException("客户端配置信息未配置或配置不正确。");
            }
            minioClient = new MinioClient(clientProperty.getUrl(), clientProperty.getUserName(), clientProperty.getPassword());
            log.info("初始化 Minio Client 完成。");
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            log.error(e.getMessage(),e);
        }
        return minioClient;
    }

    /**
     * 获得minio客户端
     *
     * @return
     */
    public MinioClient getMinioClient() {
        return minioClient;
    }

    /**
     * 以流的形式获得文件
     * @param bucketName
     * @param objectName
     * @return
     * @throws IOException
     * @throws InvalidBucketNameException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidKeyException
     * @throws IOException
     * @throws NoResponseException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     * @throws InternalException
     * @throws RegionConflictException
     * @throws InvalidArgumentException
     */
    public InputStream getObject(String bucketName, String objectName) throws IOException,
            InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, InvalidKeyException, IOException,
            NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, RegionConflictException, InvalidArgumentException {
        return minioClient.getObject(bucketName, objectName);
    }

}
