create database d_auth default charset utf8;

use d_auth;

CREATE TABLE t_user(
  id bigint PRIMARY KEY auto_increment,
  name VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE t_role(
  id bigint PRIMARY KEY auto_increment,
  name VARCHAR(20) NOT NULL UNIQUE,
  description VARCHAR(500)
);

CREATE TABLE t_user_role(
  id bigint PRIMARY KEY auto_increment,
  user_id bigint NOT NULL,
  role_id bigint NOT NULL
);

CREATE TABLE t_module(
  id bigint PRIMARY KEY auto_increment,
  name VARCHAR(20) NOT NULL,
  description VARCHAR(500)
);

CREATE TABLE t_permission(
  id bigint PRIMARY KEY auto_increment,
  permission VARCHAR(200) NOT NULL UNIQUE,
  des_zh VARCHAR(100) NOT NULL,
  module_id bigint NOT null
);

CREATE TABLE t_role_permission(
  id bigint PRIMARY KEY auto_increment,
  role_id bigint NOT NULL,
  permission_id bigint NOT NULL
);