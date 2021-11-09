<flow-definition plugin="workflow-job@2.36">
    <actions/>
    <description>${description}</description>
    <keepDependencies>${keepDependencies}</keepDependencies>
    <properties>
        <hudson.model.ParametersDefinitionProperty>
            <parameterDefinitions>
                <#if stringList??>
                    <#list stringList as item>
                        <hudson.model.StringParameterDefinition>
                            <name>${item.name}</name>
                            <description>${item.description}</description>
                            <defaultValue>${item.defaultValue}</defaultValue>
                            <trim>${item.trim?string ("true","false")}</trim>
                        </hudson.model.StringParameterDefinition>
                    </#list>
                </#if>
                <#if booleanList??>
                    <#list booleanList as item>
                        <hudson.model.BooleanParameterDefinition>
                            <name>${item.name}</name>
                            <description>${item.description}</description>
                            <defaultValue>${item.defaultValue?string ("true","false")}</defaultValue>
                        </hudson.model.BooleanParameterDefinition>
                    </#list>
                </#if>
                <#if choiceList??>
                    <#list choiceList as item>
                        <hudson.model.ChoiceParameterDefinition>
                            <name>${item.name}</name>
                            <description>${item.description}</description>
                            <choices class="java.util.Arrays$ArrayList">
                                <a class="string-array">
                                    <#list item.choiceList as choice>
                                        <string>${choice}</string>
                                    </#list>
                                </a>
                            </choices>
                        </hudson.model.ChoiceParameterDefinition>
                    </#list>
                </#if>

            </parameterDefinitions>
        </hudson.model.ParametersDefinitionProperty>
    </properties>
    <definition class="org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition" plugin="workflow-cps@2.78">
        <scm class="hudson.plugins.git.GitSCM" plugin="git@4.0.0">
            <configVersion>2</configVersion>
            <userRemoteConfigs>
                <hudson.plugins.git.UserRemoteConfig>
                    <url>${gitUrl}</url>
                    <credentialsId>${gitCredentialsId}</credentialsId>
                </hudson.plugins.git.UserRemoteConfig>
            </userRemoteConfigs>
            <branches>
                <hudson.plugins.git.BranchSpec>
                    <name>${gitBranch}</name>
                </hudson.plugins.git.BranchSpec>
            </branches>
            <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
            <submoduleCfg class="list"/>
            <extensions/>
        </scm>
        <scriptPath>${jenkinsFilePath}</scriptPath>
        <lightweight>true</lightweight>
    </definition>
    <triggers/>
    <disabled>false</disabled>
</flow-definition>