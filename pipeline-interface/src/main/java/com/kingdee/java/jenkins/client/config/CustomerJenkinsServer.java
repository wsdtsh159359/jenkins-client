package com.kingdee.java.jenkins.client.config;

import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.client.JenkinsHttpConnection;
import com.offbytwo.jenkins.client.util.EncodingUtils;

import java.io.IOException;
import java.net.URI;


/**
* @author 高伟
* @date  2020/4/22 9:18
* @descrption 自定义实现类扩展JenkinsServer类
*/
public class CustomerJenkinsServer {


    private final JenkinsHttpConnection customerClient;

    public CustomerJenkinsServer(URI serverUri) {
        this(new JenkinsHttpClient(serverUri));
    }

    public CustomerJenkinsServer(URI serverUri, String username, String passwordOrToken) {
        this(new JenkinsHttpClient(serverUri, username, passwordOrToken));
    }

    public CustomerJenkinsServer(JenkinsHttpConnection client) {
        this.customerClient=client;
    }


    /**
     * 在视图中创建job
     * @param viewName 视图名称
     * @param jobName 任务名称
     * @param jobXml 任务XML描述
     * @param crumbFlag 安全
     * @throws IOException
     */
    public void createViewJob(String viewName, String jobName, String jobXml, Boolean crumbFlag) throws IOException {
        customerClient.post_xml(viewName + "createItem?name=" + EncodingUtils.encodeParam(jobName), jobXml, crumbFlag);
    }

}
