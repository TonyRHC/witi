
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

