|   DATABASE    | CHARACTER SET |    COLLATION    |
|---------------|---------------|-----------------|
| maxcar_user_l | utf8          | utf8_general_ci |

## `configuration` (系统配置参数管理)

|       字段名称       |  数据类型     | 是否为空 |      默认值      |                    注释                     |
|---------------------|--------------|----------|-------------------|------------------------------------------------|
| configuration_id    | varchar(32)  | NO       |                   | 主键，唯一标识符                                 |
| market_id           | varchar(32)  | NO       |               007 | 市场id                                         |
| market_name         | varchar(50)  | YES      |       NULL        | 市场名称                                       |
| merants_id          | varchar(32)  | YES      |       NULL        | 商户id                                         |
| merants_name        | varchar(80)  | YES      |       NULL        | 商户名称                                       |
| configuration_name  | varchar(60)  | YES      |       NULL        | 名称                                           |
| configuration_key   | varchar(50)  | YES      |       NULL        | 配置key                                        |
| configuration_value | varchar(255) | YES      |       NULL        | 配置value                                      |
| configuration_desc  | varchar(50)  | YES      |       NULL        | 配置描述                                       |
| isvalid             | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version             | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time       | timestamp    | NO       | 当前时间           | 系统录入时间                                   |
| unit                | varchar(15)  | YES      |                   | 排序                                           |

## `login_log` (存放登录登出日志信息)

|   字段名称   |  数据类型  | 是否为空 |      默认值      |         注释          |
|------------|-------------|----------|-------------------|--------------------------|
| login_id   | varchar(32) | NO       |                   | 主键id              |
| market_id  | varchar(32) | YES      |     NULL          | 市场id                   |
| login_type | int(11)     | YES      |     NULL          | 登录/登出类型0登录,1登出 |
| login_date | timestamp   | NO       | 当前时间 | 登录登出时间             |
| login_ip   | varchar(20) | YES      |     NULL          | 登录登出ip               |
| user_id    | varchar(50) | YES      |     NULL          | 登录登出用户ID           |
| user_token | varchar(50) | YES      |     NULL          | 登录登出用户token        |

## `market` (市场信息表)

