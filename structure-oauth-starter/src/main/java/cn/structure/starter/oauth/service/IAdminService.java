package cn.structure.starter.oauth.service;


import cn.structure.starter.oauth.vo.admin.AuthorityVo;
import cn.structure.starter.oauth.vo.admin.UserAuthorizationVo;
import cn.structure.starter.oauth.vo.login.CurrentAuthorityVo;
import cn.structure.starter.oauth.vo.role.AddRoleVo;
import cn.structure.starter.oauth.vo.role.EditRoleVo;
import cn.structure.starter.oauth.vo.role.RoleVo;

import java.util.List;

/**
 * <p>
 * 管理service
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/6 22:03
 */
public interface IAdminService {

    /**
     * 通过用户ID查询用户权限
     */
    CurrentAuthorityVo findCurrentAuthorityByUserId(Integer userId);

    /**
     * 查询所有权限
     */
    List<AuthorityVo> findAllAuthority();

    /**
     * 查询全部角色
     */
    List<RoleVo> findAllRole();

    /**
     * 添加角色
     */
    Integer addRole(AddRoleVo addRoleVo);


    /**
     * 编辑角色
     */
    Integer editRole(EditRoleVo editRoleVo);

    /**
     * 删除角色
     */
    Integer deleteRole(Integer roleId);

    /**
     * 给用户授权
     */
    Integer userAuthorization(UserAuthorizationVo userAuthorizationVo);

}
