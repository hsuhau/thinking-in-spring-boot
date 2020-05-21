package com.hsuhau.bootstrap;

import com.hsuhau.annotation.TransactionalService;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@TransactionalService
public class TransactionalServiceStandardAnnotationMetadataBootstrap {
    // 基于Java反射API实现
    public static void main(String[] args) throws IOException {
        // 读取@TransactionService AnnotationMetadata 信息
//        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(TransactionalServiceStandardAnnotationMetadataBootstrap.class);
        AnnotationMetadata annotationMetadata = AnnotationMetadata.introspect(TransactionalServiceStandardAnnotationMetadataBootstrap.class);
        // 获取所有元注解类型（全类名）集合
        Set<String> metaAnnotationTypes = annotationMetadata
                .getAnnotationTypes()
                .stream()
                // 读取单注解的元注解类型集合
                .map(annotationMetadata::getMetaAnnotationTypes)
                // 合并元注解类型（全类名）类型
                .collect(LinkedHashSet::new, Set::addAll, Set::addAll);

        // 读取所有元注解类型
        metaAnnotationTypes.forEach(metaAnnotation -> {
            // 读取元注解属性信息
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(metaAnnotation);
            if (!CollectionUtils.isEmpty(annotationAttributes)) {
                annotationAttributes.forEach((name, value) ->
                        System.out.printf("注解 @%s 属性 %s = %s\n", ClassUtils.getShortName(metaAnnotation), name, value));
            }
        });


    }
}
