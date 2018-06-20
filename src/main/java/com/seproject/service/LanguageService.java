package com.seproject.service;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static java.lang.Thread.sleep;

/**
 * 百度自然语言处理接口
 */
@Service
public class LanguageService {
    //设置APPID/AK/SK
    private static final String APP_ID = "11407628";
    private static final String API_KEY = "ReGNELPKDvnOyKEs2LlqCiPq";
    private static final String SECRET_KEY = "55j1ab3svr9ot8UrNR11250vdZy53Dfx";
    private static AipNlp client=null;//建议单例模式使用
    private void initNlp(){
        // 初始化一个AipNlp
        if(client==null) {
            client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

            // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
            // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
            // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

            // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
            // 也可以直接通过jvm启动参数设置此环境变量
            // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        }
    }

    /**
     * 两个短文本相似度
     */
    public double simliarityOfText(String text1,String text2){
        if(text1==null||text1.length()<=0||text2==null||text2.length()<=0) return 0;//和空串比结果是0
        initNlp();
        // 调用接口
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "CNN");

        // 短文本相似度
        JSONObject res = client.simnet(text1, text2, options);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//一次发送太多请求会报错
        String origin=res.toString(2);
        System.out.println("短文本相似度");
        System.out.println(res);
        int index=origin.lastIndexOf("score");
        String subString=origin.substring(index);
        index=subString.indexOf(":")+2;
        int endIndex=subString.indexOf(",");
        //System.out.println(subString.substring(index,endIndex));
        return Double.parseDouble(subString.substring(index,endIndex));
    }
}
