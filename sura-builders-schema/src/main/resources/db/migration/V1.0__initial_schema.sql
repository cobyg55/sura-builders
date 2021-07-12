CREATE TABLE PRODUCT
(
    id              NUMERIC   NOT NULL,
    name            VARCHAR   NOT NULL,
    reference       VARCHAR   NOT NULL,
    created_at      TIMESTAMP NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_name_uk UNIQUE (name),
    CONSTRAINT product_reference_uk UNIQUE (reference)
);

CREATE SEQUENCE IF NOT EXISTS product_seq START 1 INCREMENT BY 1;

CREATE TABLE INVENTORY
(
    id                                 NUMERIC   NOT NULL,
    quantity                           NUMERIC   NOT NULL,
    unit_cost                          NUMERIC   NOT NULL,
    total_cost                         NUMERIC   NOT NULL,
    product_id                         NUMERIC   NOT NULL,
    created_at                         TIMESTAMP NOT NULL,
    CONSTRAINT inventory_pk PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS inventory_seq START 1 INCREMENT BY 1;

ALTER TABLE INVENTORY
    ADD CONSTRAINT fk_inventory_product FOREIGN KEY (product_id) REFERENCES PRODUCT (id);

CREATE TABLE CONSTRUCTION_CYCLE
(
    id                          NUMERIC   NOT NULl,
    name                        VARCHAR   NOT NULL,
    total_days                  NUMERIC   NOT NULL,
    created_at                  TIMESTAMP NOT NULL,
    CONSTRAINT construction_cycle_pk PRIMARY KEY (id),
    CONSTRAINT construction_cycle_uk UNIQUE (name)
);

CREATE SEQUENCE IF NOT EXISTS construction_cycle_seq START 1 INCREMENT BY 1;

CREATE TABLE CONSTRUCTION_CYCLE_CONFIG
(
    id                           NUMERIC   NOT NULl,
    day_late                     NUMERIC   NOT NULL,
    construction_cycle_id        NUMERIC   NOT NULL,
    created_at                   TIMESTAMP NOT NULL,
    CONSTRAINT builder_construction_cycle_pk PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS construction_cycle_config_seq START 1 INCREMENT BY 1;

ALTER TABLE CONSTRUCTION_CYCLE_CONFIG
    ADD CONSTRAINT fk_construction_cycle_config_cycle FOREIGN KEY (construction_cycle_id) REFERENCES CONSTRUCTION_CYCLE (id);

CREATE TABLE BUILDERS
(
    id                NUMERIC   NOT NULL,
    name              VARCHAR   NOT NULL,
    status            VARCHAR   NOT NULL,
    created_at        TIMESTAMP NOT NULL,
);

CREATE TABLE BUILDERS_CONSTRUCTION_CYCLES
(
    builders_id                   NUMERIC NOT NULl,
    construction_cycles_config_id NUMERIC NOT NULl,
    CONSTRAINT builders_construction_cycle PRIMARY KEY (builders_id, construction_cycles_config_id)
);

ALTER TABLE BUILDERS_CONSTRUCTION_CYCLES
    ADD CONSTRAINT fk_builders_construction_cycle_builders FOREIGN KEY (builders_id) REFERENCES BUILDERS (id);

ALTER TABLE BUILDERS_CONSTRUCTION_CYCLES
    ADD CONSTRAINT fk_builders_construction_cycle_conf FOREIGN KEY (construction_cycles_config_id) REFERENCES CONSTRUCTION_CYCLE_CONFIG (id);




