package com.kingdee.java.jenkins.client.service.impl;


import com.kingdee.java.jenkins.client.dto.JobParamDTO;
import com.kingdee.java.jenkins.client.service.PipelineTemplateService;
import com.kingdee.java.jenkins.client.util.freemark.XmlCreateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PipelineTemplateServiceImpl implements PipelineTemplateService {

    @Resource
    private XmlCreateUtil xmlCreateUtil;

    @Override
    public String createTemplate(JobParamDTO jobParamDTO, String templateName, String configXmlName) {
        return xmlCreateUtil.generate(jobParamDTO, templateName, configXmlName);
    }
}
