package com.seproject.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;

@Repository
public class FileDao {
    public  String  separateString="@##_##@";
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


        String tuple="";
        for(int i=0;i<al.size();i++){
            tuple=tuple+al.get(i);
            if(i<al.size()-1){
                tuple+=this.separateString;
            }
        }

        try {
            File file0=new File("target/classes/file/objectFile/"+fileName+".txt");
            if(!file0.exists()){
                System.out.println(file0.getAbsolutePath());
                 file0.createNewFile();
            }
            File file= ResourceUtils.getFile("classpath:file/objectFile/"+fileName+".txt");

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
        fileName="objectFile/"+fileName;
        ArrayList<String> objects=new ArrayList<String>();
        String [] temp=readFile(fileName).split("\n");
        for(String t:temp){
            objects.add(t);
        }
        return objects;
    }

    public ArrayList<String> read_Object(String fileName,int keyID,String key){
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


}
