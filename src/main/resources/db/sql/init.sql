insert into role(name) values ('CLIENT');
insert into role(name) values ('SALESPERSON');

insert into category(category_name) values ('ACCESSORIES');
insert into category(category_name) values ('CAMERA');
insert into category(category_name) values ('COMPUTER');
insert into category(category_name) values ('HEADPHONES');
insert into category(category_name) values ('CELLPHONE');
insert into category(category_name) values ('EBOOK_READER');


insert into address(country, city, zip_code, street, house, flat) values ('US','Washington','20001','Bloomingdale' ,'3' ,'5B');
insert into address(country, city, zip_code, street, house, flat) values ('Britain','London','20DF43','Circus' ,'45' ,'6');
insert into address(country, city, zip_code, street, house, flat) values ('US','New York','220701','42nd' ,'54' ,'5A');
insert into address(country, city, zip_code, street, house, flat) values ('US','Boston','223453','Brunswick' ,'4545' ,'1');
insert into address(country, city, zip_code, street, house, flat) values ('US','New York','210439','Central' ,'579' ,'233');
insert into address(country, city, zip_code, street, house, flat) values ('Britain','Cardiff','20AS43','Picasso' ,'45' ,'78');

insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Bill','Murrey','bill','b','b','2000-01-12','bill@yandex.ru',1);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Paul','Marley','paul','p','p','1968-04-03','paul@yandex.ru',1);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Mary','Fisher','marry','m','m','1856-06-20','mary@yandex.ru',2);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Susan','Mayer','susy','s','s','2002-12-29','suzy@yandex.ru',2);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Bob','Zaget','bob','bo','bo','1995-03-15','bob@yandex.ru',3);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Terry','Pratchet','terry','t','t','1969-04-03','terry@yandex.ru',6);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Jack','Black','jack','j','j','1866-06-23','jack@yandex.ru',5);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Iya','Thomas','iya','i','i','2003-11-29','iya@yandex.ru',3);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Mike','Brown','mike','mi','mi','1979-07-03','mike@yandex.ru',4);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Linda','Brown','linda','l','l','1878-06-23','linda@yandex.ru',4);
insert into user(name, surname, login, password, confirm_password, birth_date, email, address_id) values ('Anna','Jones','anna','a','a','1990-11-19','anna@yandex.ru',6);
-- insert into user(name, surname, login, password, birth_date, email) values ('Bill','Murrey','bill','$2a$04$dBOzyg8ikiHaKWJJONbgquqb9.gK4w5mOhAEAJcovHFTGRTxqBhTO' ,'2000-01-12','bill@yandex.ru');
-- insert into user(name, surname, login, password, birth_date, email) values ('Bob','Marley','bob','$2a$04$Tv6i5jzZ7OzQ50gMm9FhC.CmoOZh1OtSZlbktGSz31FEjV2oDu8Im' ,'04-10-1968','bob@yandex.ru');
-- insert into user(name, surname, login, password, birth_date, email) values ('Mary','Fisher','marry','$2a$04$LGSvlBTOvbQsdEszhhZItu4NToZQT3AzTH82d6vwEaPrXeyLmGaZW' ,'10-05-1856','mary@yandex.ru');
-- insert into user(name, surname, login, password, birth_date, email) values ('Susan','Mayer','susy','$2a$04$Hz5QllwBVlxte3OeqHfVl.anGjMtMt2PBVFCf8tS2M6xTFVB4Gqji' ,'04-01-2002','suzy@yandex.ru');

