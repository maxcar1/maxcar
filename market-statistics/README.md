|      DATABASE       | CHARACTER SET |    COLLATION    |
|---------------------|---------------|-----------------|
| maxcar_statistics_l | utf8          | utf8_general_ci |

### `car_price_day` (汽车价格按日统计表)

|    字段名称     |  数据类型  | 是否为空 |      默认值      |                    注释                     |
|---------------|-------------|----------|-------------------|------------------------------------------------|
| id            | int(11)     | NO       |                   | 主键id                               |
| market_id     | varchar(32) | YES      |       NULL        | 市场id                                         |
| tenant_id     | varchar(32) | YES      |       NULL        | 商户名称id                                     |
| report_time   | varchar(32) | YES      |       NULL        | 时间格式（年-月-日）                           |
| price10_count | int(11)     | YES      |       NULL        | 出售数量在10w以下                              |
| price20_count | int(11)     | YES      |       NULL        | 出售价格在10w-20w                              |
| price30_count | int(11)     | YES      |       NULL        | 出售价格在20w-30w                              |
| price40_count | int(11)     | YES      |       NULL        | 出售价格在30w-40w                              |
| price50_count | int(11)     | YES      |       NULL        | 出售价格在40w-50w                              |
| price60_count | int(11)     | YES      |       NULL        | 出售价格在60w以上                              |
| stock5_day    | int(11)     | YES      |       NULL        | 出售数量在5w以下                               |
| stock10_day   | int(11)     | YES      |       NULL        | 出售数量在5w-10w                               |
| stock15_day   | int(11)     | YES      |       NULL        | 出售数量在10w-15w                              |
| stock20_day   | int(11)     | YES      |       NULL        | 出售数量在15w-20w                              |
| stock25_day   | int(11)     | YES      |       NULL        | 出售数量在20w-25w                              |
| stock30_day   | int(11)     | YES      |       NULL        | 出售数量在25w-30w                              |
| stock35_day   | int(11)     | YES      |       NULL        | 出售数量在30w-35w                              |
| stock40_day   | int(11)     | YES      |       NULL        | 出售数量在35w-40w                              |
| stock45_day   | int(11)     | YES      |       NULL        | 出售数量在40w-45w                              |
| stock50_day   | int(11)     | YES      |       NULL        | 出售数量在45w-50w                              |
| stock60_day   | int(11)     | YES      |       NULL        | 出售数量在50w以上                              |
| isvalid       | int(2)      | YES      |                 1 | 1-有效，0-失效 |
| version       | int(2)      | YES      |       1        | 数据版本，防止脏写                             |
| register_time | datetime    | YES      | 当前时间 | 系统录入时间                                   |

