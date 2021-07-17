package cn.structure.starter.oauth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * <p>
 * 菜单-VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 16:03
 */
@Data
@ApiModel(description = "菜单")
public class MenuVo {

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "菜单类型：0 内置,1 外链 ")
    private String menuType;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "子节点")
    private List<MenuVo> children;

}
