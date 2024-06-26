package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户详情DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class UserDetailDto {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
