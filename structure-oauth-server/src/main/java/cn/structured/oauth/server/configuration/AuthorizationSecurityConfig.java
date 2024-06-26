package cn.structured.oauth.server.configuration;


import cn.structured.oauth.server.granter.PasswordAuthenticationProvider;
import cn.structured.oauth.server.granter.PlatformAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
public class AuthorizationSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        PlatformAuthenticationProvider platformAuthenticationProvider = context.getBean(PlatformAuthenticationProvider.class);
        PasswordAuthenticationProvider passwordAuthenticationProvider = context.getBean(PasswordAuthenticationProvider.class);
        //链式配置拦截策略
        http.csrf().disable()//关闭csrf跨域检查
                .authorizeRequests()
                .antMatchers("/auth-code/**").permitAll()
                .anyRequest().authenticated() //其他请求需要登录
                .and() //并行条件
                .authenticationProvider(platformAuthenticationProvider)
                .authenticationProvider(passwordAuthenticationProvider)
                .formLogin();
    }

    /**
     * 授权中心管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
