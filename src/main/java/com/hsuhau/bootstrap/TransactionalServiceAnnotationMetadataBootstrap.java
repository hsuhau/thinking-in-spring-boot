package com.hsuhau.bootstrap;

import com.hsuhau.annotation.TransactionalService;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.Set;

//    @TransactionalService标注在当前类上
@TransactionalService
public class TransactionalServiceAnnotationMetadataBootstrap {
    public static void main(String[] args) throws Exception {
        String className = TransactionalServiceAnnotationMetadataBootstrap.class.getName();
//        构建MetadataReaderFactory实例
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
//        读取@TransactionalService MetadataReader信息
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);
//        读取@TransactionalService AnnotationMetadata信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        annotationMetadata.getAnnotationTypes().forEach(
                annotationType -> {
                    Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);
//                    与@TransactionalService关联的元注解类名为4个，所以会打印四条数据
                    metaAnnotationTypes.forEach(
                            metaAnnotationType -> {
                                System.out.printf("注解 @%s 元标注 @%s\n", annotationType, metaAnnotationType);
                            }
                    );
                }
        );
    }
}
