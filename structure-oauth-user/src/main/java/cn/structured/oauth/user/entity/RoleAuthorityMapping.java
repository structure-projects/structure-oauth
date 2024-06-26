package cn.structured.oauth.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author chuck
 * @since 2024-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role_authority_mapping")
public class RoleAuthorityMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 权限CODE
     */
    @TableField("authority_code")
    private String authorityCode;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
