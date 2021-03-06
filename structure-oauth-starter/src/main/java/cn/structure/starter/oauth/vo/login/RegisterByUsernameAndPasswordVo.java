package cn.structure.starter.oauth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 注册用户 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:03
 */
@ApiModel(description = "注册用户 - VO")
@Data
public class RegisterByUsernameAndPasswordVo {

    @NotBlank
    @ApiModelProperty(value = "用户名 - 用于登录的用户名",required = true,example = "admin")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码 - 用于登录的密码,使用MD532位加密即可,仅用于数据传输用并不为存储使用",required = true,example = "E10ADC3949BA59ABBE56E057F20F883E")
    private String password;

    @ApiModelProperty(value = "验证码 - 登录时传递的验证码结果",example = "3v46")
    private String verificationCode;

    @ApiModelProperty(value = "验证码唯一标识",example = "123456")
    private Integer verificationCodeId;

}
