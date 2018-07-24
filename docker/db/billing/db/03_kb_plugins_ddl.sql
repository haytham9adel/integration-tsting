/*! USE killbill_plugins */; 

/*! SET storage_engine=INNODB */;



/* We cannot use timestamp in MySQL because of the implicit TimeZone conversions it does behind the scenes */
ALTER DOMAIN datetime OWNER TO killbill ;
/* TEXT in MySQL is smaller then MEDIUMTEXT */
ALTER DOMAIN mediumtext OWNER TO killbill ;
/* PostgreSQL uses BYTEA to manage all BLOB types */
ALTER DOMAIN mediumblob OWNER TO killbill ;

CREATE OR REPLACE LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION last_insert_id() RETURNS BIGINT AS $$
    DECLARE
        result BIGINT;
    BEGIN
        SELECT lastval() INTO result;
        RETURN result;
    EXCEPTION WHEN OTHERS THEN
        SELECT NULL INTO result;
        RETURN result;
    END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION schema() RETURNS VARCHAR AS $$
    DECLARE
        result VARCHAR;
    BEGIN
        SELECT current_schema() INTO result;
        RETURN result;
    EXCEPTION WHEN OTHERS THEN
        SELECT NULL INTO result;
        RETURN result;
    END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION hour(ts TIMESTAMP WITH TIME ZONE) RETURNS INTEGER AS $$
    DECLARE
        result INTEGER;
    BEGIN
        SELECT EXTRACT(HOUR FROM ts) INTO result;
        RETURN result;
    EXCEPTION WHEN OTHERS THEN
        SELECT NULL INTO result;
        RETURN result;
    END;
$$ LANGUAGE plpgsql IMMUTABLE;

create table if not exists cartwheel_plans(
  id serial unique,
  service_provider_id int NOT NULL,
  service_id int NOT NULL,
  service_slug varchar(150) NOT NULL,
  plan_id int NOT NULL,
  plan_slug varchar(150) NOT NULL,
  plan_category varchar(100) NOT NULL,
  plan_price_id int NOT NULL,
  plan_price_slug varchar(150) NOT NULL,
  PRIMARY KEY(id)
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;

CREATE UNIQUE INDEX cartwheel_plans_plan_price_slug ON cartwheel_plans(plan_price_slug);

create table if not exists cartwheel_flavor(
  id serial unique,
  service_provider_id int NOT NULL,
  service_id int NOT NULL,
  service_slug varchar(150) NOT NULL,
  product_id int NOT NULL,
  product_slug varchar(150) NOT NULL,
  product_category varchar(100) NOT NULL,
  flavor_id int NOT NULL,
  flavor_slug varchar(150) NOT NULL,
  original_product_slug varchar(150) NOT NULL,
  PRIMARY KEY(id),
  unique (flavor_id, product_id)
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;


CREATE TABLE if not exists cartwheel_deals(
  id SERIAL UNIQUE,
  account_external_key VARCHAR(128) NOT NULL,
  deal_id INT NOT NULL UNIQUE,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  minimum_amount NUMERIC NOT NULL,
  settled BOOLEAN DEFAULT FALSE,
  processed_date datetime DEFAULT NULL,
  upcoming_invoice_amount NUMERIC DEFAULT NULL,
  settled_amount NUMERIC DEFAULT NULL,
  PRIMARY KEY(id)
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;

CREATE INDEX cartwheel_deals_account_external_key ON cartwheel_deals(account_external_key);

CREATE TABLE if not exists cartwheel_invoice_items(
    item_id VARCHAR(36) PRIMARY KEY NOT NULL,
    invoice_id VARCHAR(36) NOT NULL,
    category VARCHAR(36) NOT NULL
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;

CREATE UNIQUE INDEX cartwheel_invoice_items_item_id ON cartwheel_invoice_items (item_id);

CREATE TABLE if not exists invoice_publish_status(
  invoice_id varchar(36) NOT NULL,
  account_id varchar(36) NOT NULL,
  tenant_id varchar(36) NOT NULL,
  status boolean NOT NULL default false,
  PRIMARY KEY(invoice_id)
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;


CREATE TABLE if not exists premium_support(
  id INT NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  status varchar(36) NOT NULL,
  customer_id INT NOT NULL,
  subscription_id INT NOT NULL,
  PRIMARY KEY(id)
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;


CREATE TABLE if not exists sts_payment_method(
  record_id SERIAL PRIMARY KEY,
  kb_account_id char(36) not null,
  kb_payment_method_id char(36) not null,
  token varchar(255) not null,
  cc_holder_name varchar(32) not null,
  cc_card_number varchar(19) not null,
  cc_exp_date varchar(4) not null,
  is_default boolean not null default TRUE,
  is_deleted boolean not null default FALSE,
  created_date datetime not null,
  updated_date datetime not null,
  kb_tenant_id char(36) not null
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;

CREATE TABLE if not exists sts_response_record(
  record_id SERIAL PRIMARY KEY,
  kb_account_id char(36) not null,
  kb_payment_method_id char(36) not null,
  kb_transaction_id char(36) not null,
  sts_transaction_id varchar(20) not null,
  sts_response TEXT,
  sts_status_code VARCHAR(5),
  sts_status_description VARCHAR(512),
  created_date datetime not null,
  kb_tenant_id char(36) not null,
  UNIQUE(sts_transaction_id)
) /*! CHARACTER SET utf8 COLLATE utf8_bin */;

CREATE UNIQUE INDEX sts_payment_method_kb_payment_method_id ON sts_payment_method (kb_payment_method_id);

ALTER TABLE cartwheel_flavor ADD COLUMN stcs_chart_of_accounts_category varchar(10) NOT NULL DEFAULT '';

ALTER TABLE cartwheel_plans ADD COLUMN stcs_chart_of_accounts_category varchar(10) NOT NULL DEFAULT '';

ALTER TABLE cartwheel_invoice_items ADD COLUMN stcs_chart_of_accounts_category varchar(10) NOT NULL DEFAULT '';

ALTER TABLE cartwheel_flavor DROP CONSTRAINT cartwheel_flavor_flavor_id_product_id_key;

CREATE UNIQUE INDEX cartwheel_flavor_flavor_slug_product_slug ON cartwheel_flavor (flavor_slug, product_slug);