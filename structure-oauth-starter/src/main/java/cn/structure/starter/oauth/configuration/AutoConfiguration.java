package cn.structure.starter.oauth.configuration;

import cn.structure.common.utils.IResultUtil;
import cn.structure.common.utils.ResultUtilSecondLevelImpl;
import cn.structure.starter.oauth.common.configuration.StructureAccessDeniedHandler;
import cn.structure.starter.oauth.common.configuration.StructureAccessTokenConverter;
import cn.structure.starter.oauth.common.configuration.StructureAuthenticationEntryPoint;
import cn.structure.starter.oauth.common.configuration.StructureTokenEnhancer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * <p>
 * 自动装配
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/7 20:24
 */
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new StructureAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler accessDeniedHandler() {
        return new StructureAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(IResultUtil.class)
    public IResultUtil resultUtil() {
        return new ResultUtilSecondLevelImpl();
    }

    @Bean
    @ConditionalOnMissingBean(TokenEnhancer.class)
    public TokenEnhancer tokenEnhancer() {
        return new StructureTokenEnhancer();
    }

    @Bean
    @ConditionalOnMissingBean(AccessTokenConverter.class)
    @ConditionalOnBean(UserDetailsService.class)
    public AccessTokenConverter accessTokenConverter() {
        return new StructureAccessTokenConverter();
    }
}
