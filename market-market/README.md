|    DATABASE     | CHARACTER SET |     COLLATION     |
|-----------------|---------------|-------------------|
| maxcar_market_l | utf8          | utf8_general_ci   |

### `area` (区域管理)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |            注释             |
|-----------------|--------------|----------|-------------------|--------------------------------|
| id              | varchar(32)  | NO       |                   | 主键                           |
| market_id       | varchar(32)  | YES      |      NULL         | 市场id                        |
| name            | varchar(50)  | YES      |      NULL         | 区域名称                       |
| type            | int(2)       | YES      |      NULL         | 物业类型(0:展厅 1:车位 2:办公室)|
| area            | double(25,3) | YES      |      NULL         | 区域面积                       |
| remarks         | varchar(255) | YES      |      NULL         | 备注                           |
| isvalid         | int(2)       | YES      |                 1 | 数据有效性 0 无效 1 有效       |
| insert_time     | timestamp    | NO       | 当前时间 | 创建时间                       |
| update_time     | datetime     | YES      |      NULL         | 修改时间                       |
| insert_operator | varchar(32)  | YES      |      NULL         | 添加人                         |
| update_operator | varchar(32)  | YES      |      NULL         | 修改人                         |

### `area_shop` (电子标签记录表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |            注释            |
|-----------------|--------------|----------|-------------------|-------------------------------|
| id              | varchar(32)  | NO       |                   | 主键                          |
| market_id       | varchar(32)  | YES      |        NULL       | 市场ID                        |
| area_id         | varchar(32)  | NO       |                   | 区域id(area表主键)            |
| area_no         | varchar(50)  | YES      |                   | 编号                          |
| park_space      | double(25,3) | YES      |        NULL       | 车位                          |
| rent_status     | int(2)       | YES      |                 0 | 租赁情况（0 ：未租  1：已租） |
| remarks         | varchar(255) | YES      |        NULL       | 备注                          |
| isvalid         | int(2)       | YES      |                 1 | 数据有效性 0 无效 1 有效      |
| insert_time     | timestamp    | NO       | 当前时间 | 创建时间                      |
| update_time     | datetime     | YES      |        NULL       | 最后修改时间                  |
| insert_operator | varchar(32)  | YES      |        NULL       | 添加人                        |
| update_operator | varchar(32)  | YES      |        NULL       | 修改人                        |

### `ele_label` (电子标签)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |        注释         |
|-----------------|--------------|----------|-------------------|------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                 |
| market_id       | varchar(10)  | YES      |      NULL         | 市场id                 |
| use_person      | varchar(32)  | YES      |      NULL         | 领用人                 |
| use_time        | datetime     | YES      |      NULL         | 领用时间               |
| use_number      | int(11)      | YES      |      NULL         | 领用张数               |
| used_number     | int(11)      | YES      |      NULL         | 已用张数               |
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用 |
| scrap_number    | int(11)      | YES      |      NULL         | 报废张数               |
| insert_time     | timestamp    | NO       | 当前时间 | 新增时间               |
| insert_operator | varchar(32)  | YES      |      NULL         | 新增人                 |
| update_time     | datetime     | YES      |      NULL         | 更新时间               |
| update_operator | varchar(32)  | YES      |      NULL         | 更新人                 |
| remark          | varchar(255) | YES      |      NULL         | 备注                   |

### `ele_label_record` (电子标签记录)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |        注释         |
|-----------------|--------------|----------|-------------------|------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                 |
| market_id       | varchar(10)  | YES      |        NULL       | 市场id                 |
| car_number      | varchar(32)  | YES      |        NULL       | 车辆编号               |
| vin             | varchar(20)  | YES      |        NULL       | 车架号                 |
| owned_tenant    | varchar(32)  | YES      |        NULL       | 所属商户               |
| old_rfid        | varchar(50)  | YES      |        NULL       | 原RFID                 |
| new_rfid        | varchar(50)  | YES      |        NULL       | 新RFID                 |
| operation_time  | datetime     | YES      |        NULL       | 操作时间               |
| operator        | varchar(32)  | YES      |        NULL       | 操作人                 |
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用 |
| insert_time     | timestamp    | NO       | 当前时间 | 新增时间               |
| insert_operator | varchar(32)  | YES      |        NULL       | 新增人                 |
| remark          | varchar(255) | YES      |        NULL       | 备注                   |

