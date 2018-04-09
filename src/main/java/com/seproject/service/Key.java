package com.seproject.service;

public @interface Key {
    //主键注解，加在领域对象的成员变量上。
    //要求所加的变量是基本数据类型或String类型。
    ValueType type() ;

}
