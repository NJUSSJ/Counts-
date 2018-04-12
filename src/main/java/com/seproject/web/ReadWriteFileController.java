package com.seproject.web;

import com.seproject.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by julia98 on 2018/3/22.
 */
@Controller
public class ReadWriteFileController {
    FileIOService fileIOService;

    @RequestMapping(value = "/write")
    @ResponseBody
    public String writeFile(@RequestBody String imgid){
       // String imgid = params.getString("imgid");
        System.out.print("获取到的文件名" + imgid);
        fileIOService.updateDataFile(imgid);
        return "666";
    }

    @RequestMapping(value = "/read")
    @ResponseBody
    public String readFile(@RequestBody String imageInfo) {
        System.out.print(imageInfo);
        String [] temp1=imageInfo.split(":");
        String [] temp2=temp1[1].split(",");
        String name=temp2[0];
        int id=Integer.parseInt(temp1[2].replace("}",""));
        String res = fileIOService.getJsonString(name, id);
        if(!res.startsWith("{")){
            res="{"+res;
            res=res+"}";
        }
        return res;
    }

    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}

}
