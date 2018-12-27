|    DATABASE     | CHARACTER SET |    COLLATION    |
|-----------------|---------------|-----------------|
| maxcar_tenant_l | utf8          | utf8_general_ci |

### `integral_detail` (积分详情表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | varchar(32)  | NO       |                   | 主键                                           |
| tenant_no       | varchar(32)  | NO       |                   | 商户号                                         |
| tenant_name     | varchar(100) | NO       |                   | 商户名称                                       |
| check_score     | double       | NO       |                 0 | 考核分数                                       |
| check_items     | varchar(200) | NO       |                   | 考核事项                                       |
| check_time      | datetime     | NO       |                   | 考核时间                                       |
| market_id       | varchar(32)  | NO       |                   | 市场                                           |
| region          | varchar(20)  | NO       |                   | 所属区域                                       |
| check_id        | varchar(32)  | YES      |      NULL         | 审核id                                        |
| type            | int(1)       | NO       |                 1 | 1:手动，2:自动                                 |
| base_score      | int(11)      | NO       |               100 | 初始默认分数                                   |
| status          | varchar(5)   | YES      |      NULL         | 状态                                          |
| remark          | varchar(255) | YES      |      NULL         | 备注                                           |
| isvalid         | int(1)       | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| insert_time     | timestamp    | NO       | 当前时间           | 数据插入时间                                    |
| insert_operator | varchar(32)  | YES      |      NULL         | 新增操作者                                     |
| update_time     | datetime     | YES      |      NULL         | 更新时间                                       |
| update_operator | varchar(32)  | YES      |      NULL         | 更新操作者                                     |

### `integral_evaluation` (积分规则表)

|       字段名称        |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|---------------------|--------------|----------|-------------------|------------------------------------------------|
| id                  | varchar(32)  | NO       |                   | 主键id                                         |
| check_items         | varchar(200) | NO       |                   | 具体标准                                       |
| scores_add_subtract | int(1)       | NO       |                   | 分数加减(1加，2减)                             |
| scope_up            | varchar(20)  | NO       |                   | 分数范围                                       |
| scope_down          | varchar(20)  | NO       |                   | 分数范围                                       |
| market_id           | varchar(32)  | NO       |                   | 市场id                                        |
| status              | int(1)       | YES      |        NULL       | 状态，预留                                     |
| remark              | varchar(255) | YES      |        NULL       | 备注                                           |
| isvalid             | int(1)       | NO       |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| insert_time         | timestamp    | NO       | 当前时间 | 数据新增时间                                     |
| insert_operator     | varchar(32)  | YES      |        NULL       | 新增操作者                                       |
| update_time         | datetime     | YES      |        NULL       | 更新时间                                       |
| update_operator     | varchar(32)  | YES      |        NULL       | 更新操作者                                      |

### `integral_statistics` (积分统计表)

|     字段名称      |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------|--------------|----------|-------------------|------------------------------------------------|
| id              | varchar(32)  | NO       |                   | 主键id                                         |
| tenant_no       | varchar(20)  | YES      |       NULL        | (此字段作废)                                   |
| tenant_name     | varchar(50)  | YES      |       NULL        | 商户名称                                       |
| time            | date         | YES      |       NULL        | 统计时间                                       |
| count_score     | int(11)      | YES      |       NULL        | 统计分数                                       |
| area            | varchar(20)  | YES      |       NULL        | 区域                                           |
| level           | varchar(15)  | YES      |       NULL        | d等级                                          |
| market_id       | varchar(32)  | YES      |       NULL        | 市场id                                         |
| isvalid         | int(1)       | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| remark          | varchar(255) | YES      |       NULL        | 备注                                           |
| insert_time     | timestamp    | NO       | 当前时间           | 数据新增时间                                     |
| insert_operator | varchar(32)  | YES      |       NULL        | 新增操作者                                      |
| update_time     | datetime     | YES      |       NULL        | 更新时间                                    |
| update_operator | varchar(32)  | YES      |       NULL        | 更新操作者                                     |