### `fee_period_time` (收费时段)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |        注释         |
|-----------------|--------------|----------|-------------------|------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                 |
| market_id       | varchar(10)  | YES      |       NULL        | 市场id                 |
| car_type        | int(1)       | YES      |       NULL        | 车辆类型               |
| start_time      | varchar(10)  | YES      |       NULL        | 开始时间               |
| end_time        | varchar(10)  | YES      |       NULL        | 结束时间               |
| fee_hour        | int(10)      | YES      |       NULL        | 每小时计费(元)         |
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用 |
| insert_time     | datetime     | YES      | 当前时间 | 新增时间               |
| insert_operator | varchar(32)  | YES      |       NULL        | 新增人                 |
| update_time     | datetime     | YES      |       NULL        | 更新时间               |
| update_operator | varchar(32)  | YES      |       NULL        | 更新人                 |
| remark          | varchar(255) | YES      |       NULL        | 备注                   |

### `invoice` (发票)

|          字段名称           |  数据类型   | 是否为空 |      默认值      |                  注释                   |
|---------------------------|--------------|----------|-------------------|--------------------------------------------|
| id                        | varchar(32)  | NO       |                   | 主键id                                      |
| market_id                 | varchar(32)  | YES      |      NULL         | 市场id                                     |
| bill_time                 | timestamp    | NO       | 当前时间 | 开票时间                                   |
| invoice_no                | varchar(255) | YES      |      NULL         | 发票号                                     |
| current_no                | varchar(32)  | YES      |      NULL         | 业务单号                                   |
| purchacer_tax_no          | varchar(100) | YES      |      NULL         | 买方纳税人识别号                           |
| contract                  | varchar(32)  | YES      |      NULL         | 合同                                       |
| purchacer_nationality     | varchar(100) | YES      |      NULL         | 买方国籍                                   |
| purchacer_name            | varchar(255) | YES      |      NULL         | 买方姓名                                   |
| purchacer_id_card         | varchar(50)  | YES      |      NULL         | 买方身份证号（单位代码）                   |
| purchacer_passport        | varchar(100) | YES      |      NULL         | 买方护照号                                 |
| is_purchacer_code         | int(1)       | YES      |                 0 | 买方是否有统一代码（0是，1否）             |
| purchacer_type            | int(10)      | YES      |                 0 | 买方类型(0个人1单位)                       |
| purchacer_no              | varchar(255) | YES      |      NULL         | 买方编号                                   |
| purchacer_address         | varchar(255) | YES      |      NULL         | 买方地址                                   |
| purchacer_nation          | varchar(100) | YES      |      NULL         | 买方民族                                   |
| purchacer_mobile          | varchar(255) | YES      |      NULL         | 买方手机号                                 |
| tenant_id                 | varchar(32)  | YES      |      NULL         | 商户id                                     |
| is_seller_code            | int(1)       | YES      |                 0 | 卖方是否有统一代码（0是，1否）             |
| seller_nationality        | varchar(100) | YES      |      NULL         | 卖方国籍                                   |
| seller_tax_no             | varchar(50)  | YES      |      NULL         | 卖方纳税人识别号                           |
| seller_id_card            | varchar(50)  | YES      |      NULL         | 卖方身份证号（单位代码）                   |
| seller_passport           | varchar(20)  | YES      |      NULL         | 卖方护照                                   |
| seller_nation             | varchar(100) | YES      |      NULL         | 卖方民族                                   |
| seller_name               | varchar(255) | YES      |      NULL         | 卖方姓名                                   |
| seller_type               | int(1)       | YES      |                 0 | 卖方类型(0个人1单位)                       |
| seller_no                 | varchar(255) | YES      |      NULL         | 卖方编号                                   |
| seller_address            | varchar(255) | YES      |      NULL         | 卖方地址                                   |
| seller_mobile             | varchar(255) | YES      |      NULL         | 卖方手机号                                 |
| transfer_type             | int(11)      | YES      |                 0 | 过户类型(0买入过户1卖出过户)               |
| factory_plate             | varchar(255) | YES      |      NULL         | 厂牌型号                                   |
| certificate_number        | varchar(255) | YES      |      NULL         | 登记证号                                   |
| invoice_portof            | int(11)      | YES      |                 2 | 开票端口（0自助交易终端，1商户app，2窗口） |
| invoice_status            | int(11)      | YES      |                 1 | 发票状态（1：未处理，0作废,2已处理）       |
| invoice_code              | varchar(255) | YES      |      NULL         | 发票代码                                   |
| car_sources               | int(11)      | YES      |      1            | 车辆来源 (1:商品车,2:挂靠,3:代办，4散户)   |
| car_id                    | varchar(32)  | YES      |      NULL         | 车辆id                                     |
| car_no                    | varchar(255) | YES      |      NULL         | 车牌照号                                   |
| price                     | double(16,2) | YES      |      NULL         | 车的价格                                   |
| registration_no           | varchar(255) | YES      |      NULL         | 登记证号                                   |
| type                      | int(11)      | YES      |      NULL         | 车辆类型                                   |
| vin                       | varchar(255) | NO       |                   | 车辆vin码                                  |
| series                    | varchar(50)  | YES      |      NULL         | 车系                                       |
| series_name               | varchar(255) | YES      |      NULL         | 车型名称                                   |
| operater                  | varchar(32)  | YES      |      NULL         | 操作人                                    |
| status                    | int(11)      | YES      |      NULL         | 1按日统计，2按月统计，3按年统计            |
| version                   | int(11)      | YES      |                 1 | 乐观锁，数据版本                |
| taxno                     | varchar(255) | YES      |      NULL         | 税号                                       |
| address                   | varchar(255) | YES      |      NULL         | 地址                                       |
| bank                      | varchar(255) | YES      |      NULL         | 银行                                       |
| account                   | varchar(255) | YES      |      NULL         | 账号                                      |
| operator_name             | varchar(255) | YES      |      NULL         | 操作人                                      |
| auction                   | varchar(255) | YES      |      NULL         |                                            |
| auction_address           | varchar(255) | YES      |      NULL         |                                            |
| auction_no                | varchar(255) | YES      |      NULL         |                                            |
| auction_bank              | varchar(255) | YES      |      NULL         |                                            |
| auction_mobile            | varchar(255) | YES      |      NULL         |                                            |
| market_taxNo              | varchar(255) | YES      |      NULL         | 纳税人识别号                               |
| market_address            | varchar(255) | YES      |      NULL         |                                            |
| market_bank               | varchar(255) | YES      |      NULL         |                                            |
| market_phone              | varchar(255) | YES      |      NULL         |                                            |
| 注释                   | varchar(255) | YES      |      NULL         |                                            |
| ticket_operator           | varchar(255) | YES      |      NULL         |                                            |
| ticket_market             | varchar(255) | YES      |      NULL         |                                            |
| office                    | varchar(255) | YES      |      NULL         | 转入车管所                                 |
| sync_time                 | timestamp    | YES      | 当前时间 | 交易时间                                   |
| insert_time               | timestamp    | YES      | 当前时间 | 新增时间                                    |
| update_time               | timestamp    | YES      | 当前时间 | 更新时间                                    |
| pay_type                  | varchar(32)  | YES      |                 2 | 支付类型                                    |
| is_new_energy             | int(11)      | YES      |                 1 | 是否新能源（0是，1否）                     |
| agent_id_card             | varchar(255) | YES      |      NULL         | 代理人身份证                               |
| agent_name                | varchar(255) | YES      |      NULL         | 代理人人名称                               |
| agent_type                | int(11)      | YES      |                 0 | 代理人类型（0个人，1单位）                 |
| agent_address             | varchar(255) | YES      |      NULL         | 代理人地址                                 |
| agent_tax_no              | varchar(255) | YES      |      NULL         | 代理人纳税人识别号                         |
| agent_mobile              | varchar(255) | YES      |      NULL         | 代理人手机号                               |
| agent_nationality         | varchar(255) | YES      |      NULL         | 代理人国籍                                 |
| agent_no                  | varchar(255) | YES      |      NULL         | 代理人组织机构编码                         |
| agent_passport            | varchar(255) | YES      |      NULL         | 代理人护照号                               |
| agent_nation              | varchar(255) | YES      |      NULL         | 代理人民族                                 |
| is_agent_code             | int(11)      | YES      |                 1 | 代统理人是否有统一代码（0：是，1：否）     |
| is_agent                  | int(11)      | YES      |                 1 | 是否代理人（0：是，1：否）                 |
| car_invoice_type          | varchar(5)   | YES      |      NULL         | 开票类型                                   |
| initial_registration_date | datetime     | YES      |      NULL         | 初次上牌时间                               |
| invoice_purchase_id       | varchar(32)  | YES      |      NULL         | 购票id                                     |
| car_stock_status          | int(1)       | YES      |      NULL         | 车辆库存状态（原车辆状态）                 |
| user_id                   | varchar(32)  | YES      |      NULL         | 用户id                                     |
| trading_type              | int(2)       | YES      |                 1 | 交易类型 1 本地 2 外迁                     |
| sex                       | int(2)       | YES      |      NULL         | 消费者 性别 1男 2 女 0 未知                |
| age                       | int(5)       | YES      |      NULL         | 消费者年龄                                 |

