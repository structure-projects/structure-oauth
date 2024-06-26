package cn.structured.oauth.user.controller.web;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.oauth.user.api.dto.user.SendEmailCodeDto;
import cn.structured.oauth.user.api.dto.user.SendSmsCodeDto;
import cn.structured.oauth.user.service.IAuthCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证码控制器
 *
 * @author chuck
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/web-api/auth-code")
public class AuthCodeController {

    @Resource
    private IAuthCodeService iAuthCodeService;

    /**
     * 发送手机号验证码
     *
     * @return 返回成功或失败即可
     */
    @PostMapping(value = "/send-phone-auth-code")
    public ResResultVO<Void> sendPhoneAuthCode(@RequestBody SendSmsCodeDto sendSmsCodeDto) {
        iAuthCodeService.sendSmsCode(sendSmsCodeDto);
        return ResultUtilSimpleImpl.success(null);
    }


    /**
     * 发送邮箱验证码
     *
     * @return 返回成功或失败即可
     */
    @PostMapping(value = "/send-email-auth-code")
    public ResResultVO<Void> sendEmailAuthCode(@RequestBody SendEmailCodeDto sendEmailCodeDto) {
        iAuthCodeService.sendEmailCode(sendEmailCodeDto);
        return ResultUtilSimpleImpl.success(null);
    }
}
