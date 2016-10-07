FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD currencymonitor-0.1.0-SNAPSHOT.jar /app.jar
RUN apk add --update bash && rm -rf /var/cache/apk/*
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
