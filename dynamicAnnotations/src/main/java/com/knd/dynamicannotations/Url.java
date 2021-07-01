package com.knd.dynamicannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Documented
@Target(METHOD)
@Retention(CLASS)
public @interface Url {
    String value() default "";
}
