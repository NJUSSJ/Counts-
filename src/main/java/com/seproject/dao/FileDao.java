package com.seproject.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;

@Repository
public class FileDao {
    public static String  separateString="@##_##@";
    public String readFile(String name){
        String res="";
        String content="";
        try {
            File file= ResourceUtils.getFile("classpath:file/"+name+".txt");

            BufferedReader br=new BufferedReader(new FileReader(file));
            while((content=br.readLine())!=null){
                res=res+content+"\n";
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File path Error");
            res="FileNotFound";
            e.printStackTrace();
        } catch (IOException e) {
            res="FileNotFound";
            e.printStackTrace();
        }

        return  res;
    }
    public boolean writeFile(String name,String content){
        try {
            File file= ResourceUtils.getFile("classpath:file/"+name+".txt");
            FileWriter writer=new FileWriter(file,false);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertFile(String name,String content,int id){
        try {
            File file= ResourceUtils.getFile("classpath:file/"+name+".txt");

            String oldContent="";
            String line;
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                oldContent=line+oldContent;
            }
            br.close();

            String[] parts=oldContent.split("/");

            parts[id-1]=name.replace("dataFile/","")+"-"+id+":"+content;

            String newContent="";
            for(String each:parts){
                newContent+=each+"/";
            }
            FileWriter writer=new FileWriter(file,false);
            writer.write(newContent);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void write_object(ArrayList<String> al, String fileName){
        //在文件末新增一个领域对象元组。
        //入参是成员变量值列表和文件名（类名）

        String tuple="";
        for(int i=0;i<al.size();i++){
            tuple=tuple+al.get(i);
            if(i<al.size()-1){
                tuple+=this.separateString;
            }
        }

        try {

            File nav= ResourceUtils.getFile("classpath:file/objectFile/navigation.txt");
            String path=nav.getAbsolutePath();
            path=path.replace("\\","/");
            int index=path.lastIndexOf("/");
            path=path.substring(0,index);
            System.out.println("Path:"+nav.getAbsolutePath());
            File file0=new File(path+"/"+fileName+".txt");
            if(!file0.exists()){

                file0.createNewFile();
            }
            File file= ResourceUtils.getFile(path+"/"+fileName+".txt");

            FileWriter writer=new FileWriter(file,true);
            writer.write(tuple+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("领域对象在写入数据库文件时发生异常");

            e.printStackTrace();
        }

    }

    public ArrayList<String> read_class(String fileName){
        // 读取并返回一个类中所有已存储的对象

        //若文件不存在，则创建该文件

        try {

            File nav= ResourceUtils.getFile("classpath:file/objectFile/navigation.txt");
            String path=nav.getAbsolutePath();
            System.out.println("Path0:"+path);
            path=path.replace("\\","/");
            System.out.println("Path1:"+path);
            int index=path.lastIndexOf("/");
            path=path.substring(0,index);

            File file0=new File(path+"/"+fileName+".txt");
            if(!file0.exists()){

                    file0.createNewFile();
            }
            //
            fileName="objectFile/"+fileName;
            ArrayList<String> objects=new ArrayList<String>();
            String content=readFile(fileName);
            if(!(content==null||content.length()<=0)) {
                String[] temp = content.split("\n");
                for (String t : temp) {
                    objects.add(t);
                }
            }
            return objects;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


    }

    public ArrayList<String> read_object(String fileName,int keyID,String key){
        //根据主键以动态数组形式返回所存储的对象的所有成员变量值。
        //第二个参数指出第几个成员是主键，从0开始计数
        //第三个参数指出主键的值
        ArrayList <String > singleObject=new ArrayList<String>();
        ArrayList <String > objects=read_class(fileName);
        for(int i=0;i<objects.size();i++){
            String temp[]=objects.get(i).split(this.separateString);
            if(temp[keyID].equals(key)){
                for(int j=0;j<temp.length;j++){
                    singleObject.add(temp[j]);
                }
                break;
            }
        }

        return singleObject;
    }

    public void delete_object(String fileName,int keyID,String key){
        //删除单张表中的单个对象
        ArrayList <String > objects=read_class(fileName);
        for(int i=0;i<objects.size();i++){
            String temp[]=objects.get(i).split(this.separateString);
            if(temp[keyID].equals(key)){
                objects.remove(i); //删除传入的主键所对应的元组
                break;
            }
        }
        String output="";
        for(int i=0;i<objects.size();i++){
            output=output+objects.get(i);
            if(i<objects.size()-1){
                output+="\n";//加入换行符
            }
        }
        fileName="objectFile/"+fileName;
        writeFile(fileName,output);
    }


}
