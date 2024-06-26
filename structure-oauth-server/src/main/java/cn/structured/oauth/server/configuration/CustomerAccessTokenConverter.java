package cn.structured.oauth.server.configuration;

import cn.structure.common.constant.AuthConstant;
import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 加入自定义用户属性到token
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

    public CustomerAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            Map<String, Object> response = new LinkedHashMap<String, Object>();
            Object principal = authentication.getPrincipal();
            response.put(USERNAME, authentication.getName());
            StructureAuthUser user = (StructureAuthUser) principal;
            response.put(AuthConstant.USER_ID, user.getId());
            //添加权限信息
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            return response;
        }
    }
}
