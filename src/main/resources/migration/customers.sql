-- Table: public.customers

-- DROP TABLE public.customers;

CREATE TABLE public.customers
(
    customer_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1000 MINVALUE 10 MAXVALUE 10000 CACHE 1 ),
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    address character varying(255) COLLATE pg_catalog."default",
    phone integer,
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
)

TABLESPACE pg_default;

ALTER TABLE public.customers
    OWNER to postgres;