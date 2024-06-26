package cn.structured.oauth.sdk.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 认证服务client请求
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@Configuration
@ConfigurationProperties(value = "structured.oauth.client")
public class AuthClientConfig {

    /**
     * host
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * scheme
     */
    private String scheme;

    /**
     * ID
     */
    private String clientId;

    /**
     * 秘钥
     */
    private String clientSecret;
    

}
