package cn.structured.oauth.server.client;

import cn.structure.common.entity.ResResultVO;
import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户域 feignClient
 *
 * @author chuck
 * @since JDK1.8
 */
@FeignClient(name = "user-center")
@RequestMapping(value = "/open-api/user")
public interface UserFeignClient {

    /**
     * 获取认证用户信息
     *
     * @param username 用户名
     * @return
     */
    @GetMapping(value = "/getUserByUsername")
    ResResultVO<StructureAuthUser> getUserByUsername(@ApiParam(value = "用户名", example = "admin")
                                            @RequestParam("username") String username);

    /**
     * 获取认证用户信息
     *
     * @param platformUserId 平台用户ID
     * @param platformCode   平台编码
     * @return
     */
    @GetMapping(value = "/getUserByPlatformUserIdAndCode")
    ResResultVO<StructureAuthUser> getUserByPlatformUserIdAndCode(@ApiParam(value = "平台ID", example = "18888888888")
                                                         @RequestParam("platformUserId") String platformUserId,
                                                                  @ApiParam(value = "平台编码", example = "phone")
                                                         @RequestParam("platformCode") String platformCode);
}