### `invoice_purchase` (发票购买)

|      字段名称      |  数据类型  | 是否为空 |      默认值      |                    注释                     |
|------------------|-------------|----------|-------------------|------------------------------------------------|
| id               | varchar(32) | NO       |                   | 购票表id                                       |
| invoice_id       | varchar(32) | YES      |       NULL        | 开票表id                                       |
| user_id          | varchar(32) | YES      |       NULL        | 用户id                                         |
| invoice_code     | varchar(12) | NO       |                   | 发票代码                                       |
| invoice_no       | varchar(8)  | YES      |       NULL        | 开票号码                                       |
| buy_ticket_name  | varchar(32) | YES      |       NULL        | 购票人名称                                     |
| poll_all         | int(8)      | YES      |       NULL        | 总票数                                         |
| poll_residue     | int(8)      | YES      |       NULL        | 剩余票数                                       |
| insert_time      | timestamp   | YES      | 当前时间 | 添加时间                                       |
| insert_operator  | varchar(32) | YES      |       NULL        | 添加人名称                                     |
| update_time      | timestamp   | YES      | 当前时间 | 修改时间                                       |
| update_operator  | varchar(32) | YES      |       NULL        | 修改人名称                                     |
| isvalid          | int(1)      | YES      |                 1 | 1-有效，0-失效 |
| status           | int(1)      | YES      |                 1 | 1-有剩余发票,2-发票已开完,0-作废               |
| invoice_start_no | varchar(8)  | NO       |                   | 发票起始号                                     |
| invoice_end_no   | varchar(8)  | NO       |                   | 发票起始号                                     |
| bill_time        | date        | YES      |       NULL        | 购票日期                                       |
| market_id        | varchar(32) | YES      |       NULL        | 市场id                                         |
| version          | bigint(11)  | YES      |                 0 |                                                |

