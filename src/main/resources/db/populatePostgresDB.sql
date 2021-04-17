DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM unit;
delete from price_variant;
DELETE From nomenclature;
DELETE FROM nomenclature_group;

ALTER SEQUENCE global_seq RESTART WITH 100000;
-- 100000
insert into price_variant(name)
values ('test');
-- 100001,100002
INSERT INTO users (name, email, password,price_variant)
VALUES ('User', 'user@gmail.com', '{noop}user',(SELECT ID FROM price_variant WHERE NAME = 'test')),
       ('Admin', 'admin@gmail.com', '{noop}admin',(SELECT ID FROM price_variant WHERE NAME = 'test'));
--
INSERT INTO user_roles (role, user_id)
VALUES ('USER', (SELECT ID FROM USERS WHERE NAME = 'User')),
       ('ADMIN', (SELECT ID FROM USERS WHERE NAME = 'Admin')),
       ('USER', (SELECT ID FROM USERS WHERE NAME = 'Admin'));
-- 100003
INSERT INTO nomenclature_group (name,level,code)
values ('Панель Матовая 05*250*2700',1,'000001064');
-- 100004 unit, pack_unit - индексы захардкожены
INSERT INTO nomenclature (name, fullName, unit, pack_unit, code, image_index, product_country, description, nomenclature_group)
values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 100005, 100006, '00000009457', '00000009457',
        'Russia', 'Матовая панель', (SELECT ID FROM nomenclature_group WHERE NAME = 'Панель Матовая 05*250*2700'));
-- 100005, 100006
INSERT INTO unit (name, code, owner, weight, volume, coefficient)
VALUES ('шт', '000016355', (SELECT ID FROM nomenclature WHERE NAME = 'панель 05*250*2700 белый матовый'), 1.08, 0.00, 0.675),
       ('упак', '000016351', (SELECT ID FROM nomenclature WHERE NAME = 'панель 05*250*2700 белый матовый'), 10.8, 0.00, 6.75);
