|   DATABASE    | CHARACTER SET |     COLLATION     |
|---------------|---------------|-------------------|
| maxcar_base_l | latin1        | latin1_swedish_ci |

### `car_brand` (汽车品牌)

|   字段名称    |  数据类型   | 是否为空 | 默认值 |   注释    |
|-------------|--------------|----------|---------|--------------|
| id          | varchar(32)  | NO       |         | 品牌id       |
| car_index   | char(1)      | YES      |   NULL  | 品牌字母索引 |
| brand_code  | varchar(20)  | YES      |   NULL  | 品牌代码     |
| brand_name  | varchar(20)  | YES      |   NULL  | 品牌名称     |
| logo_url    | varchar(200) | YES      |   NULL  | 品牌图标url  |
| insert_time | datetime     | YES      |   NULL  | 新增时间     |
| update_time | datetime     | YES      |   NULL  | 更新时间     |
| remark      | varchar(255) | YES      |   NULL  | 备注         |

### `car_model` (车型)

|   字段名称    |  数据类型   | 是否为空 | 默认值 | 注释  |
|-------------|--------------|----------|---------|----------|
| id          | varchar(32)  | NO       |         | 车型id   |
| series_id   | varchar(32)  | YES      |  NULL   | 车系id   |
| model_code  | varchar(20)  | YES      |  NULL   | 车型代码 |
| model_name  | varchar(100) | YES      |  NULL   | 车型名称 |
| insert_time | datetime     | YES      |  NULL   | 新增时间 |
| update_time | datetime     | YES      |  NULL   | 更新时间 |
| remark      | longtext     | YES      |  NULL   | 备注     |

### `car_series` (车系)

|   字段名称    |  数据类型   | 是否为空 | 默认值 | 注释  |
|-------------|--------------|----------|---------|----------|
| id          | varchar(32)  | NO       |         | 车系id   |
| brand_id    | varchar(32)  | YES      |   NULL  | 品牌id   |
| series_code | varchar(20)  | YES      |   NULL  | 车系代码 |
| series_name | varchar(100) | YES      |   NULL  | 车系名称 |
| insert_time | datetime     | YES      |   NULL  | 新增时间 |
| update_time | datetime     | YES      |   NULL  | 更新时间 |
| remark      | varchar(255) | YES      |   NULL  | 备注     |

### `city` (城市)

|  字段名称  |  数据类型   | 是否为空 | 默认值 |  注释   |
|----------|--------------|----------|---------|------------|
| id       | int(11)      | NO       |         | 城市ID     |
| province | int(11)      | NO       |         | 所在省份ID |
| name     | varchar(20)  | NO       |         | 城市名称   |
| pinyin   | varchar(255) | NO       |         | 城市首字母 |
| level    | int(11)      | NO       |         | 城市级别   |

### `color` (颜色)

| 字段名称 |  数据类型  | 是否为空 | 默认值 | 注释  |
|--------|-------------|----------|---------|----------|
| id     | int(11)     | NO       |         | 主键     |
| code   | varchar(10) | YES      |   NULL  | 颜色代码 |
| name   | varchar(10) | YES      |   NULL  | 颜色名称 |

### `id_dictionary` (字典表)

|   字段名称   |  数据类型   | 是否为空 | 默认值 | 注释 |
|------------|--------------|----------|---------|---------|
| id         | int(11)      | NO       |         | 主键id    |
| market_id  | varchar(15)  | NO       |         | 市场id   |
| table_name | varchar(255) | NO       |         | 表名   |
| pkid       | varchar(255) | NO       |         |         |
| version    | int(11)      | YES      |    1    | 乐观锁，数据版本|

### `open_api_config` (对外开放api配置表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |             注释             |
|-----------------|--------------|----------|-------------------|---------------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                          |
| app_key         | varchar(20)  | NO       |                   | AppKey                          |
| app_secret      | varchar(32)  | NO       |                   | AppSecret                       |
| ip              | varchar(100) | YES      |         NULL      | ip白名单 以逗号分隔，最大支持5个ip|
| market_id       | varchar(10)  | YES      |         NULL      | 市场id                          |
| api_type        | int(1)       | YES      |         NULL      | 接口类型  1市场 2公司          |
| company_name    | varchar(100) | YES      |         NULL      | 公司名称                        |
| is_ip           | int(1)       | YES      |         NULL      | 是否开启ip白名单 0不开启 1开启|
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用          |
| insert_time     | datetime     | YES      | 当前时间 | 新增时间                        |
| insert_operator | varchar(32)  | YES      |         NULL      | 新增人                          |
| update_time     | datetime     | YES      |         NULL      | 更新时间                        |
| update_operator | varchar(32)  | YES      |         NULL      | 更新人                          |
| remark          | varchar(255) | YES      |         NULL      | 备注                            |

### `province` (省份)

| 字段名称 |  数据类型  | 是否为空 | 默认值 | 注释 |
|--------|-------------|----------|---------|---------|
| id     | int(11)     | NO       |         | ID      |
| name   | varchar(20) | NO       |         | 名称    |

### `taobao_car` (上传淘宝，车辆基本信息)

