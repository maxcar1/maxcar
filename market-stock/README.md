|    DATABASE    | CHARACTER SET |    COLLATION    |
|----------------|---------------|-----------------|
| maxcar_stock_l | utf8          | utf8_general_ci |

### `barrier` (道闸)

|      字段名称      |  数据类型   | 是否为空 |      默认值      |   注释                          |
|------------------|--------------|----------|-------------------|------------------------------------|
| id               | int(36)      | NO       |                   | 主键id                            |
| barrier_id       | varchar(36)  | NO       |                   | 道闸ID                            |
| gateway_ip       | varchar(20)  | NO       |                   | 网关IP                            |
| subnet_mask      | varchar(30)  | NO       |                   | 子网掩码                           |
| mac_address      | varchar(100) | NO       |                   | 物理地址                           |
| client_ip        | varchar(50)  | NO       |                   | 客户端ip                           |
| server_ip        | varchar(50)  | NO       |                   | 服务器ip                           |
| client_port      | varchar(20)  | NO       |              9090 | 客户端端口                          |
| server_port      | varchar(20)  | NO       |              8080 | 服务器端口                          |
| barrier_type     | char(1)      | NO       |                   | 道闸类型（0：内场，1：外场）          |
| barrier_position | varchar(100) | NO       |                   | 道闸位置                           |
| in_out_car       | varchar(30)  | YES      |        NULL       | 11.已入场 12:已出场 13:已售出 14:售出已出场 
|                  |              |          |                   | 15:已下架 16:市场员工车 17:商户员工车 18:无|
| in_out_type      | int(1)       | NO       |                 0 |0:入口,1:出口                     |
| park_limit       | varchar(1)   | NO       |                   | 车位限制(0:不限制，1:限制)            |
| isvalid          | int(11)      | NO       |                 1 | 1-有效，0-失效 |
| status           | varchar(10)  | YES      |        NULL       | 允许出入车辆(0:不控制,1:控制,2白名单，3黑名单，4不回复不控制)|
| remark           | varchar(30)  | YES      |        NULL       | 备用字段|
| update_time      | datetime     | YES      |        NULL       | 更新时间|
| create_time      | timestamp    | YES      | 当前时间 | 创建时间|
| static_speech    | varchar(4)   | NO       |                   | 静态欢迎词|
| market_id        | varchar(3)   | NO       |                   | 市场id|
| mqtt_topic       | varchar(20)  | YES      |        NULL       | 道闸监听主题|
| barrier_mac      | varchar(30)  | NO       |                   | 岗亭mac地址|

### `barrier_camera` (道闸照相)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |        注释         |
|-----------------|--------------|----------|-------------------|------------------------|
| id              | varchar(32)  | NO       |                   | 主键                   |
| market_id       | varchar(10)  | YES      |       NULL        | 市场id                 |
| barrier_id      | varchar(50)  | YES      |       NULL        | 道闸id                 |
| device_ip       | varchar(20)  | YES      |       NULL        | 设备ip                 |
| device_port     | varchar(5)   | YES      |       NULL        | 设备端口号             |
| user_name       | varchar(10)  | YES      |       NULL        | 设备登录用户名         |
| password        | varchar(20)  | YES      |       NULL        | 设备登录密码           |
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用 |
| insert_time     | datetime     | YES      | 当前时间 | 新增时间               |
| insert_operator | varchar(32)  | YES      |       NULL        | 新增人                 |
| update_time     | datetime     | YES      |       NULL        | 更新时间               |
| update_operator | varchar(32)  | YES      |       NULL        | 更新人                 |
| remark          | varchar(255) | YES      |       NULL        | 备注                   |

### `barrier_control_car` (道闸控制车辆添加黑白名单)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | varchar(32)  | NO       |                   |                                                |
| barrier_id      | varchar(36)  | YES      |       NULL        | 道闸id                                         |
| market_id       | varchar(3)   | YES      |       NULL        | 市场id                                         |
| control_type    | int(1)       | YES      |       NULL        | 控制类型 1代表控制某个车辆  2控制的某个商户|
| list_type       | int(1)       | YES      |       NULL        | 名单类型  1代表白名单 2代表黑名单|
| car_id          | varchar(32)  | YES      |       NULL        | 车辆主键id                                     |
| vin             | varchar(50)  | YES      |       NULL        | 车架号                                         |
| rfid            | varchar(50)  | YES      |       NULL        | 车辆标签                                       |
| tenant_id       | varchar(32)  | YES      |       NULL        | 商户id                                         |
| isvalid         | int(1)       | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| remark          | varchar(255) | YES      |       NULL        | 备注|
| insert_time     | timestamp    | NO       | 当前时间 | 新增时间|
| insert_operator | varchar(32)  | YES      |       NULL        | 新增人|
| update_time     | datetime     | YES      |       NULL        | 更新时间|
| update_operator | varchar(32)  | YES      |       NULL        | 更新人|

