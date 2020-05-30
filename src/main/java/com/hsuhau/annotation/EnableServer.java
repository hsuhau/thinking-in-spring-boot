package com.hsuhau.annotation;

import com.hsuhau.registrar.ServerImportBeanDefinitionRegistrar;
import com.hsuhau.service.Server;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hsuhau
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 导入 ServerImportSelector
//@Import(ServerImportSelector.class)
// 替换 ServerImportBeanDefinitionRegistrar
@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {
    /**
     * 设置服务器类型
     *
     * @return non-null
     */
    Server.Type type();
}
