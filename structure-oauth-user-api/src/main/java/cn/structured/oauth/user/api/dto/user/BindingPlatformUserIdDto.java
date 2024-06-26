package cn.structured.oauth.user.api.dto.user;

import lombok.Data;

/**
 * 绑定用户DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class BindingPlatformUserIdDto {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 平台用户ID
     */
    private String platformUserId;

    /**
     * 平台编码
     */
    private String platformTypeCode;

}
