package cn.structure.starter.oauth.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色用户权限关系类
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/1 23:25
 */
@Data
public class RoleAuthorityUser {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer authorityId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
