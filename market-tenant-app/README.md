|      DATABASE       | CHARACTER SET |     COLLATION      |
|---------------------|---------------|--------------------|
| maxcar_tenant_app_l | utf8mb4       | utf8mb4_general_ci |

### `add_deal_info`(完善交易信息表)

|         字段名称          |  数据类型   | 是否为空 |      默认值      |            注释             |
|-------------------------|--------------|----------|-------------------|--------------------------------|
| id                      | varchar(32)  | NO       |                   | 主键id                         |
| car_manager             | varchar(50)  | YES      |      NULL         | 转入地车管所                   |
| deal_price              | double(12,2) | NO       |              0.00 | 成交价格 单位元                |
| burden_owner            | tinyint(4)   | NO       |                 3 | 转籍费/费负担 1.买房 2.卖方 3.其他|
| mileage                 | int(11)      | NO       |                 0 | 公里数                         |
| engine_no               | varchar(100) | YES      |      NULL         | 发动机号码                     |
| environmental_standards | varchar(20)  | YES      |      NULL         | 排放标准                       |
| isvalid                 | tinyint(2)   | YES      |                 1 | 数据有效性 0 无效 1 有效       |
| insert_time             | timestamp    | NO       | 当前时间           | 创建时间                       |
| update_time             | datetime     | YES      | 当前时间           | 修改时间                       |
| trading_type            | tinyint(4)   | NO       |                 1 | 交易类型 1 本地 2 外迁         |

### `add_trade_info` (交易终端：完善交易信息表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |               注释                |
|-----------------|--------------|----------|-------------------|--------------------------------------|
| transfer_car_id | varchar(32)  | NO       |                   | 主键id(transfer_id 主键id)           |
| car_manager     | varchar(50)  | NO       |                   | 转入地车管所                         |
| deal_price      | double(12,2) | NO       |                   | 成交价格 单位元                      |
| burden_owner    | tinyint(4)   | NO       |                   | 转籍费/费负担 1.买房 2.卖方 3.其他    |
| reception_time  | datetime     | NO       |                   | 车辆验收时间                         |
| pay_time        | int(11)      | NO       |                 0 | 买方支付车款日期，默认当日为0-当天   |
| procedures_time | int(11)      | NO       |                 0 | 卖方协助办理手续时间，默认值为0-当天 |
| isvalid         | tinyint(4)   | NO       |                 1 | 数据是否有效 0.无效 1.有效           |
| insert_time     | datetime     | NO       | 当前时间 | 新增时间                          |
| update_time     | datetime     | YES      | 当前时间 | 更新时间                           |

### `buy_sell_info` (买卖家信息)

|         字段名称          |  数据类型   | 是否为空 |      默认值      |            注释             |
|-------------------------|--------------|----------|-------------------|--------------------------------|
| id                      | varchar(32)  | NO       |                   | 主键id                          |
| seller_type             | int(2)       | YES      |        NULL       | 卖方类型 1卖方个人 2 卖方单位  |
| seller_name             | varchar(50)  | YES      |        NULL       | 卖方姓名                       |
| seller_idcard           | varchar(50)  | YES      |        NULL       | 卖方身份证号                   |
| seller_idcard_address   | varchar(100) | YES      |        NULL       | 卖方身份证地址                 |
| seller_phone            | varchar(32)  | YES      |        NULL       | 卖方联系方式                   |
| seller_idcard_front     | varchar(255) | YES      |        NULL       | 卖方身份证证正面照             |
| seller_idcard_reverse   | varchar(255) | YES      |        NULL       | 卖方身份证证反面照             |
| seller_agency           | varchar(50)  | YES      |        NULL       | 卖方机构名称                   |
| seller_credit_code      | varchar(50)  | YES      |        NULL       | 卖方统一社会信用代码           |
| seller_address          | varchar(100) | YES      |        NULL       | 卖方地址                       |
| seller_business_license | varchar(255) | YES      |        NULL       | 卖方营业执照                   |
| buyer_type              | tinyint(2)   | YES      |        NULL       | 买方类型  1买方个人 2 买方单位 |
| buyer_name              | varchar(50)  | YES      |        NULL       | 买方姓名                       |
| buyer_idcard            | varchar(50)  | YES      |        NULL       | 买方身份证号                   |
| buyer_idcard_address    | varchar(100) | YES      |        NULL       | 买方身份证地址                 |
| buyer_phone             | varchar(32)  | YES      |        NULL       | 买方联系方式                   |
| buyer_idcard_front      | varchar(255) | YES      |        NULL       | 买方身份证证正面照             |
| buyer_idcard_reverse    | varchar(255) | YES      |        NULL       | 买方身份证证反面照             |
| buyer_agency            | varchar(50)  | YES      |        NULL       | 买方机构名称                   |
| buyer_credit_code       | varchar(50)  | YES      |        NULL       | 买方统一社会信用代码           |
| buyer_business_license  | varchar(255) | YES      |        NULL       | 买方营业执照                   |
| insert_time             | timestamp    | NO       | 当前时间 | 创建时间                       |
| update_time             | datetime     | YES      |        NULL       | 修改时间                       |
| remarks                 | varchar(255) | YES      |        NULL       | 备注                           |
| isvalid                 | tinyint(2)   | YES      |                 1 | 数据有效性 0 无效 1 有效       |
| buyer_address           | varchar(100) | YES      |        NULL       | 买方地址                       |

