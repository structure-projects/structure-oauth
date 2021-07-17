package cn.structure.starter.oauth.controller;

import cn.structure.common.entity.ResResultVO;
import cn.structure.starter.oauth.entity.UserAccount;
import cn.structure.starter.oauth.service.IAdminService;
import cn.structure.starter.oauth.service.IUserService;
import cn.structure.starter.oauth.util.SecurityUtils;
import cn.structure.starter.oauth.vo.login.CurrentAuthorityVo;
import cn.structure.starter.oauth.vo.user.EditMyUserInfoVo;
import cn.structure.starter.oauth.vo.user.EditUserInfoVo;
import cn.structure.starter.oauth.vo.user.UpdatePasswordVo;
import cn.structure.starter.oauth.vo.user.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户我的 管理模块
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/2 13:35
 */
@RestController
@RequestMapping("/user/my")
@Api(tags = "用户我的模块")
public class MyUserController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IUserService userService;

    @PostMapping("/doLogout")
    @ApiOperation(value = "用户登出接口")
    public Object logout(HttpServletRequest request ) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION).substring(6).trim();
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResResultVO.success(null);
    }

    @PostMapping("/currentAuthority")
    @ApiOperation(value = "获取当前用户登录权限")
    public ResResultVO<CurrentAuthorityVo> currentAuthority(){
        Integer userId = SecurityUtils.getUser().getId();
        CurrentAuthorityVo currentAuthority = iAdminService.findCurrentAuthorityByUserId(userId);
        return ResResultVO.success(currentAuthority);
    }

    @PostMapping("/changePassword")
    @ApiOperation(value = "修改密码")
    public ResResultVO updateUserPassword(@Validated @RequestBody UpdatePasswordVo updatePasswordVo) {
        userService.updateUserPassword(updatePasswordVo);
        return ResResultVO.success(null);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取我的用户信息")
    public ResResultVO<UserInfoVo> userInfo() {
        UserAccount user = SecurityUtils.getUser();
        return ResResultVO.success(userService.getUserInfoById(user.getId()));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改我的用户资料")
    public ResResultVO updateMyUserInfo(@Validated @RequestBody EditMyUserInfoVo editMyUserInfoVo) {
        UserAccount user = SecurityUtils.getUser();
        EditUserInfoVo editUserInfoVo = new EditUserInfoVo();
        editUserInfoVo.setUserId(user.getId());
        editUserInfoVo.setNickName(editMyUserInfoVo.getNickName());
        editUserInfoVo.setHeadPortrait(editMyUserInfoVo.getHeadPortrait());
        userService.editUserInfo(editUserInfoVo);
        return ResResultVO.success(null);
    }



}
