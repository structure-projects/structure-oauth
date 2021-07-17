package cn.structure.starter.oauth.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 添加角色的VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/6 21:49
 */
@Data
@ApiModel(description = "添加角色的 - VO")
public class AddRoleVo {

    /**
     * 角色名
     */
    @NotBlank
    @ApiModelProperty(value = "角色名",example = "管理员")
    private String name;

    /**
     * 角色标识符
     */
    @NotBlank
    @ApiModelProperty(value = "角色标识符",example = "ADMIN")
    private String value;

    /**
     *  权限值
     */
    @ApiModelProperty(value = "权限值")
    private List<Integer> authorityIds;

}
