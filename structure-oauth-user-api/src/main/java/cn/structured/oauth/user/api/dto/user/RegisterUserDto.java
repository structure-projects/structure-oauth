package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 注册用户DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class RegisterUserDto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 类型
     */
    private String type;

    /**
     * 验证码
     */
    private String code;

}
