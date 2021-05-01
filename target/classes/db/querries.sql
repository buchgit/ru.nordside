insert into price_table(price_variant,nomenclature,unit,price)
values ((select id from price_variant where name='default'),100008,100010,1.12);