package cn.structured.oauth.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author chuck
 * @since 2024-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_bind")
public class UserBind implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 平台用户ID
     */
    @TableField("platform_user_id")
    private String platformUserId;

    /**
     * 平台编码
     */
    @TableField("platform_code")
    private String platformCode;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
