-- Table: public.order_products

-- DROP TABLE public.order_products;

CREATE TABLE public.order_products
(
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    CONSTRAINT orderproduct_pkey PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk1 FOREIGN KEY (order_id)
        REFERENCES public.orders (order_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk2 FOREIGN KEY (product_id)
        REFERENCES public.products (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.order_products
    OWNER to postgres;