package cn.structure.starter.oauth.common.configuration;

import cn.structure.common.constant.AuthConstant;
import cn.structure.common.constant.SymbolConstant;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * AccessToken 转换
 * </p>
 *
 * @author chuck
 */
public class StructureResourceAccessTokenConverter extends DefaultAccessTokenConverter {


    public StructureResourceAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }


    private static class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Authentication extractAuthentication(Map<String, ?> map) {
            Collection<? extends GrantedAuthority> authorities = this.getAuthorities(map);
            return new UsernamePasswordAuthenticationToken(map, SymbolConstant.N_A, authorities);
        }

        private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
            if (!map.containsKey(AuthConstant.AUTHORITIES)) {
                //参数不包含任何权限存入1长度的权限数组标识单纯的普通用户
                return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(new String[]{"ONLY_USER"}));
            } else {
                Object authorities = map.get(AuthConstant.AUTHORITIES);
                if (authorities instanceof String) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
                } else if (authorities instanceof Collection) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection) authorities));
                } else {
                    throw new IllegalArgumentException("Authorities must be either a String or a Collection");
                }
            }
        }

    }

}
