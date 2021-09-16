delete from order_merchandise;
delete from orders;
DELETE FROM user_roles;
DELETE FROM users;
delete from price_table;
delete from price_variant;
DELETE From nomenclature;

ALTER SEQUENCE global_seq RESTART WITH 100000;
-- 100000
insert into price_variant(name,code)
values ('default',''),
       ('default2','2');
-- 100002,100003
INSERT INTO users (name, email, password,price_variant)
VALUES ('User', 'user@gmail.com', '{noop}user',(SELECT ID FROM price_variant WHERE NAME = 'default')),
       ('Admin', 'admin@gmail.com', '{noop}admin',200000);
--
INSERT INTO user_roles (role, user_id)
VALUES ('USER', (SELECT ID FROM USERS WHERE NAME = 'User')),
       ('ADMIN', (SELECT ID FROM USERS WHERE NAME = 'Admin')),
       ('USER', (SELECT ID FROM USERS WHERE NAME = 'Admin'));

-- 100004
INSERT INTO nomenclature (name, fullName, unit,  code,
                          image_index, section, subsection, description,
                          length,width, size1,size2,diameter,high,color,pack_volume,pack_weight,pack_square,countInPack)
values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 'шт', '00000000318',
        '00000000318','Панели ПВХ', 'Панели ПВХ стеновые "Матовые"','без описания',
        2700.00,250.00,0.00,0.00,0.00,5.00,'белый',0.0034,4.030,5.005,10);
-- 100005
INSERT INTO nomenclature (name, fullName, unit,  code,
                          image_index, section, subsection, description,
                          length,width, size1,size2,diameter,high,color,pack_volume,pack_weight,pack_square,countInPack)
values ('панель 05*250*2700 белый матовый 319', 'панель 05*250*2700 белый матовый 319', 'м2', '00000000319',
        '00000000319','Панели ПВХ', 'Панели ПВХ стеновые "Матовые"','без описания',
        2700.00,250.00,0.00,0.00,0.00,5.00,'белый',0.0034,4.030,5.005,10);

-- 100006
insert into price_table(price_variant,nomenclature,unit,price)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый'),
        'шт',
        1.12);
-- 100007
insert into price_table(price_variant,nomenclature,unit,price)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый 319'),
        'м2',
        1.14);

--100008,10009
insert into orders(number_for1c,client,total_amount,total_volume,total_weight,status)
values ('Б0000000001',100002,1200.54,0.333,150.14,'NEW'),
       ('Б0000000003',100002,1200.55,0.336,150.17,'IN_PROGRESS');

--1000010,100011
insert into order_merchandise(order_id, merchandise_id)
values (100008,100006),
       (100009,100007);