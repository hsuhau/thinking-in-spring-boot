package com.hsuhau.service.impl;

import com.hsuhau.service.Server;
import org.springframework.stereotype.Component;

// 根据 ImportSelector 的契约，请确保实现为 Spring 组件
@Component
public class HttpServer implements Server {
    @Override
    public void start() {
        System.out.println("HTTP 服务启动中...");
    }

    @Override
    public void stop() {
        System.out.println("HTTP 服务关闭中...");
    }
}
