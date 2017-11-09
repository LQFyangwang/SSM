CREATE database ssm DEFAULT charset utf8;

create TABLE t_product(
  id int PRIMARY key auto_increment,
  title varchar(50),
  des varchar(200),
  price DECIMAL(10, 2)
);

create table t_customer(
  id int PRIMARY  KEY  auto_increment,
  name VARCHAR(50)
);

create table t_order(
  id int PRIMARY KEY auto_increment,
  order_time datetime,
  customer_id int
);

create table t_oder_detail(
  id int PRIMARY KEY auto_increment,
  product_id int,
  order_id int
);