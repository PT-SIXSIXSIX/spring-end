/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/6 19:19:20                            */
/*==============================================================*/


alter table bankcard_relations
   drop primary key;

drop table if exists bankcard_relations;

alter table bankcards
   drop primary key;

drop table if exists bankcards;

alter table deposit_recharge_records
   drop primary key;

drop table if exists deposit_recharge_records;

alter table drivers
   drop primary key;

drop table if exists drivers;

alter table employee_relations
   drop primary key;

drop table if exists employee_relations;

alter table kbs
   drop primary key;

drop table if exists kbs;

alter table managers
   drop primary key;

drop table if exists managers;

alter table orders
   drop primary key;

drop table if exists orders;

alter table projects
   drop primary key;

drop table if exists projects;

alter table receive_money_records
   drop primary key;

drop table if exists receive_money_records;

alter table settle_account_records
   drop primary key;

drop table if exists settle_account_records;

alter table users
   drop primary key;

drop table if exists users;

/*==============================================================*/
/* Table: bankcard_relations                                    */
/*==============================================================*/
create table bankcard_relations
(
   id                   int not null,
   bankcard_id          int,
   boss_id              int
);

alter table bankcard_relations
   add primary key (id);

/*==============================================================*/
/* Table: bankcards                                             */
/*==============================================================*/
create table bankcards
(
   id                   int not null,
   card_id              varchar(30),
   type                 varchar(20),
   owner_name           varchar(20),
   owner_id_card        varchar(30),
   bank_name            varchar(30)
);

alter table bankcards
   add primary key (id);

/*==============================================================*/
/* Table: deposit_recharge_records                              */
/*==============================================================*/
create table deposit_recharge_records
(
   id                   int not null,
   recharge_money       int,
   recharge_time        datetime,
   boss_id              int,
   created_at           datetime
);

alter table deposit_recharge_records
   add primary key (id);

/*==============================================================*/
/* Table: drivers                                               */
/*==============================================================*/
create table drivers
(
   id                   int not null,
   rest_money           int,
   driver_name          varchar(20),
   driver_phone         varchar(20),
   created_at           datetime
);

alter table drivers
   add primary key (id);

/*==============================================================*/
/* Table: employee_relations                                    */
/*==============================================================*/
create table employee_relations
(
   id                   int not null,
   employee_id          int,
   boss_id              int
);

alter table employee_relations
   add primary key (id);

/*==============================================================*/
/* Table: kbs                                                   */
/*==============================================================*/
create table kbs
(
   id                   int not null,
   name                 varchar(20),
   type                 varchar(40),
   describ              varchar(150),
   created_at           datetime
);

alter table kbs
   add primary key (id);

/*==============================================================*/
/* Table: managers                                              */
/*==============================================================*/
create table managers
(
   id                   int not null,
   company_name         varchar(64),
   id_card              varchar(30),
   pic_head_url         varchar(100),
   pic_tail_url         varchar(100),
   service_type         varchar(50),
   location             varchar(120),
   user_id              int,
   deposit              int,
   reserve_phone        varchar(30)
);

alter table managers
   add primary key (id);

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   id                   int not null,
   type                 varchar(20),
   state                int,
   order_id             varchar(30),
   project_id           int,
   created_at           datetime
);

alter table orders
   add primary key (id);

/*==============================================================*/
/* Table: projects                                              */
/*==============================================================*/
create table projects
(
   id                   int not null,
   price                int,
   type                 varchar(50),
   describ              varchar(150)
);

alter table projects
   add primary key (id);

/*==============================================================*/
/* Table: receive_money_records                                 */
/*==============================================================*/
create table receive_money_records
(
   id                   int not null,
   trade_money          int,
   trade_time           datetime,
   driver_id            int,
   boss_id              int,
   created_at           datetime
);

alter table receive_money_records
   add primary key (id);

/*==============================================================*/
/* Table: settle_account_records                                */
/*==============================================================*/
create table settle_account_records
(
   id                   int not null,
   set_acc_id           int,
   state                int,
   rev_mon_id           int,
   created_at           datetime
);

alter table settle_account_records
   add primary key (id);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   id                   int not null,
   name                 varchar(20),
   password             varchar(64),
   role                 int,
   phone                varchar(20),
   created_at           datetime
);

alter table users
   add primary key (id);

alter table bankcard_relations add constraint FK_Reference_10 foreign key (bankcard_id)
      references bankcards (id) on delete restrict on update restrict;

alter table bankcard_relations add constraint FK_Reference_9 foreign key (boss_id)
      references managers (id) on delete restrict on update restrict;

alter table deposit_recharge_records add constraint FK_Reference_8 foreign key (boss_id)
      references managers (id) on delete restrict on update restrict;

alter table employee_relations add constraint FK_Reference_2 foreign key (employee_id)
      references users (id) on delete restrict on update restrict;

alter table employee_relations add constraint FK_Reference_3 foreign key (boss_id)
      references managers (id) on delete restrict on update restrict;

alter table managers add constraint FK_Reference_1 foreign key (user_id)
      references users (id) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_4 foreign key (project_id)
      references projects (id) on delete restrict on update restrict;

alter table receive_money_records add constraint FK_Reference_5 foreign key (driver_id)
      references drivers (id) on delete restrict on update restrict;

alter table receive_money_records add constraint FK_Reference_6 foreign key (boss_id)
      references managers (id) on delete restrict on update restrict;

alter table settle_account_records add constraint FK_Reference_7 foreign key (rev_mon_id)
      references receive_money_records (id) on delete restrict on update restrict;

