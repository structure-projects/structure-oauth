package cn.structured.oauth.user.api.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * create by chuck 2023/8/3
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "绑定权限 - DTO")
public class BindingAuthorityDto {

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "1645717015337684992", required = true)
    private Long id;

    @ApiModelProperty(value = "角色权限ID", example = "[1645717015337684992,1645717015337684993]")
    private List<Long> authorities;

}
