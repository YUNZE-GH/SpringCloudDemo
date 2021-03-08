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
) engine=innodb default charset=utf8 comment='基础-用户表';

create table base_notice
(
    id int auto_increment primary key comment '主键自增id',
    notice_id varchar (36) comment '通知编号',
    notice_from_user_id varchar (36) comment '通知发送人id',
    notice_to_user_id varchar (36) comment '通知接收人id',
    notice_content varchar (36) comment '通知文本内容',
    notice_state int (1) comment '通知状态：0为等待发送，1为已发送',
    notice_type int (1) comment '通知类型：0为普通信息，1为系统信息，2为异常信息'
) engine=innodb default charset=utf8 comment='基础-通知表';