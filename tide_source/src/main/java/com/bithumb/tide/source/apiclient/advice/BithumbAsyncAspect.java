package com.bithumb.tide.source.apiclient.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class BithumbAsyncAspect {
    @Before("@annotation(BithumbAsync)")
    public void apply(JoinPoint joinPoint) {
        Method asyncMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        PostMapping postMapping = asyncMethod.getAnnotation(PostMapping.class);
        AsyncPostMapping asyncPostMapping = new AsyncPostMapping(
                postMapping.name(),
                new String[]{"/async" + postMapping.value()[0]},
                postMapping.path(),
                postMapping.params(),
                postMapping.headers(),
                postMapping.consumes(),
                postMapping.produces()
        );


        alterAnnotationOn(asyncMethod, PostMapping.class, asyncPostMapping);
    }

    private static final String ANNOTATIONS = "annotations";
    public static final String ANNOTATION_DATA = "annotationData";
    private static void alterAnnotationOn(Method methodToLookFor, Class<? extends Annotation> annotationToAlter, Annotation annotationValue) {
        try {
            Class<?> executableClass = Class.forName("java.lang.reflect.Executable");
            Field field = executableClass.getDeclaredField("declaredAnnotations");
            field.setAccessible(true);
            Map<Class<? extends Annotation>, Annotation> map =
                    (Map<Class<? extends Annotation>, Annotation>) field.get(methodToLookFor);
            map.put(annotationToAlter, annotationValue);


//            Method method = Class.class.getDeclaredMethod(ANNOTATION_DATA, null);
//            method.setAccessible(true);
//
//            Object annotationData = method.invoke(methodToLookFor);
//            methodToLookFor.setAccessible();
//
//            Field annotations = annotationData.getClass().getDeclaredField(ANNOTATIONS);
//            annotations.setAccessible(true);
//            Map<Class<? extends Annotation>, Annotation> map =
//                    (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
//            map.put(annotationToAlter, annotationValue);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
