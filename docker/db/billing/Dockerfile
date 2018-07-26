FROM postgres:9.5

ENV POSTGRES_DB billing
ENV POSTGRES_USER billing
ENV POSTGRES_PASSWORD billing

RUN mkdir /ddl

COPY ddl /ddl/
COPY db /docker-entrypoint-initdb.d/

