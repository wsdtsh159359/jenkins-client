package com.kingdee.java.jenkins.client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author 高伟
* @date  2020/4/7 16:13
* @descrption
*/
@Data
@ApiModel(value = "GitScm")
public class GitScm {

    @ApiModelProperty(value = "jenkinsfile脚本git地址",example = "http://120.79.150.108:8062/devops/gitlab4java.git",position = 1)
    private String url;

    @ApiModelProperty(value = "git凭证",example = "62609609-b9f7-4240-9e24-7eff59581330",position = 2)
    private String credentialsId;

    @ApiModelProperty(value = "git分支",example = "*/master",position = 3)
    private String gitBranch;

    @ApiModelProperty(value = "脚本路径",example = "jenkinsfile",position = 4)
    private String scriptPath;
}