### `car` (车辆基础表)

|        字段名称        |  数据类型   | 是否为空 |      默认值      |            注释             |
|----------------------|--------------|----------|-------------------|--------------------------------|
| id                   | varchar(32)  | NO       |                   | 主键id                         |
| car_no               | varchar(35)  | NO       |                   | 车辆编号                       |
| vin                  | varchar(50)  | NO       |                   | vin码 (普通索引)               |
| rfid                 | varchar(50)  | NO       |                   | rfid (普通索引)                |
| tenant               | varchar(32)  | NO       |                   | 商户id                         |
| area_id              | varchar(32)  | YES      |        NULL       | 区域                           |
| is_new_car           | int(1)       | YES      |        NULL       | 是否新车 0：新车 1：旧车       |
| stock_status         | int(11)      | YES      |        NULL       | 库存状态 -1.删除 1:在场 2:在内场 3:出场 |
|                      |              |          |                   | 4:售出未出场 5.售出已出场 6:出场超时|
| car_status           | int(1)       | YES      |        NULL       | 车辆状态 1质押 2 非质押        |
| car_type             | int(1)       | YES      |                 1 | 车辆类型 1.商品车 2市场车 3商户车|
| register_time        | datetime     | YES      |        NULL       | 录入时间                       |
| initial_licence_time | datetime     | YES      |        NULL       | 初次上牌时间                   |
| isvalid              | int(2)       | YES      |                 1 | 数据有效性 1 有效 0 无效       |
| staff_id             | varchar(32)  | YES      |        NULL       | 市场员工或者商户员工id         |
| market_id            | varchar(3)   | YES      |        NULL       | 市场id                         |
| update_operator      | varchar(32)  | YES      |        NULL       | 更新人                         |
| inster_operator      | varchar(32)  | YES      |        NULL       | 新增人                          |
| insert_time          | timestamp    | NO       | 当前时间 | 新增时间                          |
| update_time          | timestamp    | NO       | 当前时间 | 更新时间                        |
| owner_name           | varchar(255) | YES      |        NULL       | 车主姓名                       |
| owner_idcard         | varchar(255) | YES      |        NULL       | 车主身份证号                   |
| no                   | varchar(32)  | YES      |        NULL       | 车牌号                         |
| remark               | varchar(255) | YES      |        NULL       | 备注                           |
| taobao_id            | varchar(100) | YES      |        NULL       | 淘宝返回id                     |
| taobao_url           | varchar(100) | YES      |        NULL       | 淘宝返回店铺地址               |
| equip_id             | varchar(32)  | YES      |        NULL       |                              |
| engine_no            | varchar(20)  | YES      |        NULL       | 发动机号                       |
| check_result         | int(1)       | YES      |        NULL       | 是否维珍验车过 1验过 0 or null 未验过|
| limit_status         | int(1)       | YES      |        NULL       | 限制出场状态 1:不限制 2:限制   |
| stock_day            | int(22)      | YES      |        NULL       | 库存天数                       |
| driving_licence_url  | varchar(256) | YES      |        NULL       | 行驶证                         |
| is_publish           | int(2)       | YES      |                 1 | 1未发布 2已发布                |

### `car_base` (车辆详情表)

