package com.kingdee.java.jenkins.client.controller;


import com.kingdee.java.jenkins.client.config.JenkinsProperties;
import com.kingdee.java.jenkins.client.dto.JobParamDTO;
import com.kingdee.java.jenkins.client.service.PipelineJobService;
import com.kingdee.java.jenkins.client.vo.DataResult;
import com.tay.redislimiter.RateLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController
@Api(tags = "Pipeline-Job接口")
@RequestMapping(value = "/pipeline")
@Slf4j
public class PipelineJobController {

    @Resource
    private PipelineJobService pipelineJobService;
    @Resource
    private JenkinsProperties jenkinsProperties;

//    @ApiOperation(value = "创建job")
//    @PostMapping(value = "/job")
//    public DataResult createJob(@RequestBody JobParamDTO jobParamDTO) {
//        pipelineJobService.createPipelineJob(jobParamDTO);
//        return DataResultBuild.success();
//    }

    @ApiOperation(value = "创建或更新 job 并执行job")
    @PostMapping(value = "/jobAndExecute")
    //基于用户限流，独立module_name每小时最多2次访问，module_name在header中，key为module_name
    @RateLimiter(base = "#Headers['module_name']", permits = 4, timeUnit = TimeUnit.HOURS)
    public DataResult<String> createAndExecuteJob(@RequestBody JobParamDTO jobParamDTO ,@RequestHeader String module_name) {
        log.info("配置文件参数是:properties--->{}",jenkinsProperties);
        return pipelineJobService.createAndExecutePipelineJob(jobParamDTO);
    }

//
//    @ApiOperation(value = "删除job")
//    @DeleteMapping(value = "/job/{jobName}")
//    @ApiImplicitParam(name = "jobName",value = "job名称",defaultValue = "hr云扫描",dataType = "String")
//    public DataResult deleteJob(@PathVariable(value = "jobName")String jobName){
//        pipelineJobService.deletePipelineJob(jobName);
//        return DataResultBuild.success();
//    }
//
//    @ApiOperation(value = "更新job",notes = "任务名称不可修改")
//    @PutMapping(value = "/job")
//    public DataResult updateJob(@RequestBody JobParamDTO jobParamDTO){
//        pipelineJobService.updatePipelineJob(jobParamDTO);
//        return DataResultBuild.success();
//    }
//
//    @ApiOperation(value = "查看job详情",notes = "!任务名称不可修改")
//    @GetMapping(value = "/job/{jobName}")
//    @ApiImplicitParam(name = "jobName",value = "job名称",defaultValue = "hr云扫描",dataType = "String")
//    public DataResult<JobDetailVO> search(@PathVariable(value = "jobName") String jobName){
//        JobDetailVO jobWithDetails=pipelineJobService.getJob(jobName);
//        return jobWithDetails==null?DataResultBuild.fail("查询不到任务信息"):DataResultBuild.success(jobWithDetails);
//    }
//
//    @ApiOperation(value = "禁用JOB")
//    @PutMapping(value = "/disable/{jobName}")
//    @ApiImplicitParam(name = "jobName",value = "job名称",defaultValue = "hr云扫描",dataType = "String")
//    public DataResult<JobDetailVO> disableJob(@PathVariable(value = "jobName") String jobName){
//        pipelineJobService.disableJob(jobName);
//        return DataResultBuild.success();
//    }
//
//    @ApiOperation(value = "启用JOB")
//    @PutMapping(value = "/enable/{jobName}")
//    @ApiImplicitParam(name = "jobName",value = "job名称",defaultValue = "hr云扫描",dataType = "String")
//    public DataResult<JobDetailVO> enableJob(@PathVariable(value = "jobName") String jobName){
//        pipelineJobService.enableJob(jobName);
//        return DataResultBuild.success();
//    }
//
//    @ApiOperation(value = "运行JOB")
//    @PostMapping(value = "/execute")
//    public DataResult execute(@RequestBody JobParamDTO jobParamDTO){
//        String log = pipelineJobService.executeJobAndReturnLog(jobParamDTO);
//        return DataResultBuild.success(log);
//    }
//
//    @PostMapping("/test")
//    //基于用户限流，独立用户每分钟最多2次访问，用户id在header中，key为userid
//    //RateLimiter标签为静态配置，此类配置不可动态修改
//    @RateLimiter(base = "#Headers['module_name']", permits = 15, timeUnit = TimeUnit.SECONDS)
//    public String test(@RequestHeader String module_name) {
//        log.info("带的 header 是--->{}",module_name);
//        return "test!";
//    }
}
