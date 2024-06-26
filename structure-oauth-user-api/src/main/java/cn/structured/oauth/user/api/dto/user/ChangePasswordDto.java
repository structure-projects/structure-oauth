package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 更改密码DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class ChangePasswordDto {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 旧的密码
     */
    private String oldPassword;

    /**
     * 新的密码
     */
    private String newPassword;


}
