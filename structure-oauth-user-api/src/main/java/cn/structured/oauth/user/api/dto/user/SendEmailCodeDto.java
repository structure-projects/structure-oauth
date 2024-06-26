package cn.structured.oauth.user.api.dto.user;

import cn.structured.oauth.api.enums.VerificationCodeType;
import lombok.Data;

/**
 * 发送邮箱验证码
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class SendEmailCodeDto {

    /**
     * 验证码类型
     */
    private VerificationCodeType codeType;

    /**
     * 邮箱地址
     */
    private String email;

}
