CREATE database d_shiro DEFAULT charset utf8;

/**
  盐值，主要密码加密时使用

  1    test1     123456
  2    test2     123456

  md5
  test1
  test2
  使用md5加密后的密文是一样的

  为了保证安全性，应该加密后的密文也不一样

  test1: md5("123456" + "test1用户对应的一个唯一字符串")
  test2: md5("123456" + "test2用户对应的一个唯一字符串")

  此时加密的密文不一样了， 唯一字符串就是盐值

 */
create table users(
  username VARCHAR(50),
  password VARCHAR(50),
  password_salt VARCHAR(50)
);

create table user_roles(
  username VARCHAR(50),
  role_name VARCHAR(50)
);

create table roles_permissions(
  permission VARCHAR(100),
  role_name VARCHAR(50)
);

/**自定义自己的数据表*/
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
