package cn.structured.oauth.starter.resource.configuration;

import cn.structure.common.constant.AuthConstant;
import cn.structure.common.constant.SymbolConstant;
import cn.structure.common.enums.NumberEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 资源服务配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private OauthResourceProperties properties;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AccessDeniedHandler accessDeniedHandler;
    @Resource
    private TokenStore tokenStore;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests();

        Map<String, List<String>> antMatchers = properties.getAntMatchers();
        if (antMatchers != null) {
            Set<String> keys = antMatchers.keySet();
            for (String key : keys) {
                if (key.equals(AuthConstant.UN_AUTHENTICATED)) {
                    List<String> urls = antMatchers.get(key);
                    for (String url : urls) {
                        http.csrf().disable().authorizeRequests().antMatchers(url).permitAll();
                    }
                } else {
                    String[] authUrlStr = key.split(SymbolConstant.MINUS);
                    if (authUrlStr.length < NumberEnum.TWO.getValue()) {
                        continue;
                    }
                    String type = authUrlStr[NumberEnum.ZERO.getValue()];
                    String str = authUrlStr[NumberEnum.ONE.getValue()];
                    List<String> urls = antMatchers.get(key);
                    if (type.equals(AuthConstant.ROLE)) {
                        for (String url : urls) {
                            http.csrf().disable().authorizeRequests().antMatchers(url).hasRole(str);
                        }
                    }
                    if (type.equals(AuthConstant.AUTH)) {
                        for (String url : urls) {
                            http.csrf().disable().authorizeRequests().antMatchers(url).hasAuthority(str);
                        }
                    }
                }
            }
        }
        //默认全部放行
        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(properties.getResourceId())
                .stateless(true).tokenStore(tokenStore)
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}