package cn.structure.starter.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 错误码 - 枚举类
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 11:15
 */
@Getter
@AllArgsConstructor
public enum ErrCodeEnum {

    VERIFICATION_CODE_CHECK_FAILURE("101001","验证码校验失败！"),
    ERR_CODE_601("100601","用户存在验证码黑名单中！"),
    ERR_CODE_602("100602","用户一分钟内调用超过5次拒绝执行！"),
    ERR_CODE_603("100603","用户30分钟内调用超过20次拒绝执行！"),
    ERR_CODE_604("100604","用户一小时内调用超过50次拒绝执行！"),
    ERR_CODE_605("100605","用户一日内调用超过100次拒绝执行！"),

    ERR_EXIST_USER("100101","用户存在！"),
    ERR_VALIDATE_PASSWORD("100102","密码验证错误！"),

    ERR_NOT_ENABLE_CODE("100201","未开启验证码功能！"),
    ERR_NOT_ENABLE_REGISTER("100202","未开启注册功能！"),

    ERR_USER_PASSWORD("100301","用户密码错误！"),
    ERR_USER_LOCK("100302","用户锁定！请联系管理员！"),

    ;

    private String code;

    private String msg;

}
