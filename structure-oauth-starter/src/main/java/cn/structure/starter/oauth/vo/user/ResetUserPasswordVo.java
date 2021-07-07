package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 重置用户名密码
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:17
 */
@Data
@ApiModel(description = "重置用户名密码")
public class ResetUserPasswordVo {
    @NotNull
    @ApiModelProperty(value = "用户ID" ,example = "3")
    private Integer userId;

    @NotBlank
    @ApiModelProperty(value = "管理员密码")
    private String password;

    @NotBlank
    @ApiModelProperty(value = "新的密码")
    private String newPassword;
}
