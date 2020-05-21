package com.hsuhau.bootstrap;

import com.hsuhau.repository.NameRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DerivedComponentAnnotationBootstrap {
//    static {
//        System.setProperty("java.version", "1.7.0");
//    }
public static void main(String[] args) {
//    构建XML配置驱动spring上下文
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
//    设置XML配置文件的位置
    context.setConfigLocation("classpath:/META-INF/spring/context.xml");
//    启动上下文
    context.refresh();
//    获取名称为chineseNameRepository"的Bean对象
    NameRepository nameRepository = (NameRepository) context.getBean("chineseNameRepository");
//    输出用于名称：[张三，李四，王五]
    System.out.println("nameRepository.findAll() = " + nameRepository.findAll());
}
}
