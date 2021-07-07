package cn.structure.starter.oauth.configuration;

import cn.structure.starter.oauth.entity.UserAccount;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * TokenEnhancer
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/24 14:05
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserAccount user = (UserAccount) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user_id", user.getId());
        additionalInfo.put("head_portrait", user.getHeadPortrait());
        additionalInfo.put("nick_name", user.getNickName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
