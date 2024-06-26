package cn.structured.oauth.user.entity;

import cn.structured.mybatis.plus.starter.annotations.FieldJoin;
import cn.structured.mybatis.plus.starter.annotations.Join;
import cn.structured.mybatis.plus.starter.annotations.JoinCondition;
import cn.structured.mybatis.plus.starter.enums.JoinResultEnum;
import cn.structured.mybatis.plus.starter.enums.JoinTypeEnum;
import cn.structured.oauth.user.group.SearchGroup;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author chuck
 * @since 2024-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;

    /**
     * 角色类型 1 系统角色， 2 用户角色
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否删除 0 否 ，1 是
     */
    @TableField("is_deleted")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 是否启用 0：未启用 1：启用
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 组织ID
     */
    @TableField("organization_id")
    private Long organizationId;

    /**
     * 权限
     */
    @TableField(exist = false)
    private List<String> authorities;

    /**
     * 操作人
     */
    @FieldJoin(type = JoinResultEnum.ONE, result = String.class, value = {
            @Join(group = {SearchGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = User.class, aliasName = "u",
                    columns = {"nick_name"},
                    value = {@JoinCondition(currentColumn = "update_by", targetColumn = "id")}),
    })
    @TableField(exist = false)
    private String operator;

}
