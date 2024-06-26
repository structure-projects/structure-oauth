package cn.structured.oauth.sdk.configuration;

import cn.structured.oauth.sdk.client.AuthClient;
import cn.structured.oauth.sdk.service.IRemoteClientService;
import cn.structured.oauth.sdk.service.impl.RemoteClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 自动装配客户端配置
 *
 * @author chuck
 * @since JDK1.8
 */
@Configuration
@ComponentScan(basePackages = "cn.structured.oauth.sdk.**")
public class AutoClientConfiguration {

    @Resource
    private AuthClientConfig authClientConfig;

    @Bean
    @ConditionalOnClass(AuthClient.class)
    public AuthClient authClient() {
        return new AuthClient(authClientConfig);
    }

    @Bean
    @ConditionalOnClass(IRemoteClientService.class)
    public IRemoteClientService remoteClientService() {
        return new RemoteClientServiceImpl();
    }
}
