package cn.structure.example.oauth;

import cn.structure.starter.web.restful.annotation.EnableSimpleGlobalException;
import cn.structure.starter.web.restful.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *      工程管理ERP启动程序
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/3/10 21:35
 */
@EnableSwagger
@EnableSimpleGlobalException
@SpringBootApplication
public class OauthExampleApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(OauthExampleApplication.class,args);
    }
}
