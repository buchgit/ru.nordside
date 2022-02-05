drop table if exists partners;
drop table if exists nomenclature_cart_item;
drop table if exists cart_items;
drop table if exists order_merchandise;
drop table if exists client_order_line;
drop table if exists orders;
drop table if exists price_table;
DROP TABLE IF EXISTS nomenclature;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
drop table if exists price_variant;
drop table if exists nomenclature_collection;
drop table if exists nomenclature_category;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

create table partners(
    id integer primary key default nextval('global_seq'),
    name varchar not null ,
    telephone varchar,
    site varchar,
    address varchar
);
create unique index partners_unique_name_idx on partners(name);

create table nomenclature_category
(
    id   Integer primary key default nextval('global_seq'),
    name varchar not null
);

create table nomenclature_collection
(
    id   integer primary key default nextval('global_seq'),
    name varchar not null,
    category integer not null ,
    CONSTRAINT name_category_idx UNIQUE (name, category),
    foreign key (category) references nomenclature_category(id)
);

create table price_variant
(
    id   Integer primary key default nextval('global_seq'),
    name varchar not null,
    code varchar default ''
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
    category    integer not null ,
    nomenclature_collection  integer not null ,
    description VARCHAR,
    length      double precision,
    width       double precision,
    size1       double precision,
    size2       double precision,
    diameter    double precision,
    high        double precision,
    color       varchar,
    pack_volume double precision,
    pack_weight double precision,
    pack_square double precision,
    countInPack integer,
    foreign key (category) references nomenclature_category(id)
);
create unique index nomenclature_code_idx on nomenclature (code);

create table price_table(
    id integer primary key default nextval('global_seq'),
    nomenclature integer not null,
    price_variant integer not null ,
    unit varchar not null ,
    price double precision,
    count double precision,
    summa double precision,
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

create table client_order_line
(
    id           integer primary key default nextval('global_seq'),
    order_id     integer not null,
    nomenclature_id integer not null,
    unit         varchar not null,
    count        double precision,
    summa        double precision,
--     constraint nomenclature_order_number_idx unique (nomenclature_id, order_id),
    foreign key (nomenclature_id) references nomenclature (id),
    foreign key (order_id) references orders (id)
);


create table order_merchandise(
    id integer primary key default nextval('global_seq'),
    order_id integer not null ,
    merchandise_id integer not null ,
    foreign key (order_id) references orders(id),
    foreign key (merchandise_id) references price_table(id)
);

create table cart_items (
    id integer primary key default nextval('global_seq'),
    user_id integer not null ,
    nomenclature_id integer not null ,
    price double precision default 0.00,
    count double precision default 0.00,
    create_date timestamp default now() not null,
    foreign key (user_id) references users(id),
    foreign key (nomenclature_id) references nomenclature(id)
);
create unique index cart_items_unique_nomenclature_id_idx on cart_items(nomenclature_id);

create table nomenclature_cart_item(
    id integer primary key default nextval('global_seq'),
    nomenclature_id integer not null ,
    cart_item_id integer not null ,
    foreign key (nomenclature_id) references nomenclature(id),
    foreign key (cart_item_id) references cart_items(id)
);