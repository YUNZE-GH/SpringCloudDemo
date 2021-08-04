create table demo
(
    id   int auto_increment
        primary key,
    name varchar(36) null,
    sex  varchar(36) null
) engine = innodb
  default charset = utf8;


create table base_user
(
    id            int auto_increment primary key comment '主键自增id',
    user_id       varchar(36) comment '用户唯一id',
    user_account  varchar(36) comment '账号',
    user_password varchar(36) comment '密码',
    user_name     varchar(36) comment '名称'
) engine = innodb
  default charset = utf8 comment ='基础-用户表';

create table base_notice
(
    id                  int auto_increment primary key comment '主键自增id',
    notice_id           varchar(36) comment '通知编号',
    notice_from_user_id varchar(36) comment '通知发送人id',
    notice_to_user_id   varchar(36) comment '通知接收人id',
    notice_content      varchar(36) comment '通知文本内容',
    notice_state        int(1) comment '通知状态：0为等待发送，1为已发送',
    notice_type         int(1) comment '通知类型：0为普通信息，1为系统信息，2为异常信息'
) engine = innodb
  default charset = utf8 comment ='基础-通知表';

create table sys_task_job_plan
(
    id                           int auto_increment primary key comment '主键自增id',
    task_id                      varchar(36) comment '任务id',
    task_name                    varchar(36) comment '任务名称',
    task_plan_execute_class_path varchar(200) comment '任务执行类路径',
    task_sequential_execution    tinyint          default 0 comment '上个任务未执行完再次被触发时，放弃并发执行：0-否；1-是',
    task_custom_parameters       varchar(300) comment '自定义参数(json数据)',
    task_plan_type               tinyint          default 0 comment '触发规则：0-执行一次；1-无限次；2-Cron表达式',
    task_plan_cron               varchar(20) comment 'cron通配符',
    task_plan_fixed_rate         long comment 'fixedRate间隔时长（单位：毫秒）',
    remark                       varchar(300) comment '备注',
    create_time                  datetime         default now() comment '创建时间',
    create_user_id               varchar(36) comment '创建人主键ID',
    update_time                  datetime comment '更新时间',
    update_user_id               varchar(36) comment '更新人主键ID',
    status                       tinyint          default 0 comment '状态：0-停止；1-启动',
    invalid                      tinyint not null default 0 comment '是否有效：0-有效；1-无效'
) engine = innodb
  default charset = utf8 comment ='系统-任务作业执行计划表';


CREATE TABLE sys_task_job_history
(
    id              int auto_increment primary key comment '主键自增id',
    task_id         varchar(36) comment '任务主键ID(sys_task_job_plan[task_id])',
    task_start_time datetime comment '任务开始时间',
    task_end_time   datetime comment '任务结束时间',
    create_time     datetime default now() comment '创建时间',
    status          tinyint  default 0 comment '执行状态：0-成功；1-失败',
    log             varchar(1000) comment '日志记录'
) engine = innodb
  default charset = utf8 comment ='系统-任务作业执行历史表';

alter table sys_task_job_history
    add column execute_ip varchar(36) comment '任务执行地址';