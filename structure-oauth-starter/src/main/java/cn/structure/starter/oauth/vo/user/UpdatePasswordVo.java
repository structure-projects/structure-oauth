package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 更新用户密码 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:13
 */
@Data
@ApiModel(description = "更新用户密码 - VO")
public class UpdatePasswordVo {

    @NotBlank
    @ApiModelProperty(value = "旧的密码")
    private String oldPassword;

    @NotBlank
    @ApiModelProperty(value = "新的密码")
    private String newPassword;

}
