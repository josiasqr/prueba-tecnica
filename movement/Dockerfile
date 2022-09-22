FROM openjdk:11
ENV URI_ACCOUNT=account
ENV HOST_MYSQL=mysql
RUN mkdir -p /home/movement
COPY target/movement-1.0.jar /home/movement
EXPOSE 4002
CMD ["java", "-jar", "/home/movement/movement-1.0.jar"]
