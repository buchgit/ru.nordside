drop table if exists order_merchandise;
drop table if exists orders;
drop table if exists price_table;
DROP TABLE IF EXISTS pack_unit;
DROP TABLE IF EXISTS nomenclature;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS nomenclature_group;
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
    email         VARCHAR                           NOT NULL,
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

CREATE TABLE nomenclature_group
(
    id    integer primary key default nextval('global_seq'),
    name  varchar not null,
    level integer,
    code  varchar
);
create unique index code_idx on nomenclature_group (code);

CREATE TABLE unit
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    weight      DOUBLE PRECISION,
    volume      DOUBLE PRECISION,
    coefficient DOUBLE PRECISION
--     FOREIGN KEY (owner_id) REFERENCES nomenclature (id) ON DELETE CASCADE
);

CREATE TABLE nomenclature
(
    id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name               VARCHAR NOT NULL,
    fullName           VARCHAR,
    unit               INTEGER,
    pack_unit          INTEGER,
    code               VARCHAR,
    image_index        VARCHAR,
    product_country    VARCHAR,
    description        VARCHAR,
    nomenclature_group INTEGER,
    foreign key (unit) references unit(id),
    foreign key (nomenclature_group) references nomenclature_group(id)
);
create unique index nomenclature_code_idx on nomenclature (code);


CREATE TABLE pack_unit
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    owner_id    INTEGER,
    weight      DOUBLE PRECISION,
    volume      DOUBLE PRECISION,
    coefficient DOUBLE PRECISION,
    FOREIGN KEY (owner_id) REFERENCES nomenclature (id) ON DELETE CASCADE
);

create table price_table(
    id integer primary key default nextval('global_seq'),
    nomenclature integer not null,
    price_variant integer not null ,
    unit integer not null ,
    price double precision,
    constraint nomenclature_price_variant_idx unique (nomenclature, price_variant),
    foreign key (nomenclature)references nomenclature(id),
    foreign key (price_variant) references price_variant (id),
    foreign key (unit) references unit(id)
);

create table orders(
    id integer primary key default nextval('global_seq'),
    number varchar not null ,
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


