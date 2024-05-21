package com.example.ca4u.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Override //개발 시점
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
////        registry.addResourceHandler("/image/**")
////                .addResourceLocations("file:///C:/Users/Shuru2024_A00/Desktop/image/");
////        registry.addResourceHandler("/image/**")
////                .addResourceLocations("file:./image/");
//
//        registry.addResourceHandler("/image/**")
//                .addResourceLocations("file:///C:/Users/Shuru2024_A00/Desktop/Shuru_Nerd/goinmul/image/");
//    }

    //    배포 시점
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:/home/ubuntu/service/img/")
        ;
    }
}