### `user_tenant` (商户表)

|        字段名称         |  数据类型   | 是否为空 |      默认值      |                    注释                     |
|-----------------------|--------------|----------|-------------------|------------------------------------------------|
| id                    | varchar(32)  | NO       |                   | 主键                                       |
| tenant_no             | varchar(32)  | YES      |       NULL        | 商户编号（此字段只对商户二维码有用）              |
| tenant_name           | varchar(100) | NO       |                   | 商户名称                                       |
| tenant_type           | varchar(10)  | NO       |                   | 商户类型 1.经纪人 2.经纪公司 3.经销公司 4.新车经销商|
| register_time         | datetime     | YES      |       NULL        | 商户注册时间                                   |
| enter_system_time     | datetime     | YES      |       NULL        | 录入我们系统的时间                             |
| corporate_name        | varchar(50)  | YES      |       NULL        | 法人姓名                                       |
| corporate_id_card     | varchar(20)  | YES      |       NULL        | 法人身份证                                     |
| licence_no            | varchar(20)  | YES      |       NULL        | 营业执照编号，或组织机构代码证                 |
| tenant_address        | varchar(40)  | YES      |       NULL        | 商户注册地址                                   |
| tenant_phone          | varchar(20)  | YES      |       NULL        | 商户联系电话                                   |
| contact_name          | varchar(36)  | YES      |       NULL        | 商户联系人                                     |
| contact_mobile        | varchar(15)  | YES      |       NULL        | 商户联系人手机号码                             |
| contact_duty          | varchar(50)  | YES      |       NULL        | 职务                                           |
| contact_email         | varchar(36)  | YES      |       NULL        | 邮箱                                           |
| copy_business_license | varchar(200) | YES      |       NULL        | 营业执照复印件  type= 2，经纪人类型不传           |
| company               | varchar(50)  | YES      |       NULL        | 公司名称                                       |
| market_id             | varchar(10)  | YES      |       NULL        | 市场id                                        |
| Integral              | varchar(10)  | YES      |       NULL        | 积分                                           |
| tenant_shop_id        | varchar(32)  | YES      |       NULL        | 商铺编号                                       |
| check_in_time         | datetime     | YES      |       NULL        | 初次入住时间                                   |
| isvalid               | int(1)       | YES      |                 1 | 1-有效，0-失效，不能再使用，只作为历史信息保存 |
| remark                | varchar(255) | YES      |       NULL        | 备注                                          |
| insert_time           | timestamp    | NO       | 当前时间           | 数据插入时间                                    |
| insert_operator       | varchar(32)  | YES      |       NULL        | 新增操作者                                     |
| update_time           | datetime     | YES      |       NULL        | 更新时间                                        |
| update_operator       | varchar(32)  | YES      |       NULL        | 更新操作者                                      |
| corporate_photo_face  | varchar(200) | YES      |       NULL        | 法人正面照片                                   |
| corporate_photo_back  | varchar(200) | YES      |       NULL        | 法人反面照片                                   |
| corporate_phone       | varchar(50)  | YES      |       NULL        | 法人联系方式                                   |
| contact_photo_face    | varchar(200) | YES      |       NULL        | 负责人正面照片                                 |
| contact_photo_back    | varchar(200) | YES      |       NULL        | 负责人反面照片                                 |
| tenant_shop_name      | varchar(100) | YES      |       NULL        | 商铺名称                                       |
| tenant_area           | varchar(100) | NO       |                   | 商户所属区域                                   |
| contact_id_card       | varchar(20)  | YES      |       NULL        | 法人身份证号                                   |
| status                | varchar(20)  | YES      |                 1 | 1：正常，2：终止，4：已到期                    |
| catering_image        | varchar(300) | YES      |       NULL        | 残影服务许可证                                 |
| health_image          | varchar(300) | YES      |       NULL        | 健康证                                         |
