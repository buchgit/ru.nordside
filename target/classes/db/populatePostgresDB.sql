DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM unit;
DELETE From nomenclature;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', (SELECT ID FROM USERS WHERE NAME = 'User')),
       ('ADMIN', (SELECT ID FROM USERS WHERE NAME = 'Admin')),
       ('USER', (SELECT ID FROM USERS WHERE NAME = 'Admin'));

INSERT INTO nomenclature (name, fullName, unit, pack_unit, code, image_index, product_country, description)
values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 100003, 100004, '00000009457', '00000009457',
        'Russia', 'Матовая панель');

INSERT INTO unit (name, code, owner, weight, volume, coefficient)
VALUES ('шт', '000016355', 100002, 1.08, 0.00, 0.675),
       ('упак', '000016351', 100002, 10.8, 0.00, 6.75);
