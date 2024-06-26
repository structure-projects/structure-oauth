package cn.structured.oauth.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户权限关系表
 * </p>
 *
 * @author chuck
 * @since 2024-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_authority_mapping")
public class UserAuthorityMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

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
