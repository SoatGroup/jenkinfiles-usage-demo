FROM openjdk:11-jre-slim
LABEL maintainer="Patrick Allain"

ARG APP_USER=app
ARG APP_GROUP=app
ARG APP_UID=1000
ARG APP_GID=1000
ARG APP_HOME=/var/app

ENV ENVIRONMENT ''
ENV JAVA_OPTS '-XX:+UseContainerSupport -XX:MaxRAMPercentage=80 -XX:+PreferContainerQuotaForCPUCount'

RUN mkdir -p ${APP_HOME} \
  && chown ${APP_UID}:${APP_GID} ${APP_HOME} \
  && groupadd -g ${APP_UID} ${APP_GROUP} \
  && useradd -d "${APP_HOME}" -u ${APP_UID} -g ${APP_GID} -m -s /bin/false ${APP_USER}

WORKDIR ${APP_HOME}
USER ${APP_USER}

COPY target/*.jar ${APP_HOME}"/application.jar"

EXPOSE 8080
ENTRYPOINT ["java", \
    "-server", \
    "-Djava.awt.headless=true", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", "./application.jar", \
    "--spring.profiles.active=${ENVIRONMENT}"]
