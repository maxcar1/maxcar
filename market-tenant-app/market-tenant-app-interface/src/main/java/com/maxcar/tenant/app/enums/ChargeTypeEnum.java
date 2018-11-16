package com.maxcar.tenant.app.enums;

/**
 * 充值类型
 */
public enum ChargeTypeEnum {

    WXPAY_APP(1, "微信App支付"),
    ALIPAY_APP(2, "支付宝App支付");

    private int value;
    private String desc;

    ChargeTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value() {
        return value;
    }

    public String desc() {
        return desc;
    }

    public static ChargeTypeEnum get(int value) {
        for (ChargeTypeEnum item : ChargeTypeEnum.values()) {
            if (item.value() == value) {
                return item;
            }
        }
        return null;
    }
}
