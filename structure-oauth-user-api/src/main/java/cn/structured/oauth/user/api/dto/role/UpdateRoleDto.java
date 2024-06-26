package cn.structured.oauth.user.api.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 更新用户
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更新角色")
public class UpdateRoleDto {

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "1645717015337684992", required = true)
    private Long id;

    @ApiModelProperty(value = "角色名称", example = "管理员")
    private String name;

    @ApiModelProperty(value = "角色编码", example = "ADMIN")
    private String code;

    @ApiModelProperty(value = "角色类型", example = "2", notes = "系统角色：1（系统角色不运行用户创建和修改）,用户角色：2")
    private Integer type;

    @ApiModelProperty(value = "描述", example = "这个角色是一个管理员，用于管理用户角色和权限使用")
    private String remark;

    @ApiModelProperty(value = "角色权限ID", example = "[1645717015337684992,1645717015337684993]")
    private List<String> authorities;
}
