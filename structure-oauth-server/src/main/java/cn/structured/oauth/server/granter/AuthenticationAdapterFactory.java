package cn.structured.oauth.server.granter;

import cn.structured.oauth.api.adapter.IPlatformAuthenticationAdapter;
import com.alibaba.fastjson.JSONObject;

/**
 * 认证适配器工厂
 *
 * @author chuck
 * @since JDK1.8
 */
public class AuthenticationAdapterFactory {

    /**
     * 新建一个实例
     *
     * @param code code
     * @param args 参数
     * @return
     */
    public static IPlatformAuthenticationAdapter newInstance(String code, JSONObject args) {
        IPlatformAuthenticationAdapter authenticationAdapter = null;
        switch (code) {
            case "weChat":
                authenticationAdapter = new WeChatAuthenticationAdapter(args);
                break;
            case "dingTalk":
                authenticationAdapter = new DingTalkAuthenticationAdapter(args);
                break;
            default:
                break;
        }
        return authenticationAdapter;
    }

}
