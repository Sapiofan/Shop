//package com.example.shopjava.configs.webmvc;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
//@EnableWebMvc
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
//        registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
//        registry.addResourceHandler("/static/img/**").addResourceLocations("classpath:/static/img/");
//    }
//
//    @Bean
//    public FreeMarkerViewResolver freemarkerViewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        resolver.setCache(false);
//        resolver.setContentType("text/html;charset=UTF-8");
//        resolver.setRequestContextAttribute("requestContext");
//
//        return resolver;
//    }
//
//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/ftl/");
//
//        return freeMarkerConfigurer;
//    }
//}
