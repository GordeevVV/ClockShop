-- Table: public.discounts

-- DROP TABLE public.discounts;

CREATE TABLE public.discounts
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
)

    TABLESPACE pg_default;

ALTER TABLE public.discounts
    OWNER to postgres;