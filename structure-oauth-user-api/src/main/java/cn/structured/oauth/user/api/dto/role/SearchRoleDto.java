package cn.structured.oauth.user.api.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索角色
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "搜索角色")
public class SearchRoleDto {

    @ApiModelProperty(value = "角色编号", example = "0001")
    private String code;

    @ApiModelProperty(value = "角色名称", example = "管理员")
    private String name;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

}
