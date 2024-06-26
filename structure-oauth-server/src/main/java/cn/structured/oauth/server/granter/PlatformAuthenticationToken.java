package cn.structured.oauth.server.granter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * create by chuck 2024/6/9
 *
 * @author chuck
 * @since JDK1.8
 */
public class PlatformAuthenticationToken extends AbstractAuthenticationToken {


    private final Object principal;

    private Object credentials;

    private String type;

    public PlatformAuthenticationToken(String type, Object principal, Object credentials) {
        super(null);
        //类型
        this.type = type;
        //平台ID
        this.principal = principal;
        //code
        this.credentials = credentials;
        //设置没有认证
        setAuthenticated(false);
    }

    public PlatformAuthenticationToken(String type, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.type = type;
        //认证后是USER_ID
        this.principal = principal;
        //平台用户ID
        this.credentials = credentials;
        // must use super, as we override
        //设置已经认证
        super.setAuthenticated(true);
    }

    public String getType() {
        return this.type;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
