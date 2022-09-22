FROM openjdk:11
ENV URI_ACCOUNT=account
ENV URI_MOVEMENT=movement
ENV HOST_MYSQL=mysql
RUN mkdir -p /home/customer
COPY target/customer-1.0.jar /home/customer
EXPOSE 4000
CMD ["java", "-jar", "/home/customer/customer-1.0.jar"]
