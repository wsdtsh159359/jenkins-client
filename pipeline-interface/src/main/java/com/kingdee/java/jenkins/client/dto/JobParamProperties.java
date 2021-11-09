package com.kingdee.java.jenkins.client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author 高伟
* @date  2020/1/19 15:55
* @descrption
*/
@Data
@ApiModel(value = "JobParamProperties")
public class JobParamProperties {

    @ApiModelProperty(value = "字符串参数对象List",example = "[{\n" +
            "\t\t\t\"name\": \"s1\",\n" +
            "\t\t\t\"defaultValue\": \"s1\",\n" +
            "\t\t\t\"description\": \"s1\",\n" +
            "\t\t\t\"trim\": false\n" +
            "\t\t}, {\n" +
            "\t\t\t\"name\": \"s2\",\n" +
            "\t\t\t\"defaultValue\": \"s2\",\n" +
            "\t\t\t\"description\": \"s2\",\n" +
            "\t\t\t\"trim\": false\n" +
            "\t\t}]",position = 1)
    private List<StringParameterDefinition> spdList;

    @ApiModelProperty(value = "布尔参数对象List",example = "[{\n" +
            "\t\t\t\"name\": \"A\",\n" +
            "\t\t\t\"defaultValue\": true,\n" +
            "\t\t\t\"description\": \"adsad\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"name\": \"B\",\n" +
            "\t\t\t\"defaultValue\": false,\n" +
            "\t\t\t\"description\": \"dasdad\"\n" +
            "\t\t}]",position = 2)
    private List<BooleanParameterDefinition> bpdList;

    @ApiModelProperty(value = "选项参数对象List",example = "[{\n" +
            "\t\t\t\"name\": \"选项A\",\n" +
            "\t\t\t\"description\": \"选项A\",\n" +
            "\t\t\t\"choiceList\": [\"A\", \"B\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"name\": \"选项B\",\n" +
            "\t\t\t\"description\": \"选项B\",\n" +
            "\t\t\t\"choiceList\": [\"C\", \"D\"]\n" +
            "\t\t}]",position = 3)
    private List<ChoiceParameterDefinition> cpdList;
}
