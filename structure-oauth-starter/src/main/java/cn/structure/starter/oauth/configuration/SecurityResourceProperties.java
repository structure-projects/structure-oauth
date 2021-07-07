package cn.structure.starter.oauth.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  structure配置
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/2 19:07
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "structure.security")
public class SecurityResourceProperties {
    private String clientId;

    private Map<String, List<String>> antMatchers;
}
