package cn.structure.starter.oauth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户基础信息-VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 14:04
 */
@Data
@ApiModel(description = "用户基础信息-VO")
public class TokenUserInfoVo {

    @ApiModelProperty(value = "认证TOKEN")
    private String accessToken;

    @ApiModelProperty(value = "刷新TOKEN")
    private String refreshToken;

    @ApiModelProperty(value = "过期时间")
    private Integer expiresIn;

    @ApiModelProperty(value = "token类型")
    private String tokenType;

    @ApiModelProperty(value = "范围")
    private String scope;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

}
