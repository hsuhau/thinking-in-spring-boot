package com.hsuhau.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StringRepository {
    /**
     * 属性方法名称必须与{@link Component#value()}保持一致
     * @return Bean的名称
     */
    String value() default "";
}
