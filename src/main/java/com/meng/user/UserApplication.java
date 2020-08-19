package com.meng.user;

import com.meng.user.common.config.DruidProperties;
import com.meng.user.common.config.ShiroProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author dujianwei
 * @create 2020-07-04
 */
@EnableConfigurationProperties({DruidProperties.class, ShiroProperties.class})
@SpringBootApplication
public class UserApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserApplication.class);
    }
}
