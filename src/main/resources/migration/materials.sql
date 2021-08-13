-- Table: public.materials

-- DROP TABLE public.materials;

CREATE TABLE public.materials
(
    material_id integer NOT NULL,
    material character varying COLLATE pg_catalog."default",
    CONSTRAINT materials_pkey PRIMARY KEY (material_id)
)

TABLESPACE pg_default;

ALTER TABLE public.materials
    OWNER to postgres;