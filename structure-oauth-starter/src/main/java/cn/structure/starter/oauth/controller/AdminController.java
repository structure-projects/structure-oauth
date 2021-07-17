package cn.structure.starter.oauth.controller;

import cn.structure.common.entity.ResResultVO;
import cn.structure.starter.oauth.service.IAdminService;
import cn.structure.starter.oauth.vo.admin.AuthorityVo;
import cn.structure.starter.oauth.vo.admin.UserAuthorizationVo;
import cn.structure.starter.oauth.vo.role.AddRoleVo;
import cn.structure.starter.oauth.vo.role.EditRoleVo;
import cn.structure.starter.oauth.vo.role.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理控制器
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/2 17:25
 */
@Api(tags = "管理模块")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @GetMapping(value = "getAuthAll")
    @ApiOperation(value = "查询全部的权限")
    public ResResultVO<List<AuthorityVo>> findAllAuthority() {
        return ResResultVO.success(iAdminService.findAllAuthority());
    }

    @GetMapping(value = "getAllRole")
    @ApiOperation(value = "查询全部角色")
    public ResResultVO<List<RoleVo>> findAllRole() {
        return ResResultVO.success(iAdminService.findAllRole());
    }

    @PostMapping(value = "addRole")
    @ApiOperation(value = "添加角色")
    public ResResultVO addRole(@Validated @RequestBody AddRoleVo addRoleVo) {
        return ResResultVO.success(iAdminService.addRole(addRoleVo));
    }

    @PostMapping(value = "updateRole")
    @ApiOperation(value = "修改角色")
    public ResResultVO update(@Validated @RequestBody EditRoleVo editRoleVo) {
        return ResResultVO.success(iAdminService.editRole(editRoleVo));
}

    @DeleteMapping(value = "/deleteRole/{roleId}")
    @ApiOperation(value = "删除角色")
    public ResResultVO deleteRole(@ApiParam(value = "角色ID",example = "1") @PathVariable(value = "roleId") Integer roleId) {
        return ResResultVO.success(iAdminService.deleteRole(roleId));
    }

    @PostMapping(value = "/userAuthorization")
    @ApiOperation(value = "给用户授权 - 角色，权限")
    public ResResultVO userAuthorization(@Validated @RequestBody UserAuthorizationVo userAuthorizationVo) {
        return ResResultVO.success(iAdminService.userAuthorization(userAuthorizationVo));
    }
}
