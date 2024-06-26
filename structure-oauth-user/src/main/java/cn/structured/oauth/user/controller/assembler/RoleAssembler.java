package cn.structured.oauth.user.controller.assembler;

import cn.structured.oauth.user.api.dto.OptionDto;
import cn.structured.oauth.user.api.dto.role.CreateRoleDto;
import cn.structured.oauth.user.api.dto.role.RoleDto;
import cn.structured.oauth.user.api.dto.role.UpdateRoleDto;
import cn.structured.oauth.user.entity.Role;

/**
 * 角色装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class RoleAssembler {

    private RoleAssembler() {
    }

    /**
     * 创建角色DTO装配成角色
     *
     * @param createRoleDto 创建角色
     * @return 角色实体对象
     */
    public static Role assembler(CreateRoleDto createRoleDto) {
        Role role = new Role();
        role.setName(createRoleDto.getName());
        role.setCode(createRoleDto.getCode());
        role.setAuthorities(createRoleDto.getAuthorities());
        return role;
    }

    /**
     * 修改角色DTO装配成角色
     *
     * @param updateRoleDto 修改角色
     * @return 角色实体对象
     */
    public static Role assembler(UpdateRoleDto updateRoleDto) {
        Role role = new Role();
        role.setId(updateRoleDto.getId());
        role.setName(updateRoleDto.getName());
        role.setCode(updateRoleDto.getCode());
        role.setAuthorities(updateRoleDto.getAuthorities());
        return role;
    }

    /**
     * 角色实体装配为角色VO
     *
     * @param role 角色实体
     * @return 返回角色VO对象
     */
    public static RoleDto assembler(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setCode(role.getCode());
        roleDto.setEnabled(role.getEnabled());
        roleDto.setOperator(role.getOperator());
        roleDto.setOperatorTime(role.getUpdateTime());
        return roleDto;
    }

    /**
     * 角色装配成下拉选择VO对象
     *
     * @param role 角色
     * @return {@link OptionDto}
     */
    public static OptionDto assemblerOption(Role role) {
        OptionDto option = new OptionDto();
        option.setId(role.getId());
        option.setName(role.getName());
        option.setCode(role.getCode());
        return option;
    }

}
