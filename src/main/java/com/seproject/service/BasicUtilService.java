package com.seproject.service;

import com.seproject.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Service
public class BasicUtilService {
    private FileDao fileDao;
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
                if(type.equals("class java.lang.String")){//字符串
                    result.add((String)m.invoke(o));
                }
                else if (type.startsWith("class")) {//自定义类
                    ArrayList<String> info=writeClass(m.invoke(o));
                    for(String each:info){
                        result.add(each);
                    }
                } else if (type.startsWith("java.util.ArrayList<java.util.ArrayList")) {//二重ArrayList
                    ArrayList<ArrayList<Object>> list=(ArrayList<ArrayList<Object>>) m.invoke(o);
                    for(ArrayList<Object> each:list) {
                        for(Object element:each) {
                            ArrayList<String> info=writeClass(element);
                            for(String eachInfo:info){
                                result.add(eachInfo);
                            }
                        }
                    }
                } else if(type.startsWith("java.util.ArrayList<java.lang.Integer>")){
                    ArrayList<Integer> list=(ArrayList<Integer>) m.invoke(o);
                    for(int each:list) {
                        result.add(""+each);
                    }
                }else if(type.startsWith("java.util.ArrayList<java.lang.Double>")){
                    ArrayList<Double> list=(ArrayList<Double>) m.invoke(o);
                    for(double each:list) {
                        result.add(""+each);
                    }
                }else if(type.startsWith("java.util.ArrayList<java.lang.Long>")){
                    ArrayList<Long> list=(ArrayList<Long>) m.invoke(o);
                    for(long each:list) {
                        result.add(""+each);
                    }
                }else if(type.startsWith("java.util.ArrayList<java.lang.Boolean>")){
                    ArrayList<Boolean> list=(ArrayList<Boolean>) m.invoke(o);
                    for(boolean each:list) {
                        result.add(""+each);
                    }
                }else if(type.startsWith("java.util.ArrayList<java.lang.String>")){
                    ArrayList<String> list=(ArrayList<String>) m.invoke(o);
                    for(String each:list) {
                        result.add(each);
                    }
                }else if (type.startsWith("java.util.ArrayList")) {//自定义类ArrayList
                    ArrayList<Object> list= (ArrayList<Object>) m.invoke(o);
                    for(Object each:list){
                        ArrayList<String> info=writeClass(each);
                        for(String eachInfo:info){
                            result.add(eachInfo);
                        }
                    }
                } else {//基本类型
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
                        long value = (Long) m.invoke(o);
                        temp = Long.toString(value);
                    } else {
                    }
                    result.add(temp);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //调用Dao进行write
        fileDao.write_object(result,o.getClass().toString());
        return result;
    }


    public Object read(Object model,String content){
        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        int key=-1;
        for(int i=0;i<field.length;i++){
            if(field[i].getAnnotation(Key.class)!=null){
                key=i;
                break;
            }
        }
        System.out.println(key);

        ArrayList<String> info=fileDao.read_Object(model.getClass().toString(),key,"15");

        System.out.println(info);

        Method[] methods=model.getClass().getDeclaredMethods();
        try {
            for (int j = 0; j < field.length; j++) {     //遍历所有属性
                String name = field[j].getName();    //获取属性的名字
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = field[j].getGenericType().toString();
                Method getter = null;
                Method setter = null;
                for (Method each : methods) {
                    if (each.getName().equals("set" + name)) {
                        setter = each;
                    }
                    if (each.getName().equals("get" + name)) {
                        getter = each;
                    }
                }

                System.out.println(" type:" + type);
                if (type.equals("class java.lang.String")) {
                    setter.invoke(model, new Object[]{"haha"});
                } else if (type.equals("java.util.ArrayList<java.lang.Integer>")) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(5);
                    tmp.add(6);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.Double>")) {
                    ArrayList<Double> tmp = new ArrayList<Double>();
                    tmp.add(7.0);
                    tmp.add(8.0);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.Boolean>")) {
                    ArrayList<Boolean> tmp = new ArrayList<Boolean>();
                    tmp.add(true);
                    tmp.add(false);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.Long>")) {
                    ArrayList<Long> tmp = new ArrayList<Long>();
                    tmp.add((long) 9);
                    tmp.add((long) 10);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.String>")) {
                    ArrayList<String> tmp = new ArrayList<String>();
                    tmp.add("fafa");
                    tmp.add(("tata"));
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.startsWith("java.util.ArrayList<java.util.ArrayList<")) {
                    ArrayList<ArrayList<Object>> tmp = (ArrayList<ArrayList<Object>>) getter.invoke(model);
                    for (int i = 0; i < tmp.size(); i++) {
                        ArrayList<Object> each = tmp.get(i);
                        for (int k = 0; k < each.size(); k++) {
                            Object o = each.get(k);
                            o = read(o, "lala");
                            each.set(k, o);
                        }
                    }
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.startsWith("java.util.ArrayList<")) {
                    ArrayList<Object> tmp = (ArrayList<Object>) getter.invoke(model);
                    for (int i = 0; i < tmp.size(); i++) {
                        Object o = tmp.get(i);
                        o = read(o, "ssj");
                        tmp.set(i, o);
                    }
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>")) {
                    ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
                    tmp.add(new ArrayList<Integer>());
                    tmp.add(new ArrayList<Integer>());
                    tmp.get(0).add(0);
                    tmp.get(0).add(2);
                    tmp.get(1).add(2);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Double>>")) {
                    ArrayList<ArrayList<Double>> tmp = new ArrayList<ArrayList<Double>>();
                    tmp.add(new ArrayList<Double>());
                    tmp.add(new ArrayList<Double>());
                    tmp.get(0).add(1.0);
                    tmp.get(0).add(2.0);
                    tmp.get(1).add(2.0);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Long>>")) {
                    ArrayList<ArrayList<Long>> tmp = new ArrayList<ArrayList<Long>>();
                    tmp.add(new ArrayList<Long>());
                    tmp.add(new ArrayList<Long>());
                    tmp.get(0).add((long) 2);
                    tmp.get(0).add((long) 2);
                    tmp.get(1).add((long) 2);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Boolean>>")) {
                    ArrayList<ArrayList<Boolean>> tmp = new ArrayList<ArrayList<Boolean>>();
                    tmp.add(new ArrayList<Boolean>());
                    tmp.add(new ArrayList<Boolean>());
                    tmp.get(0).add(false);
                    tmp.get(0).add(true);
                    tmp.get(1).add(true);
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.String>>")) {
                    ArrayList<ArrayList<String>> tmp = new ArrayList<ArrayList<String>>();
                    tmp.add(new ArrayList<String>());
                    tmp.add(new ArrayList<String>());
                    tmp.get(0).add("kaka");
                    tmp.get(0).add("papa");
                    tmp.get(1).add("dada");
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.startsWith("class")) {
        		/*Object o=read(getter.invoke(model),"boolean:b:false");
        		setter.invoke(model, new Object[] {o});*/
                    Object o = read(getter.invoke(model), "lala");
                    setter.invoke(model, new Object[]{o});
                } else {
                    if (type.equals("int")) {
                        setter.invoke(model, new Object[]{5});//实际情况要根据content来决定
                    } else if (type.equals("double")) {
                        setter.invoke(model, new Object[]{4.0});
                    }
                    if (type.equals("boolean")) {
                        setter.invoke(model, new Object[]{false});
                    }
                    if (type.equals("long")) {
                        setter.invoke(model, new Object[]{90});
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return model;
    }

    @Autowired
    public void setFileDao(FileDao fileDao){this.fileDao=fileDao;}

}