### `carbrand_day` (车辆品牌日表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键id                                   |
| market_id       | varchar(32)  | YES      |         NULL      | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |         NULL      | 商户名称id                                     |
| report_time     | varchar(32)  | YES      |         NULL      | 时间格式（年-月-日）                           |
| brand_name      | varchar(32)  | YES      |         NULL      | 品牌名称                                       |
| stock_count     | int(11)      | YES      |         NULL      | 库存总量                                       |
| stock_price     | double(11,2) | YES      |         NULL      | 库存总价值                                     |
| sales_count     | int(11)      | YES      |         NULL      | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |         NULL      | 车辆出售交易总价值                             |
| sales_avg_price | double(11,2) | YES      |         NULL      | 车辆交易平均价值                               |
| male_count      | int(11)      | YES      |         NULL      | 购买男性数量                                   |
| female_count    | int(11)      | YES      |         NULL      | 购买女性数量                                   |
| age20_count     | int(11)      | YES      |         NULL      | 消费者年龄20岁以下数量                         |
| age25_count     | int(11)      | YES      |         NULL      | 消费者年龄20-25岁数量                          |
| age30_count     | int(11)      | YES      |         NULL      | 消费者年龄25-30岁数量                          |
| age35_count     | int(11)      | YES      |         NULL      | 消费者年龄30-35岁数量                          |
| age40_count     | int(11)      | YES      |         NULL      | 消费者年龄35-40岁数量                          |
| age45_count     | int(11)      | YES      |         NULL      | 消费者年龄40-45岁数量                          |
| age50_count     | int(11)      | YES      |         NULL      | 消费者年龄45岁以上数量                         |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效|
| version         | int(2)       | YES      |                 1 | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `carbrand_month` (车辆品牌月表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键id                                   |
| market_id       | varchar(32)  | YES      |        NULL       | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |        NULL       | 商户名称id                                     |
| report_time     | varchar(32)  | YES      |        NULL       | 时间格式（年-月）                              |
| brand_name      | varchar(32)  | YES      |        NULL       | 品牌名称                                       |
| stock_count     | int(11)      | YES      |        NULL       | 库存总量                                       |
| stock_price     | double(11,2) | YES      |        NULL       | 库存总价值                                     |
| sales_count     | int(11)      | YES      |        NULL       | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |        NULL       | 车辆出售交易总价值                             |
| sales_avg_price | double(11,2) | YES      |        NULL       | 车辆交易平均价值                               |
| male_count      | int(11)      | YES      |        NULL       | 购买男性数量                                   |
| female_count    | int(11)      | YES      |        NULL       | 购买女性数量                                   |
| age20_count     | int(11)      | YES      |        NULL       | 消费者年龄20岁以下数量                         |
| age25_count     | int(11)      | YES      |        NULL       | 消费者年龄20-25岁数量                          |
| age30_count     | int(11)      | YES      |        NULL       | 消费者年龄25-30岁数量                          |
| age35_count     | int(11)      | YES      |        NULL       | 消费者年龄30-35岁数量                          |
| age40_count     | int(11)      | YES      |        NULL       | 消费者年龄35-40岁数量                          |
| age45_count     | int(11)      | YES      |        NULL       | 消费者年龄40-45岁数量                          |
| age50_count     | int(11)      | YES      |        NULL       | 消费者年龄45岁以上数量                         |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| version         | int(2)       | YES      |                 1 | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `carprice_day` (交易价格分布)

|       字段名称        |  数据类型  | 是否为空 |      默认值      |                    注释                     |
|---------------------|-------------|----------|-------------------|------------------------------------------------|
| id                  | int(11)     | NO       |                   | 主键                               |
| market_id           | varchar(32) | YES      |       NULL        | 市场id                                         |
| tenant_id           | varchar(32) | YES      |       NULL        | 商户id                                         |
| report_time         | varchar(32) | YES      |       NULL        | 时间格式（年-月-日）                           |
| invoice_price_id    | varchar(32) | YES      |       NULL        | 交易价格层次id                                 |
| invoice_price_name  | varchar(32) | YES      |       NULL        | 交易价格层次名字                               |
| sales_count         | int(11)     | YES      |       NULL        | 交易车辆数                                     |
| stock_avg_stocktime | int(11)     | YES      |       NULL        | 平均库存天数                                   |
| isvalid             | int(5)      | YES      |                 1 | 1-有效，0-失效|
| version             | int(5)      | YES      |       1        | 数据版本，防止脏写                             |
| register_time       | datetime    | YES      | 当前时间 | 系统录入时间                                   |

### `carstock_day` (库存价值分布日表)

|       字段名称       |  数据类型  | 是否为空 |      默认值      |                    注释                     |
|--------------------|-------------|----------|-------------------|------------------------------------------------|
| id                 | int(11)     | NO       |                   | 主键                               |
| market_id          | varchar(32) | YES      |       NULL        | 市场id                                         |
| tenant_id          | varchar(32) | YES      |       NULL        | 商户id                                         |
| report_time        | varchar(32) | YES      |       NULL        | 时间格式（年-月-日）                           |
| invoice_stock_id   | varchar(32) | YES      |       NULL        | 库存价格层次id                                 |
| invoice_stock_name | varchar(32) | YES      |       NULL        | 库存价格层次名字                               |
| stock_count        | int(11)     | YES      |       NULL        | 库存价值内的车辆数                             |
| isvalid            | int(5)      | YES      |                 1 | 1-有效，0-失效 |
| version            | int(5)      | YES      |       1        | 数据版本，防止脏写                             |
| register_time      | datetime    | YES      | 当前时间 | 系统录入时间                                   |

### `carstock_month` (库存价值分布月表)

|       字段名称       |  数据类型  | 是否为空 |      默认值      |                    注释                     |
|--------------------|-------------|----------|-------------------|------------------------------------------------|
| id                 | int(11)     | NO       |                   | 主键                               |
| market_id          | varchar(32) | YES      |       NULL        | 市场id                                         |
| tenant_id          | varchar(32) | YES      |       NULL        | 商户id                                         |
| report_time        | varchar(32) | YES      |       NULL        | 时间格式（年-月）                              |
| invoice_stock_id   | varchar(32) | YES      |       NULL        | 库存价格层次id                                 |
| invoice_stock_name | varchar(32) | YES      |       NULL        | 库存价格层次名字                               |
| stock_count        | int(11)     | YES      |       NULL        | 库存价值内的车辆数                             |
| isvalid            | int(5)      | YES      |                 1 | 1-有效，0-失效 |
| version            | int(5)      | YES      |       1        | 数据版本，防止脏写                             |
| register_time      | datetime    | YES      | 当前时间 | 系统录入时间                                   |

### `carstocktime_day` (库存时长日表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键                                   |
| market_id       | varchar(32)  | YES      |      NULL         | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |      NULL         | 商户id                                         |
| report_time     | varchar(32)  | YES      |      NULL         | 时间格式（年-月-日）                           |
| stocktime_id    | int(32)      | YES      |      NULL         | 库存周期id                                     |
| stocktime_name  | varchar(32)  | YES      |      NULL         | 库存周期                                       |
| stock_count     | int(11)      | YES      |      NULL         | 库存总量                                       |
| stock_price     | double(11,2) | YES      |      NULL         | 库存总价值(万)                                 |
| sales_count     | int(11)      | YES      |      NULL         | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |      NULL         | 车辆出售交易总价值(万)                         |
| sales_avg_price | double(11,2) | YES      |      NULL         | 车辆交易平均价值                               |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| version         | int(2)       | YES      |      1         | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `carstocktime_month` (库存时长月表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键                                   |
| market_id       | varchar(32)  | YES      |       NULL        | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |       NULL        | 商户id                                         |
| report_time     | varchar(32)  | YES      |       NULL        | 时间格式（年-月-日）                           |
| stocktime_id    | varchar(32)  | YES      |       NULL        | 库存周期id                                     |
| stocktime_name  | varchar(32)  | YES      |       NULL        | 库存周期                                       |
| stock_count     | int(11)      | YES      |       NULL        | 库存总量                                       |
| stock_price     | double(11,2) | YES      |       NULL        | 库存总价值(万)                                 |
| sales_count     | int(11)      | YES      |       NULL        | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |       NULL        | 车辆出售交易总价值(万)                         |
| sales_avg_price | double(11,2) | YES      |       NULL        | 车辆交易平均价值                               |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| version         | int(2)       | YES      |       1        | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `cartype_day` (车辆类型日表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键                               |
| market_id       | varchar(32)  | YES      |        NULL       | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |        NULL       | 商户Id                                         |
| report_time     | varchar(32)  | YES      |        NULL       | 时间格式（年-月-日）                           |
| type_id         | varchar(32)  | YES      |        NULL       | 车辆类型id                                     |
| type_name       | varchar(32)  | YES      |        NULL       | 车辆类型                                       |
| sales_count     | int(10)      | YES      |        NULL       | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |        NULL       | 车辆出售交易总价值                             |
| sales_avg_price | double(11,2) | YES      |        NULL       | 车辆交易平均价值                               |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| version         | int(2)       | YES      |                 1 | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `cartype_month` (车辆类型月表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键                               |
| market_id       | varchar(32)  | YES      |     NULL          | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |     NULL          | 商户Id                                         |
| report_time     | varchar(32)  | YES      |     NULL          | 时间格式（年-月）                              |
| type_id         | varchar(32)  | YES      |     NULL          | 车辆类型id                                     |
| type_name       | varchar(32)  | YES      |     NULL          | 车辆类型                                       |
| sales_count     | int(11)      | YES      |     NULL          | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |     NULL          | 车辆出售交易总价值                             |
| sales_avg_price | double(11,2) | YES      |     NULL          | 车辆交易平均价值                               |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| version         | int(2)       | YES      |                 1 | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `caryear_day` (车辆年份日表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键                               |
| market_id       | varchar(32)  | YES      |     NULL          | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |     NULL          | 商户id                                         |
| report_time     | varchar(10)  | YES      |     NULL          | 时间格式（年-月-日）                           |
| year_id         | int(11)      | YES      |     NULL          | 年份类型id                                     |
| year_name       | varchar(11)  | YES      |     NULL          | 年份类型                                       |
| stock_count     | int(11)      | YES      |     NULL          | 库存总量                                       |
| stock_price     | double(11,2) | YES      |     NULL          | 库存总价值                                     |
| sales_count     | int(11)      | YES      |     NULL          | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |     NULL          | 车辆出售交易总价值                             |
| sales_avg_price | double(11,2) | YES      |     NULL          | 车辆交易平均价值                               |
| male_count      | int(11)      | YES      |     NULL          | 购买男性数量                                   |
| female_count    | int(11)      | YES      |     NULL          | 购买女性数量                                   |
| age20_count     | int(11)      | YES      |     NULL          | 消费者年龄20岁以下数量                         |
| age25_count     | int(11)      | YES      |     NULL          | 消费者年龄20-25岁数量                          |
| age30_count     | int(11)      | YES      |     NULL          | 消费者年龄25-30岁数量                          |
| age35_count     | int(11)      | YES      |     NULL          | 消费者年龄30-35岁数量                          |
| age40_count     | int(11)      | YES      |     NULL          | 消费者年龄35-40岁数量                          |
| age45_count     | int(11)      | YES      |     NULL          | 消费者年龄40-45岁数量                          |
| age50_count     | int(11)      | YES      |     NULL          | 消费者年龄45岁以上数量                         |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version         | int(2)       | YES      |     1          | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `caryear_month` (车辆年份月表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | int(11)      | NO       |                   | 主键                               |
| market_id       | varchar(32)  | YES      |       NULL        | 市场id                                         |
| tenant_id       | varchar(32)  | YES      |       NULL        | 商户id                                         |
| report_time     | varchar(10)  | YES      |       NULL        | 时间格式（年-月）                              |
| year_id         | varchar(11)  | YES      |       NULL        | 年份类型id                                     |
| year_name       | varchar(11)  | YES      |       NULL        | 年份类型                                       |
| stock_count     | int(11)      | YES      |       NULL        | 库存总量                                       |
| stock_price     | double(11,2) | YES      |       NULL        | 库存总价值                                     |
| sales_count     | int(11)      | YES      |       NULL        | 车辆出售总数量                                 |
| sales_price     | double(11,2) | YES      |       NULL        | 车辆出售交易总价值                             |
| sales_avg_price | double(11,2) | YES      |       NULL        | 车辆交易平均价值                               |
| male_count      | int(11)      | YES      |       NULL        | 购买男性数量                                   |
| female_count    | int(11)      | YES      |       NULL        | 购买女性数量                                   |
| age20_count     | int(11)      | YES      |       NULL        | 消费者性别20岁以下数量                         |
| age25_count     | int(11)      | YES      |       NULL        | 消费者性别20-25岁数量                          |
| age30_count     | int(11)      | YES      |       NULL        | 消费者性别25-30岁数量                          |
| age35_count     | int(11)      | YES      |       NULL        | 消费者性别30-35岁数量                          |
| age40_count     | int(11)      | YES      |       NULL        | 消费者性别35-40岁数量                          |
| age45_count     | int(11)      | YES      |       NULL        | 消费者性别40-45岁数量                          |
| age50_count     | int(11)      | YES      |       NULL        | 消费者性别45岁以上数量                         |
| isvalid         | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| version         | int(2)       | YES      |       1        | 数据版本，防止脏写                             |
| register_time   | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `date_time_num` (日期表)

|  字段名称  | 数据类型 | 是否为空 | 默认值 |   注释    |
|----------|-----------|----------|---------|--------------|
| id       | int(11)   | NO       |         | 主键ID |
| num_time | datetime  | YES      |   NULL  | 日期         |

### `inventory_invoice_day` (库存交易日表)

|       字段名称        |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|---------------------|--------------|----------|-------------------|------------------------------------------------|
| id                  | int(11)      | NO       |                   | 主键                               |
| market_id           | varchar(32)  | YES      |       NULL        | 市场id                                         |
| tenant_id           | varchar(32)  | YES      |       NULL        | 商户Id                                         |
| report_time         | varchar(32)  | YES      |       NULL        | 时间格式（年-月-日）                           |
| stock_count         | int(11)      | YES      |       NULL        | 库存总量                                       |
| stock_price         | double(11,2) | YES      |       NULL        | 库存总价值                                     |
| stock_day_avg       | int(11)      | YES      |       NULL        | 平均库存天数                                   |
| out_library_count   | int(11)      | YES      |       NULL        | 出库总数量                                     |
| tenant_space        | int(11)      | YES      |       NULL        | 商户车位总数量                                 |
| sales_count         | int(11)      | YES      |       NULL        | 车辆出售总数量                                 |
| sales_price         | double(11,2) | YES      |       NULL        | 车辆出售交易总价值                             |
| sales_avg_price     | double(11,2) | YES      |       NULL        | 车辆交易平均价值                               |
| stock_day_price_avg | double(11,2) | YES      |       NULL        | 交易车辆平均库存天数                           |
| version             | int(11)      | YES      |       NULL        | 数据版本，防止脏写                             |
| isvalid             | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| register_time       | datetime     | YES      | 当前时间 | 系统录入时间                                   |

### `inventory_invoice_month` (库存交易月表)

|      字段名称       |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-------------------|--------------|----------|-------------------|------------------------------------------------|
| id                | int(11)      | NO       |                   | 主键                               |
| market_id         | varchar(32)  | YES      |      NULL         | 市场id                                         |
| tenant_id         | varchar(32)  | YES      |      NULL         | 商户Id                                         |
| report_time       | varchar(32)  | YES      |      NULL         | 时间格式（年-月）                              |
| stock_count       | int(11)      | YES      |      NULL         | 库存总量                                       |
| stock_price       | double(11,2) | YES      |      NULL         | 库存总价值                                     |
| stock_day_avg     | int(11)      | YES      |      NULL         | 平均库存天数                                   |
| out_library_count | int(11)      | YES      |      NULL         | 出库总数量                                     |
| tenant_space      | int(11)      | YES      |      NULL         | 商户车位总数量                                 |
| sales_count       | int(11)      | YES      |      NULL         | 车辆出售总数量                                 |
| sales_price       | double(11,2) | YES      |      NULL         | 车辆出售交易总价值                             |
| sales_avg_price   | double(11,2) | YES      |      NULL         | 车辆交易平均价值                               |
| version           | int(11)      | YES      |      NULL         | 数据版本，防止脏写                             |
| isvalid           | int(2)       | YES      |                 1 | 1-有效，0-失效 |
| register_time     | datetime     | YES      | 当前时间 | 系统录入时间                                   |
