package com.kingdee.java.jenkins.client.vo;


import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.QueueItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author 高伟
* @date  2020/4/8 10:20
* @descrption
*/
@ApiModel(value = "JobDetailVO")
@Data
public class JobDetailVO {

    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "地址")
    private String url;
    @ApiModelProperty(value = "全称")
    private String fullName;

    private String description;

    private String displayName;

    private boolean buildable;

    private List<Build> builds;

    private int nextBuildNumber;

    private boolean inQueue;

    private QueueItem queueItem;

    private List<Job> downstreamProjects;

    private List<Job> upstreamProjects;

}
