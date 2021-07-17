package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 添加一个用户 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:53
 */
@Data
@ApiModel(description = "添加一个用户 - VO")
public class AddUserVo {

    @NotBlank
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "角色集合")
    private List<Integer> roleIds;

    @ApiModelProperty(value = "权限集合")
    private List<Integer> authorityIds;

}