### `open_barrier_record` (应急开闸记录表)

|      字段名称       |  数据类型   | 是否为空 |      默认值      |        注释         |
|-------------------|--------------|----------|-------------------|------------------------|
| id                | varchar(32)  | NO       |                   | 主键                   |
| parking_detail_id | varchar(32)  | YES      |       NULL        | 收费详情id             |
| barrier_id        | varchar(32)  | YES      |       NULL        | 道闸id                 |
| market_id         | varchar(10)  | YES      |       NULL        | 市场id                 |
| reason            | varchar(100) | YES      |       NULL        | 开闸原因               |
| is_valid          | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用 |
| insert_time       | datetime     | YES      | 当前时间 | 新增时间               |
| insert_operator   | varchar(32)  | YES      |       NULL        | 新增人                 |
| update_time       | datetime     | YES      |       NULL        | 更新时间               |
| update_operator   | varchar(32)  | YES      |       NULL        | 更新人                 |
| remark            | varchar(255) | YES      |       NULL        | 备注                   |

### `parking_fee` (停车收费表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      | 注释  |
|-----------------|--------------|----------|-------------------|----------|
| id              | varchar(32)  | NO       |                   | 主键ID   |
| market_id       | varchar(32)  | YES      |      NULL         | 市场ID   |
| shift           | varchar(32)  | YES      |      NULL         | 班次     |
| brake_id        | varchar(32)  | YES      |      NULL         | 道闸ID   |
| employees_id    | varchar(32)  | YES      |      NULL         | 收费员   |
| arrival_time    | datetime     | YES      |      NULL         | 上岗时间 |
| leave_time      | datetime     | YES      |      NULL         | 离岗时间 |
| is_valid        | int(11)      | YES      |                 1 | 是否可用,0不可用;1可用 |
| remark          | varchar(255) | YES      |      NULL         | 备注     |
| insert_time     | timestamp    | YES      | 当前时间 | 新增时间  |
| update_time     | datetime     | YES      |      NULL         | 更新时间  |
| insert_operator | varchar(32)  | YES      |      NULL         | 新增人  |
| update_operator | varchar(32)  | YES      |      NULL         | 更新人  |

