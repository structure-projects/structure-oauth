package cn.structure.starter.oauth.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class EditMyUserInfoVo {

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headPortrait;
}
