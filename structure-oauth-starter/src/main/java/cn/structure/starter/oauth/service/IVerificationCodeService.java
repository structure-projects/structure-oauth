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
     * @param ipAddress
     * @return
     */
    boolean isExistBlacklist(String ipAddress);

    /**
     * 是否存在频繁发放
     * @param ipAddress ip地址
     * @return
     */
    int isExistFrequently(String ipAddress);

    /**
     * 保存验证码信息
     * @param ipAddress
     * @param code
     * @return
     */
    Integer save(String ipAddress, String code);

    /**
     * 验证
     * @param id
     * @param code
     * @return
     */
    boolean check(Integer id, String code);

}
