FROM eclipse-temurin:21-jre

COPY --chown=nobody:nogroup target/*.jar /runtime/app.jar

USER nobody
WORKDIR /runtime

EXPOSE 8080

CMD exec java ${JAVA_OPTS} -jar -Dspring.profiles.active=prod app.jar
