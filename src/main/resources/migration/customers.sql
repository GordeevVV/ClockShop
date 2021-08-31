-- Table: public.customers

-- DROP TABLE public.customers;

CREATE TABLE public.customers
(
    customer_id bigint NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    address character varying(255) COLLATE pg_catalog."default",
    phone character varying COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
)

    TABLESPACE pg_default;

ALTER TABLE public.customers
    OWNER to postgres;