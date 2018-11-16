package com.maxcar.tenant.app.entity;

import com.maxcar.base.util.MoneyUtil;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@Data
public class AddDealInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String carManager;

    private Double dealPrice;

    private Byte burdenOwner;

    private Integer mileage;

    private String engineNo;

    private String environmentalStandards;

    private Byte isvalid;

    private Date insertTime;

    private Date updateTime;

    private Integer tradingType;

    public String getFieldValue(String fieldName) {
        try {
            if ("capitalDealPrice".equals(fieldName)) {
                return MoneyUtil.toChinese(String.valueOf(this.dealPrice));
            }

            if ("burdenOwner".equals(fieldName)) {
                if (this.burdenOwner == 1) {
                   return "买方";
                } else if (this.burdenOwner == 2) {
                    return "卖方";
                } else {
                    return "其他方";
                }
            }

            Field field = this.getClass().getDeclaredField(fieldName);
            Object obj = field.get(this);
            if (obj == null) {
                return "";
            }
            return field.get(this).toString();
        } catch (Exception e) {
            return "";
        }
    }
}