|            字段名称            |  数据类型  | 是否为空 |      默认值      |            注释             |
|------------------------------|-------------|----------|-------------------|--------------------------------|
| id                           | varchar(32) | NO       |                   | 主键id                         |
| brand_name                   | varchar(20) | YES      |       NULL        | 品牌名称                       |
| brand_code                   | varchar(50) | YES      |       NULL        | 品牌code                       |
| series_name                  | varchar(50) | YES      |       NULL        | 车系名称                       |
| series_code                  | varchar(50) | YES      |       NULL        | 车系code                       |
| model_year                   | varchar(5)  | YES      |       NULL        | 年款                           |
| model_name                   | varchar(50) | YES      |       NULL        | 车型名称                       |
| model_code                   | varchar(50) | YES      |       NULL        | 车型code                       |
| mileage                      | int(7)      | YES      |       NULL        | 公里数                         |
| evaluate_price               | double(9,2) | YES      |       NULL        | 估价                           |
| new_price                    | double(9,2) | YES      |       NULL        | 新车价                         |
| market_price                 | double(9,2) | YES      |       NULL        | 市场价                         |
| color                        | varchar(10) | YES      |       NULL        | 颜色                           |
| engine_volume_unitl          | double      | YES      |       NULL        | 排量                           |
| environmental_standards      | varchar(20) | YES      |       NULL        | 排放标准 1.国三以上 2.国四以上 3.国五以上|
| gear_box                     | varchar(30) | YES      |       NULL        | 变速箱类似于（五档自动一体）   |
| seat_number                  | int(1)      | YES      |       NULL        | 座椅数                         |
| fuel_form                    | varchar(15) | YES      |       NULL        | 燃油方式  1.汽油 2.茶油 3.纯电 4.混动|
| fuel_number                  | int(1)      | YES      |       NULL        | 油标                           |
| level                        | varchar(30) | YES      |       NULL        | 车型：1.SUV 2.三厢轿车 3.两厢轿车|
|                              |             |          |                   | 4.MVP 5.跑车 6.面包车 7.皮卡 |
| model_data                   | text        | YES      |       NULL        | 车型数据json                   |
| remark                       | text        | YES      |       NULL        | 备注                          |
| check_company_id             | varchar(32) | YES      |       NULL        | 检测公司                       |
| inster_operator              | varchar(32) | YES      |       NULL        | 新增人                         |
| update_operator              | varchar(32) | YES      |       NULL        | 更新人                         |
| insert_time                  | timestamp   | NO       | 当前时间 | 新增时间                        |
| update_time                  | timestamp   | NO       | 当前时间 | 更新时间                        |
| accident_type                | int(11)     | YES      |       NULL        | 事故车类型                     |
| reserve_price                | double      | YES      |       NULL        | 低价                           |
| initial_licence_time         | datetime    | YES      |       NULL        | 初次上牌时间                   |
| displacement                 | float(11,0) | YES      |       NULL        |                               |
| air_conditioner_control_type | varchar(50) | YES      |       NULL        | 手动档 自动档                  |
| attribution                  | int(11)     | YES      |       NULL        | 车辆归属地                     |
| seats                        | int(2)      | YES      |       NULL        |                              |

### `car_channel` (车辆渠道)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |         注释          |
|-----------------|--------------|----------|-------------------|--------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                    |
| market_id       | varchar(32)  | YES      |        NULL       | 市场id                    |
| channel_no      | varchar(50)  | YES      |        NULL       | 渠道编号                 |
| channel_name    | varchar(100) | YES      |        NULL       | 渠道名称                 |
| remarks         | varchar(255) | YES      |        NULL       | 备注                     |
| isvalid         | int(2)       | YES      |                 1 | 数据有效性 1 有效 0 无效 |
| insert_time     | timestamp    | NO       | 当前时间 | 创建时间                 |
| update_time     | datetime     | YES      |        NULL       | 修改时间                 |
| insert_operator | varchar(32)  | YES      |        NULL       | 添加人                   |
| update_operator | varchar(32)  | YES      |        NULL       | 修改人                   |

### `car_channel_rel` (车辆渠道关系表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |            注释             |
|-----------------|--------------|----------|-------------------|--------------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                          |
| channel_id      | varchar(32)  | NO       |                   | 车辆渠道id                     |
| car_id          | varchar(32)  | NO       |                   | 车辆id                         |
| shelf_status    | int(2)       | YES      |        NULL       | 上/下架状态(2已上架 1:上传淘宝,未上架 0:未上传淘宝,未上架)|
| remarks         | varchar(255) | YES      |        NULL       | 备注                           |
| isvalid         | int(2)       | YES      |                 1 | 数据有效性 1 有效 0 无效       |
| insert_time     | timestamp    | NO       | 当前时间 | 创建时间                       |
| update_time     | datetime     | YES      |        NULL       | 最后修改时间                   |
| insert_operator | varchar(32)  | YES      |        NULL       | 添加人                         |
| update_operator | varchar(32)  | YES      |        NULL       | 最后修改人                     |

### `car_check` (车辆检测信息)

