package cn.structure.starter.oauth.configuration;

import cn.structure.starter.oauth.enums.VerificationCodeType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Oauth相关配置
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 11:12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "structure.oauth")
public class OauthProperties {


    /**
     * 是否开启验证码校验
     */
    private Boolean isCheckVerificationCode;

    /**
     * 是否开启验证码限制
     */
    private Boolean isVerificationCode;

    /**
     * 验证码存储方式
     */
    private VerificationCodeType verificationCodeType;

    /**
     * clientId
     */
    private String clientId = "clientId";

    /**
     * clientSecret
     */
    private String clientSecret = "clientSecret";

    /**
     * grantTypes
     */
    private List<String> grantTypes = new ArrayList<>(Arrays.asList("password"));

    /**
     * 作用域
     */
    private String scopes = "all";

    /**
     * 有效分钟
     */
    private Integer validitySeconds = 3600;

    /**
     * oauth 地址
     */
    private String oauthHost = "http://localhost:8080/oauth/token";

    /**
     * 是否开启用户注册
     */
    private Boolean isUserRegister = Boolean.FALSE;

    /**
     * 是否开启管理人员审核注册
     */
    private Boolean isAdminCheckRegister = Boolean.TRUE;
}
