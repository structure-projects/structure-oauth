package cn.structured.oauth.user.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.oauth.user.entity.Role;

import java.util.List;

/**
 * 角色管理
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 启用
     *
     * @param roleId 角色ID
     */
    void enable(Long roleId);

    /**
     * 停用
     *
     * @param roleId 角色ID
     */
    void disable(Long roleId);

    /**
     * 获取角色的权限
     *
     * @param roleId 角色ID
     * @return 权限标识符 {@link List} {@link String}
     */
    List<String> getAuthorities(Long roleId);
}
