package com.minzheng.blog.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD) // 定义注解的作用目标
@Retention(RetentionPolicy.RUNTIME) // 定义注解的保留策略 Source class runtime三种
@Documented //该注解会被包含在javadoc中
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";

}
