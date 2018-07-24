FROM tomcat:8

# update and install needed packages
RUN apt-get update -yy && apt-get install -yy nginx wget default-jdk unzip python-setuptools && easy_install pip && pip install httplib2 requests 

RUN apt-get  -qq -y install net-tools netcat

RUN rm -rf /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/examples /usr/local/tomcat/webapps/host-manager /usr/local/tomcat/webapps/manager /usr/local/tomcat/webapps/docs

WORKDIR /usr/local/tomcat/webapps/
RUN wget -O ROOT.war https://search.maven.org/remotecontent?filepath=org/kill-bill/billing/killbill-profiles-killbill/0.18.16/killbill-profiles-killbill-0.18.16.war



ENV PLUGINS_DIR /killbill/bundles/plugins/java
ENV CATALOG_PLUGINS_DIR ${PLUGINS_DIR}/kb-catalog-plugin/1.0
ENV DEAL_PLUGINS_DIR ${PLUGINS_DIR}/kb-deal-plugin/1.0
ENV SUPPORT_PLUGINS_DIR ${PLUGINS_DIR}/kb-premium-support-plugin/1.0
ENV STC_PLUGINS_DIR ${PLUGINS_DIR}/kb-sts-payment-plugin/1.0
ENV INVOICE_PLUGIN_DIT ${PLUGINS_DIR}/kb-invoice-notifications-plugin/1.0

RUN mkdir -p ${PLUGINS_DIR}
RUN mkdir -p ${CATALOG_PLUGINS_DIR}
COPY kb-catalog-plugin-1.6-SNAPSHOT.jar ${CATALOG_PLUGINS_DIR}

RUN mkdir -p ${DEAL_PLUGINS_DIR}
COPY kb-deal-plugin-1.6.0-SNAPSHOT.jar ${DEAL_PLUGINS_DIR}

RUN mkdir -p ${SUPPORT_PLUGINS_DIR}
COPY kb-premium-support-plugin-1.7.0-SNAPSHOT.jar ${SUPPORT_PLUGINS_DIR}

RUN mkdir -p ${STC_PLUGINS_DIR}
COPY kb-sts-payment-plugin-1.4.0-SNAPSHOT.jar ${STC_PLUGINS_DIR}

RUN mkdir -p ${INVOICE_PLUGIN_DIT}
COPY kb-invoice-notifications-plugin-1.7.0-SNAPSHOT.jar ${INVOICE_PLUGIN_DIT}


COPY setenv.sh /usr/local/tomcat/bin/

COPY shiro.ini /killbill
COPY overdue.xml /killbill
COPY jolokia-jvm-1.3.4-agent.jar /killbill

COPY killbill.properties /tmp/
RUN cat /tmp/killbill.properties >> /usr/local/tomcat/conf/catalina.properties

COPY init.sh /killbill
COPY init.py /killbill
RUN chmod 700 /killbill/init.sh

EXPOSE 7777
EXPOSE 8080

RUN rm -rf /tmp/* /var/lib/apt/lists/*

CMD catalina.sh run & ((while ! echo exit | nc localhost 8080; do sleep 10; done) && /killbill/init.sh && while true; do sleep 10000; done )

