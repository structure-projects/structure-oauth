package cn.structured.oauth.user.configuration;

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
@ConfigurationProperties(prefix = "structure.user")
public class UserProperties {


    /**
     * 短信注册模板编码
     */
    private String smsRegisterTemplateCode;

    /**
     * 短信登录模板编码
     */
    private String smsLoginTemplateCode;

    /**
     * 重置密码
     */
    private String smsResetPasswordTemplateCode;

}
