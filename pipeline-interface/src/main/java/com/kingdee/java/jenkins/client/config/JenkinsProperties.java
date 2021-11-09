package com.kingdee.java.jenkins.client.config;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.Map;


/**
* @author 高伟
* @date  2020/4/7 14:23
* @descrption
*/
@Data
@EnableConfigurationProperties(JenkinsProperties.class)
@ConfigurationProperties(prefix = "jenkins.config")
@Configuration
public class JenkinsProperties {

    @ApiModelProperty(value = "Jenkins URI地址")
    private URI uri;

    @ApiModelProperty(value = "Jenkins-console URI地址")
    private URI consoleUri;

    @ApiModelProperty(value = "Jenkins 用户名")
    private String userName;

    @ApiModelProperty(value = "Jenkins 密码")
    private String password;

    @ApiModelProperty(value = "视图名称Map")
    private Map<String,String> viewNameMap;

}