|         字段名称         |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|------------------------|--------------|----------|-------------------|------------------------------------------------|
| id                     | varchar(32)  | NO       |                   | 主键                                           |
| market_no              | varchar(20)  | NO       |                   | 市场编号 (唯一性索引)                            |
| name                   | varchar(40)  | NO       |                   | 市场名称                                       |
| simple_name            | varchar(40)  | NO       |                   | 市场简称                                       |
| description            | varchar(400) | YES      |        NULL       | 市场描述说明                                   |
| corporation_name       | varchar(20)  | YES      |        NULL       | 法人姓名                                       |
| corporation_id         | varchar(20)  | YES      |        NULL       | 法人身份证                                     |
| city                   | int(11)      | NO       |                   | 所在城市 (普通索引)                              |
| address                | varchar(40)  | NO       |                   | 地址                                           |
| bank                   | varchar(40)  | NO       |                   | 开户行                                         |
| account                | varchar(40)  | NO       |                   | 开户行账号                                     |
| taxno                  | varchar(20)  | NO       |                   | 纳税人识别号                                   |
| lt_lon                 | double(11,8) | YES      |        NULL       | 左上角经度                                     |
| lt_lat                 | double(11,8) | YES      |        NULL       | 左上角纬度                                     |
| rb_lon                 | double(11,8) | YES      |        NULL       | 右上角经度                                     |
| rb_lat                 | double(11,8) | YES      |        NULL       | 右上角纬度                                     |
| isvalid                | int(11)      | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version                | int(11)      | YES      |                 1 | 数据版本，防止脏写                             |
| contacts1_name         | varchar(20)  | NO       |                   | 联系人1                                        |
| contacts1_mobile       | varchar(12)  | NO       |                   | 联系人1电话                                    |
| phone                  | varchar(20)  | YES      |        NULL       | 法人代表联系电话                               |
| contacts2_name         | varchar(20)  | YES      |        NULL       | 联系人2姓名                                |
| contacts2_mobile       | varchar(12)  | YES      |        NULL       | 联系人2电话                                 |
| contacts3_name         | varchar(20)  | YES      |        NULL       | 联系人3姓名                                  |
| contacts3_mobile       | varchar(12)  | YES      |        NULL       | 联系人3电话                                      |
| contacts1_post         | varchar(20)  | YES      |        NULL       | 联系人职务                                     |
| contacts2_post         | varchar(20)  | YES      |        NULL       | 联系人2职务                                      |
| contacts3_post         | varchar(20)  | YES      |        NULL       | 联系人3职务                                      |
| contacts1_mail         | varchar(40)  | YES      |        NULL       | 联系人邮箱                                     |
| contacts2_mail         | varchar(40)  | YES      |        NULL       | 联系人2邮箱                                     |
| contacts3_mail         | varchar(40)  | YES      |        NULL       | 联系人3邮箱                                     |
| tax_name               | varchar(10)  | YES      |        NULL       | 办税人名称                                     |
| tax_phone              | varchar(20)  | YES      |        NULL       | 办税人联系电话                                 |
| copy_file              | varchar(255) | YES      |        NULL       | 市场营业执照复印件                             |
| register_time          | timestamp    | YES      | 当前时间 | 系统录入时间                                   |
| picture                | varchar(500) | YES      |        NULL       | 市场图片                                       |
| export                 | int(1)       | YES      |        NULL       | 1 默认不导   2 导出                            |
| appId                  | varchar(40)  | YES      |        NULL       | appID                                          |
| appSecret              | varchar(40)  | YES      |        NULL       | 用户名                                         |
| appId2                 | varchar(255) | YES      |        NULL       | 存放消费者公众号                               |
| appSecret2             | varchar(255) | YES      |        NULL       | 存放消费者公众号                               |
| fmapID                 | varchar(20)  | YES      |        NULL       | 室内蜂鸟地图id                                 |
| e_lon                  | double(11,8) | YES      |        NULL       |                                                |
| e_lat                  | double(11,8) | YES      |        NULL       |                                                |
| market_test            | int(11)      | YES      |        NULL       | 为真0，TUF1，无2，或者本地端1有检测2无检测     |
| logo                   | varchar(500) | YES      |        NULL       | logo                                           |
| carNumber              | varchar(50)  | YES      |        NULL       | 车辆牌照                                       |
| carManageName          | varchar(255) | YES      |        NULL       | 车管所名称                                     |
| stockControls          | int(11)      | YES      |        NULL       | 库存管制                                       |
| tenantAppId            | varchar(50)  | YES      |        NULL       | 商户公众号                                     |
| tenantAppSecret        | varchar(50)  | YES      |        NULL       | 商户公众号                                     |
| pay_wechat_mchid       | varchar(50)  | YES      |        NULL       | 微信支付商户号                                 |
| pay_wechat_key         | varchar(50)  | YES      |        NULL       | 微信支付key                                    |
| pay_wechat_appid       | varchar(50)  | YES      |        NULL       | 微信支付 appid                                 |
| marketAppId            | varchar(50)  | YES      |        NULL       | 微信公众号APPID-商户                           |
| marketAppSecret        | varchar(50)  | YES      |        NULL       | 微信公众号APPSecret-商户                       |
| province               | int(11)      | YES      |        NULL       | 省份code                                       |
| alipay_appid           | varchar(50)  | YES      |        NULL       | 支付宝支付APPId商户私钥                        |
| change_the_name_price  | int(11)      | YES      |        NULL       | 过户费                                         |
| contract_property      | varchar(500) | YES      |        NULL       | 物业合同名称及oss地址                          |
| contract_trading       | varchar(500) | YES      |        NULL       | 交易合同名称及oss地址                          |
| responsibility         | varchar(500) | YES      |        NULL       | 责任书                                         |
| supplementary          | varchar(500) | YES      |        NULL       | 补充协议                                       |
| alipay_private_key     | text         | YES      |        NULL       | 支付宝支付-商户私钥                            |
| alipay_public_key      | text         | YES      |        NULL       | 支付宝支付-支付宝公钥                          |
| quality_service_switch | tinyint(4)   | NO       |                 0 | 是否提供质保服务 1.是 0.否                     |
| quality_service_fee    | int(11)      | NO       |                 0 | 质保服务费用 单位元                            |
| display_card_template  | int(2)       | YES      |                 0 | 展示证模版 0 标准版  1自定义套打               |

