package cn.structure.starter.oauth.service;


import cn.structure.starter.oauth.vo.login.RegisterByUsernameAndPasswordVo;
import cn.structure.starter.oauth.vo.user.*;

/**
 * <p>
 * 用户service
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 0:21
 */
public interface IUserService {

    /**
     * 添加一个用户
     */
    Integer addUser(AddUserVo addUserVo);

    /**
     * 删除一个用户
     */
    void delete(Integer userId);

    /**
     * 更改状态
     */
    void changeEnabled(ChangeEnabledVo changeEnabledVo) ;

    /**
     * 重置密码
     */
    void resetPassword(ResetUserPasswordVo resetUserPasswordVo) ;

    /**
     * 修改密码
     */
    void updateUserPassword(UpdatePasswordVo updatePasswordVo) ;

    /**
     * 修改用户信息
     */
    void editUserInfo(EditUserInfoVo editUserInfoVo);


    /**
     * 获取用户信息
     */
    UserInfoVo getUserInfoById(Integer userId);

    /**
     * 分页获取列表
     */
    ResPageDTO<UserInfoVo> getList(int currentPage, int pageSize, String search, Boolean enabled, Boolean unlocked);

    /**
     * 用户注册
     */
    void register(RegisterByUsernameAndPasswordVo registerByUsernameAndPasswordVo);

}
