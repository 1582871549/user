package com.meng.user.common.config;

import com.meng.user.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/home/login")
                .excludePathPatterns("/home/register");

        ;
    }

    /**
     * 修改springboot中默认的静态文件路径
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler请求路径
        //addResourceLocations 在项目中的资源路径
        //setCacheControl 设置静态资源缓存时间
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
        ;
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    // 跨域配置
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")//设置允许跨域的路径
    //             .allowedOrigins("*")//设置允许跨域请求的域名
    //             .allowCredentials(true)//是否允许证书 不再默认开启
    //             .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
    //             .maxAge(3600);//跨域允许时间
    // }

}
