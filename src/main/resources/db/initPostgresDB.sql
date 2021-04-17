DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS nomenclature;
DROP TABLE IF EXISTS nomenclature_group;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                           NOT NULL,
    email      VARCHAR                           NOT NULL,
    password   VARCHAR                           NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    enabled    BOOL                DEFAULT TRUE  NOT NULL
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

