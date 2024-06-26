package cn.structured.oauth.server.granter;

import cn.structure.common.exception.CommonException;
import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户名密码提供者
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        Object details = authentication.getDetails();
        log.info("details -> {}", details);
        //查询用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
        if (!matches) {
            //todo 密码错误
            throw new CommonException("", "密码错误");
        }
        StructureAuthUser user = (StructureAuthUser) userDetails;

        // 返回一个完全填充的Authentication对象
        return new UsernamePasswordAuthenticationToken(userDetails, user.getId(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
}
