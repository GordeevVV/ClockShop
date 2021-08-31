-- Table: public.orders

-- DROP TABLE public.orders;

CREATE TABLE public.orders
(
    order_id integer NOT NULL DEFAULT nextval('orders_order_id_seq'::regclass),
    customer_id bigint,
    created_at timestamp with time zone,
    calc_price real,
    status character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (order_id),
    CONSTRAINT fk1 FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to postgres;