package com.seproject.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Searchable {
    //可检索键
    //加此注释的变量可以作为增删改查中的键值，并支持返回多个元组。
    //需要在注释中显示地指出被注释变量的变量名
    String varName();

}
