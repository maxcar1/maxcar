package com.maxcar.core.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.log4j.Logger;

import org.apache.commons.codec.binary.Base64;
/*import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;*/

/**
 * 签名
 */
public class SignatureTools {
    //加密算法RSA
    public static final String KEY_ALGORITHM = "RSA";
    //签名算法
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    //签名算法
    public static final String CHARSET = "UTF-8";

    /**
     * 获取私钥对象
     * @param keyString 私钥字符串
     * @return PrivateKey
     */
    private static PrivateKey getPrivateKey(String keyString) {
        try {
            byte[] keyBytes = new Base64().decode(keyString);//new BASE64Decoder().decodeBuffer(keyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取公钥对象
     * @param keyString 公钥字符串
     * @return PublicKey
     */
    private static PublicKey getPublicKey(String keyString) {
        try {
            byte[] keyBytes = new Base64().decode(keyString);//new BASE64Decoder().decodeBuffer(keyString);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 签名
     * @param content 待签名字符串
     * @param privateKeyString 私钥字符串
     * @return 签名字符串
     */
    public static String sign(String content, String privateKeyString) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(getPrivateKey(privateKeyString));
            signature.update(content.getBytes(CHARSET));
            return new Base64().encodeToString(signature.sign());//new BASE64Encoder().encodeBuffer(signature.sign());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 验签
     * @param content 待签名字符串
     * @param sign 签名字符串
     * @param publicKeyString 公钥字符串
     * @return true-验签通过
     */
    public static boolean verify(String content, String sign, String publicKeyString) {
        try {
            Logger.getRootLogger().info("SignatureTools签名验证-------签名内容,签名,公钥----》"+content+","+sign+","+publicKeyString);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(getPublicKey(publicKeyString));
            signature.update(content.getBytes(CHARSET));
            //return signature.verify(new BASE64Decoder().decodeBuffer(sign));
            return signature.verify(new Base64().decode(sign));
        } catch (Exception ex) {
            Logger.getRootLogger().error("签名校验异常", ex);
            return false;
        }
    }

}
