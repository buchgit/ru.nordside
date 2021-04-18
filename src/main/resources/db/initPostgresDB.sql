drop table if exists price_table;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS nomenclature;
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
    foreign key (nomenclature_group) references nomenclature_group(id)
);

CREATE TABLE unit
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    owner       INTEGER,
    code        VARCHAR NOT NULL,
    weight      DOUBLE PRECISION,
    volume      DOUBLE PRECISION,
    coefficient DOUBLE PRECISION,
    FOREIGN KEY (owner) REFERENCES nomenclature (id) ON DELETE CASCADE
);

create table price_table(
    id integer primary key default nextval('global_seq'),
    nomenclature integer not null,
    price_variant integer not null ,
    unit integer not null ,
    price double precision,
    foreign key (nomenclature)references nomenclature(id),
    foreign key (price_variant) references price_variant (id),
    foreign key (unit) references unit(id)
);

