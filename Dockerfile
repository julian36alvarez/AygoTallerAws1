FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000
ENV JAVA_OPTS -Xmx512m -Xms256m

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency




CMD ["java","-cp","./classes:./dependency/*","SparkWebServer"]