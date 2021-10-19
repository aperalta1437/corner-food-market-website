//package com.cornerfoodmarketwebsite.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.configuration.annotation.CorsRegistry;
//import org.springframework.web.servlet.configuration.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.configuration.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class CORSConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
////                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedMethods("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }
//}
