FROM openjdk:11
ENV URI_ACCOUNT=account
ENV URI_MOVEMENT=movement
ENV HOST_MYSQL=mysql
RUN mkdir -p /home/client
COPY target/client-1.0.jar /home/client
EXPOSE 4000
CMD ["java", "-jar", "/home/client/client-1.0.jar"]
