package com.seproject.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Repository
public class FileDao {
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


}
