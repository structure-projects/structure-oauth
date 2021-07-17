package cn.structure.starter.oauth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;


/**
 * @author chuck
 */
@Data
public class UserAccount implements UserDetails {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headPortrait;


    /**
     * 是否启用 1:启用 0:未启用
     */
    private Boolean enable;

    /**
     * 是否锁定 1:锁定 0:未锁定
     */
    private Boolean unlocked;

    /**
     * 是否过期 1：过期 0：未过期
     */
    private Boolean unexpired;

    /**
     * 是否删除 0：未删除 1：删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private transient Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.unexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.unlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

}