package cn.structure.starter.oauth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Role implements GrantedAuthority,Comparable<Role>, Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色唯一标识
     */
    private String value;

    /**
     * 是否禁用
     */
    private Boolean enable;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    @Override
    public String getAuthority() {
        StringBuilder sb = new StringBuilder();
        sb.append("ROLE_");
        sb.append(this.value);
        return sb.toString();
    }

    @Override
    public int compareTo(Role o) {
        if(this.id.equals(o.getId())){
            return 0;
        }
        return -1;
    }
}