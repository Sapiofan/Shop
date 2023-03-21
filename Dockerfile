#FROM tomcat:latest
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY target/root.war /usr/local/tomcat/webapps/
#CMD ["/usr/local/tomcat/bin/catalina.sh","run"]

FROM tomcat:9.0.13-jre11
RUN rm -rf /usr/local/tomcat/webapps/*
ADD target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]

#FROM maven AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package
#
#FROM tomcat
#COPY --from=build /app/target/root.war /usr/local/tomcat/webapps