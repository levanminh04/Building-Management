

FROM eclipse-temurin:17-jre

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file .war vào container
COPY target/myapp.war /app/myapp.war

ENV SERVER_PORT=${PORT}

#chạy trong môi trường production
ENV SPRING_PROFILES_ACTIVE=prod

# Chạy ứng dụng với Tomcat
CMD ["java", "-jar", "/app/myapp.war"]
