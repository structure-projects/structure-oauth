package cn.structure.starter.oauth.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码黑名单
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 16:29
 */
@Data
public class VerificationCodeBlacklist {

    /**
     * id
     */
    private Integer id;

    /**
     * ip地址
     */
    private String address;

    /**
     * 添加黑名单时间
     */
    private LocalDateTime createTime;

}
