package cn.structure.starter.oauth.common.configuration;

import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 加入自定义用户属性到token
 *
 * @author chuck
 */
public class StructureAccessTokenConverter extends DefaultAccessTokenConverter {

    public StructureAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private static class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put(USERNAME, authentication.getName());
            StructureAuthUser user = (StructureAuthUser) authentication.getPrincipal();
            response.put("user_id", user.getId());
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            return response;
        }
    }
}
