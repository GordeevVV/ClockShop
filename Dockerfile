FROM openjdk:8-alpine3.9
MAINTAINER clockshop.com
ADD target/clockshop.jar /app/clockshop.jar


CMD ["/usr/bin/java", "-Xmx1G", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/clockshop.jar"]
