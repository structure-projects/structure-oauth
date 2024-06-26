package cn.structured.oauth.starter.resource.configuration;

import cn.structure.common.constant.AuthConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * <p>
 * 自动装配
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/7 20:24
 */
@Configuration
@EnableConfigurationProperties({OauthResourceProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(value = {ResourceServerConfig.class})
public class AutoResourceConfiguration {


    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthExceptionEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    @Bean
    @ConditionalOnMissingBean(JwtAccessTokenConverter.class)
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        org.springframework.core.io.Resource resource = new ClassPathResource(AuthConstant.PUBLIC_CERT);
        String publicKey ;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        //读取自定义
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return converter;
    }

    @Bean
    @Qualifier("tokenStore")
    @ConditionalOnMissingBean(TokenStore.class)
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }
}
