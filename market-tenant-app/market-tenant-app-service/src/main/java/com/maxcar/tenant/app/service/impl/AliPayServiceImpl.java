package com.maxcar.tenant.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.EnvironmentUtil;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.app.dao.ChargeOrderDetailMapper;
import com.maxcar.tenant.app.entity.ChargeOrderDetail;
import com.maxcar.tenant.app.enums.ChargeStateEnum;
import com.maxcar.tenant.app.enums.ChargeTypeEnum;
import com.maxcar.tenant.app.service.AliPayService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service("aliPayService")
@Slf4j
@SuppressWarnings("all")
public class AliPayServiceImpl implements AliPayService {

    private static final String APP_ID = "2018102261808252";
//    private static final String APP_ID = "2018102461818890";

    private static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDPTb8LqY31tfoauiFnddz+M0g8R5Yp4lYMsbTv/gzcBB+NtXryewbleY/xkSfRrLvwsTyhfmmE9PKwQy3WSiZuFQKZfpicEeEXstRJ+riAu1gPetSGnEqWJxdbVKYCGEFAYhur3yDgalUaRCCf171gCWAajGsN2RPCywixK9I1mZCv+uBdecUUP4zTfPHIqMBZxqwnYLRHutixnDoo8FKuvlxtXyZMObyf97buaonrqwzmRKIx/6GPqGkdDX0/tiEoExfy1jRyCMd0IbjKuLLBFCdIDv5VmoYtSWnyTm9kGAjNH6Lv6Wg0vmsYDqMgFvLN9qVpcCTtLQruD9m2WkFRAgMBAAECggEAD+UVLe69PMbMO3pkD0vBOxhxhHW0tNdu46BIkFI5aDFEvhfZKnh2aeftOod3tyL4chcU4AkSTCh/5zXvcMvUsIIZ5e0IBId2a9vBXT7FING8ictQOlYHfgXXS6Bs7iIv9qDPIFmGas6cYdwxaDAhjBiL7t0vZCtt0XTB4NvMTWGql12Ea90PkNOIfIL7la4c6o+ckkDUu6KvZ3VLmXtMQExMkKb+F5DgafqYAMy+IY5C2L2s17lhVVGHz/9noi4ObiZhZvJhacX5dahW5iUohYPPmsz3lQEP/pWTt2FemawVdeGd0h1+fG4JQjAKI9hRgaVVcr4pX4CxXTwwsQEQcQKBgQDtis4O9FaZ3wyOYubACrNTJhuxu7chvkWAzijtSRec7A2y5iRO1zXgfBnakrU5cRyGlthLmTpIjbhzrzCagSII3O+6UMRbkev4ZF2BVZ9odkl3ZEDiVbr/LoUmg5l4XFu/F8woEyhm2eWE57A3iNXC1k0R+xZkW29kewxEA7izvQKBgQDfaW+rSMM3itVKJGZNA9HwMuB2yOw1o9OkGGXZCUUvpGaLN+Ew43dNjTiYYI+eTtNh1VTstk7LjEK89HL9QdROSPUHQ6DVC/ToJTmD5s5Dya3ypeCiSu8DxzUKelFrO/hykXTqSxXZd/YbUD7RSJJgUPpEt3oYuML0jVMQKQpTJQKBgGSbTmy9BdTDyICh2NNrdMn5sO8U7xpGiQ9lwaXGOE8JF0A3Axe1RKdgSF7vlfvPMWK7jr5uFmZWR9IPWAmIOc8BLDOAM7oI0qVwdL3rcsl+l90ORADYiIu9uz01rZFxpJYJ0TkD7C+rh+9DZboPpWGQ/DFqY/B7XXF0QByqatcNAoGAKqqULP4EakMVYMd60hJ8sYbF32mX0taqwJf4O27Ny7915fiSBSgrzJ+/wzoNs4oSTIjVYy+d9LmgtT1qCHvd8+VzBiQgPw+9geg6B7q4gbGuiryrHdN5uhr9QjcB/ZzCYf7teSFF1vZWBflZRda6PbK5WQ5BPx9p/BJRXrC3zBECgYAzvmPeGELuqeloUX2wb2xoMZU3HTBc6gDN/LfiRhZHtYZ20u67FZ/1v9qHtDTJjQoV6exnDuufyfTh8zbHFrmAUtcfAtkXu1ZCuiRC/L1PYGw1y5t4bDQN9mhLhgc4YmZRyfRwyVnDhi/sLo4IlxBGdsaQnkqDDJOJfusdK+P+aA==";
//    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJLZ4B/Xou6/gr1wxRdP29rvsSTC25VnVxndG3aTSCljx8qXxdN36+eX4Scyu5Czzex3JBHktklWghzu+lLdVmIBrqL2Ra2fYFLLzw1bFEdeJaTKWWp+Zawy3krSltB+AfzNaQAwsbHJEvz5uhgV/tKHfB89tXUtYqTzjwuncOXGrtQDSZt/cmACgsGQeVg2znD81B79KUrYlk4OwWvKQT/tbjkd704oETB4LdZhYPydKL45Ws0XJG5f31m2JWIQVsTHS82yBDAw0AldZkHvukzye0UHBtFizUEV+t9l4F0ytaWlOha6dDjH+8dHxNjOt78DJppPeOFogFniZrEWzHAgMBAAECggEADBRIG3h7wJt8gC1eWPm8TopWGugmCq3NSXAfanLPq1OX+Cg5BmPRoWAsGLF/m5G4KuwwyQYnordx1C3Xp8RTvtkIpK0JyoaxxefExHUgOqsVVzSo8J3OL/7uH3/iL+C8LUygSW9VWGBju0os3wqkltnKLwQUVG7t2wZ8tb+MbHk2rnH0W/pbm6Im/B1tQ+v/GIb2+gv0N3F+CsCF/bhSlNJfyv2ZyK3usDr3bKab3oKW8y2Fh0ZoweZbB+ti690FccDRti9i8lI2DyrkO72A+Sff8PekJoRv9PkqsRFDGd9uAZPdnMa1MlY/MQmpa6zXtcF7X+Zy/W8MoT7qtxEm6QKBgQD8VwpFVYx1VLNZ1smFMUfmG2BovfYC7PWnN4mELwvO7liXCiN6MS+WScneqpTW4ow2MgbJTAfEsH2yALDSjtT6Bd6ig//akYb2F6LL3VV3qZW6xyknpFPo0NvttfEFFi6FFt6+4nR1+gZCsS7b2rHP9UCeUaFkO45idAkNzl6y4wKBgQDMGJvkV1Cugy6USOxFvE2L1gZ4rrkP5+I0Q2yQ8LaaZBtxROFMnCmfX844jcPgUMwOWKkpfPYs0btxvK4A9jT+iJEd8siXnor2fKHjhCMNiMxyyrNHmlW4LY2N7Z1gBMCRHpqJMGeMTJ5GdXRwJ4CnKjokTPtiqs/V62QLqrGvzQKBgDOiE8XwJVd0x2YgnweT9+RLwcRs6kPa/MEEiizz+3SSLKprNqswGU6lNptLSD1YCdylC+Jwnj5O5slffs72zvmB7fZPEDFHCdidKYCRf4WGu86GJhOoekrvQbE4Q2MJEOfGsDla2P2y9Uj8PFxcOXoV8uzyQxXhYdcfayLaEWKFAoGAadohkC4Q9FGpKNMhZ5GgDnR0ascDyFIVqdGGTy3pJu3gpcB5sUgU9km0JPSxsHd3PcGSCqh67Qfh1b1r6afIGHMwAH5YtvpNTEWbmOqBW5Wlo6upi+tppdpKXl/sjU4pqZpbv+dQ7R0XwadNOpXiTdDC0uzyPFwm6uXov3xKJ8kCgYEAsVVQyeO3Kj29kY4ZX4XOcOp1U1C1t8eTI+MT8ePzshe/lEndTD1KManhQto5YDrKP/lxOc+RWxzpQGjxt8D/MmlnAe2z2YqWSAwPeSjcJG6QdvYZjFCU8Vr82n1tJhjwx4SIdIH7TfxNMoIJv2T62Y9ik2S/8cZ+SNMzzek9MT8=";