### `charge_notify_detail` (回调消息存储表)

|     字段名称      |  数据类型  | 是否为空 |      默认值      | 注释  |
|-----------------|-------------|----------|-------------------|----------|
| id              | int(11)     | NO       |                   | 主键     |
| charge_order_id | varchar(32) | NO       |                   | 充值订单id|
| detail          | text        | NO       |                   | 通知文本 |
| gmt_create      | datetime    | YES      | 当前时间 | 创建时间 |
| gmt_modified    | datetime    | YES      | 当前时间 | 修改时间 |

### `charge_order_detail` (充值详情表)

|     字段名称      |  数据类型  | 是否为空 |      默认值      |            注释             |
|-----------------|-------------|----------|-------------------|--------------------------------|
| id              | varchar(32) | NO       |                   | 主键                           |
| staff_id        | varchar(32) | NO       |                   | 员工id                        |
| charge_type     | tinyint(4)  | NO       |                 1 | 充值类型 1.微信App支付 2.支付宝App支付|
| charge_money    | int(11)     | NO       |                 0 | 充值金额 单位分                |
| state           | tinyint(4)  | NO       |                 0 | 订单状态 0.创建 1.处理中 2.成功 3.失败|
| supplier_biz_id | varchar(32) | YES      |        NULL       | 微信 & 支付宝支付订单号        |
| prepay_id       | varchar(64) | NO       |                   | 微信预支付交易会话标识         |
| fee_type        | tinyint(4)  | NO       |                 1 | 费用类型：1.交易过户费 2.质保服务费|
| transfer_car_no | varchar(32) | NO       |                   | 车辆交易过户业务编号           |
| gmt_create      | datetime    | YES      | 当前时间 | 创建时间                       |
| gmt_modified    | datetime    | YES      | 当前时间 | 修改时间                       |

### `notice` (通知表)

|   字段名称    |  数据类型   | 是否为空 |      默认值      |            注释             |
|-------------|--------------|----------|-------------------|--------------------------------|
| id          | bigint(20)   | NO       |                   | 主键                           |
| title       | varchar(50)  | NO       |                   | 标题                           |
| content     | varchar(500) | NO       |                   | 内容                           |
| market_id   | varchar(32)  | NO       |                   | 市场id                         |
| staff_id    | varchar(32)  | NO       |                   | 员工id 0.表示该市场所有员工    |
| link_mark   | varchar(50)  | NO       |                   | 链接标记                       |
| type        | tinyint(4)   | NO       |                 1 | 通知类型 1.过户通知 2.入库通知 3.审批消息 4.注册申请通知 5.市场通知|
| state       | tinyint(4)   | NO       |                 1 | 状态 1.未读 2.已读 3.已删除    |
| insert_time | datetime     | YES      | 当前时间 | 创建时间                       |
| update_time | datetime     | YES      | 当前时间 | 修改时间                       |

### `staff_check` (商户员工审批)

|    字段名称    |  数据类型   | 是否为空 |      默认值      |             注释             |
|--------------|--------------|----------|-------------------|---------------------------------|
| id           | varchar(32)  | NO       |                   | 主键                            |
| staff_id     | varchar(32)  | NO       |                   | 员工id                          |
| role_id      | varchar(32)  | NO       |                   | 角色id                          |
| state        | tinyint(4)   | NO       |                 0 | 审批状态 -1.拒绝 0.待审批 1.同意 |
| tenant_id    | varchar(32)  | NO       |                   | 商户id                          |
| market_id    | varchar(32)  | NO       |                   | 市场id                          |
| remark       | varchar(200) | NO       | ''                | 拒绝理由                        |
| version      | int(11)      | NO       |                 1 | 数据版本，防止脏写              |
| gmt_create   | datetime     | YES      | 当前时间 | 创建时间                        |
| gmt_modified | datetime     | YES      | 当前时间 | 修改时间                        |
| is_admin     | tinyint(4)   | NO       |                 0 | 是否商户管理员(0:员工,1:管理员) |

### `staff_role` (商户员工和角色关系)

|    字段名称    |  数据类型  | 是否为空 |      默认值      |      注释       |
|--------------|-------------|----------|-------------------|--------------------|
| id           | varchar(32) | NO       |                   | 主键               |
| staff_id     | varchar(32) | NO       |                   | 员工id            |
| role_id      | varchar(32) | NO       |                   | 角色id             |
| version      | int(11)     | NO       |                 1 | 数据版本，防止脏写 |
| gmt_create   | datetime    | YES      | 当前时间 | 创建时间           |
| gmt_modified | datetime    | YES      | 当前时间 | 修改时间           |

