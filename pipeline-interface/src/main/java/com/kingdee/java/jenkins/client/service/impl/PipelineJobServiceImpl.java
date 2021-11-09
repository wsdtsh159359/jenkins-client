package com.kingdee.java.jenkins.client.service.impl;

import com.google.common.collect.Maps;


import com.kingdee.java.jenkins.client.config.CustomerJenkinsServer;
import com.kingdee.java.jenkins.client.config.JenkinsProperties;
import com.kingdee.java.jenkins.client.dto.GitScm;
import com.kingdee.java.jenkins.client.dto.JobParamDTO;
import com.kingdee.java.jenkins.client.dto.JobParamProperties;
import com.kingdee.java.jenkins.client.service.PipelineJobService;
import com.kingdee.java.jenkins.client.service.PipelineTemplateService;
import com.kingdee.java.jenkins.client.util.XmlUtil;
import com.kingdee.java.jenkins.client.vo.DataResult;
import com.kingdee.java.jenkins.client.vo.DataResultBuild;
import com.kingdee.java.jenkins.client.vo.JobDetailVO;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;


/**
 * @author 高伟
 * @date 2020/4/7 13:55
 * @descrption 流水线任务Service类
 */
@Service
@Slf4j
public class PipelineJobServiceImpl implements PipelineJobService {

    @Resource
    private JenkinsServer jenkinsServer;
    @Resource
    private CustomerJenkinsServer customerJenkinsServer;
    @Resource
    private PipelineTemplateService pipelineTemplateService;
    @Resource
    private JenkinsProperties jenkinsProperties;
    /**
     * 生成配置xml基础路径
     */
    @Value(value = "${kingdee.ci.configXmlBasePath}")
    private String configXmlBasePath;



