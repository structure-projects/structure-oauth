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
     * @param addUserVo
     * @return
     */
    Integer addUser(AddUserVo addUserVo);

    /**
     * 删除一个用户
     * @param userId
     */
    void delete(Integer userId);

    /**
     * 更改状态
     * @param changeEnabledVo
     */
    void changeEnabled(ChangeEnabledVo changeEnabledVo) ;

    /**
     * 重置密码
     * @param resetUserPasswordVo
     * @return
     */
    void resetPassword(ResetUserPasswordVo resetUserPasswordVo) ;

    /**
     * 修改密码
     * @param updatePasswordVo
     */
    void updateUserPassword(UpdatePasswordVo updatePasswordVo) ;

    /**
     * 修改用户信息
     * @param editUserInfoVo
     */
    void editUserInfo(EditUserInfoVo editUserInfoVo);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfoVo getUserInfoById(Integer userId);

    /**
     * 分页获取列表
     * @param currentPage
     * @param pageSize
     * @param search
     * @param enabled
     * @return
     */
    ResPageDTO<UserInfoVo> getList(int currentPage, int pageSize, String search, Boolean enabled, Boolean unlocked);

    /**
     * 用户注册
     * @param registerByUsernameAndPasswordVo
     */
    void register(RegisterByUsernameAndPasswordVo registerByUsernameAndPasswordVo);

}
