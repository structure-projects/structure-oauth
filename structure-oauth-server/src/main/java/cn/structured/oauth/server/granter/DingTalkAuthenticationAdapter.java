package cn.structured.oauth.server.granter;

import cn.structured.oauth.api.adapter.IPlatformAuthenticationAdapter;
import com.alibaba.fastjson.JSONObject;

/**
 * 钉钉认证适配
 *
 * @author chuck
 * @since JDK1.8
 */
public class DingTalkAuthenticationAdapter implements IPlatformAuthenticationAdapter {


    public DingTalkAuthenticationAdapter(JSONObject params) {

    }

    @Override
    public String authentication(String code) {
        return null;
    }
}
