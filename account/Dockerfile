FROM openjdk:11
ENV URI_MOVEMENT=movement
ENV HOST_MYSQL=mysql
ENV URI_CUSTOMER=customer
RUN mkdir -p /home/account
COPY target/account-1.0.jar /home/account
EXPOSE 4001
CMD ["java", "-jar", "/home/account/account-1.0.jar"]
