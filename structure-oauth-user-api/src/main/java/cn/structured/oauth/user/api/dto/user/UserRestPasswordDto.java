package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 重置密码
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class UserRestPasswordDto {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 通过手机号验证
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 密码
     */
    private String password;

}
