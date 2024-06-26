package cn.structure.starter.oauth.common.configuration;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * <p>
 * TokenEnhancer
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/24 14:05
 */
public class StructureTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        return accessToken;
    }
}
