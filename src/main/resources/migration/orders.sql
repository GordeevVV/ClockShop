-- Table: public.orders

-- DROP TABLE public.orders;

CREATE TABLE public.orders
(
    order_id integer NOT NULL,
    customer_id integer,
    created_at timestamp with time zone,
    calc_price real,
    CONSTRAINT order_pkey PRIMARY KEY (order_id),
    CONSTRAINT fk1 FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to postgres;