    /**
     * 支付宝公钥
     */
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgngiAmXylBYSzdniy1c4mBZeLd6MPlK+BKuq3jr6/q5Pow9U1ROtTRk2T/dq1f89Wiqetv1/KzXgr+qch2i5liOkXI/+clG0wsMvdVfHCir/kHFQ7lOagSkSf7hx74Em+hh4u3M0nl5DNgbBVpo+GkZEyNIapvg0VFeUVovXJeW3L2zjS7cBYCQQ3V1+QLkrWyqJikKTy8s94XNdAzG3PvV1jrfwFmgL9Qo4N26oUbMXyblDPu6xtlqKYMN6Mo+H+5oL7PZkTr3zPiVHo6wOlD5POw0N8hKT6urXzteTCYfj2gLyjKqk2EwRjZfxaCALRC6ZXkAL83WarZCQowHOVQIDAQAB";
//    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3AO0BPZna1qistgwatEwZI3L9Hc/8ra9rdVY9aY82+4z1VVNNpEbppM1hFVS8AT6OLlrA8KlvxWe9d21UhlU9xhxaWvlNhoEcVj26Dc75coN5kJwIDunXOoDhEJp3dPl7HJ0bRO/pAgWZo9MkoEKlxAfgbbF8HVltPcQI1458uMV4vL+H/bvQbL6wkrZlGyFlXvRtmv1W/Kl25Le0IejEKZ29MFt5fXoeXPv1ht0ray7sl/2aTCvNihmpcg+QFC817sEFvFa7tgE2Ffoda2qODc+44nV6hQlz3exvUhLZJ1wxu8y70dbLQ5k5YEXMhb47XWgMQ9pmCw3ZDetEyx2cQIDAQAB";

