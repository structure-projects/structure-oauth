package cn.structure.starter.oauth.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 编辑角色
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/6 21:51
 */
@Data
@ApiModel(description = "编辑角色 - VO")
public class EditRoleVo {

    /**
     * 角色ID
     */
    @NotNull
    @ApiModelProperty(value = "角色ID",example = "2")
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名",example = "测试角色")
    private String name;

    /**
     * 角色值
     */
    @ApiModelProperty(value = "角色值")
    private String value;

    /**
     *  权限值
     */
    @ApiModelProperty(value = "全限值")
    private List<Integer> authorityIds;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

}
