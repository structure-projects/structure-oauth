package cn.structured.oauth.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author chuck
 * @since 2024-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_authority")
public class OauthAuthority implements Serializable, GrantedAuthority, Comparable<OauthAuthority> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ClientID
     */
    @TableField("app_id")
    private String appId;

    /**
     * 权限名
     */
    @TableField("name")
    private String name;

    /**
     * 权限值标识符
     */
    @TableField("value")
    private String value;

    /**
     * 是否删除：0 否，1 是
     */
    @TableField("is_deleted")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public int compareTo(OauthAuthority o) {
        if (this.id.equals(o.getId())) {
            return 0;
        }
        return -1;
    }

    @Override
    public String getAuthority() {
        return this.value;
    }

}
