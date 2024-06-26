package cn.structured.oauth.api.dto.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户端DTO
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "客户端 - DTO")
public class ClientDto {

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @ApiModelProperty(value = "资源ID集合", notes = "资源ID服务")
    private List<String> resourceIds;

    @ApiModelProperty(value = "客户端密匙")
    private String clientSecret;

    @ApiModelProperty(value = "客户端申请的权限范围")
    private String scope;

    @ApiModelProperty(value = "客户端支持的grant_type")
    private List<String> authorizedGrantTypes;

    @ApiModelProperty(value = "客户端所拥有的Spring Security的权限值")
    private List<String> authorities;

    @ApiModelProperty(value = "访问令牌有效时间值")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "更新令牌有效时间值")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "附加信息")
    private String additionalInformation;

    @ApiModelProperty(value = "用户是否自动Approval操作")
    private Boolean autoApprove;

    @ApiModelProperty(value = "web重定向地址")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
