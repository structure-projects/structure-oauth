package cn.structure.starter.oauth.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色出参VO
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/6 21:56
 */
@Data
@ApiModel(description = "角色出参VO")
public class RoleVo {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String name;

    /**
     * 角色值
     */
    @ApiModelProperty(value = "角色值")
    private String value;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
}
