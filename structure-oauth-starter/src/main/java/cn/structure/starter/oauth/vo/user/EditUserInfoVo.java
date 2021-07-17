package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 编辑用户信息 - VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:10
 */
@Data
@ApiModel(description = "编辑用户信息 - VO")
public class EditUserInfoVo {

    @NotNull
    @ApiModelProperty(value = "用户ID",example = "2")
    private Integer userId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "锁定 true/false")
    private Boolean lock;

    @ApiModelProperty(value = "启用 true/false")
    private Boolean enable;

}
