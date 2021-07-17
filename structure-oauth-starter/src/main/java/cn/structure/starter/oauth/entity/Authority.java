package cn.structure.starter.oauth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author chuck
 * @since 2020-12-08
 */
@Data
public class Authority implements Serializable, GrantedAuthority, Comparable<Authority> {

private static final long serialVersionUID=1L;

    /**
     * 权限ID
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限标识
     */
    private String value;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 权限类型：0 功能，1 菜单，2 按钮
     */
    private String type;

    /**
     * 路径
     */
    private String path;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * icon 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 权限
     */
    private List<Authority> authorities;

    /**
     * 设置权限
     */
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public int compareTo(Authority o) {
        if(this.id.equals(o.getId())){
            return 0;
        }
        return -1;
    }

    @Override
    public String getAuthority() {
        return this.value;
    }
}
































































