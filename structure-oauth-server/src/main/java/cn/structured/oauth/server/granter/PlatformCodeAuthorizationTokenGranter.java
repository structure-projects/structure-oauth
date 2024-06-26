package cn.structured.oauth.server.granter;

import cn.structured.oauth.api.enums.PlatformCodeEnum;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 平台code码认证
 *
 * @author chuck
 * @since JDK1.8
 */
public class PlatformCodeAuthorizationTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "platform_grant";
    private final AuthenticationManager authenticationManager;

    public PlatformCodeAuthorizationTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    protected PlatformCodeAuthorizationTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        String type = parameters.get("type");
        String platform_id = parameters.get("platform_id");
        String code = parameters.get("code");
        //判断类型是否为空
        if (type == null) {
            throw new InvalidRequestException("An type must be supplied.");
        }
        //判断code是否为空
        if (code == null) {
            throw new InvalidRequestException("An code must be supplied.");
        }
        //判断是否为手机号
        if (type.equals(PlatformCodeEnum.PHONE.getCode()) || type.equals(PlatformCodeEnum.EMAIL.getCode())) {
            if (platform_id == null) {
                throw new InvalidRequestException("An platform_id must be supplied.");
            }
        }
        //第三方平台 如微信，企业微信，钉钉（）
        Authentication userAuth = new PlatformAuthenticationToken(type, platform_id, code);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        try {
            userAuth = this.authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException var8) {
            throw new InvalidGrantException(var8.getMessage());
        }

        if (userAuth != null && userAuth.isAuthenticated()) {
            OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException("Could not authenticate");
        }
    }
}
