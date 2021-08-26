call mvn clean install -DskipTests=true

call docker build . --rm -t clockshop/clockshop:1.0
call docker push clockshop/clockshop:1.0