    private static final String CHARSET = "UTF-8";

    private static final String SIGN_TYPE = "RSA2";

    private static final String FORMAT = "json";

    private static final String ALIPAY_GRATE_WAY = "https://openapi.alipay.com/gateway.do";

    @Value("${alipay.app.notify.url}")
    private String NOTIFY_URL;

    @Autowired
    private ChargeOrderDetailMapper chargeOrderDetailMapper;

    @Autowired
    private MarketService marketService;

    @Autowired
    private StaffService staffService;

    @Override
    public InterfaceResult alipayCharge(String staffId, String ip, int feeType, String transferCarNo) {

        InterfaceResult interfaceResult = new InterfaceResult();
        String appId = APP_ID;
        String privateKey = PRIVATE_KEY;

        Staff staff = staffService.selectByPrimaryId(staffId);
        Market market = marketService.getMarketById(staff.getMarketId());
        int chargeMoney = market.getQualityServiceFee() * 100;
        String subject = "全车通商户版-质保服务费";
        if (feeType == 1) {

            if (StringUtils.isBlank(market.getAlipayAppid())
                    || StringUtils.isBlank(market.getAlipayPrivateKey())
                    || StringUtils.isBlank(market.getAlipayPublicKey())) {
                interfaceResult.InterfaceResult600("市场方不支持在线过户");
                return interfaceResult;
            }

            appId = market.getAlipayAppid();
            privateKey = market.getAlipayPrivateKey();
            chargeMoney = market.getChangeTheNamePrice() * 100;
            subject = "全车通商户版-过户费";
        }

        if (!EnvironmentUtil.isProduct()) {
            chargeMoney = 1;
        }

        ChargeOrderDetail chargeOrderDetail = new ChargeOrderDetail();
        chargeOrderDetail.setId(UuidUtils.generateIdentifier());
        chargeOrderDetail.setChargeMoney(chargeMoney);
        chargeOrderDetail.setChargeType(ChargeTypeEnum.ALIPAY_APP.value());
        chargeOrderDetail.setState(ChargeStateEnum.CREATE.value());
        chargeOrderDetail.setStaffId(staffId);
        chargeOrderDetail.setFeeType(feeType);
        chargeOrderDetail.setTransferCarNo(transferCarNo);

        int ret = chargeOrderDetailMapper.insert(chargeOrderDetail);

        String sign = createOrderStr(chargeMoney, appId, privateKey, chargeOrderDetail.getId(), subject);

        if (sign == null) {
            interfaceResult.InterfaceResult600("支付宝签名失败");
            return interfaceResult;
        }

        Map<String, String> map = new HashMap<>(4);
        map.put("orderString", sign);
        map.put("chargeOrderId", String.valueOf(chargeOrderDetail.getId()));

        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }

