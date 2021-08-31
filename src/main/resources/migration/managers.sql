-- Table: public.managers

-- DROP TABLE public.managers;

CREATE TABLE public.managers
(
    manager_id bigint NOT NULL DEFAULT nextval('managers_manager_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default",
    token character varying COLLATE pg_catalog."default",
    chat_id bigint,
    CONSTRAINT managers_pkey PRIMARY KEY (manager_id)
)
    WITH (
        OIDS = FALSE
        )
    TABLESPACE pg_default;

ALTER TABLE public.managers
    OWNER to postgre;