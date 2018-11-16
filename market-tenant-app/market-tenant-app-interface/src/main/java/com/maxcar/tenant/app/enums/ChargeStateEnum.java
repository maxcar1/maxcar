package com.maxcar.tenant.app.enums;

/**
 * 充值订单状态
 */
public enum ChargeStateEnum {

    CREATE(0, "创建"),
    PROCESS(1, "处理中"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败");

    private int value;
    private String desc;

    ChargeStateEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value() {
        return value;
    }

    public String desc() {
        return desc;
    }

    public static ChargeStateEnum get(int value) {
        for (ChargeStateEnum item : ChargeStateEnum.values()) {
            if (item.value() == value) {
                return item;
            }
        }
        return null;
    }
}
