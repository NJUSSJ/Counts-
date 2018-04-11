

package com.seproject.service;

import com.seproject.dao.FileDao;
import com.seproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
                else if (type.startsWith("class java.util.ArrayList<java.util.ArrayList")) {//二重ArrayList
                    ArrayList<ArrayList<Object>> list=(ArrayList<ArrayList<Object>>) m.invoke(o);
                    for(ArrayList<Object> each:list) {
                        for(Object element:each) {
                            ArrayList<String> info=writeClass(element);
                            for(String eachInfo:info){
                                result.add(eachInfo);
                            }
                        }
                    }
                } else if (type.equals("class java.util.ArrayList")) {//ArrayList
                    ArrayList list= (ArrayList) m.invoke(o);
                    if(list.size()==0){
                        result.add("[]");
                        continue;
                    }else{
                        String category=list.get(0).getClass().toString();
                        System.out.println(category);
                        if((!category.startsWith("class java.lang"))){
                            String str="[";
                            for(Object each:list){
                                writeClass(each);
                                str=str+"<"+each.getClass().toString()+"#"+getKeyValue(each,getKeyID(each))+">,";
                            }
                            result.add(str.substring(0,str.length()-1)+"]");//去掉最后一个逗号
                        }else{
                            result.add(list.toString());
                        }
                    }
                }else if (type.startsWith("class")) {//自定义类
                    Object object=m.invoke(o);
                    writeClass(object);

                    int key=getKeyID(object);
                    result.add("<"+object.getClass().toString()+"#"+getKeyValue(object,key)+">");

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
        //System.out.println(result);
        if(fileDao.read_object(o.getClass().toString(),getKeyID(o),getKeyValue(o,getKeyID(o))).size()==0){
            fileDao.write_object(result,o.getClass().toString());
        }else{
            System.out.println("主键已经存在，不能写入");
        };


        return result;
    }


    public Object read(Object model,String keyValue){
        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组

        ArrayList<String> info=fileDao.read_object(model.getClass().toString(),getKeyID(model),keyValue);

        System.out.println("info:"+info);

        Method[] methods=model.getClass().getDeclaredMethods();
        try {
            for (int j = 0; j < field.length; j++) {     //遍历所有属性
                String name = field[j].getName();    //获取属性的名字
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = field[j].getGenericType().toString();
                System.out.println("type:"+type);
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
                    setter.invoke(model, new Object[]{info.get(j)});
                } else if (type.equals("java.util.ArrayList<java.lang.Integer>")) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    String content=info.get(j).replace("[","").replace("]","");

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Integer.parseInt(each));
                    }
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.Double>")) {
                    ArrayList<Double> tmp = new ArrayList<Double>();
                    String content=info.get(j).replace("[","").replace("]","");

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Double.parseDouble(each));
                    }
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.Boolean>")) {
                    ArrayList<Boolean> tmp = new ArrayList<Boolean>();
                    String content=info.get(j).replace("[","").replace("]","");

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Boolean.parseBoolean(each));
                    }
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.Long>")) {
                    ArrayList<Long> tmp = new ArrayList<Long>();
                    String content=info.get(j).replace("[","").replace("]","");

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Long.parseLong(each));
                    }
                    setter.invoke(model, new Object[]{tmp});
                } else if (type.equals("java.util.ArrayList<java.lang.String>")) {
                    ArrayList<String> tmp = new ArrayList<String>();
                    String content=info.get(j).replace("[","").replace("]","");

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(each);
                    }
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
                } else if (type.startsWith("java.util.ArrayList<")) {//ArrayList含自定义类型
                    ArrayList<Object> tmp = new ArrayList<Object>();
                    String content=info.get(j).replace("[","").replace("]","");
                    String[] details=content.split(",");
                    System.out.println(details[0]);
                    System.out.println(details.length);
                    for (int i = 0; i < details.length; i++) {

                        int begin_index=details[i].indexOf("<");
                        int middle_index=details[i].indexOf("#");
                        int end_index=details[i].indexOf(">");
                        String value=details[i].substring(middle_index+1,end_index);
                        String className=details[i].substring(begin_index+1,middle_index);

                        int sp=className.lastIndexOf(" ");
                        className=className.substring(sp+1);
                        System.out.println("value:"+value+" className:"+className);
                        Object o=Class.forName(className).newInstance();//用指针中的类名创建对象
                        o=read(o,value);
                        tmp.add(o);
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
                    String temp=info.get(j);
                    int begin_index=temp.indexOf("<");
                    int middle_index=temp.indexOf("#");
                    int end_index=temp.indexOf(">");
                    String value=temp.substring(middle_index+1,end_index);
                    String className=temp.substring(begin_index+1,middle_index);

                    int sp=className.lastIndexOf(" ");
                    className=className.substring(sp+1);
                    System.out.println("value:"+value+" className:"+className);
                    Object o=Class.forName(className).newInstance();//用指针中的类名创建对象
                    o=read(o,value);
/*                    System.out.println("value:"+value);
                    Object object=getter.invoke(model);
                    if(object==null){
                        System.out.println("NULL");
                    }
                    Object o=read(object,value);*/
                    System.out.println("I'm here!"+" value:"+value);
                    setter.invoke(model, new Object[]{o});
                } else {
                    if (type.equals("int")) {
                        int temp=Integer.parseInt(info.get(j));
                        setter.invoke(model, new Object[]{temp});//实际情况要根据content来决定
                    } else if (type.equals("double")) {
                        double temp=Double.parseDouble(info.get(j));
                        setter.invoke(model, new Object[]{temp});
                    }
                    if (type.equals("boolean")) {
                        boolean temp=Boolean.parseBoolean(info.get(j));
                        setter.invoke(model, new Object[]{temp});
                    }
                    if (type.equals("long")) {
                        long temp=Long.parseLong(info.get(j));
                        setter.invoke(model, new Object[]{temp});
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return model;
    }

    /**
     *
     * @param model
     * @return model里主键属性在所有属性中的位置，以0起始
     */
    public int getKeyID(Object model){
        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        int key=-1;
        for(int i=0;i<field.length;i++){
            if(field[i].getAnnotation(Key.class)!=null){
                key=i;
                break;
            }
        }
        return key;
    }

    /**
     *
     * @param o 对象
     * @param key 主键位置
     * @return o里主键属性的值，以String类型返回
     */
    public String getKeyValue(Object o,int key){
        Field[] fields=o.getClass().getDeclaredFields();
        String name = fields[key].getName();    //获取属性的名字
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            Method m=o.getClass().getMethod("get"+name);
            return m.invoke(o).toString();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getKeyID(Object model,String keyName){
        //方法重载，获得@Searchable(varName=keyName)的ID

        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        int key=-1;
        for(int i=0;i<field.length;i++){
            if(field[i].getAnnotation(Searchable.class)!=null){
                Annotation annotation=field[i].getAnnotation(Searchable.class);
                if(((Searchable) annotation).varName().equals(keyName)) {
                    key = i;
                    break;
                }
            }
        }
        return key;
    }

    @Autowired
    public void setFileDao(FileDao fileDao){this.fileDao=fileDao;}

}

