FROM rabbitmq:3.6-management 

ENV RABBITMQ_PLUGINS "rabbitmq_tracing rabbitmq_management_visualiser rabbitmq_shovel rabbitmq_shovel_management"

ENV RABBITMQ_HOME /var/lib/rabbitmq
ENV HOME $RABBITMQ_HOME

RUN rabbitmq-plugins enable --offline $RABBITMQ_PLUGINS 

ADD rabbitmq.config /etc/rabbitmq/
ADD definitions.json /etc/rabbitmq/

RUN chown rabbitmq:rabbitmq /etc/rabbitmq/rabbitmq.config /etc/rabbitmq/definitions.json
CMD ["rabbitmq-server"]

EXPOSE 15672
EXPOSE 5672