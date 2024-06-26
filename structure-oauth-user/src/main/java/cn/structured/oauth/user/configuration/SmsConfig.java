package cn.structured.oauth.user.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 短信服务配置
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "structure.user.sms")
public class SmsConfig {
    /**
     * 产品名称
     */
    private String product;
    /**
     * 是否启用短信验证
     */
    private Boolean enable = false;
    /**
     * 产品域名
     */
    private String domain;
    /**
     * accessKeyId
     */
    private String accessKeyId;
    /**
     * accessKeySecret
     */
    private String accessKeySecret;
    /**
     * 地区
     */
    private String regionId;

    /**
     * 端点名
     */
    private String endpointName;

    /**
     * 签名
     */
    private String signName;
}
