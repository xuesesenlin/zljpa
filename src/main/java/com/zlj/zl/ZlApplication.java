package com.zlj.zl;

import com.zlj.zl.sysConfig.reuquest.MyWebFilter;
import groovy.util.logging.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories("com.zlj.zl.*.*.jpa")
@EnableScheduling
public class ZlApplication {
    //        extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ZlApplication.class, args);
    }

    //    错误页面
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

    //    自定义过滤器
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyWebFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }

    //    websocket
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    // war
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder
//                                                         builder) {
//        // TODO Auto-generated method stub
//        return builder.sources(Application.class);
//    }
}
