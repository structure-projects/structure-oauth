package cn.structured.oauth.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 下拉选-DTO
 *
 * @author cqliut
 * @version 2023.0731
 * @since 1.0.1
 */
@Data
@ApiModel(description = "下拉选-Dto")
public class OptionDto {

    @ApiModelProperty(value = "ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "名称", example = "公共空间")
    private String name;

    @ApiModelProperty(value = "编码", example = "public")
    private String code;

    @ApiModelProperty(value = "子集", example = "[]")
    private List<OptionDto> children;

}
