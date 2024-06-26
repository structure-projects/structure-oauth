package cn.structured.oauth.server.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis存储授权码
 *
 * @author chuck
 * @since JDK1.8
 */
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    @Resource
    private RedisTemplate<String, OAuth2Authentication> redisTemplate;

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        redisTemplate.boundValueOps(code).set(oAuth2Authentication, 10, TimeUnit.MINUTES);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        OAuth2Authentication auth = redisTemplate.boundValueOps(code).get();
        redisTemplate.delete(code);
        return auth;
    }
}
