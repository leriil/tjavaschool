insert into role(name) values ('CLIENT');
insert into role(name) values ('SALESPERSON');

insert into address(country, city, zip_code, street, house, flat) values ('US','Washington','20001','Bloomingdale' ,'3' ,'5B');
insert into address(country, city, zip_code, street, house, flat) values ('Britain','London','20DF43','Circus' ,'45' ,'6');

insert into user(name, surname, login, password, birth_date, email, address_id) values ('Bill','Murrey','bill','b' ,'2000-01-12','bill@yandex.ru',1);
insert into user(name, surname, login, password, birth_date, email, address_id) values ('Paul','Marley','paul','p' ,'1968-04-03','paul@yandex.ru',1);
insert into user(name, surname, login, password, birth_date, email, address_id) values ('Mary','Fisher','marry','m' ,'1856-06-20','mary@yandex.ru',2);
insert into user(name, surname, login, password, birth_date, email, address_id) values ('Susan','Mayer','susy','s' ,'2002-12-29','suzy@yandex.ru',2);
-- insert into user(name, surname, login, password, birth_date, email) values ('Bill','Murrey','bill','$2a$04$dBOzyg8ikiHaKWJJONbgquqb9.gK4w5mOhAEAJcovHFTGRTxqBhTO' ,'2000-01-12','bill@yandex.ru');
-- insert into user(name, surname, login, password, birth_date, email) values ('Bob','Marley','bob','$2a$04$Tv6i5jzZ7OzQ50gMm9FhC.CmoOZh1OtSZlbktGSz31FEjV2oDu8Im' ,'04-10-1968','bob@yandex.ru');
-- insert into user(name, surname, login, password, birth_date, email) values ('Mary','Fisher','marry','$2a$04$LGSvlBTOvbQsdEszhhZItu4NToZQT3AzTH82d6vwEaPrXeyLmGaZW' ,'10-05-1856','mary@yandex.ru');
-- insert into user(name, surname, login, password, birth_date, email) values ('Susan','Mayer','susy','$2a$04$Hz5QllwBVlxte3OeqHfVl.anGjMtMt2PBVFCf8tS2M6xTFVB4Gqji' ,'04-01-2002','suzy@yandex.ru');

insert into product(name, category, in_stock, price, volume, weight) values ('laptop','DEVICE',20,1000.00 ,NULL ,500);
insert into product(name, category, in_stock, price, volume, weight) values ('iphone','DEVICE',10,400.00 ,NULL ,40);
insert into product(name, category, in_stock, price, volume, weight) values ('camera','DEVICE',15,600.00 ,NULL ,100);
insert into product(name, category, in_stock, price, volume, weight) values ('ebook','DEVICE',5,50.00 ,NULL ,20);
insert into product(name, category, in_stock, price, volume, weight) values ('laptop_service','SERVICE',20,1500.00 ,NULL ,NULL);
insert into product(name, category, in_stock, price, volume, weight) values ('iphone_service','SERVICE',20,2000.00 ,NULL ,NULL);
insert into product(name, category, in_stock, price, volume, weight) values ('camera_service','SERVICE',20,1500.00 ,NULL ,NULL);
insert into product(name, category, in_stock, price, volume, weight) values ('ebook_service','SERVICE',20,1500.00 ,NULL ,NULL);

insert into user_role(user_id,role_id)values (1,1);
insert into user_role(user_id,role_id)values (2,1);
insert into user_role(user_id,role_id)values (3,2);
insert into user_role(user_id,role_id)values (4,2);



insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status) values (1,1,1,'cash','pickup','PAID','DELIVERED');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status) values (2,2,3,'cash','pickup','NOT_PAID','IN_TRANSIT');

insert into order_product (order_id,product_id)values (1,2);
insert into order_product (order_id,product_id)values (1,2);
insert into order_product (order_id,product_id)values (2,1);
insert into order_product (order_id,product_id)values (2,2);



