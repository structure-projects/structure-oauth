package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 变更用户DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class ChangeUserDto {

    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

}
