package cn.structure.starter.oauth.service.impl;

import cn.structure.starter.oauth.entity.Authority;
import cn.structure.starter.oauth.entity.Role;
import cn.structure.starter.oauth.entity.UserAccount;
import cn.structure.starter.oauth.mapper.UserAccountMapper;
import cn.structure.starter.oauth.mapper.UserAuthorityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户账户ServiceImpl
 *
 * @author chuck
 * @date 2020/2/27 14:36
 * @since 1.0.1
 */
@Slf4j
@Service
public class UserAccountServiceImpl implements UserDetailsService {

    /**
     * 用户账户Mapper
     */
    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 根据用户名查询用户账户
        UserAccount userAccount = userAccountMapper.selectUserByUsername(s);
        if (null == userAccount) {
            throw new UnauthorizedUserException("用户不存在！");
        }
        if (!userAccount.getUnexpired()) {
            throw new UnauthorizedUserException("用户已过期！");
        }
        if (!userAccount.getUnlocked()) {
            throw new UnauthorizedUserException("用户已锁定！");
        }
        if (!userAccount.getEnable()) {
            throw new UnauthorizedUserException("用户未启用！");
        }
        List<Authority> authorityList = userAuthorityMapper.findAuthorityAllByUserId(userAccount.getId());
        List<Role> roleList = userAuthorityMapper.findRoleByUserId(userAccount.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(authorityList);
        authorities.addAll(roleList);
        userAccount.setAuthorities(authorities);
        return userAccount;
    }
}
