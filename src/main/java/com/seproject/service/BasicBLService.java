package com.seproject.service;

import com.seproject.dao.FileDao;
import com.seproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BasicBLService<T> {
   T t; //模板类实例，用于传参数
   FileDao fileDao;
   BasicUtilService basicUtilService;

    public void setT(T t){
        this.t=t;
    }

    /*
    * 基本业务逻辑---增删改查的实现
    * 使用泛型加载领域对象
    * 用于实现数据库中对象的增加、修改、删除、精确查找、模糊查找
     */

   public RM add(T t0){
       //增加单个对象
       this.basicUtilService.writeClass(t0);
       return RM.SUCCESS;
   }
   public RM delete(String keyValue){
       //根据主键删除单个对象
       String fileName=t.getClass().toString();
       int keyID=this.basicUtilService.getKeyID(t);
       this.fileDao.delete_object(fileName,keyID,keyValue);
       return RM.SUCCESS;

   }
   public RM update(T t0){
       //修改单个对象
       int keyID=this.basicUtilService.getKeyID(t0);
       String keyValue=this.basicUtilService.getKeyValue(t0,keyID);
       //先删除再增加
       delete(keyValue);
       add(t0);

       return RM.SUCCESS;
   }
   public T findByKey(String keyValue){
       //根据主键精准查找单个对象
       return (T)basicUtilService.read(t,keyValue);
   }

   public ArrayList<T> search(String keyName,SearchCategory category,String keyValue){
       //多返回值查找，返回所有键值=keyValue的元组
       //可查找的成员变量要加@Searchable，并在此方法内给出所查找的变量的变量名
       /*
       *例子：  search("userName","Tony")
       *       返回所有名字叫Tony的User对象
       *       此方法未来会被重载，第三个参数是查找策略，可支持模糊查找
        */
       ArrayList<T> arr=new ArrayList<T>();
       String fileName=t.getClass().toString();
       ArrayList<String> objects=this.fileDao.read_class(fileName);
       int searchableID=this.basicUtilService.getKeyID(t,keyName);
       int keyID=this.basicUtilService.getKeyID(t);
       ArrayList<String> names=new ArrayList<String>();

       for(int i=0;i<objects.size();i++){
           String [] temp=objects.get(i).split(this.fileDao.separateString);
           switch (category){
               case EQUAL:
                   if(temp[searchableID].equals(keyValue)){
                   names.add(temp[keyID]);
               }
               break;
               case NOT_EQUAL:
                   if(!temp[searchableID].equals(keyValue)){
                   names.add(temp[keyID]);
               }
               break;
               case CONTAINS:
                   if(contains(temp[searchableID],keyValue)){
                       names.add(temp[keyID]);
                   }
                   break;
               case LARGER_THAN:
                   if(Double.parseDouble(temp[searchableID])>Double.parseDouble(keyValue)){
                       names.add(temp[keyID]);
                   }
                   break;
               case SMALLER_THAN:
                   if(Double.parseDouble(temp[searchableID])<Double.parseDouble(keyValue)){
                       names.add(temp[keyID]);
                   }
                   break;
           }

       }


       for(String name:names){
           arr.add((T)(this.basicUtilService.read(t,name)));
       }
       return arr;
   }

   public boolean checkKeyExists(String keyValue){
       //主键查重
       //查询该主键对应的对象是否已经存在
       boolean result=false;
       String fileName=t.getClass().toString();
       ArrayList<String> objects=this.fileDao.read_class(fileName);
       int keyID=basicUtilService.getKeyID(t);
       for(int i=0;i<objects.size();i++){
           String [] temp=objects.get(i).split(this.fileDao.separateString);
           String key=temp[keyID];
           if(key.equals(keyValue)){
               result=true;
               break;
               }
       }

            return result;
   }

   public ArrayList<T> getAllObjects(){
       //返回全部
       //返回该类下的所有对象
       ArrayList<T> arr=new ArrayList<T>();
       String fileName=t.getClass().toString();
       ArrayList<String> objects=this.fileDao.read_class(fileName);
       int keyID=basicUtilService.getKeyID(t);
       for(int i=0;i<objects.size();i++){
           String [] temp=objects.get(i).split(this.fileDao.separateString);
           String key=temp[keyID];
           arr.add((T)basicUtilService.read(t,key));
       }
       return arr;
   }

    private boolean contains(String origin,String pattern) {
        char[] pat=pattern.toCharArray();
        int index=-1;
        for(char each:pat) {
            int sequence=origin.indexOf(each);
            if(sequence<=index) {
                return false;
            }else {
                origin=origin.substring(sequence+1);
            }
        }
        return true;
    }


   @Autowired
   public void setFileDao(FileDao fileDao){this.fileDao=fileDao;}
   @Autowired
    public  void setBasicUtilService(BasicUtilService basicUtilService){this.basicUtilService=basicUtilService;}
}
