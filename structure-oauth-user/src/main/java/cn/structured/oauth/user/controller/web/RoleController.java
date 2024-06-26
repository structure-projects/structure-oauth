package cn.structured.oauth.user.controller.web;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.oauth.user.api.dto.OptionDto;
import cn.structured.oauth.user.api.dto.role.CreateRoleDto;
import cn.structured.oauth.user.api.dto.role.RoleDto;
import cn.structured.oauth.user.api.dto.role.SearchRoleDto;
import cn.structured.oauth.user.api.dto.role.UpdateRoleDto;
import cn.structured.oauth.user.controller.assembler.RoleAssembler;
import cn.structured.oauth.user.entity.Role;
import cn.structured.oauth.user.group.SearchGroup;
import cn.structured.oauth.user.service.IRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色控制器
 *
 * @author chuck
 * @since JDK1.8
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/web-api/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateRoleDto createRole) {
        Role role = RoleAssembler.assembler(createRole);
        roleService.save(role);
        return ResultUtilSimpleImpl.success(role.getId());
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateRoleDto updateRole) {
        roleService.updateById(RoleAssembler.assembler(updateRole));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "角色列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<RoleDto>> page(@ApiParam(value = "页码", required = true, example = "1")
                                              @PathVariable("page") Long page,
                                              @ApiParam(value = "页大小", required = true, example = "20")
                                              @PathVariable("pageSize") Long pageSize,
                                              SearchRoleDto searchRoleQuery) {
        Role role = new Role();
        role.setName(searchRoleQuery.getName());
        role.setEnabled(searchRoleQuery.getEnabled());
        QueryJoinPageListWrapper<Role> queryWrapper = new QueryJoinPageListWrapper<>(role);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<Role> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<Role> pageResult = roleService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, RoleAssembler::assembler));
    }

    @ApiOperation(value = "角色详情")
    @GetMapping(value = "/get")
    public ResResultVO<RoleDto> get(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                    @RequestParam("roleId") Long roleId) {
        Role role = new Role();
        role.setId(roleId);
        QueryJoinPageListWrapper<Role> queryWrapper = new QueryJoinPageListWrapper<>(role);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);
        role = roleService.list(queryWrapper)
                .stream()
                .findFirst()
                .orElse(role);
        RoleDto resultRole = RoleAssembler.assembler(role);
        List<String> authorities = roleService.getAuthorities(roleId);
        resultRole.setAuthorities(authorities);
        return ResultUtilSimpleImpl.success(resultRole);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/{roleId}")
    public ResResultVO<Void> remove(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                    @PathVariable("roleId") Long roleId) {
        roleService.removeById(roleId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{roleId}")
    public ResResultVO<Void> enable(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                    @PathVariable("roleId") Long roleId) {
        roleService.enable(roleId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{roleId}")
    public ResResultVO<Void> disable(@ApiParam(value = "角色ID", example = "1645717015337684992")
                                     @PathVariable("roleId") Long roleId) {
        roleService.disable(roleId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "角色列表")
    @GetMapping(value = "/list")
    public ResResultVO<List<OptionDto>> option(@ApiParam(value = "关键字", example = "name")
                                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        Role role = new Role();
        role.setEnabled(Boolean.TRUE);
        QueryJoinPageListWrapper<Role> queryWrapper = new QueryJoinPageListWrapper<>(role);
        queryWrapper.setIsJoin(false);
        queryWrapper.addColumn("id", "name");
        queryWrapper.addSearch("name");
        queryWrapper.setSearch(keyword);
        List<Role> pageResult = roleService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(pageResult
                .stream()
                .map(RoleAssembler::assemblerOption)
                .collect(Collectors.toList()));
    }

}
