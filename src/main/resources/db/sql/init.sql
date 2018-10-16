insert into role(name) values ('client');
insert into role(name) values ('salesperson');

insert into user(name, surname, login, password, birth_date, email) values ('Bill','Murrey','bill','$2a$04$dBOzyg8ikiHaKWJJONbgquqb9.gK4w5mOhAEAJcovHFTGRTxqBhTO' ,'2000-02-12','bill@yandex.ru');
insert into user(name, surname, login, password, birth_date, email) values ('Bob','Marley','bob','$2a$04$Tv6i5jzZ7OzQ50gMm9FhC.CmoOZh1OtSZlbktGSz31FEjV2oDu8Im' ,'1990-04-15','bob@yandex.ru');
insert into user(name, surname, login, password, birth_date, email) values ('Mary','Fisher','marry','$2a$04$LGSvlBTOvbQsdEszhhZItu4NToZQT3AzTH82d6vwEaPrXeyLmGaZW' ,'1988-10-05','mary@yandex.ru');
insert into user(name, surname, login, password, birth_date, email) values ('Susan','Mayer','susy','$2a$04$Hz5QllwBVlxte3OeqHfVl.anGjMtMt2PBVFCf8tS2M6xTFVB4Gqji' ,'2001-04-18','suzy@yandex.ru');

insert into product(name, category, in_stock, price, volume, weight) values ('laptop','DEVICE',20,1000.00 ,NULL ,500);
insert into product(name, category, in_stock, price, volume, weight) values ('iphone','DEVICE',10,400.00 ,NULL ,100);

insert into user_role(user_id,role_id)values (1,1);
insert into user_role(user_id,role_id)values (2,1);
insert into user_role(user_id,role_id)values (3,1);
insert into user_role(user_id,role_id)values (4,2);
insert into user_role(user_id,role_id)values (3,2);




