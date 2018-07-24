#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER killbill WITH PASSWORD 'killbill' SUPERUSER;
    CREATE DATABASE killbill OWNER killbill;
    GRANT ALL PRIVILEGES ON DATABASE killbill TO killbill;
EOSQL

psql -v ON_ERROR_STOP=1 --username killbill --dbname killbill -f /docker-entrypoint-initdb.d/02_killbill_ddl.sql

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE killbill_plugins OWNER killbill;
    GRANT ALL PRIVILEGES ON DATABASE killbill_plugins TO killbill;
EOSQL

psql -v ON_ERROR_STOP=1 --username killbill --dbname killbill_plugins -f /docker-entrypoint-initdb.d/03_kb_plugins_ddl.sql

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER billing_service WITH PASSWORD 'billing_service' SUPERUSER;
    CREATE DATABASE billing_service_db OWNER billing_service;
    GRANT ALL PRIVILEGES ON DATABASE billing_service_db TO billing_service;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER ordermanagement WITH PASSWORD 'orderManagement' SUPERUSER;
    CREATE DATABASE order_management OWNER ordermanagement;
    GRANT ALL PRIVILEGES ON DATABASE order_management TO ordermanagement;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER camunda WITH PASSWORD 'camunda' SUPERUSER;
    CREATE DATABASE camunda OWNER camunda;
    GRANT ALL PRIVILEGES ON DATABASE camunda TO camunda;
EOSQL

