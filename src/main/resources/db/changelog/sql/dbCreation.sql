CREATE TABLE if not exists customers
(
    customer_id bigint NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    address character varying(255) COLLATE pg_catalog."default",
    phone character varying COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);

CREATE TABLE if not exists managers
(
    manager_id bigserial,
    name character varying COLLATE pg_catalog."default",
    token character varying COLLATE pg_catalog."default",
    chat_id bigint,
    CONSTRAINT managers_pkey PRIMARY KEY (manager_id)
);

CREATE TABLE if not exists materials
(
    material_id integer NOT NULL,
    material character varying COLLATE pg_catalog."default",
    CONSTRAINT materials_pkey PRIMARY KEY (material_id)
);

CREATE TABLE if not exists mech_types
(
    mech_id integer NOT NULL,
    type character varying COLLATE pg_catalog."default",
    CONSTRAINT mechs_types_pkey PRIMARY KEY (mech_id)
);

CREATE TABLE if not exists stamp
(
    stamp_id integer NOT NULL,
    stamp character varying COLLATE pg_catalog."default",
    CONSTRAINT "Stamp_pkey" PRIMARY KEY (stamp_id)
);

CREATE TABLE if not exists products
(
    product_id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    release_date timestamp,
    image_url character varying COLLATE pg_catalog."default",
    material_id integer NOT NULL,
    mech_id integer NOT NULL,
    price real NOT NULL,
    stamp_id integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id),
    CONSTRAINT fk_material FOREIGN KEY (material_id)
        REFERENCES public.materials (material_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT fk_mech FOREIGN KEY (mech_id)
        REFERENCES public.mech_types (mech_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT fk_stamp FOREIGN KEY (stamp_id)
        REFERENCES public.stamp (stamp_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE if not exists public.orders
(
    order_id serial,
    customer_id bigint,
    created_at timestamp with time zone,
    calc_price real,
    status character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (order_id),
    CONSTRAINT fk1 FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE if not exists public.order_products
(
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    orderproduct_id serial,
    CONSTRAINT order_products_pk PRIMARY KEY (orderproduct_id),
    CONSTRAINT fk1 FOREIGN KEY (order_id)
        REFERENCES public.orders (order_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk2 FOREIGN KEY (product_id)
        REFERENCES public.products (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE if not exists logs
(
    id bigserial,
    product_url  varchar,
    product_name varchar,
    buy_time     timestamp,
    material     varchar,
    mech_type    varchar,
    stamp        varchar,
    CONSTRAINT logs_pkey PRIMARY KEY (id)
);

CREATE TABLE if not exists public.discounts
(
    discount_id integer NOT NULL,
    product_id integer,
    customer_id integer,
    name character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    percent integer,
    CONSTRAINT discounts_pkey PRIMARY KEY (discount_id),
    CONSTRAINT fk_cust FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_prod FOREIGN KEY (product_id)
        REFERENCES public.products (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