### `parking_fee_detail` (停车收费详情)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |            注释             |
|-----------------|--------------|----------|-------------------|--------------------------------|
| id              | varchar(32)  | NO       |                   | 主键ID                         |
| market_id       | varchar(32)  | YES      |      NULL         | 市场ID                         |
| parking_fee_id  | varchar(32)  | YES      |      NULL         | 收费班次ID                     |
| union_id        | varchar(32)  | YES      |      NULL         | 微信UnionID                    |
| card_no         | varchar(32)  | YES      |      NULL         | 卡号                           |
| charge_price    | int(10)      | YES      |      NULL         | 实收金额                       |
| parking_volume  | int(10)      | YES      |      NULL         | 停车卷                         |
| pay_type        | int(11)      | YES      |      NULL         | 支付类型 0.支付宝 1.微信 2.现金 3.公众号|
| price           | int(10)      | YES      |      NULL         | 应付金额                       |
| before_time     | datetime     | YES      |      NULL         | 入场时间                       |
| after_time      | datetime     | YES      |      NULL         | 出厂时间                       |
| before_image    | longtext     | YES      |      NULL         | 入场照片                       |
| after_image     | longtext     | YES      |      NULL         | 出厂照片                       |
| is_valid        | int(2)       | YES      |                 1 | 数据有效性                     |
| remark          | varchar(255) | YES      |      NULL         | 备注                           |
| insert_time     | timestamp    | YES      | 当前时间 | 创建时间                       |
| update_time     | datetime     | YES      |      NULL         | 更新时间                       |
| insert_operator | varchar(32)  | YES      |      NULL         | 创建人                         |
| update_operator | varchar(32)  | YES      |      NULL         | 最后更新人                     |
| in_type         | int(1)       | YES      |      NULL         | 入场方式 0扫码  1刷卡          |
| reason          | varchar(100) | YES      |      NULL         | 原因,金额不一致时,此字段必填   |
| is_vip          | int(1)       | YES      |                 0 | 是否是会员，0不是 1是          |
| pay_time        | datetime     | YES      |      NULL         | 支付时间                       |

### `parking_fee_integral` (停车收费针对南通微信会员支付积分使用记录)

|        字段名称         |  数据类型   | 是否为空 |      默认值      |       注释        |
|-----------------------|--------------|----------|-------------------|----------------------|
| id                    | varchar(32)  | NO       |                   | 主键               |
| parking_fee_detail_id | varchar(32)  | YES      |       NULL        | 停车记录id           |
| card_no               | varchar(255) | YES      |       NULL        | 停车卡号             |
| openid                | varchar(60)  | YES      |       NULL        | 消费者id             |
| price                 | int(11)      | YES      |       NULL        | 实际支付的费用       |
| integral              | int(11)      | YES      |                 0 | 本次支付所使用的积分 |
| is_valid              | int(2)       | YES      |                 1 | 数据有效性           |
| remark                | varchar(255) | YES      |       NULL        | 备注                 |
| insert_time           | timestamp    | YES      | 当前时间 | 创建时间             |
| update_time           | datetime     | YES      |       NULL        | 更新时间             |
| insert_operator       | varchar(32)  | YES      |       NULL        | 创建人               |
| update_operator       | varchar(32)  | YES      |       NULL        | 最后更新人           |

