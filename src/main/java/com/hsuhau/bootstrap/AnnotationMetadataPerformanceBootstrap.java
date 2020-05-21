package com.hsuhau.bootstrap;

import com.hsuhau.annotation.TransactionalService;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

public class AnnotationMetadataPerformanceBootstrap {
    public static void main(String[] args) throws IOException {
        // 反射实现
//        AnnotationMetadata standardAnnotationMetadata = new StandardAnnotationMetadata(TransactionalService.class);
        AnnotationMetadata standardAnnotationMetadata = AnnotationMetadata.introspect(TransactionalService.class);

        // ASM实现
        SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();
        AnnotationMetadata asmAnnotationMetadata = factory.getMetadataReader(TransactionalService.class.getName()).getAnnotationMetadata();

        int times = 10 * 10000;
        int[] multiples = new int[]{1, 10, 100, 1000};
        for (int multiple : multiples) {
            testAnnotationMetadataPerformance(standardAnnotationMetadata, times, multiple);
            testAnnotationMetadataPerformance(asmAnnotationMetadata, times, multiple);
        }
    }

    private static void testAnnotationMetadataPerformance(AnnotationMetadata annotationMetadata, int times, int multiple) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times * multiple; i++) {
            annotationMetadata.getAnnotationTypes();
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("%d 次 %s.getAnnotationTypes() 方法执行消耗 %s ms\n",
                times * multiple, annotationMetadata.getClassName(), endTime - startTime);
    }
}
