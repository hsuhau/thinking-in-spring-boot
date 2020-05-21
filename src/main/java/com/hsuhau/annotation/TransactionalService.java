package com.hsuhau.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@Service(value = "transactionalService")
public @interface TransactionalService {
    /**
     * @return 服务 Bean 名称
     */
//    @AliasFor("transactionManager")
    String name() default "txManager";

    /**
     * 覆盖{@link org.springframework.transaction.PlatformTransactionManager}Bean 名称，默认关联 "txManager" Bean
     */
//    String transactionalManager() default "txManager";
    @AliasFor("name")
    String transactionManager() default "txManager";

    //    @AliasFor("transactionManager")
//    @AliasFor("name")
//    String value() default "txManager";
    @AliasFor(attribute = "transactionManager", annotation = Transactional.class)
    String manager() default "txManager";
}
