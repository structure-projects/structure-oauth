package cn.structured.oauth.server.configuration;

import lombok.Data;

/**
 * 适配器配置
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
public class AdapterProperties {

    private String host;

    private String accessKey;

    private String secretKey;
}
