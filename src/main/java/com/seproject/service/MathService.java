package com.seproject.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MathService<T> {
    /**
     * 并运算
     */
    BasicUtilService basicUtilService=new BasicUtilService();
    public ArrayList<T> union(ArrayList<T> list1,ArrayList<T> list2){
        boolean emptyFirst=false;
        boolean emptySecond=false;
        if(list1==null||list1.size()==0){ emptyFirst=true; }
        if(list2==null||list2.size()==0){ emptySecond=true; }

        if(emptyFirst&&emptySecond){ return new ArrayList<T>(); }
        else if(emptyFirst){ return list2;}
        else if(emptySecond){ return list1; }
        else{
            ArrayList<T> l1=new ArrayList<T>(list1);
            ArrayList<T> l2=new ArrayList<T>(list2);
            int key=basicUtilService.getKeyID(list1.get(0));
            ArrayList<String> keyOfList1=new ArrayList<String>();
            ArrayList<T> result=new ArrayList<T>();
            result.addAll(l1);
            for(T each:l1){
                keyOfList1.add(basicUtilService.getKeyValue(each,key));
            }
            for(int i=0;i<l2.size();i++){
                String keyValue=basicUtilService.getKeyValue(l2.get(i),key);
                if(keyOfList1.contains(keyValue)){
                    l2.remove(i);
                    i--;
                }
            }
          result.addAll(l2);
            return result;
        }
    }

    /**
     * 交运算
     */
    public ArrayList<T> intersection(ArrayList<T> list1,ArrayList<T> list2){
        boolean emptyFirst=false;
        boolean emptySecond=false;
        if(list1==null||list1.size()==0){ emptyFirst=true; }
        if(list2==null||list2.size()==0){ emptySecond=true; }

        if(emptyFirst&&emptySecond){ return new ArrayList<T>(); }
        else if(emptyFirst){ return list2;}
        else if(emptySecond){ return list1; }
        else{
            int key=basicUtilService.getKeyID(list1.get(0));
            ArrayList<String> keyOfList1=new ArrayList<String>();
            ArrayList<T> result=new ArrayList<T>();
            for(T each:list1){
                keyOfList1.add(basicUtilService.getKeyValue(each,key));
            }
            for(T each:list2){
                String keyValue=basicUtilService.getKeyValue(each,key);
                if(keyOfList1.contains(keyValue)){
                    result.add(each);
                }
            }
            return result;
        }
    }

    /**
     * 差运算
     * @param list1 被减数
     * @param list2 减数
     */
    public ArrayList<T> difference(ArrayList<T> list1,ArrayList<T> list2) {
        boolean emptyFirst = false;
        boolean emptySecond = false;
        if (list1 == null || list1.size() == 0) {
            emptyFirst = true;
        }
        if (list2 == null || list2.size() == 0) {
            emptySecond = true;
        }

        if (emptyFirst) {
            return new ArrayList<T>();
        } else {
            if (emptySecond) {
                return list1;
            } else {
                ArrayList<T> l1=new ArrayList<T>(list1);
                ArrayList<T> l2=new ArrayList<T>(list2);
                int key = basicUtilService.getKeyID(l1.get(0));
                ArrayList<String> keyOfList2 = new ArrayList<String>();
                ArrayList<T> result = new ArrayList<T>();
                for (T each : l2) {
                    keyOfList2.add(basicUtilService.getKeyValue(each, key));
                }
                for (int i=0;i<l1.size();i++) {
                    String keyValue = basicUtilService.getKeyValue(l1.get(i), key);
                    if (keyOfList2.contains(keyValue)) {
                        l1.remove(i);
                        i--;
                    }
                }
                return l1;
            }
        }
    }

    /**
     *升序排序
     */
    public void upSort(Object[] objects,String paraName){
        if(objects!=null&&objects.length>0) {
            try {
                Field para = objects[0].getClass().getDeclaredField(paraName);
                paraName = paraName.substring(0, 1).toUpperCase() + paraName.substring(1);
                Method getter = objects[0].getClass().getMethod("get" + paraName);
                for (int i = 0; i < objects.length - 1; i++) {
                    for (int j = i + 1; j < objects.length; j++) {
                        int foreNum = (Integer) getter.invoke(objects[i]);
                        int backNum = (Integer) getter.invoke(objects[j]);
                        if (foreNum>backNum){
                            Object temp=objects[i];
                            objects[i]=objects[j];
                            objects[j]=temp;
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 降序排序
     */
    public void downSort(Object[] objects,String paraName){
        if(objects!=null&&objects.length>0) {
            upSort(objects,paraName);
            for(int i=0;i<objects.length/2;i++){
                Object temp=objects[i];
                objects[i]=objects[objects.length-1-i];
                objects[objects.length-i-1]=temp;
            }
        }

    }
    /**
     * json字符串 转对象
     */

    /**
     * 对象 转json字符串
     */
}
