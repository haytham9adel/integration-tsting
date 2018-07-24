FROM java:8

ENV WORKDIR /root/billing_services
ENV BILLING_SERVICE_DIR $WORKDIR

# update and install needed packages
RUN apt-get update -yy && apt-get install -yy haveged

RUN mkdir $BILLING_SERVICE_DIR

COPY billing-services-3.1.0-SNAPSHOT.jar $BILLING_SERVICE_DIR
COPY application.properties $BILLING_SERVICE_DIR
COPY logback.xml $BILLING_SERVICE_DIR

EXPOSE 8070

WORKDIR $WORKDIR

CMD ["java", "-jar", "billing-services-3.1.0-SNAPSHOT.jar", "--spring.config.location=file:/application.properties"]