|   字段名称    |  数据类型  | 是否为空 | 默认值 | 注释  |
|-------------|-------------|----------|---------|----------|
| id          | varchar(32) | NO       |         | 主键id    |
| company_id  | varchar(32) | YES      |   NULL  | 公司code |
| data        | text        | YES      |   NULL  | 检测数据 |
| describes   | int(1)      | YES      |   NULL  | 描述     |
| car_id      | varchar(32) | YES      |   NULL  | car表id  |
| vin         | varchar(32) | YES      |   NULL  |          |
| insert_time | datetime    | YES      |   NULL  | 新增时间  |

### `car_pic` (车辆图片)

|     字段名称      |  数据类型   | 是否为空 |      默认值      | 注释  |
|-----------------|--------------|----------|-------------------|----------|
| id              | varchar(32)  | NO       |                   | 主键id|
| car_id          | varchar(32)  | YES      |         NULL      | 车id (普通索引)|
| src             | varchar(255) | YES      |         NULL      | 图片的地址|
| type            | int(11)      | YES      |         NULL      | 图片类型 |
| inster_operator | varchar(32)  | YES      |         NULL      | 新增人|
| update_operator | varchar(32)  | YES      |         NULL      | 更新人|
| insert_time     | timestamp    | NO       | 当前时间 | 新增时间|
| update_time     | datetime     | YES      |         NULL      | 更新时间|

### `car_record` (出入场记录)

|    字段名称    |  数据类型   | 是否为空 |      默认值      |          注释           |
|--------------|--------------|----------|-------------------|----------------------------|
| id           | varchar(32)  | NO       |                   | 主键id                     |
| market_id    | varchar(32)  | YES      |        NULL       | 市场id                     |
| vin          | varchar(32)  | YES      |        NULL       | 车架号                     |
| car_no       | varchar(50)  | YES      |        NULL       | 车牌号                     |
| rfid         | varchar(50)  | YES      |        NULL       | rfid                       |
| channel      | varchar(32)  | YES      |        NULL       | 道闸的编号                 |
| type         | int(11)      | YES      |        NULL       | 车辆出入类型 0 入场 1 出场 |
| pic          | varchar(400) | YES      |        NULL       | 道闸照片id                 |
| insert_time  | timestamp    | NO       | 当前时间 | 新增时间                   |
| channel_name | varchar(20)  | YES      |        NULL       | 道闸名称                   |

### `car_review` (车辆审核表)

|   字段名称    |  数据类型   | 是否为空 | 默认值 |            注释     |
|-------------|--------------|----------|---------|------------------------|
| id          | varchar(32)  | NO       |         | 主键id             |
| car_id      | varchar(64)  | YES      |  NULL   | car表id               |
| market_id   | varchar(11)  | YES      |  NULL   | 市场id                  |
| user_id     | varchar(32)  | YES      |  NULL   | 用户id                 |
| out_reason  | varchar(255) | YES      |  NULL   | 出厂原因                 |
| back_time   | datetime     | YES      |  NULL   | 返厂时间                 |
| is_pass     | int(11)      | YES      |  NULL   | 是否通过  0未审核 1:通过 2拒绝|
| reason_desc | varchar(64)  | YES      |  NULL   | 原因备注                 |
| is_pledge   | int(11)      | YES      |  NULL   | 是否质押车 1是 2不是      |
| insert_time | datetime     | YES      |  NULL   | 插入时间                 |
| is_valid    | int(11)      | YES      |  NULL   | 是否有效  0否 1是        |
| step_level  | int(2)       | YES      |       0 | 流程步骤                 |
| is_complete | int(2)       | YES      |       0 | 是否完成 0:未完成 1:已完成 |

### `check_company` (检测公司表)

|    字段名称    |  数据类型  | 是否为空 | 默认值 | 注释  |
|--------------|-------------|----------|---------|----------|
| id           | varchar(32) | NO       |         | 主键id    |
| company_id   | varchar(32) | YES      |  NULL   | 公司编号 |
| company_name | varchar(32) | YES      |  NULL   | 公司名称 |

### `flow_step` (流程步骤)

