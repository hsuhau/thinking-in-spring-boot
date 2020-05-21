package com.hsuhau.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Indexed;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@SpringBootApplication

/**
 * 非@Configuration类WebConfiguration在当前项目行为上与改造前无异，
 * 说明SpringApplication#run(Class,String...)方法引导Springboot应用时，
 * 并不强依赖于@Configuration类作为首参，该参数称为“首要配置源”--primarySource，
 * 更深层次的讨论请参考
 */
//@EnableAutoConfiguration
@Indexed
@Configuration
public class WebConfiguration {
    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello-world"), request -> ok().body(Mono.just("Hello,world"), String.class));
    }

//    @Bean
//    public ApplicationRunner runner(WebServerApplicationContext context) {
//        return args -> {
//            System.out.println("当前WebServer实现类为: "
//                    + context.getWebServer().getClass().getName());
//        };
//    }

    @Bean
    public ApplicationRunner runner(BeanFactory beanFactory) {
        return args -> {
            System.out.println("当前helloworld实现类为: "
                    + beanFactory.getBean("helloWorld").getClass().getName());
            System.out.println("当前WebConfiguration Bean实现类为"
                    + beanFactory.getBean(WebConfiguration.class).getClass().getName());
        };
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("当前WebServer实现类为： " +
                event.getWebServer().getClass().getName());
    }
}
