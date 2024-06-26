package cn.structured.oauth.server.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.oauth.server.granter.PlatformAuthenticationManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控管理
 *
 * @author chuck
 * @since JDK1.8
 */
@Api(tags = "用户控管理")
@RestController
@RequestMapping(value = "/web-api/user")
public class UserController {

    @ApiOperation(value = "通过平台认证获取平台的用户ID",notes = "全局有权限即可！")
    @GetMapping(value = "/get/{platformCode}/{authCode}")
    public ResResultVO<String> getPlatformUserId(@PathVariable String platformCode, @PathVariable String authCode) {
        String userId = PlatformAuthenticationManager.authentication(platformCode, authCode);
        return ResultUtilSimpleImpl.success(userId);
    }
}
