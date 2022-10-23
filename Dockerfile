FROM tomcat:10
ADD ./conf/* /usr/local/tomcat/conf/
WORKDIR /usr/local/tomcat/bin
CMD echo tryiing to reastart server
CMD mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps2
CMD mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps
CMD cp /tmp/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
CMD catalina.sh run