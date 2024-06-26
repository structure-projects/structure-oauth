package cn.structured.oauth.server.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户Service
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IUserService{


    /**
     * 通过绑定的平台用户ID和平台类型查询用户信息
     *
     * @param platformUserId 平台用户ID
     * @param platformCode   平台编码
     * @return 用户信息 {@link UserDetails}
     */
    UserDetails loadUserByPlatformUserIdAndPlatformCode(String platformUserId, String platformCode);

}
