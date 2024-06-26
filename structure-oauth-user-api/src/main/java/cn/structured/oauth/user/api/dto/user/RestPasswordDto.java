package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 重置密码
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class RestPasswordDto {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 密码
     */
    private String password;

}
