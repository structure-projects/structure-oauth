package cn.structured.oauth.user.configuration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 短信自动装配类
 *
 * @author chuck
 * @since JDK1.8
 */
@Configuration
public class SmsConfiguration {


    @Resource
    private SmsConfig smsConfig;

    @Bean
    @ConditionalOnProperty( value = "structure.user.sms.enable",havingValue = "true")
    public IAcsClient acsClient() {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(smsConfig.getRegionId(), smsConfig.getAccessKeyId(), smsConfig.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint(smsConfig.getEndpointName(), smsConfig.getRegionId(), smsConfig.getProduct(), smsConfig.getDomain());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new DefaultAcsClient(profile);
    }

}
