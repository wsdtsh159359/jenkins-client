"""
============================
author:gaojunjun
time:2020/04/14
E-mail:291906477@qq.com
发送Http请求的方法
============================
"""
import json

import requests


class HttpRequest(object):
    """不记录任何的请求方法"""

    @classmethod
    def request(cls, method, url, data=None, headers=None):  # 这里是要传入的参数，请求方法、接口地址、传参、头文件
        method = method.upper()  # 这里将传入的请求方法统一大写，然后进行判断采用什么方法
        if method == 'POST':
            return requests.post(url=url, data=data, headers=headers)
        elif method == 'GET':
            return requests.get(url=url, params=data, headers=headers)


class HttpSession(object):
    """记录Session的方法"""

    def __init__(self):
        self.session = requests.session()  # 初始化一个保存session的方法

    def request(self, method, url, data=None, headers=None):
        method = method.upper()
        if method == 'POST':
            return self.session.post(url=url, data=data, headers=headers)
        elif method == 'GET':
            return self.session.get(url=url, params=data, headers=headers)

    def close(self):
        """断开session连接的方法"""
        self.session.close()


if __name__ == '__main__':
    http = HttpSession()
    data = {
        "jobName": "hr云扫描2",
        "label": "myjob",
        "actions": "actions",
        "description": "测试描述",
        "jobParamProperties": {
            "spdList": [
                {
                    "name": "s1",
                    "defaultValue": "s1",
                    "description": "s1",
                    "trim": 'false'
                },
                {
                    "name": "s2",
                    "defaultValue": "s2",
                    "description": "s2",
                    "trim": 'false'
                }
            ],
            "bpdList": [
                {
                    "name": "A",
                    "defaultValue": 'true',
                    "description": "adsad"
                },
                {
                    "name": "B",
                    "defaultValue": 'false',
                    "description": "dasdad"
                }
            ],
            "cpdList": [
                {
                    "name": "选项A",
                    "description": "选项A",
                    "choiceList": [
                        "A",
                        "B"
                    ]
                },
                {
                    "name": "选项B",
                    "description": "选项B",
                    "choiceList": [
                        "C",
                        "D"
                    ]
                }
            ]
        },
        "pipelineDefine": {
            "pipelineScriptType": 2,
            "pipelineScriptScmType": 1,
            "scriptGitUrl": "http://120.79.150.108:8062/devops/gitlab4java.git",
            "gitUrlCredentials": "62609609-b9f7-4240-9e24-7eff59581330",
            "branch": "*/master",
            "scriptPath": "Jenkinsfile"
        },
        "gitScm": {
            "url": "http://120.79.150.108:8062/devops/gitlab4java.git",
            "credentialsId": "62609609-b9f7-4240-9e24-7eff59581330",
            "gitBranch": "*/master",
            "scriptPath": "jenkinsfile"
        }
    }
    http_one = http.request(method='post', url='http://127.0.0.1:8888/jenkins/pipeline/job',
                            data=json.dumps(data), headers={'Content-Type': 'application/json'}).json()
    #    http_two = http.request(method='get', url='http://www.baidu.com').json()
    print(http_one)
#    print(http_two)
