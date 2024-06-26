package cn.structured.oauth.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * client
 * </p>
 *
 * @author chuck
 * @since 2024-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @TableId(value = "client_id", type = IdType.NONE)
    private String clientId;

    /**
     * 资源ID集合,多个资源时用逗号(,)分隔
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     * 客户端密匙
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     * 客户端申请的权限范围
     */
    @TableField("scope")
    private String scope;

    /**
     * 客户端支持的grant_type
     */
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
     */
    @TableField("authorities")
    private String authorities;

    /**
     * 访问令牌有效时间值
     */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 更新令牌有效时间值
     */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @TableField("additional_information")
    private String additionalInformation;

    /**
     * 用户是否自动Approval操作
     */
    @TableField("autoapprove")
    private String autoapprove;

    /**
     * web重定向地址
     */
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
