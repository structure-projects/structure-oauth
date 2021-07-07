package cn.structure.starter.oauth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 出参：验证码 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/30 21:14
 */
@Data
@ApiModel(description = "出参：验证码 - VO")
public class VerificationCodeVo {

    @ApiModelProperty(value = "验证码ID")
    private Integer id;

    @ApiModelProperty(value = "验证码Base64")
    private String imageBase;

}
