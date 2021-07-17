package cn.structure.starter.oauth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 当前用户权限-VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 16:00
 */
@Data
@ApiModel(description = "当前用户权限-VO")
public class CurrentAuthorityVo {

    @ApiModelProperty(value = "菜单列表")
    private List<MenuVo> menuList;

    @ApiModelProperty(value = "权限列表")
    private List<String> authority;

}