### `tenant_res` (商户资源表，存放所有可操作请求地址)

|    字段名称    |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|--------------|--------------|----------|-------------------|------------------------------------------------|
| id           | varchar(32)  | NO       |                   | 主键                                           |
| res_name     | varchar(30)  | NO       |                   | 资源名称                                       |
| res_url      | varchar(50)  | NO       |                   | 资源地址                                       |
| res_desc     | varchar(200) | YES      |                   | 资源说明                                       |
| ind          | int(11)      | YES      |                 0 | 排序                                           |
| isvalid      | tinyint(4)   | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version      | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| gmt_create   | datetime     | YES      | 当前时间 | 创建时间                                       |
| gmt_modified | datetime     | YES      | 当前时间 | 修改时间                                       |

### `tenant_role` (商户角色)

|    字段名称    |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|--------------|--------------|----------|-------------------|------------------------------------------------|
| id           | varchar(32)  | NO       |                   | 主键                                           |
| role_name    | varchar(50)  | NO       |                   | 角色名称                                       |
| role_desc    | varchar(200) | YES      |                   | 角色说明                                       |
| tenant_id    | varchar(32)  | YES      |                   | 商户id                                         |
| isvalid      | tinyint(4)   | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version      | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| gmt_create   | datetime     | YES      | 当前时间 | 创建时间                                       |
| gmt_modified | datetime     | YES      | 当前时间 | 修改时间                                       |

### `tenant_role_res` (存放角色和权限关系信息)

|    字段名称    |  数据类型  | 是否为空 |      默认值      |      注释       |
|--------------|-------------|----------|-------------------|--------------------|
| id           | varchar(32) | NO       |                   | 主键               |
| role_id      | varchar(32) | NO       |                   | 角色id             |
| res_id       | varchar(32) | NO       |                   | 资源id             |
| version      | int(11)     | NO       |                 1 | 数据版本，防止脏写 |
| gmt_create   | datetime    | YES      | 当前时间 | 创建时间           |
| gmt_modified | datetime    | YES      | 当前时间 | 修改时间           |

### `transfer_car` (车辆交易过户基本信息表)

|        字段名称        |  数据类型   | 是否为空 |      默认值      |            注释             |
|----------------------|--------------|----------|-------------------|--------------------------------|
| id                   | varchar(32)  | NO       |                   | 主键id                         |
| transfer_car_no      | varchar(50)  | NO       |                   | 业务编号                       |
| market_id            | varchar(32)  | YES      |NULL               | 市场id                         |
| car_id               | varchar(32)  | YES      |NULL               | 车id                           |
| bye_sell_info        | varchar(32)  | YES      |NULL               | 买卖方主键                     |
| add_deal_info        | varchar(32)  | YES      |NULL               | 完善交易信息主键               |
| tenant_id            | varchar(32)  | YES      |NULL               | 商户id                         |
| status               | tinyint(3)   | NO       |                 1 | 记录标识 1.填写车辆信息 2.填写卖家信息 |
|                      |              |          |                   | 3.填写买家信息 4.完善交易信息 5.已支付过户费|
| type                 | tinyint(4)   | NO       |                 1 | 过户状态 1.进行中 2.待处理 3.已完成|
| vin                  | varchar(50)  | YES      |NULL               | vin                            |
| brand_model          | varchar(50)  | YES      |NULL               | 厂牌型号                       |
| car_type             | tinyint(5)   | YES      |NULL               | 车辆交易类型 1 库存车 2 挂靠车 |
| configuration_id     | varchar(32)  | YES      |NULL               | 车辆类型: 配置id               |
| register_code        | varchar(50)  | YES      |NULL               | 登记证号                       |
| plate_num            | varchar(50)  | YES      |NULL               | 车牌照号                       |
| check_out_photo      | varchar(255) | YES      |NULL               | 外检单照片                     |
| start_time           | datetime     | YES      |NULL               | 发起时间                       |
| remarks              | varchar(255) | YES      |NULL               | 备注                           |
| isvalid              | tinyint(2)   | YES      |NULL             1 | 数据有效性 0 无效 1 有效       |
| insert_time          | timestamp    | NO       |当前时间 | 创建时间                       |
| update_time          | datetime     | YES      |NULL               | 修改时间                       |
| quality_service      | tinyint(4)   | NO       |              0 | 是否购买质保服务 0.否 1.是     |
| contract_url         | varchar(200) | YES      |NULL               | 合同图片地址                   |
| buyer_sign           | varchar(200) | YES      |NULL               | 买家签名                       |
| seller_sign          | varchar(200) | YES      |NULL               | 卖家签名                       |
| register_photo       | varchar(255) | NO       |                   | 登记证照片                     |
| driver_license_photo | varchar(255) | NO       |                   | 驾驶证照片                     |
| source               | tinyint(4)   | NO       |                 1 | 来源 1.商户App 2.交易终端      |
