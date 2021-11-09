package com.kingdee.java.jenkins.client.service;


import com.kingdee.java.jenkins.client.dto.JobParamDTO;
import com.kingdee.java.jenkins.client.vo.DataResult;
import com.kingdee.java.jenkins.client.vo.JobDetailVO;
import com.offbytwo.jenkins.model.JobWithDetails;

import java.io.IOException;

public interface PipelineJobService {

    /**
     * 创建流水线JOB
     * @param jobParamDTO 任务参数DTO
     * @throws IOException
     */
    void createPipelineJob(JobParamDTO jobParamDTO);

    /**
     * 创建流水线 JOB 并执行
     * @param jobParamDTO 任务参数DTO
     * @throws IOException
     */
    DataResult<String> createAndExecutePipelineJob(JobParamDTO jobParamDTO);


    /**
     * 删除JOB
     * @param jobName
     */
    void deletePipelineJob(String jobName);

    /**
     * 更新JOB
     * @param jobParamDTO
     */
    void updatePipelineJob(JobParamDTO jobParamDTO);


    /**
     * 获取任务信息
     * @param jobName
     * @return
     */
    JobDetailVO getJob(String jobName);

    /**
     * 禁用JOB
     * @param jobName
     */
    void disableJob(String jobName);

    /**
     * 启用JOB
     * @param jobName
     */
    void enableJob(String jobName);

    /**
     * 启动JOB
     * @param jobParamDTO
     */
    void executeJob(JobParamDTO jobParamDTO);

    /**
     * 启动JOB
     * @param jobParamDTO
     */
    String executeJobAndReturnLog(JobParamDTO jobParamDTO);

}
