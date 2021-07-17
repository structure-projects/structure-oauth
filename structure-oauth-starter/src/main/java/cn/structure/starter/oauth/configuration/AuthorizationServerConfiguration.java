package cn.structure.starter.oauth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import java.util.List;


/**
 * 配置授权中心信息
 * @author: CHUCK
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private AccessTokenConverter accessTokenConverter;

    @Resource
    private TokenEnhancer tokenEnhancer;

    @Resource
    private OauthProperties oauthProperties;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .userDetailsService(userDetailsService).tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(tokenEnhancer);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        List<String> grantTypes = oauthProperties.getGrantTypes();
        String [] grantType = grantTypes.toArray( new String[]{});
        clients.inMemory()
                .withClient(oauthProperties.getClientId())
                .secret(passwordEncoder.encode(oauthProperties.getClientSecret()))
                .authorizedGrantTypes(grantType)
                .scopes(oauthProperties.getScopes())
                .accessTokenValiditySeconds(oauthProperties.getValiditySeconds());
    }
}
