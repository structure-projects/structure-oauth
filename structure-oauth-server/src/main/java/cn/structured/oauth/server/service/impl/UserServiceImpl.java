package cn.structured.oauth.server.service.impl;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.exception.CommonException;
import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import cn.structured.oauth.server.client.UserFeignClient;
import cn.structured.oauth.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户账户ServiceImpl
 *
 * @author chuck
 * @since 1.0.1
 */
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResResultVO<StructureAuthUser> resultUser = userFeignClient.getUserByUsername(username);
        if (!resultUser.getSuccess()) {
            throw new CommonException(resultUser.getCode(), resultUser.getMessage());
        }
        return resultUser.getData();
    }

    @Override
    public UserDetails loadUserByPlatformUserIdAndPlatformCode(String platformUserId, String platformCode) {
        ResResultVO<StructureAuthUser> resultUser = userFeignClient.getUserByPlatformUserIdAndCode(platformUserId, platformCode);
        if (!resultUser.getSuccess()) {
            throw new CommonException(resultUser.getCode(), resultUser.getMessage());
        }
        return resultUser.getData();
    }


}
