delete from order_merchandise;
delete from orders;
DELETE FROM user_roles;
DELETE FROM users;
delete from price_table;
DELETE FROM unit;
DELETE FROM pack_unit;
delete from price_variant;
DELETE From nomenclature;
DELETE FROM nomenclature_group;


ALTER SEQUENCE global_seq RESTART WITH 100000;
-- 100000
insert into price_variant(name)
values ('default');
-- 100001,100002
INSERT INTO users (name, email, password,price_variant)
VALUES ('User', 'user@gmail.com', '{noop}user',(SELECT ID FROM price_variant WHERE NAME = 'default')),
       ('Admin', 'admin@gmail.com', '{noop}admin',200000);
--
INSERT INTO user_roles (role, user_id)
VALUES ('USER', (SELECT ID FROM USERS WHERE NAME = 'User')),
       ('ADMIN', (SELECT ID FROM USERS WHERE NAME = 'Admin')),
       ('USER', (SELECT ID FROM USERS WHERE NAME = 'Admin'));
-- 100003
INSERT INTO nomenclature_group (name,level,code)
values ('Панель Матовая 05*250*2700',1,'000001064');

-- 100004
INSERT INTO unit (name, weight, volume, coefficient)
VALUES ('шт', 1.08, 0.03, 0.675);

-- 100004 unit, pack_unit - индексы захардкожены
INSERT INTO nomenclature (name, fullName, unit, pack_unit, code, image_index, product_country, description, nomenclature_group)
values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 100004, 100006, '00000009457', '00000009457',
        'Russia', 'Матовая панель', (SELECT ID FROM nomenclature_group WHERE NAME = 'Панель Матовая 05*250*2700'));


-- -- 100004 unit, pack_unit - индексы захардкожены
-- INSERT INTO nomenclature (name, fullName, unit, pack_unit, code, image_index, product_country, description, nomenclature_group)
-- values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 100005, 100006, '00000009457', '00000009457',
--         'Russia', 'Матовая панель', (SELECT ID FROM nomenclature_group WHERE NAME = 'Панель Матовая 05*250*2700'));
-- -- -- 100005
-- INSERT INTO unit (name, owner_id, weight, volume, coefficient)
-- VALUES ('шт',  (SELECT ID FROM nomenclature WHERE NAME = 'панель 05*250*2700 белый матовый'), 1.08, 0.03, 0.675);

-- 100006
INSERT INTO pack_unit (name, owner_id, weight, volume, coefficient)
VALUES ('упак',  (SELECT ID FROM nomenclature WHERE NAME = 'панель 05*250*2700 белый матовый'), 10.8, 0.30, 6.75);

-- 100007
insert into price_table(price_variant,nomenclature,unit,price)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый'),
        (select id from unit where name='шт'),
        1.12);

--100008
insert into orders(number_for1c,client,total_amount,total_volume,total_weight,status)
values ('Б0000000001',100001,1200.54,0.333,150.14,'NEW'),
       ('Б0000000003',100001,1200.55,0.336,150.17,'IN_PROGRESS');

--100010,100011
insert into order_merchandise(order_id, merchandise_id)
values (100008,100007),
       (100009,100007);