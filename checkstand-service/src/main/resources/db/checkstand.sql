-- create table provider(
--   id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
--   name varchar(100) COMMENT '支付服务提供商名称',
--
--
--   contact varchar(30) COMMENT '联系人',
--   telephone varchar(50) COMMENT '联系电话',
--   address varchar(255) COMMENT '联系地址',
--   email varchar(255) COMMENT '联系人邮箱',
--   note varchar(255) COMMENT '备注',
--
--   PRIMARY KEY (id)
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table company(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  name varchar(100) COMMENT '企业名称',
  platform_company_id  int(10) NOT NULL COMMENT'外贸圈平台企业ID',
  company_code varchar(64) NOT NULL COMMENT '公司编码',
  register_name  varchar(64) COMMENT '注册人名称',
  register_identity_code varchar(64) COMMENT '注册人身份证号码',
  register_tel varchar(20) COMMENT '注册人联系方式',

  status  varchar(20) COMMENT '状态码',

  note varchar(255) COMMENT '备注',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 企业商户
create table merchant(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  company_id int(10) NOT NULL COMMENT '所属企业id',
  name varchar(100) NOT NULL COMMENT '商户名称',
  invoice_title varchar(64) NOT NULL COMMENT '发票抬头',
  cw_merchant_code varchar(64) NOT NULL COMMENT '云翼商户代码',
  note varchar(255) COMMENT '备注',

  create_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  create_user_id int(10) COMMENT '数据创建用户ID',
  update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近操作时间',
  update_user_id int(10) COMMENT '操作用户ID',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 收银员（用户）
create table user(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  company_id int(10) NOT NULL COMMENT '所属企业ID',
  platform_user_id int(10) NOT NULL COMMENT '所属外贸圈平台用户id',
  platform_company_id int(10) NOT NULL COMMENT '所属外贸圈平台企业id',
  role_id  int(10) COMMENT '角色id',
  name varchar(32) COMMENT '用户名',
  account varchar(255) NOT NULL COMMENT '账户名称',
  password varchar(64) COMMENT '密码',
  phone varchar(32) COMMENT '联系方式',
  email varchar(64) COMMENT '邮箱',
  avatar varchar(255) COMMENT '头像信息',
  avatar_type varchar(16) COMMENT '头像类型',
  status varchar(20) COMMENT '状态码',

  note varchar(255) COMMENT '备注',
  create_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近操作时间',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 办公现场
create table office(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  company_id int(10) NOT NULL COMMENT '所属企业ID',
  name varchar(128) NOT NULL COMMENT '办公现场名称',
  code varchar(64) NOT NULL COMMENT '办公现场代码',
  address varchar(255) COMMENT '办公现场地址',
  telephone varchar(32) COMMENT '办公现场联系电话',
  note varchar(255) COMMENT '备注',

  create_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  create_user_id int(10) COMMENT '数据创建用户ID',
  update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近操作时间',
  update_user_id int(10) COMMENT '操作用户ID',


  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 企业办公现场-商户映射表
create table office_merchant(
  oid int(10) NOT NULL COMMENT '办公现场id',
  mid int(10) NOT NULL COMMENT '商户id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 企业用户-办公现场映射表
create table user_office(
  uid int(10) NOT NULL COMMENT '用户ID',
  oid int(10) NOT NULL COMMENT '办公现场ID'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户角色
create table role(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  name varchar(64) NOT NULL COMMENT '角色名称',
  code varchar(32) NOT NULL COMMENT '角色代码',
  description varchar(255) COMMENT '角色描述',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户-角色映射表
create table user_role(
  uid int(10) NOT NULL COMMENT '用户id',
  rid int(10) NOT NULL COMMENT '角色id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 权限表
create table permission(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  name varchar(32) NOT NULL COMMENT '权限名称',
  p_id int(10) COMMENT '父级权限id',
  is_menu tinyint(1) COMMENT '菜单or功能',
  is_public tinyint(1) COMMENT '是否是公共权限（资源）',
  sort_code tinyint(3) COMMENT '排序',
  url varchar(255) COMMENT 'url链接地址',
  enable_mark tinyint(1) COMMENT '是否可用（0-禁用，1-可用）',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table role_permission(
  rid int(10) NOT NULL COMMENT '角色id',
  pid int(10) NOT NULL COMMENT '权限id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 支付订单表
create table payment_order(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  order_no varchar(64) NOT NULL COMMENT '企业支付订单编号',
  company_id int(10) NOT NULL COMMENT '所属企业id',
  merchant_id int(10) NOT NULL COMMENT '所属商户id',
  office_id  int(10) NOT NULL COMMENT '所属现场id',
  op_type  varchar(10) NOT NULL COMMENT '收款类型',

  sum    int(10) NOT NULL COMMENT '收款金额',
  subject   varchar(128) NOT NULL COMMENT '商品主体',
  body    varchar(128)  COMMENT '商品描述',
  note    varchar(255) COMMENT '备注',
  pay_type  varchar(16) NOT NULL COMMENT '支付方式（微信-wx；支付宝-ali）',
  status   int(10) NOT NULL COMMENT '支付订单状态',
  result   varchar(255) COMMENT '支付结果描述',

  trade_no varchar(64) COMMENT '支付交易流水号',


  create_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  create_user_id int(10) COMMENT '数据创建用户ID',
  update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近操作时间',
  update_user_id int(10) COMMENT '操作用户ID',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table order_goods(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  order_id int(10) NOT NULL COMMENT '所属订单id',
  goods_name varchar(255) NOT NULL COMMENT '商品名称',
  price decimal(19,5) NOT NULL COMMENT '单价',
  qty  decimal(19,5) NOT NULL COMMENT '数量',
  goods_describe varchar(512) COMMENT '商品描述',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table payment_order_record(
  id int(10) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  order_id int(10) NOT NULL COMMENT '所属订单id',
  operation varchar(255) COMMENT '操作',
  op_method varchar(255) COMMENT '操作方法',
  params    text        COMMENT '操作参数',
  note    varchar(255) COMMENT '备注',

  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

