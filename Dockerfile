FROM openjdk:8-alpine3.9
ADD target/clockshop.jar /app/clockshop.jar


CMD ["/usr/bin/java","-Xmx1G","-jar", "/app/clockshop.jar"]
