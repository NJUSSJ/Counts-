package com.seproject.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Service
public class BasicUtilService {
    public ArrayList<String> writeClass(Object o){
        Field[] field = o.getClass().getDeclaredFields();
        ArrayList<String> result=new ArrayList<String>();
        for(int i=0;i<field.length;i++){
            String temp="";
            String type=field[i].getType().toString();
            String name = field[i].getName();    //获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                Method m = o.getClass().getMethod("get" + name);
                if (type.startsWith("class")) {
                    ArrayList<String> info=writeClass(m.invoke(o));
                    for(String each:info){
                        result.add(name+":"+type+":"+each);
                    }
                } else if (type.startsWith("java.util.ArrayList<java.util.ArrayList")) {
                    ArrayList<ArrayList<Object>> list=(ArrayList<ArrayList<Object>>) m.invoke(o);
                    for(ArrayList<Object> each:list) {
                        for(Object element:each) {
                            ArrayList<String> info=writeClass(element);
                            for(String eachInfo:info){
                                result.add(name+":"+type+":"+each);
                            }
                        }
                    }
                } else if (type.startsWith("java.util.ArrayList")) {
                    ArrayList<Object> list= (ArrayList<Object>) m.invoke(o);
                    for(Object each:list){
                        ArrayList<String> info=writeClass(each);
                        for(String eachInfo:info){
                            result.add(name+":"+type+":"+eachInfo);
                        }
                    }
                } else {
                    if ("int".equals(type)) {
                        int value = (Integer) m.invoke(o);
                        temp = Integer.toString(value);
                    } else if ("double".equals(type)) {
                        double value = (Double) m.invoke(o);
                        temp = Double.toString(value);
                    } else if ("char".equals(type)) {
                        char value = (Character) m.invoke(o);
                        temp = Character.toString(value);
                    } else if ("boolean".equals(type)) {
                        boolean value = (Boolean) m.invoke(o);
                        temp = Boolean.toString(value);
                    } else if ("long".equals(type)) {
                        Long value = (Long) m.invoke(o);
                        temp = Long.toString(value);
                    } else {
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            result.add(temp);
        }
        //调用Dao进行write
        return result;
    }
}
