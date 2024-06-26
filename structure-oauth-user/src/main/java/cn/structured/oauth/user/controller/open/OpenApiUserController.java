package cn.structured.oauth.user.controller.open;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import cn.structured.oauth.user.api.dto.user.RegisterPlatformUserDto;
import cn.structured.oauth.user.api.dto.user.UserDetailDto;
import cn.structured.oauth.user.controller.assembler.UserAssembler;
import cn.structured.oauth.user.entity.User;
import cn.structured.oauth.user.service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 开放api
 *
 * @author chuck
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/open-api/user")
public class OpenApiUserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "通过用户ID查询用户详情", notes = "全局有权限即可")
    @GetMapping(value = "/get/{userId}")
    public ResResultVO<UserDetailDto> get(@ApiParam(value = "用户ID", example = "1645717015337684992")
                                          @PathVariable("userId") Long userId) {
        User user = userService.getById(userId);
        return ResultUtilSimpleImpl.success(UserAssembler.assembler(user));
    }

    @ApiOperation(value = "通过用户ID查询用户详情", notes = "全局有权限即可")
    @GetMapping(value = "/get/list")
    public ResResultVO<Map<Long, UserDetailDto>> getListByIds(Set<Long> userIds) {
        Map<Long, UserDetailDto> userMap = userService.list(Wrappers.<User>lambdaQuery()
                .in(User::getId, userIds)
                .select(User::getId, User::getNickName, User::getAvatar))
                .stream()
                .collect(Collectors.toMap(User::getId, UserAssembler::assembler));
        return ResultUtilSimpleImpl.success(userMap);
    }

    @ApiOperation(value = "注册平台用户")
    @GetMapping(value = "/register")
    public ResResultVO<Long> register(@RequestBody RegisterPlatformUserDto registerPlatformUser) {
        Long userId = userService.registerPlatformUser(registerPlatformUser);
        return ResultUtilSimpleImpl.success(userId);
    }

    @GetMapping(value = "/getUserByUsername")
    public ResResultVO<StructureAuthUser> getUserByUsername(@ApiParam(value = "用户名", example = "admin")
                                                            @RequestParam("username") String username) {
        User user = userService.loadUserByUserName(username);
        return ResultUtilSimpleImpl.success(UserAssembler.assemblerAuthUser(user));
    }

    @GetMapping(value = "/getUserByPlatformUserIdAndCode")
    public ResResultVO<StructureAuthUser> getUserByPlatformUserIdAndCode(@ApiParam(value = "平台ID", example = "18888888888")
                                                                         @RequestParam("platformUserId") String platformUserId,
                                                                         @ApiParam(value = "平台编码", example = "phone")
                                                                         @RequestParam("platformCode") String platformCode) {
        User user = userService.loadUserByPlatformUserIdAndPlatformCode(platformUserId, platformCode);
        return ResultUtilSimpleImpl.success(UserAssembler.assemblerAuthUser(user));
    }
}
