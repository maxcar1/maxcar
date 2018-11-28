package com.maxcar.core.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件
 */
public class PropertiesTools {

    public static String applicantID;//载体标识
    public static String RSAPrivateKey;//RSA私钥
    public static String RSAPublicKey;//RSA公钥
    public static String queryProvinceUrl;//查询省代码url
    public static String queryCityUrl;//查询城市url
    public static String queryProvinceByInsurerUrl;//保险公司支持的地区url
    public static String queryInsurerByProvinceUrl;//地区支持的保险公司url
    public static String queryRiskUrl;//险别查询url
    public static String queryCarUrl;//车辆信息（根据车牌号查询）url
    public static String queryCarByVinUrl;//车辆信息（根据车架号查询）url
    public static String queryCarModelUrl;//车型信息url
    public static String queryCarLikeModelUrl;//模糊匹配车型url
    public static String queryEffectiveDateUrl;//保单信息url
    public static String queryReferenceQuoteUrl;//参考报价url
    public static String queryExactnessQuoteUrl;//精准报价url
    public static String queryUnderwriteUrl;//申请核保url
    public static String queryPayLinkUrl;//获取支付链接url
    public static String queryBjVerifyCodeUrl;//手机号验证码接口url
    public static String queryInsureStatementUrl;//保险公司投保声明url
    public static String uploadFileUrl;//上传影像接口url
    public static String queryScopeUrl;//查询影像资料和文件格式接口url
    public static String VehicleInfoRevisionUrl;//一键修正车辆信息url
    public static String VehicleAndModelUrl;//车辆车型信息
    public static String applicationUrl;//支付成功后跳转的url地址

    static {
        try {
        	InputStream is = PropertiesTools.class.getResourceAsStream("../../../../../conf/ztwl.properties");
    		Properties dbProps = new Properties();
    		dbProps.load(is);
            applicantID = dbProps.getProperty("applicantID").trim();
            Logger.getRootLogger().info("载体标识 -->"+applicantID);
            RSAPrivateKey = dbProps.getProperty("RSAPrivateKey").trim();
            Logger.getRootLogger().info("RSA加密私钥 -->"+RSAPrivateKey);
            RSAPublicKey = dbProps.getProperty("RSAPublicKey").trim();
            Logger.getRootLogger().info("RSA加密公钥 -->"+RSAPublicKey);
            queryProvinceUrl = dbProps.getProperty("queryProvinceUrl").trim();
            queryCityUrl = dbProps.getProperty("queryCityUrl").trim();
            queryProvinceByInsurerUrl = dbProps.getProperty("queryProvinceByInsurerUrl").trim();
            queryInsurerByProvinceUrl = dbProps.getProperty("queryInsurerByProvinceUrl").trim();
            queryRiskUrl = dbProps.getProperty("queryRiskUrl").trim();
            queryCarUrl = dbProps.getProperty("queryCarUrl").trim();
            queryCarByVinUrl = dbProps.getProperty("queryCarByVinUrl").trim();
            queryCarModelUrl = dbProps.getProperty("queryCarModelUrl").trim();
            queryCarLikeModelUrl = dbProps.getProperty("queryCarLikeModelUrl").trim();
            queryEffectiveDateUrl = dbProps.getProperty("queryEffectiveDateUrl").trim();
            queryReferenceQuoteUrl = dbProps.getProperty("queryReferenceQuoteUrl").trim();
            queryExactnessQuoteUrl = dbProps.getProperty("queryExactnessQuoteUrl").trim();
            queryUnderwriteUrl = dbProps.getProperty("queryUnderwriteUrl").trim();
            queryPayLinkUrl = dbProps.getProperty("queryPayLinkUrl").trim();
            queryBjVerifyCodeUrl = dbProps.getProperty("queryBjVerifyCodeUrl").trim();
            queryInsureStatementUrl = dbProps.getProperty("queryInsureStatementUrl").trim();
            uploadFileUrl = dbProps.getProperty("uploadFileUrl").trim();
            queryScopeUrl = dbProps.getProperty("queryScopeUrl").trim();
            VehicleInfoRevisionUrl = dbProps.getProperty("VehicleInfoRevisionUrl").trim();
            VehicleAndModelUrl = dbProps.getProperty("VehicleAndModelUrl").trim();
        } catch (Exception ex) {
            Logger.getRootLogger().error("读取 ztwl.properties 失败", ex);
        }
    }
}
