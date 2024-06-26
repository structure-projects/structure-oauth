package cn.structured.user;

import cn.structure.starter.web.restful.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动入口
 *
 * @author chuck
 * @since JDK1.8
 */
@EnableSwagger
@SpringBootApplication
public class UserBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBootApplication.class,args);
    }
}
