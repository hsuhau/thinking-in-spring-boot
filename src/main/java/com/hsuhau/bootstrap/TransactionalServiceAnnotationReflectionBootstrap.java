package com.hsuhau.bootstrap;

import com.hsuhau.annotation.TransactionalService;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TransactionalService(name = "test") // name属性内容
public class TransactionalServiceAnnotationReflectionBootstrap {
    public static void main(String[] args) {
////        Class 实现了 AnnotatedElement 接口
//        AnnotatedElement annotatedElement = TransactionalServiceAnnotationReflectionBootstrap.class;
////        从 AnnotatedElement 获取 TransactionalService
//        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);
////        显式调用属性方法 TransactionalService#name() 获取属性
////        String nameAttribute = transactionalService.name();
////        System.out.println("TransactionalService.name() = " + nameAttribute);
//
////        完全Java反射实现（ReflectionUtils为Spring反射类）
//        ReflectionUtils.doWithMethods(TransactionalService.class,
//                method -> System.out.printf("@TransactionalService.%s() = %s\n", method.getName(),
//                        ReflectionUtils.invokeMethod(method, transactionalService)),
////                method -> method.getParameterCount() == 0); // 选择无参数方法
//                method -> !method.getDeclaringClass().equals(Annotation.class));
//    }


//        Class 实现了 AnnotatedElement 接口
        AnnotatedElement annotatedElement = TransactionalServiceAnnotationReflectionBootstrap.class;
//        从 AnnotatedElement 获取 TransactionalService
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);

        Set<Annotation> metaAnnotations = getAllMetaAnnotations(transactionalService);

        metaAnnotations.forEach(TransactionalServiceAnnotationReflectionBootstrap::printAnnotationAttribute);
    }

    private static void printAnnotationAttribute(Annotation annotation) {
        Class<?> annotationType = annotation.annotationType();
//        完全Java反射实现（ReflectionUtils为Spring反射类）
        ReflectionUtils.doWithMethods(annotationType,
                method -> System.out.printf("@TransactionalService.%s() = %s\n", method.getName(),
                        ReflectionUtils.invokeMethod(method, annotation)),
//                method -> method.getParameterCount() == 0); // 选择无参数方法
                method -> !method.getDeclaringClass().equals(Annotation.class));

    }

    private static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {
        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();
        // 没有找到，返回空集合
        if (ObjectUtils.isEmpty(metaAnnotations)) {
            return Collections.emptySet();
        }

        // 获取所有非Java标准元注解集合
        Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations)
                //排除Java标准注解，如@Target @Documented等，他们相互依赖，将导致递归不断，通过java.lang.annotation包名排除
                .filter(metaAnnotation ->
                        !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());

        // 递归查找元注解的元注解集合
        Set<Annotation> metaMetaAnnotationsSet = metaAnnotationsSet.stream()
                .map(TransactionalServiceAnnotationReflectionBootstrap::getAllMetaAnnotations).collect(HashSet::new, Set::addAll, Set::addAll);
        //添加递归结果
        metaAnnotationsSet.addAll(metaMetaAnnotationsSet);
        return metaAnnotationsSet;
    }


}