### `parking_fee_total` (停车收费总规则)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |            注释            |
|-----------------|--------------|----------|-------------------|-------------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                        |
| market_id       | varchar(10)  | YES      |        NULL       | 市场id                        |
| car_type        | int(1)       | YES      |                 1 | 车辆类型  1为商品车 2为社会车 |
| free_time       | int(10)      | YES      |        NULL       | 免费停车时长(分钟)            |
| is_free         | int(1)       | YES      |        NULL       | 是否开启免费  0不开启;1开启   |
| beyond_time     | int(10)      | YES      |        NULL       | 超出时长(小时)                |
| beyond_fee      | int(10)      | YES      |        NULL       | 超时计费(元)                  |
| is_beyond       | int(1)       | YES      |        NULL       | 是否开启超时  0不开启;1开启   |
| fee_limit       | int(10)      | YES      |        NULL       | 收费上限(元)                  |
| is_limit        | int(1)       | YES      |        NULL       | 是否开启收费上限              |
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用        |
| insert_time     | datetime     | YES      | 当前时间 | 新增时间                      |
| insert_operator | varchar(32)  | YES      |        NULL       | 新增人                        |
| update_time     | datetime     | YES      |        NULL       | 更新时间                      |
| update_operator | varchar(32)  | YES      |        NULL       | 更新人                        |
| remark          | varchar(255) | YES      |        NULL       | 备注                          |

### `property_contract` (商户合同表)

|      字段名称      |  数据类型   | 是否为空 |      默认值      |            注释             |
|------------------|--------------|----------|-------------------|--------------------------------|
| id               | varchar(32)  | NO       |                   | 商户合同表主键                 |
| market_id        | varchar(32)  | NO       |                   | 市场id                         |
| contract_no      | varchar(40)  | NO       |                   | 合同编号                       |
| tenant_id        | varchar(32)  | NO       |                   | 商户id                         |
| margin           | int(10)      | YES      |       NULL        | 保证金                         |
| status           | int(2)       | YES      |                 1 | 合同状态 1 正常 2 终止 3 撤销 4 已到期|
| contract_remark  | varchar(50)  | YES      |       NULL        | 签订合同备注                   |
| contract_image   | varchar(700) | YES      |       NULL        | 租赁合同图片                   |
| guarantee_image  | varchar(300) | YES      |       NULL        | 保证书图片                     |
| commitment_image | varchar(300) | YES      |       NULL        | 承诺书图片                     |
| isvalid          | int(2)       | YES      |                 1 | 数据有效性 1 有效 0 无效       |
| remark           | varchar(255) | YES      |       NULL        | 备注                           |
| insert_time      | timestamp    | YES      | 当前时间 | 新增时间                       |
| update_time      | datetime     | YES      |       NULL        | 最后修改时间                   |
| insert_operator  | varchar(32)  | YES      |       NULL        | 创建人                         |
| update_operator  | varchar(32)  | YES      |       NULL        | 最后修改人                     |

### `property_contract_detail` (合同详情)

|             字段名称              |   数据类型   | 是否为空 |      默认值      |            注释             |
|---------------------------------|---------------|----------|-------------------|--------------------------------|
| id                              | varchar(32)   | NO       |                   | 合同详情表数据                 |
| market_id                       | varchar(32)   | NO       |                   | 市场id                         |
| property_contract_id            | varchar(32)   | NO       |                   | 合同主键id                     |
| tenant_id                       | varchar(32)   | NO       |                   | 商户id                         |
| area_id                         | varchar(300)  | NO       |                   | 物业编号(area_shop主键)        |
| contract_category               | int(2)        | NO       |                   | 物业类型 1.车位 0.展厅 2.办公室|
| price                           | decimal(10,2) | YES      |       NULL        | 单价                           |
| preferential_price              | decimal(10,2) | YES      |       NULL        | 优惠后单价                     |
| type                            | int(2)        | NO       |                   | 付款方式 1.月付 2.季付 3.年付 |
| property_contract_detail_status | int(2)        | YES      |                 1 | 合同状态 1.正常 2.终止 3.撤销 4.已到期|
| status_tme                      | datetime      | NO       |                   | 合同开始日期                   |
| end_time                        | datetime      | NO       |                   | 合同结束日期                   |
| termination_remark              | varchar(300)  | YES      |       NULL        | 终止合同备注                   |
| termination_message             | varchar(300)  | YES      |       NULL        | 终止合同原因                   |
| termination_time                | datetime      | YES      |       NULL        | 终止合同时间                   |
| isvalid                         | int(2)        | YES      |                 1 | 数据有效性 1 有效 0 无效       |
| remark                          | varchar(255)  | YES      |       NULL        | 备注                           |
| insert_time                     | timestamp     | YES      | 当前时间 | 创建时间                       |
| update_time                     | datetime      | YES      |       NULL        | 最后更新时间                   |
| insert_operator                 | varchar(32)   | YES      |       NULL        | 创建人                         |
| update_operator                 | varchar(32)   | YES      |       NULL        | 最后修改人                     |

