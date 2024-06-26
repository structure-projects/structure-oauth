package cn.structured.oauth.server.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * create by chuck 2024/6/5
 *
 * @author chuck
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "structure.oauth.jwt")
public class OauthJwtProperties {

    /**
     * 非对称加密的密钥路径
     */
    private String jksPath;

    /**
     * 加载JKS的密码
     */
    private String password;

    /**
     * JKS 私钥KEY
     */
    private String keyPair;
}
