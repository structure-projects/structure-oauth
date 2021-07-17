package cn.structure.starter.oauth.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码信息
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 16:31
 */
@Data
public class VerificationCodeInfo {

    /**
     * 验证码ID
     */
    private Integer id;

    /**
     * code码
     */
    private String code;

    /**
     * 是否校验
     */
    private Boolean check;

    /**
     * ip地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