|   字段名称    |  数据类型   | 是否为空 | 默认值 | 注释  |
|-------------|--------------|----------|---------|----------|
| id          | int(11)      | NO       |         | 主键id   |
| brand_name  | varchar(20)  | YES      |  NULL   | 品牌名称 |
| brand_pid   | varchar(20)  | YES      |  NULL   | 品牌pid  |
| brand_vid   | varchar(20)  | YES      |  NULL   | 品牌vid   |
| series_name | varchar(45)  | YES      |  NULL   | 车系名称   |
| series_pid  | varchar(20)  | YES      |  NULL   | 车系pid  |
| series_vid  | varchar(20)  | YES      |  NULL   | 车系vid   |
| year_name   | varchar(10)  | YES      |  NULL   | 年份   |
| year_pid    | varchar(20)  | YES      |  NULL   |          |
| year_vid    | varchar(20)  | YES      |  NULL   |          |
| model_name  | varchar(40)  | YES      |  NULL   | 车型    |
| model_pid   | varchar(20)  | YES      |  NULL   |          |
| model_vid   | varchar(20)  | YES      |  NULL   |          |
| ali_value   | varchar(100) | YES      |  NULL   |          |
| ali_code    | varchar(10)  | YES      |  NULL   |          |

### `taobao_item_values` (上传淘宝，属性值)

|  字段名称   |  数据类型   | 是否为空 | 默认值 | 注释 |
|-----------|--------------|----------|---------|---------|
| pid       | varchar(100) | NO       |         | 主键    |
| vid       | varchar(100) | NO       |         |         |
| name      | varchar(100) | NO       |         | 名称     |
| pidname   | varchar(100) | NO       |         |         |
| cid       | varchar(100) | NO       |         |         |
| isparent  | tinyint(1)   | YES      |  NULL   |         |
| parentvid | varchar(100) | YES      |  NULL   |         |
| level     | int(1)       | NO       |         |         |

### `taobao_market_config` (上传淘宝，市场配置)

|                 字段名称                 |  数据类型   | 是否为空 |              默认值               | OMMENT|
|----------------------------------------|--------------|----------|------------------------------------|------------- |
| market_id                              | varchar(32)  | NO       |                                    | 主键id(市场id)|
| cid                                    | int(10)      | YES      |                           50050566 | 叶子类目id|
| market_name                            | varchar(30)  | NO       |                                    | 市场名称|
| session_key                            | varchar(100) | NO       |                                    | 市场淘宝sessionKey|
| check_source                           | int(1)       | NO       |                                    | 来源 0:无1:维真,2:中车检,3:TUV|
| ftl_name                               | varchar(30)  | NO       |                                    | 模板名称|
| market_phone                           | varchar(20)  | NO       |                                    | 市场电话|
| type                                   | varchar(10)  | YES      | fixed                              | 发布类型 fixed auction|
| stuff_status                           | varchar(10)  | YES      | second                             | 新旧程度 new(新)，second(二手)，unused(闲置)|
| approve_status                         | varchar(10)  | YES      | instock                            | instock在仓库中，onsale在售，默认在售|
| locality_life_expirydate               | varchar(5)   | YES      |                                  7 | 电子凭证时效为7天|
| is_offline                             | varchar(1)   | YES      |                                  3 | 是否是线下商品 1线上商品（默认值）2线上或线下商品 3线下商品 |
| sku_outer_ids                          | varchar(50)  | YES      | 3782914410043                | |
| num                                    | int(5)       | YES      |                                  1 | 商品数量|
| auction_point                          | int(5)       | YES      |                                  1 | |
| has_invoice                            | tinyint(1)   | YES      |                                  1 | 发票|
| has_warranty                           | tinyint(1)   | YES      |                                  0 | 是否有保修|
| sub_stock                              | int(3)       | YES      |                                  2 | 否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)|
| sku_properties                         | varchar(50)  | YES      | 14829532:3292081 | 设置定金|
| sku_quantities                         | varchar(10)  | YES      | 1,1                                | |
| subscription                           | varchar(20)  | NO       |                                    | 定金|
| location_state                         | varchar(10)  | NO       |                                    | 所在省|
| location_city                          | varchar(10)  | NO       |                                    | 所在市|
| valid_thru                             | int(3)       | YES      |                                  7 | 有效期|
| sell_promise                           | tinyint(1)   | YES      |                                  1 | 是否承诺退换货服务!虚拟商品无须设置此项!|
| locality_life_merchant                 | varchar(10)  | YES      | 0:淘宝                             |  |
| locality_life_onsale_auto_refund_ratio | int(10)      | YES      |                                100 |  |
| locality_life_refund_ratio             | int(10)      | YES      |                                100 | 退款比例，百分比%前的数字,1-100的正整数值|
| locality_life_choose_logis             | varchar(3)   | YES      |                                  1 | 使用邮寄|
| freight_payer                          | varchar(10)  | YES      | buyer                              | 买家运费承担|
| post_fee                               | varchar(10)  | YES      |                                999 |  |
| express_fee                            | varchar(10)  | YES      |                                999 |  |
| locality_life_eticket                  | varchar(20)  | YES      | ;has_pos:1;                        |  |
