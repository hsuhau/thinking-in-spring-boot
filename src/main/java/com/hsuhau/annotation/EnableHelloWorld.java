package com.hsuhau.annotation;

import com.hsuhau.config.HelloWorldConfiguration;
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
@Documented
@Retention(RetentionPolicy.RUNTIME)
// 导入HelloWorldConfiguration
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
