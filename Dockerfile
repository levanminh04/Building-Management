# Sử dụng Tomcat chính thức với JDK 17 (tương thích Spring Boot 3)
FROM tomcat:10-jdk17

# Đặt biến môi trường (tuỳ chọn)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Sao chép file WAR vào thư mục chạy của Tomcat
COPY target/spring-boot-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Mở cổng 8080 (Railway sẽ tự động map)
EXPOSE 8080

# Khởi động Tomcat
CMD ["catalina.sh", "run"]

