package com.kingdee.java.jenkins.client;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2Doc
public class JavaJenkinsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaJenkinsClientApplication.class, args);
    }

}
