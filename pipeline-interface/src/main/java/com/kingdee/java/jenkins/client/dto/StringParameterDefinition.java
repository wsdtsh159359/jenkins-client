package com.kingdee.java.jenkins.client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author 高伟
* @date  2020/1/19 16:01
* @descrption
*/
@Data
@ApiModel(value = "StringParameterDefinition")
public class StringParameterDefinition {

    @ApiModelProperty(value = "参数名称",example = "codeUrl",position = 1)
    private String name;

    @ApiModelProperty(value = "默认参数值",example = "www.baidu.com",position = 2)
    private String defaultValue;

    @ApiModelProperty(value = "描述",example = "代码仓库地址",position = 3)
    private String description;

    @ApiModelProperty(value = "是否清除空格",example = "true",position = 4)
    private Boolean trim;
}
