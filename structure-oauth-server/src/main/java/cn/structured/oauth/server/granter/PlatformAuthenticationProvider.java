package cn.structured.oauth.server.granter;

import cn.structure.common.constant.SymbolConstant;
import cn.structure.common.exception.CommonException;
import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import cn.structured.oauth.api.enums.PlatformCodeEnum;
import cn.structured.oauth.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cn.structured.oauth.api.enums.VerificationCodeType.LOGIN;

/**
 * 平台认证的提供者
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Component
public class PlatformAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PlatformAuthenticationToken platformAuthenticationToken = (PlatformAuthenticationToken) authentication;
        String type = platformAuthenticationToken.getType();
        String platformUserId = authentication.getPrincipal().toString();
        String platformCode = authentication.getCredentials().toString();
        Object details = authentication.getDetails();
        log.info("details -> {}",details);
        //手机号+密码 邮箱+密码 用户名+密码

        //验证验证码
        if (type.equals(PlatformCodeEnum.PHONE.getCode()) || type.equals(PlatformCodeEnum.EMAIL.getCode())) {
            String redisKey = type + SymbolConstant.COLON + LOGIN + SymbolConstant.COLON + platformUserId;
            BoundValueOperations<String, String> codeKey = redisTemplate.boundValueOps(redisKey);
            String code = codeKey.get();
            redisTemplate.delete(redisKey);
            //在研发环境下是不校验code的
            if (!"dev".equals(active) && !platformCode.equals(code)) {
                throw new CommonException("", "验证失败");
            }
        } else {
            //其他平台认证适配
            platformUserId = PlatformAuthenticationManager.authentication(type, platformCode);
        }
        //查询用户信息
        UserDetails userDetails = userService.loadUserByPlatformUserIdAndPlatformCode(platformUserId, type);
        StructureAuthUser user = (StructureAuthUser) userDetails;

        // 返回一个完全填充的Authentication对象
        return new PlatformAuthenticationToken(type, userDetails, platformUserId, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(PlatformAuthenticationToken.class);
    }
}
