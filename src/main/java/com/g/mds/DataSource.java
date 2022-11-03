package com.g.mds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    @AliasFor("key")
    DataSourceKey value() default DataSourceKey.MASTER;

    @AliasFor("value")
    DataSourceKey key() default DataSourceKey.MASTER;
}
