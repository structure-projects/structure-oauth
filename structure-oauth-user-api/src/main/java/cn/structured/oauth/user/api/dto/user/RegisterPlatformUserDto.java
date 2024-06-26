package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 注册平台用户
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class RegisterPlatformUserDto {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 平台用户ID
     */
    private String platformUserId;

    /**
     * 平台类型
     */
    private String type;


}
