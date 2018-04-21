/**
 * @author fortune
 */
package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CollectionController {
<<<<<<< HEAD

    private BasicBLService<User> basicBLService;
    private BasicBLService<Mission> missionBasicBLService;
=======
    private FileIOService fileIOService;
    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());
>>>>>>> bb99aaa1456330cc53f7efaa1343751e719b78ce

    @RequestMapping(value = "/details.html")
    public ModelAndView getDetailofCollection(HttpServletRequest request){
        int picNum;
        String missionName=request.getParameter("imageURL");
        missionBasicBLService.setT(new Mission());
        picNum=missionBasicBLService.findByKey(missionName).getFileNum();
        String fixx="\'http://120.79.221.158:8080/Pictures/";
        String url=fixx+request.getParameter("imageURL")+"/\'";
        String collection="\'"+request.getParameter("imageURL")+"\'";
        ModelAndView model=new ModelAndView("MultiPic");
        model.addObject("url",url);
        model.addObject("collection", collection);
        model.addObject("picNum",picNum);
        return model;
    }

<<<<<<< HEAD

    @Autowired
    public void setBasicBLService(BasicBLService<User> basicBLService){ this.basicBLService=basicBLService;
    this.basicBLService.setT(new User());//做一个试验
    }
    @Autowired
    public void setMissionBasicBLService(BasicBLService<Mission> missionBasicBLService){
        this.missionBasicBLService=missionBasicBLService;
        this.missionBasicBLService.setT(new Mission());
    }

=======
  /*  private void tryReflect(){
        //试一下好不好用
        basicBLService.add(new User());
        basicBLService.delete("123");
        basicBLService.findByKey("123");
        basicBLService.update(new User());
    }*/
    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}
/*    @Autowired
    public void setBasicBLService(BasicBLService<User> basicBLService){ this.basicBLService=basicBLService;
    this.basicBLService.setT(new User());//做一个试验
    }*/
>>>>>>> bb99aaa1456330cc53f7efaa1343751e719b78ce
}
