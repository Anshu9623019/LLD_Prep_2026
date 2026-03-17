package org.example.Annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface CustomAnnotation {
    String name() default "RAM";
    int value() default 999;
    int []tags() default {4,5,6,7};
}
