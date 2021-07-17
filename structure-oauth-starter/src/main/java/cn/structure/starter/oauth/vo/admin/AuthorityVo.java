package cn.structure.starter.oauth.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 权限-vo
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/26 19:37
 */
@Data
@ApiModel(description = "权限 - VO")
public class AuthorityVo {

    @ApiModelProperty(value = "key",example = "1")
    private Integer key;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "孩子们")
    private List<AuthorityVo> children;
}
