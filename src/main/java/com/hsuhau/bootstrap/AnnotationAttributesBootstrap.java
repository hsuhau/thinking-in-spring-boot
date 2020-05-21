package com.hsuhau.bootstrap;


import com.hsuhau.bean.TransactionalServiceBean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;

public class AnnotationAttributesBootstrap {
    public static void main(String[] args) {
//        AnnotatedElement annotatedElement = TransactionalService.class;
        AnnotatedElement annotatedElement = TransactionalServiceBean.class;

        // 获取@Service注解属性独享
        AnnotationAttributes serviceAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);

        // 获取@Transactionsl注解属性独享
        AnnotationAttributes transactionalAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);

        print(serviceAttributes);
        print(transactionalAttributes);
    }

    private static void print(AnnotationAttributes annotationAttributes) {
        System.out.printf("注解 @%s 属性集合 \n：", annotationAttributes.annotationType().getName());
        annotationAttributes.forEach((name, value) ->
                System.out.printf("\t 属性 %s : %s \n", name, value));
    }
}
