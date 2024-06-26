package cn.structured.oauth.user.entity;

import cn.structured.mybatis.plus.starter.annotations.FieldJoin;
import cn.structured.mybatis.plus.starter.annotations.Join;
import cn.structured.mybatis.plus.starter.annotations.JoinCondition;
import cn.structured.mybatis.plus.starter.enums.JoinResultEnum;
import cn.structured.mybatis.plus.starter.enums.JoinTypeEnum;
import cn.structured.oauth.user.group.DetailsGroup;
import cn.structured.oauth.user.group.SearchGroup;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chuck
 * @since 2024-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 加密后的密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 是否未过期 0：过期 1：未过期
     */
    @TableField("is_unexpired")
    private Boolean unexpired;

    /**
     * 是否启用 1:  启用 0:未启用
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Boolean enabled;

    /**
     * 是否未锁定 0:  锁定 1:未锁定
     */
    @TableField("is_unlocked")
    private Boolean unlocked;

    /**
     * 是否删除 0：未删除 1：删除
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

    /**
     * 操作人
     */
    @FieldJoin(type = JoinResultEnum.ONE, result = String.class, value = {
            @Join(group = {SearchGroup.class, DetailsGroup.class}, result = true, joinType = JoinTypeEnum.LEFT_JOIN,
                    joinTarget = User.class, aliasName = "u",
                    columns = {"nick_name"},
                    value = {@JoinCondition(currentColumn = "update_by", targetColumn = "id")}),
    })
    @TableField(exist = false)
    private String operator;

}
