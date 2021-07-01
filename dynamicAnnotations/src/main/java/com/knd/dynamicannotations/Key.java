package com.knd.dynamicannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Documented
@Target(PARAMETER)
@Retention(CLASS)
public @interface Key {
    /** The query parameter name. */
    String value();
}
