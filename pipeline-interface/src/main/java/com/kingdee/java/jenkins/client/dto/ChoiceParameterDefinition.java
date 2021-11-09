package com.kingdee.java.jenkins.client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author 高伟
* @date  2020/4/7 15:30
* @descrption
*/
@Data
@ApiModel(value = "ChoiceParameterDefinition")
public class ChoiceParameterDefinition {

    @ApiModelProperty(value = "参数名称",example = "choiceA",position = 1)
    private String name;

    @ApiModelProperty(value = "描述",example = "选择A",position = 2)
    private String description;

    @ApiModelProperty(value = "描述",example = "代码仓库地址",position = 3)
    private List<String> choiceList;

}
