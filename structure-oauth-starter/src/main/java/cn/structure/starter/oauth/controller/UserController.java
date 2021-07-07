package cn.structure.starter.oauth.controller;

import cn.structure.common.entity.ResResultVO;
import cn.structure.starter.oauth.service.IUserService;
import cn.structure.starter.oauth.vo.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/1 23:55
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {


    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "添加一个用户")
    public ResResultVO addUser(@Validated @RequestBody AddUserVo addUserVo){
        userService.addUser(addUserVo);
        return ResResultVO.success(null);
    }

    @DeleteMapping("/delete/{userId}/")
    @ApiOperation(value = "删除一个用户")
    public ResResultVO delete(@ApiParam("用户ID")@PathVariable("userId") Integer userId){
        userService.delete(userId);
        return ResResultVO.success(null);
    }

    @PostMapping("/changeEnabled")
    @ApiOperation(value = "更改状态")
    public ResResultVO changeEnabled(@Validated @RequestBody ChangeEnabledVo changeEnabledVo) {
        userService.changeEnabled(changeEnabledVo);
        return ResResultVO.success(null);
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码")
    public ResResultVO resetPassword(@Validated @RequestBody ResetUserPasswordVo resetUserPasswordVo) {
        userService.resetPassword(resetUserPasswordVo);
        return ResResultVO.success(null);
    }

    @PostMapping("/changePassword")
    @ApiOperation(value = "修改密码")
    public ResResultVO updateUserPassword(@Validated @RequestBody UpdatePasswordVo updatePasswordVo) {
        userService.updateUserPassword(updatePasswordVo);
        return ResResultVO.success(null);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户信息")
    public ResResultVO editUserInfo(@Validated @RequestBody EditUserInfoVo editUserInfoVo){
        userService.editUserInfo(editUserInfoVo);
        return ResResultVO.success(null);
    }

    @GetMapping("/info/{userId}/")
    @ApiOperation(value = "获取用户信息")
    public ResResultVO<UserInfoVo> getUserInfoById(@ApiParam("用户ID")@PathVariable("userId") Integer userId){
        return ResResultVO.success(userService.getUserInfoById(userId));
    }

    @GetMapping(value = "/{currentPage}/{pageSize}/page")
    @ApiOperation(value = "分页用户列表")
    public ResResultVO<ResPageDTO<UserInfoVo>> getList(@ApiParam("页码") @PathVariable("currentPage") int currentPage,
                                                       @ApiParam("页大小")@PathVariable("pageSize") int pageSize,
                                                       @ApiParam("搜索 -> 昵称和用户名") String search,
                                                       @ApiParam("是否启用") Boolean enabled,
                                                       @ApiParam("是否锁定") Boolean locked
                                                       ){
        return ResResultVO.success(userService.getList(currentPage,pageSize,search,enabled,!locked));
    }
}
