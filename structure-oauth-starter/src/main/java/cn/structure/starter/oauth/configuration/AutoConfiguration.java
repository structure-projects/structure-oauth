package cn.structure.starter.oauth.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 自动装配
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/7 20:24
 */
@MapperScan(basePackages = "cn.structure.starter.oauth.mapper.**")
@ComponentScan(basePackages = "cn.structure.starter.oauth.**")
public class AutoConfiguration {
}
