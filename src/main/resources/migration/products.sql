-- Table: public.products

-- DROP TABLE public.products;

CREATE TABLE public.products
(
    product_id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    release_date date,
    image_url character varying COLLATE pg_catalog."default",
    material_id integer NOT NULL,
    mech_id integer NOT NULL,
    price real NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id),
    CONSTRAINT fk_material FOREIGN KEY (material_id)
        REFERENCES public.materials (material_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
        NOT VALID,
    CONSTRAINT fk_mech FOREIGN KEY (mech_id)
        REFERENCES public.mechs_types (mech_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.products
    OWNER to postgres;