package cn.structured.oauth.server.configuration;

import cn.structure.starter.oauth.common.configuration.StructureAccessTokenConverter;
import cn.structured.oauth.api.adapter.IPlatformAuthenticationAdapter;
import cn.structured.oauth.server.granter.AuthenticationAdapterFactory;
import cn.structured.oauth.server.granter.PlatformAuthenticationManager;
import cn.structured.oauth.server.service.impl.RedisAuthorizationCodeServices;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 自动装配
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/7 20:24
 */
@EnableFeignClients(basePackages = "cn.structured.oauth.server.client.**")
@Configuration
@AutoConfigureBefore(OauthProperties.class)
@ComponentScan(basePackages = "cn.structured.oauth.server.**")
public class AutoAuthorizationConfiguration {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private OauthJwtProperties jwtProperties;

    @Resource
    private OauthProperties oauthProperties;

    @PostConstruct
    public void initAuthorizationAdapter() {
        //通过oauthProperties 对适配器进行初始化
        Map<String, AdapterProperties> adapters = oauthProperties.getAdapters();
        adapters.forEach((k, v) -> {
                    JSONObject parse = JSON.parseObject(JSON.toJSONString(v));
                    IPlatformAuthenticationAdapter authenticationAdapter = AuthenticationAdapterFactory.newInstance(k, parse);
                    PlatformAuthenticationManager.put(k, authenticationAdapter);
                }
        );
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(TokenStore.class)
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new RedisAuthorizationCodeServices();
    }

    @Bean
    @ConditionalOnMissingBean(AccessTokenConverter.class)
    public AccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置加密
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(jwtProperties.getJksPath()), jwtProperties.getPassword().toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPair()));
        jwtAccessTokenConverter.setAccessTokenConverter(new StructureAccessTokenConverter());
        return jwtAccessTokenConverter;
    }

}
