-- Table: public.order_products

-- DROP TABLE public.order_products;

CREATE TABLE public.order_products
(
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    orderproduct_id integer NOT NULL DEFAULT nextval('order_products_orderproduct_id_seq'::regclass),
    CONSTRAINT order_products_pk PRIMARY KEY (orderproduct_id),
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