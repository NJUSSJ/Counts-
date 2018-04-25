

package com.seproject.service;

import com.seproject.dao.FileDao;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class BasicUtilService {
    private FileDao fileDao;
    public void writeClass(Object o){
        Field[] field = o.getClass().getDeclaredFields();
        ArrayList<String> result=new ArrayList<String>();
        for(int i=0;i<field.length;i++){
            String temp="";
            String type=field[i].getGenericType().toString();
            String name = field[i].getName();    //获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                Method m = o.getClass().getMethod("get" + name);
                if(type.equals("class java.lang.String")){//字符串
                    result.add((String)m.invoke(o));
                }
                else if (type.startsWith("java.util.ArrayList<java.util.ArrayList")) {//二重ArrayList
                    ArrayList<ArrayList<Object>> list=(ArrayList<ArrayList<Object>>) m.invoke(o);
                    if(list==null||list.size()==0){
                        result.add("[]");
                    }else{
                        String str="[";
                        for(int k=0;k<list.size();k++){
                            ArrayList eachList=list.get(k);
                                if(eachList.size()==0){
                                    str+="[]";
                                }else{
                                    String category=eachList.get(0).getClass().toString();
                                    if((!category.startsWith("class java.lang"))) {
                                        str+="[";
                                        for(Object eachObject:eachList){
                                            writeClass(eachObject);
                                            str+="<"+eachObject.getClass()+"#"+getKeyValue(eachObject,getKeyID(eachObject))+">,";
                                        }
                                        str=str.substring(0,str.length()-1)+"],";//一层list去掉末尾逗号
                                    }else{
                                        str+=eachList.toString()+",";
                                    }
                                }
                        }
                        str=str.substring(0,str.length()-1)+"]";//二层list去掉末尾逗号
                        result.add(str);
                    }
                } else if (type.startsWith("java.util.ArrayList")) {//ArrayList
                    ArrayList list= (ArrayList) m.invoke(o);
                    if(list==null||list.size()==0){
                        int firstIndex=type.indexOf("<");
                        int lastIndex=type.indexOf(">");
                        String className=type.substring(firstIndex+1,lastIndex);
                        System.out.println("className:"+className);
                        if(className.startsWith("class")) {
                            fileDao.read_class(className);
                        }//如果是自定义类型空list，则先创建对应的文件
                        result.add("[]");
                    }else{
                        String category=list.get(0).getClass().toString();
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
        ArrayList<String> objectInfo=fileDao.read_object(o.getClass().toString(),getKeyID(o),getKeyValue(o,getKeyID(o)));
        if(objectInfo==null||objectInfo.size()<=0){
            fileDao.write_object(result,o.getClass().toString());
        }else{
            System.out.println("主键已经存在，不能写入");
        };


    }


    public Object read(Object model,String keyValue){
        try {
            Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            String className=model.getClass().toString();
            int sp=className.indexOf(" ");
            className=className.substring(sp+1);
            model= Class.forName(className).newInstance();

            ArrayList<String> info=fileDao.read_object(model.getClass().toString(),getKeyID(model),keyValue);
            if(info==null||info.size()<=0){//非空判断
                return null;
            }

            Method[] methods=model.getClass().getDeclaredMethods();
            for (int j = 0; j < field.length; j++) {     //遍历所有属性
                String name = field[j].getName();    //获取属性的名字
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = field[j].getGenericType().toString();
                Method setter = null;
                for (Method each : methods) {
                    if (each.getName().equals("set" + name)) {
                        setter = each;
                    }
                }

                if (type.equals("class java.lang.String")) {
                    setter.invoke(model, info.get(j));
                } else if (type.equals("java.util.ArrayList<java.lang.Integer>")) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    String content=info.get(j);
                    content=content.substring(1,content.length()-1);

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Integer.parseInt(each));
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.lang.Double>")) {
                    ArrayList<Double> tmp = new ArrayList<Double>();
                    String content=info.get(j);
                    content=content.substring(1,content.length()-1);
                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Double.parseDouble(each));
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.lang.Boolean>")) {
                    ArrayList<Boolean> tmp = new ArrayList<Boolean>();
                    String content=info.get(j);
                    content=content.substring(1,content.length()-1);

                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Boolean.parseBoolean(each));
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.lang.Long>")) {
                    ArrayList<Long> tmp = new ArrayList<Long>();
                    String content=info.get(j);
                    content=content.substring(1,content.length()-1);
                    String[] details=content.split(", ");
                    for(String each:details){
                        tmp.add(Long.parseLong(each));
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.lang.String>")) {
                    ArrayList<String> tmp = new ArrayList<String>();
                    String content=info.get(j);
                    content=content.substring(1,content.length()-1);
                    while(true) {
                        int index=content.indexOf(", ");
                        if(index==-1){
                            if(content!=null&&content.length()>0) {
                                tmp.add(content);
                            }
                            break;
                        }
                        System.out.println("index"+index);
                        tmp.add(content.substring(0,index));
                        content=content.substring(index+2);
                        System.out.println("In the loop:"+content);
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>")) {
                    ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
                    String[] details=getDetails(info.get(j));
                    for (int i = 0; i < details.length; i++) {
                        ArrayList<Integer> each = new ArrayList<Integer>();
                        String[] eachContent=details[i].split(", ");
                        for (int k = 0; k < eachContent.length; k++) {
                            each.add(Integer.parseInt(eachContent[k]));
                        }
                        tmp.add(each);
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Double>>")) {
                    ArrayList<ArrayList<Double>> tmp = new ArrayList<ArrayList<Double>>();
                    String[] details=getDetails(info.get(j));
                    for (int i = 0; i < details.length; i++) {
                        ArrayList<Double> each = new ArrayList<Double>();
                        String[] eachContent=details[i].split(", ");
                        for (int k = 0; k < eachContent.length; k++) {
                            each.add(Double.parseDouble(eachContent[k]));
                        }
                        tmp.add(each);
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Long>>")) {
                    ArrayList<ArrayList<Long>> tmp = new ArrayList<ArrayList<Long>>();
                    String[] details=getDetails(info.get(j));
                    for (int i = 0; i < details.length; i++) {
                        ArrayList<Long> each = new ArrayList<Long>();
                        String[] eachContent=details[i].split(", ");
                        for (int k = 0; k < eachContent.length; k++) {
                            each.add(Long.parseLong(eachContent[k]));
                        }
                        tmp.add(each);
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.Boolean>>")) {
                    ArrayList<ArrayList<Boolean>> tmp = new ArrayList<ArrayList<Boolean>>();
                    String[] details=getDetails(info.get(j));
                    for (int i = 0; i < details.length; i++) {
                        ArrayList<Boolean> each = new ArrayList<Boolean>();
                        String[] eachContent=details[i].split(", ");
                        for (int k = 0; k < eachContent.length; k++) {
                            each.add(Boolean.parseBoolean(eachContent[k]));
                        }
                        tmp.add(each);
                    }
                    setter.invoke(model, tmp);
                } else if (type.equals("java.util.ArrayList<java.util.ArrayList<java.lang.String>>")) {
                    ArrayList<ArrayList<String>> tmp = new ArrayList<ArrayList<String>>();
                    String[] details=getDetails(info.get(j));
                    for (int i = 0; i < details.length; i++) {
                        ArrayList<String> each = new ArrayList<String>();
                        String[] eachContent=details[i].split(", ");
                        each.addAll(Arrays.asList(eachContent));
                        tmp.add(each);
                    }
                    setter.invoke(model, tmp);
                }  else if (type.startsWith("java.util.ArrayList<java.util.ArrayList<")) {//二维ArrayList含自定义类型
                    ArrayList<ArrayList<Object>> tmp =new ArrayList<ArrayList<Object>>();
                    String[] details=getDetails(info.get(j));
                    for (int i = 0; i < details.length; i++) {
                        ArrayList<Object> each = new ArrayList<Object>();
                        String[] eachContent=details[i].split(",");
                        for (int k = 0; k < eachContent.length; k++) {
                            Object o = createObjectByKey(eachContent[k]);
                            each.add(o);
                        }
                        tmp.add(each);
                    }
                    setter.invoke(model, tmp);
                }else if (type.startsWith("java.util.ArrayList<")) {//ArrayList含自定义类型
                    ArrayList<Object> tmp = new ArrayList<Object>();
                    String content=info.get(j);
                    content=content.substring(1,content.length()-1);
                    String[] details=content.split(",");
                    if(details.length<=0) {
                        for (int i = 0; i < details.length; i++) {
                            Object o = createObjectByKey(details[i]);
                            tmp.add(o);
                        }
                    }
                    setter.invoke(model, tmp);
                } else if (type.startsWith("class")) {
                    Object o=createObjectByKey(info.get(j));
                    setter.invoke(model, o);
                } else {
                    if (type.equals("int")) {
                        int temp=Integer.parseInt(info.get(j));
                        setter.invoke(model, temp);
                    } else if (type.equals("double")) {
                        double temp=Double.parseDouble(info.get(j));
                        setter.invoke(model, temp);
                    }
                    if (type.equals("boolean")) {
                        boolean temp=Boolean.parseBoolean(info.get(j));
                        setter.invoke(model, temp);
                    }
                    if (type.equals("long")) {
                        long temp=Long.parseLong(info.get(j));
                        setter.invoke(model, temp);
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

    /**
     *方法重载，获得@Searchable(varName=keyName)的ID
     */
    public int  getKeyID(Object model,String keyName){

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

    /**
     *
     * @param key 形如"<className,keyValue>"的指针字符串
     * @return 根据对象类型和主键值生成唯一的对象
     */
    private Object createObjectByKey(String key){
        int begin_index=key.indexOf("<");
        int middle_index=key.indexOf("#");
        int end_index=key.indexOf(">");
        String value=key.substring(middle_index+1,end_index);
        String className=key.substring(begin_index+1,middle_index);

        int sp=className.lastIndexOf(" ");
        className=className.substring(sp+1);
        Object o= null;//用指针中的类名创建对象
        try {
            o = Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        o=read(o,value);
        return o;
    }
    private String[] getDetails(String origin){
        if(origin.equals("[]")){
            String[] s=new String[0];
            return s;
        }
        String content=origin.substring(1,origin.length()-1);
        String[] details=content.split("\\],\\[");//[，]在java中有特殊意义，要转义
        String first=details[0];
        String last=details[details.length-1];
        details[0]=first.substring(1);
        details[1]=last.substring(0,last.length()-1);
        return details;
    }
    @Autowired
    public void setFileDao(FileDao fileDao){this.fileDao=fileDao;}

}

