create table demo
(
    id   int auto_increment
        primary key,
    name varchar(36) null,
    sex  varchar(36) null
) engine=innodb default charset=utf8;


create table base_user
(
    id int auto_increment primary key comment '主键自增id',
    user_id varchar (36) comment '用户唯一id',
    user_account varchar (36) comment '账号',
    user_password varchar (36) comment '密码',
    user_name varchar (36) comment '名称'
) engine=innodb default charset=utf8 comment='用户表';