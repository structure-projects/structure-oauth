package cn.structured.oauth.server.granter;

import cn.structure.common.exception.CommonException;
import cn.structured.oauth.api.adapter.IPlatformAuthenticationAdapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 平台认证管理器
 *
 * @author chuck
 * @since JDK1.8
 */
public class PlatformAuthenticationManager {

    private final static Map<String, IPlatformAuthenticationAdapter> ADAPTER_CACHE = new ConcurrentHashMap<>();

    public static String authentication(String type, String code) {
        IPlatformAuthenticationAdapter iPlatformAuthenticationAdapter = ADAPTER_CACHE.get(type);
        if (null == iPlatformAuthenticationAdapter) {
            //todo 抛出异常不存在的认证适配器
            throw new CommonException();
        }
        return iPlatformAuthenticationAdapter.authentication(code);
    }

    /**
     * 添加认证适配器
     *
     * @param code                  code
     * @param authenticationAdapter 认证适配器
     * @return {@link IPlatformAuthenticationAdapter}
     */
    public static IPlatformAuthenticationAdapter put(String code, IPlatformAuthenticationAdapter authenticationAdapter) {
        return ADAPTER_CACHE.put(code, authenticationAdapter);
    }

    /**
     * 移除认证适配器
     *
     * @param code code
     * @return {@link IPlatformAuthenticationAdapter}
     */
    public static IPlatformAuthenticationAdapter remove(String code) {
        return ADAPTER_CACHE.remove(code);
    }
}
