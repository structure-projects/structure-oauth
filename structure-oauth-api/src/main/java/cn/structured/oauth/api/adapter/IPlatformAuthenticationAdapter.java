package cn.structured.oauth.api.adapter;


/**
 * 平台认证适配器
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IPlatformAuthenticationAdapter {

    String authentication(String code);
}
