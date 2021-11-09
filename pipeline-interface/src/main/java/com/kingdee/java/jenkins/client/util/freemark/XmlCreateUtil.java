package com.kingdee.java.jenkins.client.util.freemark;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.kingdee.java.jenkins.client.dto.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class XmlCreateUtil {

    /**
     * freemark模板路径
     */
    @Value(value = "${kingdee.ci.templateLocation}")
    private String templateLocation;

    /**
     * 生成配置xml基础路径
     */
    @Value(value = "${kingdee.ci.configXmlBasePath}")
    private String configXmlBasePath;

    /**
     * 通过Freemark模板生成jenkins任务的xml文件
     * @param jobParamDTO jenkins任务参数对象
     * @param templateName 模板名称
     * @param configXmlName 配置生成XML名称
     */
    public String generate(JobParamDTO jobParamDTO,String templateName,String configXmlName){
        BufferedWriter out=null;
        try {
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            configuration.setDirectoryForTemplateLoading(new File(templateLocation));
            Template template = configuration.getTemplate(templateName);
            Map<String, Object> map = getParam(jobParamDTO);
            File file = new File(configXmlBasePath+configXmlName);
            out = new BufferedWriter(new FileWriter(file));
            template.process(map, out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out!=null) {
                try {
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return configXmlBasePath+configXmlName;
    }

    public Map<String,Object> getParam(JobParamDTO jobParamDTO){
        Map<String, Object> map = Maps.newHashMap();
        map.put("description", jobParamDTO.getDescription());
        map.put("keepDependencies", String.valueOf(jobParamDTO.getKeepDependencies()));
        JobParamProperties jobParamProperties=jobParamDTO.getJobParamProperties();
        if(jobParamProperties!=null){
            if(jobParamProperties.getSpdList()!=null){
                map.put("stringList",jobParamProperties.getSpdList());
            }
            if(jobParamProperties.getBpdList()!=null){
                map.put("booleanList",jobParamProperties.getBpdList());
            }
            if(jobParamProperties.getCpdList()!=null){
                map.put("choiceList",jobParamDTO.getJobParamProperties().getCpdList());
            }
        }
        GitScm gitScm=jobParamDTO.getGitScm();
        if(gitScm!=null){
            map.put("gitUrl",gitScm.getUrl());
            map.put("gitCredentialsId",gitScm.getCredentialsId());
            map.put("gitBranch",gitScm.getGitBranch());
            map.put("jenkinsFilePath",gitScm.getScriptPath());
        }
        return map;
    }

    public static void main(String[] args) {
        JobParamDTO jobParamDTO=new JobParamDTO();
        jobParamDTO.setDescription("测试");
        jobParamDTO.setKeepDependencies(true);
        List<StringParameterDefinition> list=new ArrayList<>();
        StringParameterDefinition s1=new StringParameterDefinition();
        s1.setName("s1");
        s1.setDefaultValue("s1");
        s1.setDescription("s1");
        s1.setTrim(false);
        StringParameterDefinition s2=new StringParameterDefinition();
        s2.setName("s2");
        s2.setDefaultValue("s2");
        s2.setDescription("s2");
        s2.setTrim(false);
        list.add(s1);
        list.add(s2);
        List<BooleanParameterDefinition> bpdList=new ArrayList<>();
        BooleanParameterDefinition b1=new BooleanParameterDefinition();
        b1.setName("A");
        b1.setDefaultValue(true);
        b1.setDescription("adsad");
        BooleanParameterDefinition b2=new BooleanParameterDefinition();
        b2.setName("B");
        b2.setDefaultValue(false);
        b2.setDescription("dasdad");
        bpdList.add(b1);
        bpdList.add(b2);
        List<ChoiceParameterDefinition> cpdList=new ArrayList<>();
        ChoiceParameterDefinition c1=new ChoiceParameterDefinition();
        c1.setName("选项A");
        c1.setDescription("选项A");
        c1.setChoiceList(Lists.newArrayList("A","B"));
        cpdList.add(c1);
        ChoiceParameterDefinition c2=new ChoiceParameterDefinition();
        c2.setName("选项B");
        c2.setDescription("选项B");
        c2.setChoiceList(Lists.newArrayList("C","D"));
        cpdList.add(c2);
        JobParamProperties jobParamProperties=new JobParamProperties();
        jobParamProperties.setSpdList(list);
        jobParamProperties.setBpdList(bpdList);
        jobParamProperties.setCpdList(cpdList);
        jobParamDTO.setJobParamProperties(jobParamProperties);
        Gson gson=new Gson();
        String json=gson.toJson(jobParamDTO,JobParamDTO.class);
        System.out.println(json);
    }
}


