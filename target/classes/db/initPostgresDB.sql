drop table if exists order_merchandise;
drop table if exists orders;
drop table if exists price_table;
DROP TABLE IF EXISTS nomenclature;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
drop table if exists price_variant;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

create table price_variant
(
    id   Integer primary key default nextval('global_seq'),
    name varchar not null
);

CREATE TABLE users
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          VARCHAR                           NOT NULL,
    email         VARCHAR                           NOT NULL ,
    password      VARCHAR                           NOT NULL,
    registered    TIMESTAMP           DEFAULT now() NOT NULL,
    price_variant INTEGER                           not null,
    enabled       BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE nomenclature
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    fullName    VARCHAR,
    unit        varchar,
    code        VARCHAR not null ,
    image_index VARCHAR,
    section     VARCHAR,
    subsection  VARCHAR,
    description VARCHAR,
    length      double precision,
    width       double precision,
    high        double precision,
    color       varchar,
    volume      double precision,
    countInPack integer
);
create unique index nomenclature_code_idx on nomenclature (code);

create table price_table(
    id integer primary key default nextval('global_seq'),
    nomenclature integer not null,
    price_variant integer not null ,
    unit varchar not null ,
    price double precision,
    constraint nomenclature_price_variant_idx unique (nomenclature, price_variant),
    foreign key (nomenclature)references nomenclature(id),
    foreign key (price_variant) references price_variant (id)
);

create table orders(
    id integer primary key default nextval('global_seq'),
    create_date timestamp default now() not null ,
    number_for1c varchar,
    client integer not null ,
    total_amount double precision,
    total_volume double precision,
    total_weight double precision,
    status varchar not null ,
    foreign key (client) references users(id)
);
create unique index number_for1c_idx on orders(number_for1c);

create table order_merchandise(
    id integer primary key default nextval('global_seq'),
    order_id integer not null ,
    merchandise_id integer not null ,
    foreign key (order_id) references orders(id),
    foreign key (merchandise_id) references price_table(id)
);


