package cn.structure.starter.oauth.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Security 核心配置类
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/3/10 22:20
 */
@Slf4j
@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 拦截所有请求,使用httpBasic方式登陆
     * @param http
     * @throws Exception
     */
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("HttpSecurity");
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req,resp, authException) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token","/login").permitAll()
                .antMatchers("/**").authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and()
                .userDetailsService(userDetailsService)
                .httpBasic();
    }

    /**
     * 授权中心管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

}