## `market_map` (市场地图)

|       字段名称       |  数据类型  | 是否为空 | 默认值 |                    注释                     |
|--------------------|-------------|----------|---------|------------------------------------------------|
| id                 | varchar(32) | NO       |         | 主键id                                          |
| market_id          | varchar(32) | YES      |  NULL   | 市场id                                         |
| equipment_name_map | varchar(40) | YES      |  NULL   | 设备在地图上的名称                             |
| building_no_area   | varchar(40) | YES      |  NULL   | 某号楼中的具体区域                             |
| building_no        | varchar(20) | YES      |  NULL   | 楼号                                           |
| fmap_id            | varchar(20) | YES      |  NULL   | 室内蜂鸟地图id                                 |
| isvalid            | int(11)     | YES      |       1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |

## `notice_details` (通知详情)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                                   |
| market_id       | varchar(32)  | YES      |        NULL       | 市场id                                          |
| message_title   | varchar(50)  | NO       |                   | 消息标题                                       |
| message_content | varchar(255) | NO       |                   | 消息内容                                       |
| push_time       | datetime     | YES      | 当前时间 | 发布时间                                       |
| push_peo        | varchar(32)  | YES      |        NULL       | 发布人员                                       |
| push_status     | int(1)       | YES      |                 1 | 发布状态 1已发布0已撤回                        |
| isvalid         | int(1)       | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version         | int(11)      | YES      |                 1 | 数据版本，防止脏写                             |
| insert_time     | timestamp    | YES      | 当前时间 | 系统录入时间                                   |
| update_operator | varchar(32)  | YES      |        NULL       | 修改人名称                                     |
| insert_operator | varchar(32)  | YES      |        NULL       | 添加人名称                                     |
| update_time     | datetime     | YES      |        NULL       | 修改时间                                       |
| status          | int(1)       | YES      |        NULL       | 备用状态                                       |

## `operation_record` (数据操作记录表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| operation_id    | varchar(32)  | NO       |                   | 主键                                            |
| market_id       | varchar(32)  | YES      |      NULL         | 市场id                                          |
| user_id         | varchar(32)  | YES      |      NULL         | 用户id                                         |
| true_name       | varchar(50)  | YES      |      NULL         | 姓名                                           |
| user_name       | varchar(50)  | YES      |      NULL         | 用户名称                                       |
| operation_title | varchar(50)  | NO       |                   | 操作标题                                       |
| operation_info  | varchar(255) | YES      |      NULL         | 操作内容                                       |
| operation_type  | varchar(20)  | YES      |                 0 | 操作类型curd，增删改查                         |
| operation_url   | varchar(100) | YES      |      NULL         | 请求地址                                       |
| ind             | int(11)      | YES      |                 0 | 排序                                           |
| isvalid         | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存|
| version         | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time   | timestamp    | NO       | 当前时间 | 系统录入时间                                   |

## `organizations` (机构信息)

|    字段名称     |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|---------------|--------------|----------|-------------------|------------------------------------------------|
| org_id        | varchar(32)  | NO       |                   | 主键                                           |
| market_id     | varchar(32)  | YES      |       NULL        | 市场id                                          |
| org_name      | varchar(32)  | NO       |                   | 机构名称                                       |
| org_code      | varchar(15)  | YES      |       NULL        | 机构编码                                       |
| parent_id     | varchar(32)  | NO       |                   | 上级id                                         |
| remark        | varchar(255) | YES      |       NULL        | 备注                                           |
| isvalid       | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| version       | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time | timestamp    | NO       | 当前时间 | 系统录入时间                                   |
| ind           | int(11)      | YES      |                 0 | 排序                                           |

## `out_market_manage` (出场时间管理)