|     字段名称     |  数据类型  | 是否为空 | 默认值 |         注释         |
|----------------|-------------|----------|---------|-------------------------|
| id             | int(11)     | NO       |         | 主键id                   |
| flow_name      | varchar(11) | NO       |         | 流程名称                |
| is_need_review | int(2)      | NO       |       1 | 是否需要审核            |
| market_id      | varchar(11) | YES      |         | 市场id                  |
| review_type    | int(2)      | NO       |         | 审批方式0:自动   1:手动 |
| code           | int(11)     | NO       |         | 审核类型                |
| insert_time    | datetime    | YES      | 当前时间 | 插入时间                |
| is_valid       | int(2)      | YES      |         | 是否有效                |

### `review_detail`
审核详情表

|      字段名称      |  数据类型  | 是否为空 | KEY | 默认值 | CHARACTER SET |    COLLATION    |            注释             |
|------------------|-------------|----------|-----|---------|---------------|-----------------|--------------------------------|
| id               | int(11)     | NO       | PRI |         |               |                 |                                |
| review_id        | varchar(32) | YES      |     |         | utf8          | utf8_general_ci | 审核id                         |
| review_person_id | varchar(32) | YES      |     |         | utf8          | utf8_general_ci | 审核人id                       |
| review_result    | int(2)      | YES      |     |         |               |                 | 审核结果 0未审核   1审核通过   |
|                  |             |          |     |         |               |                 | 2审核不通过                    |
| review_desc      | varchar(32) | YES      |     |         | utf8          | utf8_general_ci | 审核备注                       |
| level            | int(2)      | YES      |     |         |               |                 | 等级                           |
| insert_time      | datetime    | YES      |     |         |               |                 |                                |

### `review_step`
步骤表

|      字段名称      |  数据类型  | 是否为空 | KEY | 默认值 | CHARACTER SET |    COLLATION    |            注释             |
|------------------|-------------|----------|-----|---------|---------------|-----------------|--------------------------------|
| id               | int(11)     | NO       | PRI |         |               |                 |                                |
| step_name        | varchar(32) | NO       |     |         | utf8          | utf8_general_ci | 步骤名称                       |
| review_person_id | varchar(32) | YES      |     |         | utf8          | utf8_general_ci | 审核人id                       |
| org_id           | varchar(32) | YES      |     |         | utf8          | utf8_general_ci | 部门id                         |
| level            | int(2)      | YES      |     |         |               |                 | 审核等级                       |
| type             | int(2)      | YES      |     |         |               |                 | 审核方式1或签     2会签        |
| apply_type       | int(2)      | YES      |     |         |               |                 | 申请类型1:质押车出厂申请       |
|                  |             |          |     |         |               |                 | 2:非质押车出厂申请             |
| is_need_review   | int(2)      | YES      |     |       1 |               |                 | 是否需要审核1：需要  0:不需要  |
| market_id        | varchar(11) | YES      |     |         | utf8          | utf8_general_ci | 市场id                         |

### `shopping_guide` (导购统计)

|     字段名称      |   数据类型   | 是否为空 |      默认值      |         注释         |
|-----------------|---------------|----------|-------------------|-------------------------|
| id              | varchar(32)   | NO       |                   | 主键                    |
| head_portrait   | varchar(200)  | YES      |         NULL      | 头像                    |
| nick_name       | varchar(50)   | YES      |         NULL      | 昵称                    |
| brand           | varchar(100)  | YES      |         NULL      | 品牌                    |
| tenant_id       | varchar(10)   | YES      |         NULL      | 商户                    |
| car_type        | int(11)       | YES      |         NULL      | 车型                    |
| market_price    | decimal(10,2) | YES      |         NULL      | 市场价格                |
| view_times      | int(11)       | YES      |         NULL      | 浏览次数                |
| navigate        | int(1)        | YES      |         NULL      | 是否导航  0不是;1是     |
| is_valid        | int(1)        | YES      |         NULL      | 是否可用  0不可用;1可用 |
| insert_time     | datetime      | YES      | 当前时间 | 新增时间                |
| insert_operator | varchar(32)   | YES      |         NULL      | 新增人                  |
| update_time     | datetime      | YES      |         NULL      | 更新时间                |
| update_operator | varchar(32)   | YES      |         NULL      | 更新人                  |
| remark          | varchar(255)  | YES      |         NULL      | 备注                    |

### `stock_carbrighicon` (库存车图标)

