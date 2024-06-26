package cn.structured.oauth.user.api.dto.user;

import cn.structured.oauth.api.enums.VerificationCodeType;
import lombok.Data;

/**
 * 发送短信验证码
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class SendSmsCodeDto {

    /**
     * 验证码类型
     */
    private VerificationCodeType codeType;

    /**
     * 手机号
     */
    private String phone;

}
