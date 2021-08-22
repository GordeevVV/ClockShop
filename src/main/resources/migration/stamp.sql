-- Table: public.stamp

-- DROP TABLE public.stamp;

CREATE TABLE public.stamp
(
    stamp_id integer NOT NULL,
    stamp character varying COLLATE pg_catalog."default",
    CONSTRAINT "Stamp_pkey" PRIMARY KEY (stamp_id)
)

TABLESPACE pg_default;

ALTER TABLE public.stamp
    OWNER to postgres;