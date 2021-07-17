package cn.structure.starter.oauth.service;

/**
 * <p>
 * 验证码service
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 11:27
 */
public interface IVerificationCodeService {

    /**
     * 是否存在黑名单
     */
    boolean isExistBlacklist(String ipAddress);

    /**
     * 是否存在频繁发放
     * @param ipAddress ip地址
     */
    int isExistFrequently(String ipAddress);

    /**
     * 保存验证码信息
     */
    Integer save(String ipAddress, String code);

    /**
     * 验证
     */
    boolean check(Integer id, String code);

}
