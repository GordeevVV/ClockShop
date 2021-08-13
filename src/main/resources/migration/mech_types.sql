-- Table: public.mechs_types

-- DROP TABLE public.mechs_types;

CREATE TABLE public.mechs_types
(
    mech_id integer NOT NULL,
    type character varying COLLATE pg_catalog."default",
    CONSTRAINT mechs_types_pkey PRIMARY KEY (mech_id)
)

TABLESPACE pg_default;

ALTER TABLE public.mechs_types
    OWNER to postgres;