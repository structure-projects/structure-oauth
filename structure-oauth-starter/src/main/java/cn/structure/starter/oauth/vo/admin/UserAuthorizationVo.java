package cn.structure.starter.oauth.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户授权 - vo
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/26 19:45
 */
@Data
@ApiModel(description = "用户授权 - vo")
public class UserAuthorizationVo {

    @NotNull
    @ApiModelProperty(value = "用户ID",required = true,example = "1")
    private Integer userId;

    @ApiModelProperty(value = "权限ids")
    private List<Integer> authorityIds;

    @ApiModelProperty(value = "角色ID")
    private List<Integer> roleIds;

}
