package cn.structured.oauth.user.service;

import cn.structured.oauth.user.api.dto.user.SendEmailCodeDto;
import cn.structured.oauth.user.api.dto.user.SendSmsCodeDto;

/**
 * Code服务类
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IAuthCodeService {

    /**
     * 发送短信验证码
     *
     * @param sendSmsCodeDto 发送短信DTO
     */
    void sendSmsCode(SendSmsCodeDto sendSmsCodeDto);

    /**
     * 发送邮箱验证码
     *
     * @param sendEmailCodeDto 邮箱DTO
     */
    void sendEmailCode(SendEmailCodeDto sendEmailCodeDto);

}