|       字段名称        |  数据类型   | 是否为空 |      默认值      |                    注释                    |
|---------------------|--------------|----------|-------------------|-----------------------------------------------|
| id                  | varchar(32)  | NO       |                   | 主键id                                         |
| market_id           | varchar(32)  | YES      |       NULL        | 市场id                                        |
| out_market_type     | varchar(255) | YES      |       NULL        | 出场时间控制 0不控制,1控制,2出场审批              |
| out_market_time     | double       | YES      |       NULL        | 允许出场时间,在不控制和出场审批的时候没有意义 |
| out_market_approval | varchar(255) | YES      |       NULL        | 出场审批预留字段                              |
| remarks             | varchar(255) | YES      |       NULL        | 备注                                          |
| isvalid             | int(2)       | YES      |                 1 | 数据有效性 0 无效 1 有效                      |
| insert_time         | timestamp    | NO       | 当前时间 | 创建时间                                      |
| update_time         | datetime     | YES      |       NULL        | 修改时间                                      |
| insert_operator     | varchar(32)  | YES      |       NULL        | 添加人                                        |
| update_operator     | varchar(32)  | YES      |       NULL        | 修改人                                        |

## `resource` (系统资源表，存放所有可操作请求地址)

|     字段名称     |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|----------------|--------------|----------|-------------------|------------------------------------------------|
| resource_id    | int(32)      | NO       |                   | 主键，唯一标识符                                 |
| parent_id      | varchar(32)  | NO       |                 0 | 父资源id                                       |
| resource_name  | varchar(50)  | NO       |                   | 资源名称                                       |
| resource_url   | varchar(100) | NO       |                 1 | 资源地址                                       |
| resource_desc  | varchar(200) | YES      |        NULL       | 资源说明                                       |
| resource_level | int(11)      | YES      |        NULL       | 资源等级默认1级                                |
| ind            | int(11)      | YES      |                 0 | 排序                                           |
| resource_type  | int(11)      | YES      |                 0 | 资源类型                                       |
| isvalid        | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存    |
| version        | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time  | timestamp    | NO       | 当前时间 | 系统录入时间                                   |

## `resource_option` (资源对应操作权限表，存放该资源可操作控制范围)

|       字段名称       |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|--------------------|--------------|----------|-------------------|------------------------------------------------|
| resource_option_id | varchar(32)  | NO       |                   | 主键，唯一标识符                                 |
| resource_id        | varchar(32)  | YES      |        NULL       | 资源id                                         |
| option_name        | varchar(15)  | NO       |                   | 操作名称                                       |
| option_type        | int(11)      | YES      |                 0 | 操作类型0按钮                                  |
| option_url         | varchar(100) | YES      |        NULL       | 请求地址                                       |
| ind                | int(11)      | YES      |                 0 | 排序                                           |
| isvalid            | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存     |
| version            | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time      | timestamp    | NO       | 当前时间 | 系统录入时间                                   |

## `role` (系统角色)

|    字段名称     |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|---------------|--------------|----------|-------------------|------------------------------------------------|
| role_id       | varchar(32)  | NO       |                   | 主键，唯一标识符                                 |
| role_name     | varchar(50)  | NO       |                   | 角色名称                                       |
| role_desc     | varchar(200) | YES      |        NULL       | 角色说明       |
| isvalid       | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存|
| version       | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time | timestamp    | NO       | 当前时间 | 系统录入时间                                   |
| market_id     | varchar(32)  | YES      |                   |                                                |

## `role_resource` (存放角色和权限关系信息)

|       字段名称        |  数据类型  | 是否为空 | 默认值 | 注释 |
|---------------------|-------------|----------|---------|---------|
| role_permissions_id | varchar(32) | NO       |         | 主键id  |
| role_id             | varchar(32) | NO       |         | 角色id  |
| resource_id         | varchar(32) | NO       |         | 资源id  |

## `role_resource_option` (角色对应数据操作权限表，存放该角色某个资源可操作数据范围)

|         字段名称          |  数据类型  | 是否为空 | 默认值 |        注释         |
|-------------------------|-------------|----------|---------|------------------------|
| role_resource_option_id | varchar(32) | NO       |         | 唯一标识符|
| role_id                 | varchar(32) | YES      |   NULL  | 角色id                 |
| resource_option_id      | varchar(32) | NO       |         | 资源操作id             |

