
CREATE DATABASE IF NOT EXISTS witi;
use witi;

CREATE TABLE strategy(
	id int primary key auto_increment,
    stock_name varchar(50) not null,
    volume int not null,
    input_time varchar(50) not null,
    strategy_type varchar(50) not null,
    status varchar(50) not null,
    crossed varchar(50) not null
);


CREATE TABLE transaction (
	id int primary key auto_increment,
	strategy_id	int,
	stock_name varchar(50) ,
	stock_position varchar(50) not null,
	transaction_price double not null,
	profit double not null,
	is_complete varchar(1) not null,
	transaction_time varchar(50) not null,
	FOREIGN KEY (strategy_id) references strategy(id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE TABLE two_moving_averages (
	id int primary key,
	long_time int not null,
	short_time int not null,
	time_purchased varchar(50) not null,
    FOREIGN KEY (id) references strategy(id)
	ON DELETE NO ACTION
		ON UPDATE CASCADE
);


/****TEST DATA******/
Insert Into strategy
VALUES(1, 'AAPL', 100, '00:00:01', 'tma' , 'running', 'y');

Insert Into strategy
VALUES(2, 'MSFT', 200, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(3, 'C', 450, '00:00:02', 'tma', 'running', 'y');

Insert Into strategy
VALUES(4, 'AKS', 600, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(5, 'AVP', 300, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(6, 'CHK', 150, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(7, 'CLF', 80, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(8, 'CNX', 125, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(9, 'MRO', 200, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(10, 'NAV', 185, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(11, 'SUNE', 75, '00:00:01', 'tma', 'running', 'y');

Insert Into strategy
VALUES(12, 'X', 325, '00:00:01', 'tma', 'running', 'y');

Insert Into two_moving_averages
VALUES(1, 10, 5, '00:00:01');

Insert Into two_moving_averages
VALUES(2, 20, 8, '00:00:01');

Insert Into two_moving_averages
VALUES(3, 15, 7, '00:00:02');

Insert Into two_moving_averages
VALUES(4, 8, 4, '00:00:01');

Insert Into two_moving_averages
VALUES(5, 15, 10, '00:00:01');

Insert Into two_moving_averages
VALUES(6, 35, 5, '00:00:01');

Insert Into two_moving_averages
VALUES(7, 6, 3, '00:00:01');

Insert Into two_moving_averages
VALUES(8, 12, 6, '00:00:01');

Insert Into two_moving_averages
VALUES(9, 14, 7, '00:00:01');

Insert Into two_moving_averages
VALUES(10, 20, 15, '00:00:01');

Insert Into two_moving_averages
VALUES(11, 12, 10, '00:00:01');

Insert Into two_moving_averages
VALUES(12, 8, 6, '00:00:01');

Insert Into transaction
VALUES(0, 4, 'AKS', 'BUY', 2.1, -1260, 'Y', '07:43:12');

Insert Into transaction
VALUES(0, 5, 'AVP', 'BUY', 3.22, -966, 'Y', '07:43:12');

Insert Into transaction
VALUES(0, 10, 'NAV', 'BUY', 7.07, -1307.95, 'Y', '07:43:12');

Insert Into transaction
VALUES(0, 12, 'X', 'BUY', 7.32, -2379, 'Y', '07:43:12');

Insert Into transaction
VALUES(0, 7, 'CLF', 'BUY', 1.83, -146.4, 'Y', '07:43:14');

Insert Into transaction
VALUES(0, 12, 'X', 'SELL', 7.43, 36, 'Y', '07:43:17');

Insert Into transaction
VALUES(0, 5, 'AVP', 'SELL', 3.26, 12, 'Y', '07:43:20');

Insert Into transaction
VALUES(0, 11, 'SUNE', 'BUY', 2.48, -186, 'Y', '07:43:21');

Insert Into transaction
VALUES(0, 8, 'CNX', 'BUY', 8.33, -1041.25, 'Y', '07:43:27');

Insert Into transaction
VALUES(0, 6, 'CHK', 'BUY', 1.98, -297, 'Y', '07:43:36');

Insert Into transaction
VALUES(0, 11, 'SUNE', 'SELL', 2.52, -3, 'Y', '07:43:37');

Insert Into transaction
VALUES(0, 11, 'SUNE', 'BUY', 2.53, -187, 'Y', '07:43:52');

Insert Into transaction
VALUES(0, 12, 'X', 'BUY', 7.16, -2291, 'Y', '07:43:58');

Insert Into transaction
VALUES(0, 12, 'X', 'SELL', 7.24, 62, 'Y', '07:44:01');

Insert Into transaction
VALUES(0, 4, 'AKS', 'SELL', 2.13, 18, 'Y', '07:44:07');

Insert Into transaction
VALUES(0, 11, 'SUNE', 'SELL', 2.56, 5, 'Y', '07:44:18');

Insert Into transaction
VALUES(0, 7, 'CLF', 'SELL', 1.85, 2, 'Y', '07:44:25');

Insert Into transaction
VALUES(0, 4, 'AKS', 'BUY', 2.2, -1302, 'Y', '07:44:41');

Insert Into transaction
VALUES(0, 10, 'NAV', 'SELL', 7.15, 15, 'Y', '07:44:48');

Insert Into transaction
VALUES(0, 4, 'AKS', 'SELL', 2.24, 42, 'Y', '07:45:29');

Insert Into transaction
VALUES(0, 8, 'CNX', 'SELL', 7.48, -106, 'Y', '07:45:59');

Insert Into transaction
VALUES(0, 6, 'CHK', 'SELL', 2, 3, 'Y', '07:46:43');

Insert Into transaction
VALUES(0, 4, 'AKS', 'BUY', 2.26, -1278, 'Y', '07:47:03');

Insert Into transaction
VALUES(0, 4, 'AKS', 'SELL', 2.29, 96, 'Y', '07:47:07');

Insert Into transaction
VALUES(0, 10, 'NAV', 'BUY', 7.24, -1251, 'Y', '07:48:07');

Insert Into transaction
VALUES(0, 12, 'X', 'BUY', 7.27, -2271, 'Y', '07:48:13');

Insert Into transaction
VALUES(0, 10, 'NAV', 'SELL', 7.33, 105, 'Y', '07:48:15');

Insert Into transaction
VALUES(0, 12, 'X', 'SELL', 7.35, 118, 'Y', '07:48:28');

Insert Into transaction
VALUES(0, 12, 'X', 'BUY', 7.38, -2271, 'Y', '07:48:36');

Insert Into transaction
VALUES(0, 12, 'X', 'SELL', 7.47, 118, 'Y', '07:48:48');

