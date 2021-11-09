package com.kingdee.java.jenkins.client.config;


import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;


/**
* @author 高伟
* @date  2020/1/19 16:31
* @descrption Jenkins 配置类
*/
@Configuration
public class JenkinsConfiguration {

    @Resource
    private JenkinsProperties jenkinsProperties;


    /**
      * @Description 注入JenkinsHttpClient对象
      * @param:
      * @Return
      * @Exception
    **/
    @Bean(name = "jenkinsHttpClient")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JenkinsHttpClient getJenkinsHttpClient(){
        return new JenkinsHttpClient(
                jenkinsProperties.getUri(),
                jenkinsProperties.getUserName(),
                jenkinsProperties.getPassword()
        );
    }

    /**
      * @Description 注入jenkinsServer对象
      * @param: jenkinsHttpClient
      * @Return
      * @Exception
    **/
    @Bean(name = "jenkinsServer")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JenkinsServer getJenkinsServer(@Qualifier("jenkinsHttpClient") JenkinsHttpClient jenkinsHttpClient) {
        return new JenkinsServer(jenkinsHttpClient);
    }

    /**
     * 自定义jenkinsServer对象
     * @param jenkinsHttpClient
     * @return
     */
    @Bean(name = "customerJenkinsServer")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CustomerJenkinsServer customerJenkinsServer(@Qualifier("jenkinsHttpClient") JenkinsHttpClient jenkinsHttpClient){
        return new CustomerJenkinsServer(jenkinsHttpClient);
    }

}
