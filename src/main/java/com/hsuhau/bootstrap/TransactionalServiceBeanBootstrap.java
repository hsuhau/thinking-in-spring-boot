package com.hsuhau.bootstrap;

import com.hsuhau.bean.TransactionalServiceBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;

import java.util.Map;

@Configuration
// 扫描 TransactionalServiceBean 所在的 package
@ComponentScan(basePackageClasses = TransactionalServiceBean.class)
@EnableTransactionManagement
public class TransactionalServiceBeanBootstrap {
    public static void main(String[] args) {
//        注册当前引导类作为 Configuration.class
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TransactionalServiceBeanBootstrap.class);
//        获取所有 TransactionalServiceBean 类型 Bean，其中 key 为 Bean 名称
        Map<String, TransactionalServiceBean> beansMap = context.getBeansOfType(TransactionalServiceBean.class);
        beansMap.forEach((beanName, bean) -> {
            System.out.printf("Bean 名称：%s ，对象：%s\n", beanName, bean);
            bean.save();
        });
        context.close();
    }

    /*
     * Bean 名称：transactionalServiceBean ，对象：com.hsuhau.bean.TransactionalServiceBean@38604b81
     * WARNING: An illegal reflective access operation has occurred
     * WARNING: Illegal reflective access by org.springframework.cglib.core.ReflectUtils (file:/C:/Users/hsuhau/.m2/repository/org/springframework/spring-core/5.2.6.RELEASE/spring-core-5.2.6.RELEASE.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
     * WARNING: Please consider reporting this to the maintainers of org.springframework.cglib.core.ReflectUtils
     * WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
     * WARNING: All illegal access operations will be denied in a future release
     * Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.transaction.TransactionManager' available
     * 	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:354)
     * 	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:345)
     * 	at org.springframework.transaction.interceptor.TransactionAspectSupport.determineTransactionManager(TransactionAspectSupport.java:480)
     * 	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:335)
     * 	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118)
     * 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
     * 	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)
     * 	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)
     * 	at com.hsuhau.bean.TransactionalServiceBean$$EnhancerBySpringCGLIB$$bcd41c96.save(<generated>)
     * 	at com.hsuhau.bootstrap.TransactionalServiceBeanBootstrap.lambda$main$0(TransactionalServiceBeanBootstrap.java:24)
     * 	at java.base/java.util.LinkedHashMap.forEach(LinkedHashMap.java:684)
     * 	at com.hsuhau.bootstrap.TransactionalServiceBeanBootstrap.main(TransactionalServiceBeanBootstrap.java:22)
     */

    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {
                // todo 为什么这里会执行
                System.out.println("txManager ：事务提交...");
            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {

            }
        };
    }

    @Bean("txManager2")
    public PlatformTransactionManager txManager2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("txManager2 ：事务提交...");
            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {

            }
        };
    }
}