insert into product(name, category_id, in_stock, price, volume, weight) values ('Energizer AA Batteries, Double A Battery Max Alkaline (48 Count)',1,20,18.99 ,NULL ,100);
insert into product(name, category_id, in_stock, price, volume, weight) values ('Fitbit Versa Smart Watch, Gray/Silver Aluminium, One Size (S & L Bands Included)',1,10,199.95 ,NULL ,57);
insert into product(name, category_id, in_stock, price, volume, weight) values ('Fujifilm Instax Mini 9 Ice Blue Instant Camera',2,15,56.00 ,NULL ,200);
insert into product(name, category_id, in_stock, price, volume, weight) values ('GoPro HERO5 Black Waterproof Digital Action Camera for Travel with Touch Screen 4K HD Video 12MP Photos',2,15,248.00 ,NULL ,150);
insert into product(name, category_id, in_stock, price, volume, weight) values ('WD 2TB Elements Portable External Hard Drive - USB 3.0 - WDBU6Y0020BBK-WESN',3,5,74.47 ,NULL ,20);
insert into product(name, category_id, in_stock, price, volume, weight) values ('HP 23.8-inch FHD IPS Monitor with Tilt/Height Adjustment and Built-in Speakers (VH240a, Black)',3,20,109.99 ,NULL ,3020);
insert into product(name, category_id, in_stock, price, volume, weight) values ('Jabra Elite Active 65t Alexa Enabled True Wireless Sports Earbuds with Charging Case Copper Blue',4,20, 159.99 ,NULL ,13);
insert into product(name, category_id, in_stock, price, volume, weight) values ('Samsung Galaxy S7 SM-G930V 32GB for Verizon (Certified Refurbished)',5,20, 210.07 ,NULL ,90);
insert into product(name, category_id, in_stock, price, volume, weight) values ('Apple iPod Touch 128GB Space Gray (6th Generation) Newest Model',5,20,215.40 ,NULL ,110);
insert into product(name, category_id, in_stock, price, volume, weight) values ('All-New Kindle Paperwhite Leather Cover (10th Generation-2018), Black ',6,20, 39.99 ,NULL ,60);
insert into product(name, category_id, in_stock, price, volume, weight) values ('All-new Kindle Paperwhite Now Waterproof with 2x the Storage (International Version)',5,20,149.00 ,NULL ,75);

insert into user_role(user_id,role_id)values (1,1);
insert into user_role(user_id,role_id)values (2,1);
insert into user_role(user_id,role_id)values (3,2);
insert into user_role(user_id,role_id)values (4,2);
insert into user_role(user_id,role_id)values (5,1);
insert into user_role(user_id,role_id)values (6,1);
insert into user_role(user_id,role_id)values (7,1);
insert into user_role(user_id,role_id)values (8,1);
insert into user_role(user_id,role_id)values (9,1);
insert into user_role(user_id,role_id)values (10,1);
insert into user_role(user_id,role_id)values (11,1);

insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (1,1,1,'cash','delivery','PAID','DELIVERED', '2018-10-14');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (2,2,2,'cash','delivery','PAID','DELIVERED', '2018-10-16');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (3,1,1,'cash','pickup','PAID','DELIVERED', '2018-10-20');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (4,2,2,'cash','pickup','NOT_PAID','EXPIRED', '2018-10-24');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (5,3,5,'online','delivery','PAID','DELIVERED', '2018-10-28');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (6,6,6,'online','pickup','PAID','IN_TRANSIT', '2018-10-30');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (7,5,7,'cash','pickup','PAID','DELIVERED', '2018-11-1');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (8,3,8,'cash','pickup','NOT_PAID','IN_TRANSIT', '2018-11-13');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (9,4,9,'cash','pickup','PAID','IN_TRANSIT', '2018-11-16');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (10,4,10,'online','pickup','PAID','SHIPPED', '2018-11-23');
insert into order_(order_id, address_id, user_id, payment_method, delivery_method, payment_status, order_status, order_date) values (11,6,11,'cash','pickup','NOT_PAID','PENDING_APPROVAL', '2018-11-26');

insert into order_product (order_id,product_id)values (1,2);
insert into order_product (order_id,product_id)values (1,2);
insert into order_product (order_id,product_id)values (2,1);
insert into order_product (order_id,product_id)values (2,6);
insert into order_product (order_id,product_id)values (3,4);
insert into order_product (order_id,product_id)values (3,5);
insert into order_product (order_id,product_id)values (3,5);
insert into order_product (order_id,product_id)values (4,1);
insert into order_product (order_id,product_id)values (5,2);
insert into order_product (order_id,product_id)values (5,7);
insert into order_product (order_id,product_id)values (5,9);
insert into order_product (order_id,product_id)values (5,10)
insert into order_product (order_id,product_id)values (6,3);
insert into order_product (order_id,product_id)values (7,8);
insert into order_product (order_id,product_id)values (8,4);
insert into order_product (order_id,product_id)values (8,5);
insert into order_product (order_id,product_id)values (8,11);
insert into order_product (order_id,product_id)values (8,1);
insert into order_product (order_id,product_id)values (9,1);
insert into order_product (order_id,product_id)values (9,4);
insert into order_product (order_id,product_id)values (10,5);
insert into order_product (order_id,product_id)values (11,10);
insert into order_product (order_id,product_id)values (11,1);



