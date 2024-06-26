package cn.structured.oauth.server.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.oauth.server.entity.OauthClientDetails;
import cn.structured.oauth.server.mapper.OauthClientDetailsMapper;
import cn.structured.oauth.server.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 终端管理
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Service
public class ClientServiceImpl extends BaseServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IClientService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean save(OauthClientDetails entity) {
        entity.setClientSecret(passwordEncoder.encode(entity.getClientSecret()));
        return super.save(entity);
    }

    @Override
    public boolean updateById(OauthClientDetails entity) {
        entity.setClientSecret(passwordEncoder.encode(entity.getClientSecret()));
        return super.updateById(entity);
    }
}
