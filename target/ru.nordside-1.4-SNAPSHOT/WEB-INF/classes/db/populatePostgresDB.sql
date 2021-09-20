delete from order_merchandise;
delete from orders;
DELETE FROM user_roles;
DELETE FROM users;
delete from price_table;
delete from price_variant;
DELETE From nomenclature;
delete from nomenclature_collection;
delete from nomenclature_category;

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
-- 100004, 100005,100006,100007
insert into nomenclature_category (name)
values
        ('Виниловый сайдинг'),
        ('Фасадные панели'),
        ('ПВХ панели'),
        ('Водосточные системы')
;

-- 8 шт. 100008,100009,100010,100011, 100012,100013,100014,100015
insert into nomenclature_collection(name,
                                    category)
values ('Панели с офсетной печатью',
        (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели')),
       ('Панели с термопереводной печатью',
        (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели')),
       ('Панели с цифровой печатью',
        (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели')),
       ('Панели ламинированные',
        (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели')),
       ('Панели потолочные',
        (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели')),

       ('Желоб водосточный',
        (select id from nomenclature_category where nomenclature_category.name = 'Водосточные системы')),
       ('Труба сливная',
        (select id from nomenclature_category where nomenclature_category.name = 'Водосточные системы')),
       ('Аксессуары для монтажа',
        (select id from nomenclature_category where nomenclature_category.name = 'Водосточные системы'))
       ;

INSERT INTO nomenclature (name, fullName, unit,  code,
                          image_index,
                          category,
                          nomenclature_collection,
                          description,
                          length,width, size1,size2,diameter,high,color,pack_volume,pack_weight,pack_square,countInPack)
-- 100016
values ('панель 05*250*2700 белый матовый', 'панель 05*250*2700 белый матовый', 'шт', '00000000318',
        '00000000318',
        (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели'),
        (select id from nomenclature_collection where nomenclature_collection.name = 'Панели с офсетной печатью'),
        'без описания',
        2700.00,250.00,0.00,0.00,0.00,5.00,'белый',0.0034,4.030,5.005,10),
-- 100017
        ('Желоб водосточный', 'Желоб водосточный белый', 'шт', '00000008439',
        '00000008439',
        (select id from nomenclature_category where nomenclature_category.name = 'Водосточные системы'),
         (select id from nomenclature_collection where nomenclature_collection.name = 'Желоб водосточный'),
        'без описания',
        2700.00,250.00,0.00,0.00,0.00,5.00,'белый',0.0034,4.030,5.005,10),
-- 100018
    ('панель 05*250*2700 белый матовый 319', 'панель 05*250*2700 белый матовый 319', 'м2', '00000000319',
    '00000000319',
    (select id from nomenclature_category where nomenclature_category.name = 'ПВХ панели'),
    (select id from nomenclature_collection where nomenclature_collection.name = 'Панели с офсетной печатью'),
    'без описания',
    2700.00,250.00,0.00,0.00,0.00,5.00,'белый',0.0034,4.030,5.005,10);

-- 100019
insert into price_table(price_variant,nomenclature,unit,price)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый'),
        'шт',
        1.12);
-- 100020
insert into price_table(price_variant,nomenclature,unit,price)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый 319'),
        'м2',
        1.14);

--100021,100022
insert into orders(number_for1c,client,total_amount,total_volume,total_weight,status)
values ('Б0000000001',100002,1200.54,0.333,150.14,'NEW'),
       ('Б0000000003',100002,1200.55,0.336,150.17,'IN_PROGRESS');

--1000022,100023
insert into order_merchandise(order_id, merchandise_id)
values (100021,100019),
       (100022,100020);

-- last
insert into partners(name,site,address,telephone)
values ('Петрович','petrovich.ru/','пр. Энгельса, 157, лит. А','8 (812) 334-88-88'),
       ('Торговый дом «Вимос»','www.vimos.ru','г. Всеволожск, Колтушское ш., 298','(812) 666-66-55'),
       ('М-Профиль','www.mprofil.ru','Санкт-Петербург, ул.Воскова, д. 10','+7 (812) 336-23-96');