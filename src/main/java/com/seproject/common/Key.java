package com.seproject.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Key {
    //主键注解，加在领域对象的成员变量上。
    //要求所加的变量是基本数据类型或String类型。


}
