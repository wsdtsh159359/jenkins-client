FROM java:8
VOLUME /tmp
ADD *.jar app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
ADD ./run.sh /tmp
RUN chmod a+x /tmp/run.sh
# 应用端口
ENV SERVER_PORT="8888"
ENTRYPOINT [ "sh", "-c", "/tmp/run.sh" ]