package cn.structure.starter.oauth.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.structure.common.exception.CommonException;
import cn.structure.starter.oauth.configuration.OauthProperties;
import cn.structure.starter.oauth.entity.UserAccount;
import cn.structure.starter.oauth.enums.ErrCodeEnum;
import cn.structure.starter.oauth.mapper.UserAccountMapper;
import cn.structure.starter.oauth.mapper.UserAuthorityMapper;
import cn.structure.starter.oauth.service.IUserService;
import cn.structure.starter.oauth.util.SecurityUtils;
import cn.structure.starter.oauth.vo.login.RegisterByUsernameAndPasswordVo;
import cn.structure.starter.oauth.vo.user.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户Service实现
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:22
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private OauthProperties oauthProperties;

    @Override
    @Transactional
    public Integer addUser(AddUserVo addUserVo) {
        String username = addUserVo.getUsername();
        UserAccount account = userAccountMapper.selectUserByUsername(username);
        if (null != account) {
            throw new CommonException(ErrCodeEnum.ERR_EXIST_USER.getCode(),ErrCodeEnum.ERR_EXIST_USER.getMsg());
        }
        LocalDateTime dateTime = LocalDateTime.now();
        //构建用户实体
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(addUserVo.getUsername());
        String password = passwordEncoder.encode(addUserVo.getPassword());
        userAccount.setPassword(password);
        String nickName = IdUtil.randomUUID();
        userAccount.setNickName("用户:" + nickName);
        userAccount.setHeadPortrait("/default.jpg");
        userAccount.setEnable(Boolean.TRUE);
        userAccount.setUnlocked(Boolean.TRUE);
        userAccount.setUnexpired(Boolean.TRUE);
        userAccount.setDeleted(Boolean.FALSE);
        userAccount.setCreateTime(dateTime);
        userAccount.setUpdateTime(dateTime);
        userAccountMapper.insertUserAccount(userAccount);
        List<Integer> authorityIds = addUserVo.getAuthorityIds();
        List<Integer> roleIds = addUserVo.getRoleIds();
        Integer userId = userAccount.getId();
        if (null != authorityIds && authorityIds.size() > 0) {
            userAuthorityMapper.deleteUserAuthorityByUserId(userId);
            userAuthorityMapper.insertUserAuthorityList(authorityIds,userId, LocalDateTime.now());
        }
        if (null != roleIds && roleIds.size() > 0) {
            userAuthorityMapper.deleteUserRoleByUserId(userId);
            userAuthorityMapper.insertUserRoleList(roleIds,userId, LocalDateTime.now());
        }

        return userId;
    }

    @Override
    public void delete(Integer userId) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userId);
        userAccount.setDeleted(Boolean.TRUE);
        userAccountMapper.updateUser(userAccount);
    }

    @Override
    public void changeEnabled(ChangeEnabledVo changeEnabledVo) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(changeEnabledVo.getUserId());
        userAccount.setEnable(changeEnabledVo.getEnabled());
        userAccountMapper.updateUser(userAccount);
    }

    @Override
    public void resetPassword(ResetUserPasswordVo resetUserPasswordVo) {
        //新密码
        String newPassword = passwordEncoder.encode(resetUserPasswordVo.getNewPassword());
        UserAccount user = SecurityUtils.getUser();
        boolean matches = passwordEncoder.matches(resetUserPasswordVo.getPassword(), user.getPassword());
        if (!matches) {
            throw new CommonException(ErrCodeEnum.ERR_VALIDATE_PASSWORD.getCode(),ErrCodeEnum.ERR_VALIDATE_PASSWORD.getMsg());
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(resetUserPasswordVo.getUserId());
        userAccount.setPassword(newPassword);
        userAccountMapper.updateUser(userAccount);
    }

    @Override
    public void updateUserPassword(UpdatePasswordVo updatePasswordVo) {
        UserAccount user = SecurityUtils.getUser();
        boolean matches = passwordEncoder.matches(updatePasswordVo.getOldPassword(), user.getPassword());
        if (!matches) {
            throw new CommonException(ErrCodeEnum.ERR_VALIDATE_PASSWORD.getCode(),ErrCodeEnum.ERR_VALIDATE_PASSWORD.getMsg());
        }
        String newPassword =passwordEncoder.encode(updatePasswordVo.getNewPassword()) ;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(user.getId());
        userAccount.setPassword(newPassword);
        userAccountMapper.updateUser(userAccount);
    }

    @Override
    public void editUserInfo(EditUserInfoVo editUserInfoVo) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(editUserInfoVo.getUserId());
        userAccount.setHeadPortrait(editUserInfoVo.getHeadPortrait());
        userAccount.setNickName(editUserInfoVo.getNickName());
        userAccount.setEnable(editUserInfoVo.getEnable());
        userAccount.setUnlocked(!editUserInfoVo.getLock());
        userAccountMapper.updateUser(userAccount);
    }

    @Override
    public UserInfoVo getUserInfoById(Integer userId) {
        UserAccount userAccount = userAccountMapper.selectUserById(userId);
        return getUserInfoVo(userAccount);
    }

    @Override
    public ResPageDTO<UserInfoVo> getList(int currentPage, int pageSize, String search, Boolean enabled,Boolean unlock) {
        PageHelper.startPage(currentPage,pageSize);
        List<UserAccount> userAccounts = userAccountMapper.selectUserList(search, enabled,unlock);
        PageInfo<UserAccount> pageInfo = new PageInfo<>(userAccounts);
        List<UserInfoVo> userInfoVos = pageInfo.getList().stream().map(userAccount -> {
            return getUserInfoVo(userAccount);
        }).collect(Collectors.toList());
        ResPageDTO<UserInfoVo> pageDTO = new ResPageDTO<>();
        pageDTO.setCurrent(pageInfo.getPageNum());
        pageDTO.setPages(pageInfo.getPages());
        pageDTO.setRecords(userInfoVos);
        pageDTO.setSize(pageInfo.getSize());
        pageDTO.setTotal(pageInfo.getTotal());
        return pageDTO;
    }

    @Override
    @Transactional
    public void register(RegisterByUsernameAndPasswordVo registerByUsernameAndPasswordVo) {
        String username = registerByUsernameAndPasswordVo.getUsername();
        UserAccount account = userAccountMapper.selectUserByUsername(username);
        if (null != account) {
            throw new CommonException(ErrCodeEnum.ERR_EXIST_USER.getCode(),ErrCodeEnum.ERR_EXIST_USER.getMsg());
        }
        LocalDateTime dateTime = LocalDateTime.now();
        //构建用户实体
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(registerByUsernameAndPasswordVo.getUsername());
        String password = passwordEncoder.encode(registerByUsernameAndPasswordVo.getPassword());
        userAccount.setPassword(password);
        String nickName = IdUtil.randomUUID();
        userAccount.setNickName("用户:" + nickName);
        userAccount.setHeadPortrait("/default.jpg");
        userAccount.setEnable(Boolean.TRUE);
        if (oauthProperties.getIsAdminCheckRegister()) {
            userAccount.setUnlocked(Boolean.FALSE);
        }else {
            userAccount.setUnlocked(Boolean.TRUE);
        }
        userAccount.setUnexpired(Boolean.TRUE);
        userAccount.setDeleted(Boolean.FALSE);
        userAccount.setCreateTime(dateTime);
        userAccount.setUpdateTime(dateTime);
        userAccountMapper.insertUserAccount(userAccount);
    }

    private UserInfoVo getUserInfoVo(UserAccount userAccount) {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserId(userAccount.getId());
        userInfoVo.setUsername(userAccount.getUsername());
        userInfoVo.setNickName(userAccount.getNickName());
        userInfoVo.setHeadPortrait(userAccount.getHeadPortrait());
        userInfoVo.setEnabled(userAccount.getEnable());
        userInfoVo.setCreateTime(userAccount.getCreateTime());
        userInfoVo.setUpdateTime(userAccount.getUpdateTime());
        userInfoVo.setHeadPortrait(userAccount.getHeadPortrait());
        return userInfoVo;
    }
}
