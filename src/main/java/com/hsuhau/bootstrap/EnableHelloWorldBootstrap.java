package com.hsuhau.bootstrap;

import com.hsuhau.annotation.EnableHelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@EnableHelloWorld
@Configuration
public class EnableHelloWorldBootstrap {
    public static void main(String[] args) {
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前引导类（被@Configuration 标注） 到 Spring 上下文
        context.register(EnableHelloWorldBootstrap.class);
        // 启动上下文
        context.refresh();
        // 获取名称为 "helloWorld" 的 Bean 对象
        String helloWorld = context.getBean("helloWorld", String.class);
        // 输出用户名称： Hello,World
        System.out.printf("helloWorld = %s \n", helloWorld);
        // 关闭上下文
        context.close();
    }
}
