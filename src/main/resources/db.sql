CREATE TABLE `t_user` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(255) COMMENT '名称',
    `shop_id` INT COMMENT '店铺ID',
    `age` INT COMMENT '年龄',
    `email` VARCHAR(255) COMMENT '邮箱',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `t_good_category` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(255) COMMENT '分类名称',
    `shop_id` INT COMMENT '店铺ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';
CREATE TABLE `t_good` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(255) COMMENT '商品名称',
    `shop_id` INT COMMENT '店铺ID',
    `imgs` TEXT COMMENT '图片地址（多个用逗号分隔）',
    `category_id` BIGINT COMMENT '分类ID',
    `unit` VARCHAR(50) COMMENT '单位',
    `standards` VARCHAR(255) COMMENT '规格',
    `bar_code` VARCHAR(50) COMMENT '条形码',
    `in_price` FLOAT COMMENT '进价',
    `warning_num` INT COMMENT '库存预警数量',
    `store_id` BIGINT COMMENT '仓库ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';
CREATE TABLE `t_in_out_type` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',    `name` VARCHAR(255) COMMENT '类型名称',
    `shop_id` INT COMMENT '店铺ID',
    `type` INT COMMENT '出入库类型（例如：1-入库，2-出库）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出入库类型表';


ALTER TABLE t_good_category
ADD COLUMN order_id INT DEFAULT 0 COMMENT '排序ID';

DROP TABLE IF EXISTS `t_specs`;
CREATE TABLE `t_specs` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `unit_val_0` int DEFAULT NULL COMMENT '单位字段一',
  `unit_name_0` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单位字段一名字',
  `unit_val_1` int DEFAULT NULL COMMENT '单位字段二',
  `unit_name_1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单位字段二名字',
  `unit_val_2` int DEFAULT NULL COMMENT '单位字段三',
  `unit_name_2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单位字段三名字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品规格表';
