package cn.structured.oauth.user.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.oauth.user.api.dto.user.BindingPlatformUserIdDto;
import cn.structured.oauth.user.api.dto.user.RegisterPlatformUserDto;
import cn.structured.oauth.user.api.dto.user.RegisterUserDto;
import cn.structured.oauth.user.entity.User;

/**
 * 用户Service
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IUserService extends IBaseService<User> {


    /**
     * 通过绑定的平台用户ID和平台类型查询用户信息
     *
     * @param username 用户名
     * @return 用户信息 {@link User}
     */
    User loadUserByUserName(String username);

    /**
     * 通过绑定的平台用户ID和平台类型查询用户信息
     *
     * @param platformUserId 平台用户ID
     * @param platformCode   平台编码
     * @return 用户信息 {@link User}
     */
    User loadUserByPlatformUserIdAndPlatformCode(String platformUserId, String platformCode);

    /**
     * 注册用户
     *
     * @param registerUserDto 注册用户
     * @return 返回用户ID {@link Long}
     */
    Long registerUser(RegisterUserDto registerUserDto);


    /**
     * 更改密码
     *
     * @param userId      用户ID
     * @param oldPassword 旧的密码
     * @param newPassword 新的密码
     */
    void changePassword(Long userId, String oldPassword, String newPassword);

    /**
     * 重置密码
     *
     * @param userId      用户ID
     * @param newPassword 密码
     */
    void resetPassword(Long userId, String newPassword);


    /**
     * 启用
     *
     * @param userId 用户ID
     */
    void enable(Long userId);

    /**
     * 停用
     *
     * @param userId 用户ID
     */
    void disable(Long userId);

    /**
     * 锁定用户
     *
     * @param userId 用户ID
     */
    void lock(Long userId);

    /**
     * 解锁用户
     *
     * @param userId 用户ID
     */
    void unlock(Long userId);

    /**
     * 绑定平台用户
     *
     * @param bindingPlatformUserIdDto 绑定平台用户
     */
    void bindingPlatformUser(BindingPlatformUserIdDto bindingPlatformUserIdDto);

    /**
     * 注册平台用户
     *
     * @param registerPlatformUserDto 平台用户DTO
     * @return 用户ID {@link Long}
     */
    Long registerPlatformUser(RegisterPlatformUserDto registerPlatformUserDto);

}