## `search_car` (消费者心仪车记录表)

|   字段名称   |  数据类型   | 是否为空 | 默认值 | 注释 |
|------------|--------------|----------|---------|---------|
| id         | varchar(50)  | NO       |         | 主键    |
| user_name  | varchar(255) | YES      |  NULL   | 用户名   |
| phone      | varchar(255) | YES      |  NULL   | 手机号码 |
| conditions | varchar(255) | YES      |  NULL   |         |

## `staff` (车商或市场员工表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |             注释             |
|-----------------|--------------|----------|-------------------|---------------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                          |
| group_id        | varchar(32)  | YES      |       NULL        | 所属车商或部门id                |
| staff_type      | int(1)       | YES      |       NULL        | 员工类型 1市场员工;2车商员工    |
| staff_number    | varchar(30)  | YES      |       NULL        | 员工编号                        |
| staff_name      | varchar(30)  | YES      |       NULL        | 员工姓名                        |
| staff_role      | varchar(32)  | YES      |       NULL        | 员工角色                        |
| staff_phone     | varchar(20)  | YES      |       NULL       | 员工手机号                      |
| is_valid        | int(1)       | YES      |                 1 | 是否可用,0不可用;1可用          |
| insert_time     | datetime     | YES      | 当前时间 | 新增时间                        |
| insert_operator | varchar(32)  | YES      |       NULL        | 新增人                          |
| update_time     | datetime     | YES      |       NULL        | 更新时间                        |
| update_operator | varchar(32)  | YES      |       NULL        | 更新人                          |
| remark          | varchar(255) | YES      |       NULL        | 备注                            |
| market_id       | varchar(32)  | YES      |       NULL        | 市场id                           |
| open_id         | varchar(100) | YES      |       NULL        | 与微信关联字段                  |
| weixin_id       | varchar(40)  | YES      |       NULL        | 微信id                          |
| id_card         | varchar(20)  | YES      |       NULL        | 员工身份证号                    |
| is_admin        | int(1)       | YES      |                   | 是否商户管理员(0:员工,1:管理员) |
| wx_nick_name    | varchar(20)  | YES      |       NULL        | 微信昵称                        |
| staff_sex       | int(11)      | YES      |                   | 员工性别（0:女，1：男）         |

## `user` (普通用户信息)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| user_id         | varchar(32)  | NO       |                   | 唯一标识符                                     |
| user_name       | varchar(15)  | NO       |                   | 用户名                                         |
| true_name       | varchar(15)  | NO       |                   | 姓名                                           |
| market_id       | varchar(32)  | YES      |       NULL        | 市场id                                         |
| org_id          | varchar(32)  | YES      |       NULL        | 机构id                                         |
| staff_id        | varchar(32)  | NO       |                   | 市场员工id                                     |
| user_pwd        | varchar(50)  | NO       |                   | 密码                                           |
| user_phone      | varchar(11)  | NO       |                   | 手机号码                                       |
| user_email      | varchar(50)  | YES      |       NULL        | 电子邮箱                                       |
| manager_flag    | smallint(6)  | YES      |                 0 | 是否管理员0超级管理员，1不是，2市场管理员      |
| remark          | varchar(255) | YES      |       NULL        | 备注                                           |
| last_login_ip   | varchar(50)  | YES      |       NULL        | 最后登录ip                                     |
| last_login_time | datetime     | YES      |       NULL        | 最后登录时间                                   |
| isvalid         | int(11)      | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存|
| version         | int(11)      | NO       |                 1 | 数据版本，防止脏写                             |
| register_time   | timestamp    | NO       | 当前时间 | 系统录入时间                                   |
| ind             | int(11)      | YES      |                 0 | 排序                                           |
| user_code       | varchar(50)  | YES      |       NULL        | 用户编号                                       |

## `user_role` (存储用户和角色对应关系表)

|    字段名称    |  数据类型  | 是否为空 | 默认值 | 注释 |
|--------------|-------------|----------|---------|---------|
| user_role_id | varchar(32) | NO       |         | 主键    |
| user_id      | varchar(32) | NO       |         | 用户id  |
| role_id      | varchar(32) | NO       |         | 角色id  |
