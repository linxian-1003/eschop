package com.neu.base.anno;


//作用的地方
//有效范围

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerAnno {
    String listUI() default "";
    String saveUI() default "";
    String modelName();
    String msg();
}
