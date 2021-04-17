DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM unit;
DELETE From nomenclature;
DELETE FROM nomenclature_group;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', (SELECT ID FROM USERS WHERE NAME = 'User')),
       ('ADMIN', (SELECT ID FROM USERS WHERE NAME = 'Admin')),
       ('USER', (SELECT ID FROM USERS WHERE NAME = 'Admin'));

INSERT INTO nomenclature_group (name,level,code)
values ('Панель Матовая 05*250*2700',1,'000001064');

INSERT INTO nomenclature (name, fullName, unit, pack_unit, code, image_index, product_country, description, nomenclature_group)
values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 100004, 100005, '00000009457', '00000009457',
        'Russia', 'Матовая панель', 100002);

INSERT INTO unit (name, code, owner, weight, volume, coefficient)
VALUES ('шт', '000016355', 100003, 1.08, 0.00, 0.675),
       ('упак', '000016351', 100003, 10.8, 0.00, 6.75);
