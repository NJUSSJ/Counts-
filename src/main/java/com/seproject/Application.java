package com.seproject;

import com.seproject.service.InitData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableTransactionManagement
public class Application  extends SpringBootServletInitializer implements WebApplicationInitializer {
    public static void main(String[] args) throws  Exception{
        SpringApplication.run(Application.class,args);
        InitData initData=new InitData();
        initData.initUserData();
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
