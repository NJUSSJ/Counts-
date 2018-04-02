package com.seproject.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Service
public class BasicUtilService {
    public boolean writeClass(Object o){
        Field[] field = o.getClass().getDeclaredFields();
        ArrayList<String> result=new ArrayList<String>();
        for(int i=0;i<field.length;i++){
            String temp="";
            String type=field[i].getType().toString();
            String name = field[i].getName();    //获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if(type.startsWith("class")){

            }else {
                try {
                    Method m = o.getClass().getMethod("get" + name);
                    if ("int".equals(type)) {
                        int value = (Integer) m.invoke(o);
                        temp=Integer.toString(value);
                    } else if ("double".equals(type)) {
                        double value = (Double) m.invoke(o);
                        temp=Double.toString(value);
                    } else if ("char".equals(type)) {
                        char value = (Character) m.invoke(o);
                        temp=Character.toString(value);
                    } else if ("boolean".equals(type)) {
                        boolean value = (Boolean) m.invoke(o);
                        temp=Boolean.toString(value);
                    } else if ("long".equals(type)) {
                        Long value = (Long) m.invoke(o);
                        temp=Long.toString(value);
                    } else {
                    }
                }catch (Exception e){
                    return false;
                }
            }
            result.add(temp);
        }
        //调用Dao进行write
        return true;
    }
}
