package cn.structured.oauth.server.configuration;

import cn.hutool.extra.spring.SpringUtil;
import cn.structured.oauth.server.granter.PlatformCodeAuthorizationTokenGranter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author 认证服务配置
 */
@Configuration
@EnableAuthorizationServer
@Import(SpringUtil.class)
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private AccessTokenConverter accessTokenConverter;

    @Resource
    private DataSource dataSource;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()") // oauth/token_key公开
                .checkTokenAccess("permitAll()") // oauth/check_token公开
                .allowFormAuthenticationForClients(); // 表单认证，申请令牌
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenGranter(tokenGranter(endpoints))
                .exceptionTranslator(new OauthWebResponseExceptionTranslator())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);

    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        // endpoints.getTokenGranter() 获取SpringSecurity OAuth2.0 现有的授权类型
        List<TokenGranter> granters = new ArrayList<TokenGranter>(Collections.singletonList(endpoints.getTokenGranter()));
        // 构建自定义授权类型
        PlatformCodeAuthorizationTokenGranter platformAuthorizationTokenGranter = new PlatformCodeAuthorizationTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());
        // 向集合中添加定义授权类型
        granters.add(platformAuthorizationTokenGranter);
        // 返回所有类型
        return new CompositeTokenGranter(granters);
    }

}