package com.seproject.service;

import com.seproject.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicBLService<T> {
   T t; //模板类实例，用于传参数
   FileDao fileDao;

    public void setT(T t){
        this.t=t;
    }
    /*
    * 基本业务逻辑---增删改查的实现
    * 使用泛型加载领域对象
    * 用于实现数据库中对象的增加、修改、删除、精确查找、模糊查找
     */
   public void add(T t){
       //增加单个对象
   }
   public void delete(String keyValue){
       //根据主键删除单个对象
       String fileName=t.getClass().toString();

   }
   public void update(T t){
       //修改单个对象
   }
   public T findByKey(String keyValue){
       //根据主键精准查找单个对象
       return null;
   }

}