    private String createOrderStr(int totalAmount, String appId, String privateKey, String orderId, String subject) {
        try {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            String floatTotalAount = fnum.format(totalAmount / 100F);
            TreeMap<String, String> params = new TreeMap<>();
            params.put("app_id", appId);
            params.put("method", "alipay.trade.app.pay");
            params.put("charset", "utf-8");
            params.put("sign_type", "RSA2");
            params.put("timestamp", DateUtils.getSecondStr(new Date()));
            params.put("version", "1.0");

            params.put("notify_url", NOTIFY_URL);

            TreeMap<String, Object> bizParams = new TreeMap<>();
            bizParams.put("subject", subject);
            bizParams.put("out_trade_no", orderId);
            bizParams.put("product_code", "QUICK_MSECURITY_PAY");
            bizParams.put("total_amount", floatTotalAount + "");
            bizParams.put("body", "test");
            bizParams.put("timeout_express", "30m");
            params.put("biz_content", JSONObject.toJSONString(bizParams));
            StringBuilder builder1 = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.isNotBlank(value)) {
                    if (builder1.length() > 0) {
                        builder1.append("&");
                    }
                    builder1.append(key);
                    builder1.append("=");
                    builder1.append(value);
                }
            }

            String sign = AlipaySignature.rsa256Sign(builder1.toString(), privateKey, "utf-8");
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.isNotBlank(value)) {
                    builder.append(key);
                    builder.append("=");
                    builder.append(URLEncoder.encode(value, "utf-8"));
                    builder.append("&");
                }
            }
            builder.append("sign");
            builder.append("=");
            builder.append(URLEncoder.encode(sign, "utf-8"));

            return builder.toString();
        } catch (Exception e) {
            log.error("生成支付宝签名失败", e);
        }
        return null;
    }

    @Override
    public boolean checkSign(Map<String, String> data, String staffId, int feeType) {

        String publicKey = PUBLIC_KEY;
        if (feeType == 1) {
            Staff staff = staffService.selectByPrimaryId(staffId);
            Market market = marketService.selectByPrimaryKey(staff.getMarketId());
            publicKey = market.getAlipayPublicKey();
        }

        try {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.isNotBlank(value) && (!"sign".equals(key) && !("sign_type".equals(key)))) {
                    if (builder.length() > 0) {
                        builder.append("&");
                    }
                    builder.append(key);
                    builder.append("=");
                    builder.append(URLDecoder.decode(value, "utf-8"));
                }
            }
            return AlipaySignature.rsa256CheckContent(builder.toString(), data.get("sign"), publicKey, CHARSET);
        } catch (Exception e) {
            log.error("校验sign失败", e);
            return false;
        }
    }

}
