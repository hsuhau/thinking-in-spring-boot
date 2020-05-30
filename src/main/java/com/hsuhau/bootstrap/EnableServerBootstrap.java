package com.hsuhau.bootstrap;

import com.hsuhau.annotation.EnableServer;
import com.hsuhau.service.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author hsuhau
 */
@Configuration
// 设置为HTTP服务器类型
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootstrap {
    public static void main(String[] args) {
        // 构建Annotation配置驱动上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前引导类（被@Configuration标注）到Spring上下文
        context.register(EnableServerBootstrap.class);
        // 启动上下文
        context.refresh();
        // 获取Server Bean对象，实际为HttpServer
        Server server = context.getBean(Server.class);
        // 启动服务器
        server.start();
        // 关闭服务器
        server.stop();
        context.close();
    }
}
