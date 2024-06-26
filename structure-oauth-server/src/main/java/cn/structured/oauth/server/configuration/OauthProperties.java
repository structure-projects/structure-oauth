package cn.structured.oauth.server.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

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
@ConfigurationProperties(prefix = "structure.oauth")
@AutoConfigureBefore(OauthJwtProperties.class)
public class OauthProperties {

    /**
     * 适配器
     */
    private Map<String, AdapterProperties> adapters;

}
