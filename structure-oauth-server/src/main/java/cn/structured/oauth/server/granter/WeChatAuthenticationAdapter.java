package cn.structured.oauth.server.granter;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.structure.common.exception.CommonException;
import cn.structured.oauth.api.adapter.IPlatformAuthenticationAdapter;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信认证适配
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
public class WeChatAuthenticationAdapter implements IPlatformAuthenticationAdapter {

    private String appId;

    private String appSecret;

    private WxMaService wxMaService;

    public WeChatAuthenticationAdapter(JSONObject params) {
        String accessKey = params.getString("accessKey");
        String secretKey = params.getString("secretKey");
        this.appId = accessKey;
        this.appSecret = secretKey;
        wxMaService = wxMaService(wxMaConfig());
    }

    @Override
    public String authentication(String code) {
        WxMaJscode2SessionResult sessionInfo;
        try {
            sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            log.error("微信小程序登录失败", e);
            throw new CommonException("", e.getMessage());
        }
        return sessionInfo.getOpenid();
    }


    private WxMaConfig wxMaConfig() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appId);
        config.setSecret(appSecret);
        return config;
    }

    private WxMaService wxMaService(WxMaConfig wxMaConfig) {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaConfig);
        return service;
    }
}
