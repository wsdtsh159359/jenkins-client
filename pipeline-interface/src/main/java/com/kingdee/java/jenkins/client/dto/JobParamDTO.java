package com.kingdee.java.jenkins.client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "JobParamDTO")
@Data
public class JobParamDTO implements Serializable {

    @ApiModelProperty(value = "视图名称Key(sonar:Sonar扫描)", example = "sonar", position = 1)
    private String viewNameKey;

    @ApiModelProperty(value = "job 所属视图描述", example = "Sonar扫描专用视图",position = 2)
    private String viewDescription;

    @ApiModelProperty(value = "任务名称", example = "hr云扫描", position = 3)
    private String jobName;

    @ApiModelProperty(value = "任务标签", example = "myjob", position = 4)
    private String label;

    @ApiModelProperty(value = "Job动作", example = "actions", position = 5)
    private String actions;

    @ApiModelProperty(value = "Job描述", example = "测试描述", position = 6)
    private String description;

    @ApiModelProperty(value = "保持依赖关系", example = "false", position = 7, hidden = true)
    private Boolean keepDependencies;

    @ApiModelProperty(value = "任务参数", position = 8)
    private JobParamProperties jobParamProperties;

    @ApiModelProperty(value = "git scm信息", position = 9)
    private GitScm gitScm;



}
