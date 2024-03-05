#FROM registry.eccom.com.cn/eccom/jdk:8
FROM openjdk:8-jre

ENV port 8080
ENV profile demo
COPY ./business-china-unicom-*.jar app.jar
EXPOSE $port
ENTRYPOINT ["java","-jar","/app.jar"]