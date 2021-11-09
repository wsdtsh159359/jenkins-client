package com.kingdee.java.jenkins.client.service;

import com.kingdee.java.jenkins.client.dto.JobParamDTO;

public interface PipelineTemplateService {

    /**
     * 创建jenkins任务对象模板
     * @param jobParamDTO 参数对象DTO
     * @param templateName 模板名称
     * @param configXmlName 配置xml名称
     * @return 生成configXml的路径
     */
    String createTemplate(JobParamDTO jobParamDTO, String templateName, String configXmlName);
}