### `property_contract_pay` (合同缴费表)

|        字段名称        |   数据类型   | 是否为空 |      默认值      |            注释             |
|----------------------|---------------|----------|-------------------|--------------------------------|
| id                   | varchar(32)   | NO       |                   | 合同缴费列表                   |
| market_id            | varchar(32)   | NO       |                   | 市场ID                         |
| property_contract_id | varchar(32)   | NO       |                   | 合同id                         |
| tenant_id            | varchar(32)   | NO       |                   | 商户id                         |
| status               | int(2)        | NO       |                 1 | 缴费状态 1.未缴清 2.已缴清 3.终止(预留)|
| price                | decimal(10,2) | YES      |              0.00 | 待缴纳金额                     |
| complete_price       | decimal(10,2) | YES      |              0.00 | 已缴纳金额                     |
| isvalid              | int(2)        | YES      |                 1 | 数据有效性 1 有效 0 无效       |
| remark               | varchar(255)  | YES      |       NULL        | 备注                           |
| insert_time          | timestamp     | YES      | 当前时间 |                                |
| update_time          | datetime      | YES      |       NULL        |                                |
| insert_operator      | varchar(32)   | YES      |       NULL        |                                |
| update_operator      | varchar(32)   | YES      |       NULL        |                                |

### `property_contract_pay_detail` (合同缴费记录表)

|          字段名称          |   数据类型   | 是否为空 |      默认值      |            注释             |
|--------------------------|---------------|----------|-------------------|--------------------------------|
| id                       | varchar(32)   | NO       |                   | 合同缴费记录id                 |
| market_id                | varchar(32)   | NO       |                   | 市场ID                         |
| property_contract_id     | varchar(32)   | NO       |                   | 合同ID                         |
| property_contract_detail | varchar(32)   | NO       |                   | 合同详情id                     |
| pay_time                 | datetime      | YES      |        NULL       | 缴费日期                       |
| status                   | int(2)        | YES      |                 0 | 缴费状态 0.未缴纳 1.未缴清 2.已缴清|
| pay_price                | decimal(10,2) | YES      |              0.00 | 已缴纳金额                     |
| isvalid                  | int(2)        | YES      |                 1 |                                |
| remark                   | varchar(255)  | YES      |        NULL       |                                |
| insert_time              | timestamp     | YES      | 当前时间 |                                |
| update_time              | datetime      | YES      |        NULL       |                                |
| insert_operator          | varchar(32)   | YES      |        NULL       |                                |
| update_operator          | varchar(32)   | YES      |        NULL       |                                |

### `shopping_guide_detail` (商户合同表)

|       字段名称        |  数据类型   | 是否为空 |      默认值      |         注释          |
|---------------------|--------------|----------|-------------------|--------------------------|
| id                  | varchar(32)  | NO       |                   | 商户合同表主键           |
| logo                | varchar(300) | YES      |       NULL        | logo图                   |
| market_id           | varchar(32)  | NO       |                   | 市场id                   |
| market_introduction | varchar(700) | YES      |       NULL        | 市场简介图               |
| gallery_image       | varchar(700) | YES      |       NULL        | 首页轮播图               |
| isvalid             | int(2)       | YES      |                 1 | 数据有效性 1 有效 0 无效 |
| insert_time         | timestamp    | YES      | 当前时间 | 新增时间                 |
| update_time         | datetime     | YES      |       NULL        | 最后修改时间             |
