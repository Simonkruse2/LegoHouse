-- drop schema if exists legohouse;
create schema if not exists legohouse;
use legohouse;
 
 
create table if not exists `users` (
 
`email` varchar(45) not null,
`admin` tinyint(4) default 0,
`password` varchar(45) not null,
primary key(`email`)
)engine=INNODB;
 
create table if not exists `orders`(
`order_id` int(10) auto_increment,
`width` int(10) not null,
`length` int(10) not null,
`height` int(10) not null,
`bandType` varchar(45),
`email` varchar(45) not null,
`isShipped` tinyint(4) default 0,
primary key(`order_id`),
foreign key(`email`) references `users`(`email`)
)engine=INNODB;

insert into users
values('admin@admin.dk',1,'admin');
insert into users
values('simonkruse2@gmail.com',0,'1234');