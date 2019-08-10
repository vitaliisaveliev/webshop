drop database webshop;
create DATABASE webshop;
USE webshop;

create TABLE users(
id int primary key auto_increment,
`name` varchar(20),
`surname` varchar(20),
email varchar(30) unique,
`password` varchar(30),
country varchar (20)
);

create table payment_cards(
id int primary key
);

create table cards_on_user(
user_id int references users(id) on delete cascade on update cascade,
card_id int references payment_cards(id) on delete cascade on update cascade
);


insert into users(id, `name`, `surname`, email, `password`, country) 
values(default,"Vitalii", "Saveliev", "vitalii.saveliev.4@gmail.com", "poseydon74", "Ukraine");

insert into users(id, `name`, `surname`, email, `password`, country) 
values(default,"Vladislav", "Tsebenko", "tsebenko@gmail.com", "asd12345", "Russia");

insert into users(id, `name`, `surname`, email, `password`, country) 
values(default,"Aleksey", "Rekuta", "rekuta@gmail.com", "asd12345", "Ukraine");

create table categories(
id int primary key auto_increment,
`name` varchar(20)
);

create table manufacture(
id int primary key auto_increment,
country varchar(20)
);

create table products(
id int primary key auto_increment,
`name` varchar(20) not null unique,
price double,
image varchar(40),
cat_id int references categories(id) on delete cascade,
man_id int references manufacture(id) on delete cascade
);

insert into manufacture(id, country) values(default, "Belgium"); 
insert into manufacture(id, country) values(default, "USA"); 
insert into manufacture(id, country) values(default, "Russia");
insert into manufacture(id, country) values(default, "Germany");
insert into manufacture(id, country) values(default, "Israel");

insert into categories(id, `name`) values(default, "Assault rifle");
insert into categories(id, `name`) values(default, "Marksman rifle");
insert into categories(id, `name`) values(default, "Pistol");
insert into categories(id, `name`) values(default, "Machine gun");

insert into products(id, `name`, price, image, cat_id, man_id) values(default,"FN F2000", 400, "FN F2000.jpeg", 1, 1);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"M4A1", 300, "M4A1.png", 1, 2);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"SKS", 550, "SKS.jpg", 2, 3);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"AK-47", 240, "AK-47.jpg", 1, 3);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"FN FAL", 250, "FN FAL.jpg", 1, 1);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"FN Five-seveN", 250, "FN Five-seveN.jpg", 3, 1);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"FN P90", 350, "FN P90.jpg", 4, 1);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"FN SCAR", 500, "FN SCAR.jpg", 1, 1);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"HK G36", 500, "HK G36.jpg", 1, 4);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"HK MP5", 250, "HK MP5.jpg", 4, 4);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"HK MP7", 350, "HK MP7.jpg", 4, 4);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"HK USP", 250, "HK USP.jpg", 3, 4);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"M39 EMR", 650, "M39 EMR.png", 2, 2);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"Mk14 EBR", 700, "Mk 14 EBR.jpg", 2, 2);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"Mk47", 550, "Mk47.jpg", 1, 2);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"SIG Sauer P220", 230, "SIG Sauer P220.jpg", 3, 4);
insert into products(id, `name`, price, image, cat_id, man_id) values(default,"Walther P99", 240, "Walther P99.png", 3, 4);


create table orders(
id int primary key auto_increment,
`status` varchar(40) not null,
statement varchar(40) not null,
`date` datetime null default null,
user_id int references users(id) on delete cascade,
payment_type varchar(40)not null,
delivery_type varchar(40) not null
);

create table products_in_order(
order_id int references orders(id) on delete cascade on update cascade,
product_id int references products(id) on delete cascade on update cascade,
`count` int not null
);


