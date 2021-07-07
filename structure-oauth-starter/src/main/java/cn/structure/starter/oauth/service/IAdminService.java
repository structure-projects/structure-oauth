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
     * @param userId
     * @return
     */
    CurrentAuthorityVo findCurrentAuthorityByUserId(Integer userId);

    /**
     * 查询所有权限
     * @return
     */
    List<AuthorityVo> findAllAuthority();

    /**
     * 查询全部角色
     * @return
     */
    List<RoleVo> findAllRole();

    /**
     * 添加角色
     * @param addRoleVo
     * @return
     */
    Integer addRole(AddRoleVo addRoleVo);


    /**
     * 编辑角色
     * @param editRoleVo
     * @return
     */
    Integer editRole(EditRoleVo editRoleVo);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Integer deleteRole(Integer roleId);

    /**
     * 给用户授权
     * @param userAuthorizationVo
     * @return
     */
    Integer userAuthorization(UserAuthorizationVo userAuthorizationVo);

}
