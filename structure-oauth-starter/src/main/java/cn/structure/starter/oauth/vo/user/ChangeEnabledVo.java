package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 更改用户是否启用 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:48
 */
@Data
@ApiModel(description = "更改用户是否启用 - VO")
public class ChangeEnabledVo {

    @ApiModelProperty(value = "用户Id",example = "2")
    private Integer userId;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
}
