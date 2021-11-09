package com.kingdee.java.jenkins.client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author 高伟
* @date  2020/1/19 16:59
* @descrption
*/
@Data
@ApiModel(value = "BooleanParameterDefinition")
public class BooleanParameterDefinition {

    @ApiModelProperty(value = "参数名称",example = "是否开启",position = 1)
    private String name;

    @ApiModelProperty(value = "默认参数值",example = "true",position = 2)
    private Boolean defaultValue;

    @ApiModelProperty(value = "描述",example = "是否开启XXXX",position = 3)
    private String description;

}
