alter system set deferred_segment_creation=false;
create user wangwu identified by wangwu;
grant connect,resource to wangwu;

--drop table TUSER;
--drop table t_orderItem;
--drop table t_order;
--drop table t_liuyan;
--drop table t_lianjie;
--drop table t_goods;
--drop table t_gonggao;
--drop table t_catelog;
--drop table t_admin;

--drop sequence tuser_id;
--drop sequence orderItem_id;
--drop sequence order_id;
--drop sequence liuyan_id;
--drop sequence lianjie_id;
--drop sequence goods_id;
--drop sequence gonggao_id;
--drop sequence catelog_id;
--drop sequence admin_id;
----------------------------------------------
create table TUSER
(
   userid          NUMBER(*) not null,
   username        NVARCHAR2(255),
   userpw        NVARCHAR2(255),
   phonenumber     NVARCHAR2(255),
   usertype  NUMBER(*),
   userrealname NVARCHAR2(255),
   useraddress NVARCHAR2(255),
   usersex NVARCHAR2(255),
   usertel NVARCHAR2(255),
   useremail NVARCHAR2(255),
   userqq  NVARCHAR2(255),
   userman  NVARCHAR2(255),
   userage  NVARCHAR2(255),
   userbirthday  NVARCHAR2(255),
   userxueli  NVARCHAR2(255),
   userdel  NVARCHAR2(255),
   userjf nvarchar2(255) default '500' ,
   userQDtime nvarchar2(255) 
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table TUSER
  add primary key (USERID)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
  
  
  ---------------------------------------------
  create table t_orderItem
(
   orderItemId          NUMBER(*) not null,
   orderId       NUMBER(*) ,
   goodsId        NUMBER(*),
   goodsQuantity      NUMBER(*)
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_orderItem
  add primary key (orderItemId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence orderItem_id
  start with 1 
  increment by 1
  ---------------------------------------------
  
    create table t_order
(
   orderId          NUMBER(*) not null,
   orderBianhao        NVARCHAR2(255),
   orderDate        NVARCHAR2(255),
   orderZhuangtai      NVARCHAR2(255),
   odderSonghuodizhi NVARCHAR2(255),
   odderFukuangfangshi NVARCHAR2(255),
   orderJine  NUMBER(*) ,
   orderUserId  NUMBER(*) 
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_order
  add primary key (orderId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence order_id
  start with 1 
  increment by 1
  -------------------------------------------------
      create table t_liuyan
(
   liuyanId          NUMBER(*) not null,
   liuyanTitle        NVARCHAR2(255),
   liuyanContent       clob,
   liuyanDate      NVARCHAR2(255),
   liuyanUser NVARCHAR2(255)
   
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_liuyan
  add primary key (liuyanId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence liuyan_id
  start with 1 
  increment by 1
  
  ---------------------------------------
        create table t_lianjie
(
   lianjieId          NUMBER(*) not null,
   lianjieWeb        NVARCHAR2(255),
   lianjieName        NVARCHAR2(255)
   
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_lianjie
  add primary key (lianjieId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence lianjie_id
  start with 1 
  increment by 1
  
  -----------------------------------------
          create table t_goods
(
   goodsId          NUMBER(*) not null,
   goodsMiaoshu        clob,
   goodsName        NVARCHAR2(255),
   goodsPic        NVARCHAR2(255),
   goodsYanse NVARCHAR2(255),
   goodsShichangjia NVARCHAR2(255),
   goodsTejia NUMBER(*),
   goodsIsnottejia NVARCHAR2(255),
   goodsIsnottuijian NVARCHAR2(255),
   goodsCatelogId NUMBER(*),
   goodsKucun NUMBER(*),
   goodsDel NVARCHAR2(255)
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_goods
  add primary key (goodsId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence goods_id
  start with 1 
  increment by 1
  -----------------------------
  
            create table t_gonggao
(
   gonggaoId          NUMBER(*) not null,
   gonggaoTitle        NVARCHAR2(255),
   gonggaoContent        clob,
   gonggaoData clob,
   gonggaoFabuzhe NVARCHAR2(255),
   gonggaoDel  NVARCHAR2(255),
   gonggaoOne1 NVARCHAR2(255),
   gonggaoOne2 NVARCHAR2(255),
   gonggaoOne3  NVARCHAR2(255),
   gonggaoOne4  NVARCHAR2(255),
   gonggaoOne5 NVARCHAR2(255),
   gonggaoOne6 NVARCHAR2(255),
   gonggaoOne7 NVARCHAR2(255),
   gonggaoOne8 NVARCHAR2(255)
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_gonggao
  add primary key (gonggaoId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence gonggao_id
  start with 1 
  increment by 1
  -----------------------------------------------
    
            create table t_catelog
(
   catelogId          NUMBER(*) not null,
   catelogName        NVARCHAR2(255),
   catelogMiaoshu        NVARCHAR2(255),
   catelogDel NVARCHAR2(255)
  
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_catelog
  add primary key (catelogId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence catelog_id
  start with 1 
  increment by 1
  ----------------------------------------
      
            create table t_admin
(
   userId          NUMBER(*) not null,
   userName        NVARCHAR2(255),
   userPw        NVARCHAR2(255)
  
  
   )
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table t_admin
  add primary key (userId)
  using index 
  tablespace USERs
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  CREATE sequence admin_id
  start with 1 
  increment by 1
  
    CREATE sequence tuser_id
  start with 1 
  increment by 1
 
  
  insert into TUSER values (tuser_id.nextval, 'admin', 'admin', '15389071915',1,'高鑫', '陕西省西安市','男','15389071915','945912173@qq.com','945912173','sa','30','11月13','研究生','no','10000','20180906');
  insert into t_admin values (admin_id.nextval,'sa','sa');
  ------------------------------------------
  select * from TUSER
  
