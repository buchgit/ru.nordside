delete from partners;
delete from order_merchandise;
delete from nomenclature_cart_item;
delete from cart_items;
delete from client_order_line;
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
       ('Admin', 'admin@gmail.com', '{noop}admin',(SELECT ID FROM price_variant WHERE NAME = 'default2'));
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
insert into price_table(price_variant,nomenclature,unit,price,count,summa)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый'),
        'шт',
        1.12,
        1.00,
        1.12
        );
-- 100020
insert into price_table(price_variant,nomenclature,unit,price,count,summa)
values ((select id from price_variant where name='default'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый 319'),
        'м2',
        1.14,
        2.00,
        2.28);
-- 100021
insert into price_table(price_variant,nomenclature,unit,price,count,summa)
values ((select id from price_variant where name='default2'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый'),
        'шт',
        2.22,
        2.00,
        4.44);
-- 100022
insert into price_table(price_variant,nomenclature,unit,price,count,summa)
values ((select id from price_variant where name='default2'),
        (select id from nomenclature  where name='панель 05*250*2700 белый матовый 319'),
        'м2',
        2.24,
        1.00,
        2.24
        );

--100023,100024
insert into orders(number_for1c,client,total_amount,total_volume,total_weight,status)
values ('Б0000000001',(SELECT ID FROM USERS WHERE NAME = 'User'),1200.54,0.333,150.14,'NEW'),
       ('Б0000000003',(SELECT ID FROM USERS WHERE NAME = 'User'),1200.55,0.336,150.17,'IN_PROGRESS');

-- 100025
insert into client_order_line(order_id, nomenclature_id, unit, count, summa)
values ((SELECT ID FROM orders WHERE number_for1c = 'Б0000000001'),
        (SELECT ID FROM nomenclature WHERE name = 'панель 05*250*2700 белый матовый'),
        'шт',
        1.00,
        112.54);

--100026,100027
insert into order_merchandise(order_id, merchandise_id)
values ((select id from orders where orders.number_for1c = 'Б0000000001'),
        100019),
       ((select id from orders where orders.number_for1c = 'Б0000000003'),
        100020);

-- 100028,10029
insert into cart_items(user_id,nomenclature_id,price,count,create_date)
values ((SELECT ID FROM USERS WHERE NAME = 'User'),(select id from nomenclature  where name='панель 05*250*2700 белый матовый'),100.00,1.00,now()),
       ((SELECT ID FROM USERS WHERE NAME = 'User'),(select id from nomenclature  where name='панель 05*250*2700 белый матовый 319'),105.00,2.00,now());

--
insert into nomenclature_cart_item(nomenclature_id,cart_item_id)
values ((select id from nomenclature  where name='панель 05*250*2700 белый матовый'),100028),
       ((select id from nomenclature  where name='панель 05*250*2700 белый матовый 319'),100029);

-- last
insert into partners(name,site,address,telephone)
values ('Петрович','petrovich.ru/','пр. Энгельса, 157, лит. А','8 (812) 334-88-88'),
       ('Торговый дом «Вимос»','www.vimos.ru','г. Всеволожск, Колтушское ш., 298','(812) 666-66-55'),
       ('М-Профиль','www.mprofil.ru','Санкт-Петербург, ул.Воскова, д. 10','+7 (812) 336-23-96');