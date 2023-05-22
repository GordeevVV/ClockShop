create table logs
(
    id           bigint default nextval('logs_seq'::regclass) not null
        primary key,
    product_url  varchar,
    product_name varchar,
    buy_time     timestamp,
    material     varchar,
    mech_type    varchar,
    stamp        varchar
);

alter table logs
    owner to postgres;