    /**
     * 创建 pipelineJob
     */
    @Override
    public void createPipelineJob(JobParamDTO jobParamDTO) {
        try {
            // 通过freemark模板生成XML
            String xml = getStringXml(jobParamDTO);
            String viewName=getViewNameByKey(jobParamDTO.getViewNameKey());
            customerJenkinsServer.createViewJob(viewName,jobParamDTO.getJobName(), xml, true);
            File file = new File(configXmlBasePath + jobParamDTO.getLabel() + ".xml");
            if (file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException("创建Pipeline任务IO异常:" + jobParamDTO.getJobName());
        }

    }


    /**
     * 获取视图名称
     * @param key
     * @return
     */
    private String getViewNameByKey(String key){
        return new StringBuilder().append("view/").append(jenkinsProperties.getViewNameMap().get(key)).append("/").toString();
    }

    /**
     * 创建并执行 job
     */
    @Override
    public DataResult<String> createAndExecutePipelineJob(JobParamDTO jobParamDTO) {
        log.info("createAndExecutePipelineJob -->创建 job 参数{}",jobParamDTO);
        String console;
        try {
            // 通过freeMark模板生成XML
            String xml = getStringXml(jobParamDTO);
            JobWithDetails jobWithDetails = jenkinsServer.getJob(jobParamDTO.getJobName());
            //如果 job 不为空先更新 job 然后再执行 job
            if (jobWithDetails != null) {
                updatePipelineJob(jobParamDTO);
            } else {
                //不存在则创建 job
                String viewName = getViewNameByKey(jobParamDTO.getViewNameKey());
                customerJenkinsServer.createViewJob(viewName, jobParamDTO.getJobName(), xml, true);
                File file = new File(configXmlBasePath + jobParamDTO.getLabel() + ".xml");
                if (file.exists()) {
                    file.delete();
                }
            }
            Map<String, String> map = getStrParam(jobParamDTO);
            log.info("createAndExecutePipelineJob -->创建 jenkinsServer 参数{}",jenkinsServer);
            JobWithDetails details = jenkinsServer.getJob(jobParamDTO.getJobName());
            details.build(map, true);
            console = new StringBuilder(jenkinsProperties.getConsoleUri().toString())
                    .append("/")
                    .append("job")
                    .append("/")
                    .append(details.getDisplayName())
                    .append("/")
                    .append(details.getNextBuildNumber())
                    .append("/")
                    .append("console").toString();

        } catch (IOException e) {
            throw new RuntimeException("创建Pipeline任务IO异常:" + jobParamDTO.getJobName());
        }
        return DataResultBuild.success(console);
    }

    /**
     * 删除 job
     */
    @Override
    public void deletePipelineJob(String jobName) {
        try {
            jenkinsServer.deleteJob(jobName, true);
        } catch (IOException e) {
            throw new RuntimeException("删除Pipeline任务IO异常:" + jobName);
        }
    }

    /**
     * 更新 job
     */
    @Override
    public void updatePipelineJob(JobParamDTO jobParamDTO) {
        try {
            String xml = getStringXml(jobParamDTO);
            jenkinsServer.updateJob(jobParamDTO.getJobName(), xml, true);
            File file = new File(configXmlBasePath + jobParamDTO.getLabel() + ".xml");
            if (file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("更新Pipeline任务IO异常:" + jobParamDTO.getJobName());
        }
    }


    /**
     * 获取 job
     */
    @Override
    public JobDetailVO getJob(String jobName) {
        try {
            JobWithDetails jobWithDetails = jenkinsServer.getJob(jobName);
            JobDetailVO vo = new JobDetailVO();
            vo.setDescription(jobWithDetails.getDescription());
            vo.setDisplayName(jobWithDetails.getDisplayName());
            vo.setBuildable(jobWithDetails.isBuildable());
            vo.setBuilds(jobWithDetails.getBuilds());
            vo.setNextBuildNumber(jobWithDetails.getNextBuildNumber());
            vo.setInQueue(jobWithDetails.isInQueue());
            vo.setQueueItem(jobWithDetails.getQueueItem());
            vo.setDownstreamProjects(jobWithDetails.getDownstreamProjects());
            vo.setUpstreamProjects(jobWithDetails.getUpstreamProjects());
            vo.setName(jobWithDetails.getName());
            vo.setUrl(jobWithDetails.getUrl());
            vo.setFullName(jobWithDetails.getFullName());
            return vo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取任务信息IO异常");
        }

    }


    @Override
    public void disableJob(String jobName) {
        try {
            jenkinsServer.disableJob(jobName, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("禁用JOB出现IO异常：" + jobName);
        }

    }

    @Override
    public void enableJob(String jobName) {
        try {
            jenkinsServer.enableJob(jobName, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("启用JOB出现IO异常：" + jobName);
        }
    }

    /**
     * 执行 job 阻塞获取日志
     */
    @Override
    public void executeJob(JobParamDTO jobParamDTO) {
        Map<String, String> map = getStrParam(jobParamDTO);
        try {
            QueueReference queueReference = jenkinsServer.getJob(jobParamDTO.getJobName()).build(map, true);
            Thread.sleep(5000);
            QueueItem queueItem = jenkinsServer.getQueueItem(queueReference);
            Build build = jenkinsServer.getBuild(queueItem);
            BuildWithDetails details = build.details();
            BuildResult result = details.getResult();
            String logs = details.getConsoleOutputText();
            String urlPart = queueReference.getQueueItemUrlPart();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("运行JOB出现IO异常：" + jobParamDTO.getJobName());
        }
    }

    /**
     * 执行 job 返回最近一次得 log
     */
    @Override
    public String executeJobAndReturnLog(JobParamDTO jobParamDTO) {
        Map<String, String> map = getStrParam(jobParamDTO);
        String log = "";
        try {
            QueueReference queueReference = jenkinsServer.getJob(jobParamDTO.getJobName()).build(map, true);
            log = jenkinsServer.getJob(jobParamDTO.getJobName()).getLastBuild().details().getConsoleOutputHtml();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("运行JOB出现IO异常：" + jobParamDTO.getJobName());
        }
        return log;
    }

    public Map<String, String> getStrParam(JobParamDTO jobParamDTO) {
        Map<String, String> map = Maps.newHashMap();
        map.put("description", jobParamDTO.getDescription());
        map.put("keepDependencies", String.valueOf(jobParamDTO.getKeepDependencies()));
        JobParamProperties jobParamProperties = jobParamDTO.getJobParamProperties();
        if (jobParamProperties != null) {
            if (jobParamProperties.getSpdList() != null) {
                map.put("stringList", jobParamProperties.getSpdList().toString());
            }
            if (jobParamProperties.getBpdList() != null) {
                map.put("booleanList", jobParamProperties.getBpdList().toString());
            }
            if (jobParamProperties.getCpdList() != null) {
                map.put("choiceList", jobParamDTO.getJobParamProperties().getCpdList().toString());
            }
        }
        GitScm gitScm = jobParamDTO.getGitScm();
        if (gitScm != null) {
            map.put("gitUrl", gitScm.getUrl());
            map.put("gitCredentialsId", gitScm.getCredentialsId());
            map.put("gitBranch", gitScm.getGitBranch());
            map.put("jenkinsFilePath", gitScm.getScriptPath());
        }
        return map;
    }

    private String getStringXml(JobParamDTO jobParamDTO) {
        try {
            String configXml = pipelineTemplateService.createTemplate(jobParamDTO, "scm-git.ftl", jobParamDTO.getLabel() + ".xml");
            String xml = XmlUtil.xml2Str(configXml);
            return xml;
        } catch (IOException e) {
            throw new RuntimeException("生成任务XML文件IO异常:" + jobParamDTO.getJobName());
        } catch (JDOMException ex) {
            throw new RuntimeException("生成任务XML文件JDOMException异常:" + jobParamDTO.getJobName());
        }
    }
}
