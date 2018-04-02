package com.seproject.service;

import com.seproject.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileIOService {
    private FileDao fileDao;

    public String readDataFile(String name){
        return fileDao.readFile("dataFile/"+name);
    }

    public String readUserFile(String name){
        return fileDao.readFile("userFile/"+name);
    }

    public boolean writeDataFile(String name,String content){
        return fileDao.writeFile("dataFile/"+name,content);
    }

    public boolean writeUserFile(String name,String content){
        return fileDao.writeFile("userFile/"+name,content);
    }

    public Map<String,Integer> getMap(){
        String[] info=fileDao.readFile("dataFile/registry").split("\n");
        Map<String,Integer> result=new HashMap<String, Integer>();
        for(String each:info){
            String[] detail=each.split(",");
            result.put(detail[0],Integer.parseInt(detail[1]));
        }
        return result;
    }

    /**
     * 解析json字符串并写入相应的txt位置中
     * @param :json字符串
     *
     */
    public boolean updateDataFile(String str){
        int index=str.indexOf("\"imgid\":\"")+9;
        String nextPart=str.substring(index);

        int nextIndex=nextPart.indexOf("\"");
        String tag=nextPart.substring(0,nextIndex);


        int split=tag.indexOf("-");
        int id=Integer.parseInt(tag.substring(split+1));
        String parent=tag.substring(0,split);

        return fileDao.insertFile("dataFile/"+parent,str,id);
    }

    /**
     * 根据图集名和id返回相应的json字符串
     * @param name：图集名
     * @param id：图片在图集中的Id
     */
    public String getJsonString(String name,int id){
        String allContent=this.readDataFile(name);
        String[] info=allContent.split("/");
        String result=info[id-1].substring(name.length()+3);//如果是两位数id记得要修改这一段
        return result;
    }
    @Autowired
    public void setFileDao(FileDao fileDao){
        this.fileDao=fileDao;
    }
}