|   字段名称    |  数据类型   | 是否为空 |      默认值      | 注释 |
|-------------|--------------|----------|-------------------|---------|
| id          | varchar(15)  | NO       |                   | 主键id   |
| title       | varchar(40)  | NO       |                   | 标题  |
| url         | varchar(100) | NO       |                   | url    |
| field       | varchar(40)  | YES      |      NULL         | 字段    |
| create_time | timestamp    | YES      | 当前时间 | 新增时间  |
| update_time | timestamp    | YES      | 当前时间 | 更新时间 |
| orders      | int(11)      | YES      |      NULL         |     |

### `stock_equip` (库存设备)

|     字段名称      |  数据类型  | 是否为空 |      默认值      |            注释             |
|-----------------|-------------|----------|-------------------|--------------------------------|
| id              | varchar(32) | NO       |                   | 主键id                         |
| no              | varchar(30) | NO       |                   | 设备编号                       |
| model           | varchar(20) | NO       |                   | 型号                           |
| market          | varchar(32) | YES      |      NULL         |                              |
| start_time      | datetime    | NO       |                   | 启用时间                       |
| deprecated_time | datetime    | YES      |      NULL         | 弃用时间                       |
| status          | int(2)      | NO       |                   | 1-正常；2-维修；3-报废         |
| isbind          | int(11)     | YES      |      NULL         | 0和null表示没有绑定 1表示该设备已经绑定|
| version         | int(11)     | NO       |                   | 数据版本号，防止脏写           |
| rfid            | varchar(40) | YES      |      NULL         |                                |
| insert_time     | timestamp   | YES      | 当前时间 | 新增时间                        |
| update_time     | timestamp   | YES      | 当前时间 | 更新时间                        |

### `stock_stats` (库存统计表)

|         字段名称         |  数据类型  | 是否为空 |      默认值      |        注释         |
|------------------------|-------------|----------|-------------------|------------------------|
| id                     | bigint(20)  | NO       |                   | 主键                   |
| tenant_id              | varchar(32) | NO       |                   | 商户id                 |
| market_id              | varchar(32) | NO       |                   | 市场id                 |
| add_up_stock           | int(11)     | NO       |                 0 | 累计库存               |
| venue_stock            | int(11)     | NO       |                 0 | 场内库存               |
| outside_stock          | int(11)     | NO       |                 0 | 场外库存               |
| sold_num               | int(11)     | NO       |                 0 | 已售数量               |
| authenticated_num      | int(11)     | NO       |                 0 | 认证数量               |
| pledge_num             | int(11)     | NO       |                 0 | 质押数量               |
| add_up_stock_value     | double(9,2) | NO       |              0.00 | 累计库存总价值         |
| current_stock_value    | double(9,2) | NO       |              0.00 | 当前库存价值           |
| add_up_trade_value     | double(9,2) | NO       |              0.00 | 当前累计总价值         |
| pledge_value           | double(9,2) | NO       |              0.00 | 质押车辆总价值         |
| add_up_avg_stock_days  | int(11)     | NO       |                 0 | 累计平均库存天数       |
| current_avg_stock_days | int(11)     | NO       |                 0 | 当前平均库存天数       |
| avg_trade_value        | double(9,2) | NO       |              0.00 | 平均交易价值           |
| carport_satur          | tinyint(4)  | NO       |                 0 | 车位饱和度 单位.百分比 |
| insert_time            | datetime    | YES      | 当前时间 | 创建时间               |
| update_time            | datetime    | YES      | 当前时间 | 修改时间               |

### `stock_type` (库存类型)

|   字段名称   |  数据类型  | 是否为空 | 默认值 |     注释      |
|------------|-------------|----------|---------|------------------|
| id         | varchar(32) | NO       |         | 主键             |
| vin        | varchar(30) | YES      |   NULL  | vin              |
| car_id     | varchar(15) | YES      |   NULL  | car表id          |
| stock_type | int(1)      | NO       |         | 1: 库存  2：库容 |

### `wish_list` (心愿单)

|   字段名称    |  数据类型  | 是否为空 | 默认值 |    注释     |
|-------------|-------------|----------|---------|----------------|
| id          | int(32)     | NO       |         | 主键id         |
| car_id      | varchar(32) | YES      |   NULL  | 车id           |
| market_id   | varchar(11) | YES      |   NULL  | 市场id         |
| ticket      | text        | YES      |   NULL  | 微信返回ticket |
| user_id     | varchar(32) | YES      |   NULL  | 用户id         |
| is_valid    | int(2)      | YES      |       0 | 是否有效       |
| insert_time | datetime    | YES      |   NULL  | 插入时间       |
