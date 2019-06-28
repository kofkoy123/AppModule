package com.lzr.module_base.utils.encryption;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAUtils {
    /**
     * 加密算法
     */
    static String KEY_ALGORITHM = "RSA";
    /**
     * 加密算法
     */
    static String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    /**
     * 密钥长度，用来初始化
     */
    static int KEY_SIZE = 1024;
    /**
     * 分隔符
     */
    static String SPLIT_CHAR = " ";
    /**
     * 加密分段长度，不可超过117
     */
    static int MAX_LENGTH = 117;

    /**
     * 字符串和byte[]转化模式
     */
    public enum ConvertMode{
        BASE64,//Base64
        HEX    //16进制
    }


    /**
     * 获取一个秘钥对实例
     * @return 实例
     */
    public static KeyPair newInstance() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            kpg.initialize(KEY_SIZE, secureRandom);
            return kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过模指生成公钥，对信息加密，该方法好处在于每次生成公钥因随机值不同，安全性更高
     * @param res 源文本信息
     * @param modulus 模
     * @param exponent 指
     * @return 密文
     */
    public static String encode (String res, BigInteger modulus, BigInteger exponent, ConvertMode convertMode) {
        String publicKey = getPublicKeyStr(modulus, exponent,convertMode);
        return encode(res, publicKey,convertMode);
    }

    /**
     * 通过公钥信息加密
     * @param res 源文本信息
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encode (String res, String publicKey, ConvertMode convertMode) {
        byte[] resBytes = res.getBytes();
//        byte[] keyBytes = parseHexStr2Byte(publicKey);//先把公钥转为2进制
        byte[] keyBytes = Base64.decode(publicKey);//先把公钥转为2进制
//        byte[] keyBytes = publicKey.getBytes();//先把公钥转为2进制
        StringBuilder result = new StringBuilder();//结果
        //如果超过了100个字节就分段
        if (resBytes.length<= MAX_LENGTH) {//不超过直接返回即可
            return encode(resBytes, keyBytes,convertMode);
        }else {
            int size = resBytes.length/ MAX_LENGTH + (resBytes.length% MAX_LENGTH >0?1:0);
            for (int i = 0; i < size; i++) {
                int len = i==size-1?resBytes.length% MAX_LENGTH : MAX_LENGTH;
                byte[] bs = new byte[len];//临时数组
                System.arraycopy(resBytes, i* MAX_LENGTH, bs, 0, len);
                result.append(encode(bs, keyBytes,convertMode));
                if(i!=size-1)result.append(SPLIT_CHAR);
            }
            return result.toString();
        }
    }

    /**
     * 通过公钥信息加密
     * @param resBytes 源字节数组
     * @param publicKeyBytes 公钥字节数组
     * @return 密文
     */
    private static String encode (byte[] resBytes, byte[] publicKeyBytes, ConvertMode convertMode) {
        X509EncodedKeySpec x5 = new X509EncodedKeySpec(publicKeyBytes);//用2进制的公钥生成x509
        try {
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey pubKey = kf.generatePublic(x5);//用KeyFactory把x509生成公钥pubKey
            Cipher cp = Cipher.getInstance(CIPHER_ALGORITHM);//生成相应的Cipher
            cp.init(Cipher.ENCRYPT_MODE, pubKey);//给cipher初始化为加密模式，以及传入公钥pubKey
            return parseByte2Str(convertMode,cp.doFinal(resBytes));//以16进制的字符串返回
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过公私钥信息解密
     * @param res 密文
     * @param privateKey 私钥字符串
     * @return 解密字符
     */
    public static String decode (ConvertMode convertMode, String res, String privateKey) {
        byte[] keyBytes = parseStr2Byte(convertMode,privateKey);
        //先分段
        String[] rs = res.split(SPLIT_CHAR);

        //分段解密
        int len = 0;
        //组合byte[]
        byte[] result = new byte[rs.length* MAX_LENGTH];
        for (int i = 0; i < rs.length; i++) {
            byte[] bs = decode(parseStr2Byte(convertMode,rs[i]), keyBytes);
            System.arraycopy(bs, 0, result, i* MAX_LENGTH, bs.length);
            len+=bs.length;
        }
        byte[] newResult = new byte[len];
        System.arraycopy(result, 0, newResult, 0, len);
        //还原字符串
        return new String(newResult);
    }

    /**
     * 通过公私钥信息解密
     * @param res 密文
     * @param keyBytes 私钥字节数组
     * @return 解密字符
     */
    private static byte[] decode (byte[] res, byte[] keyBytes) {
        PKCS8EncodedKeySpec pk8 = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
            Key priKey = kf.generatePrivate(pk8);
            Cipher cp = Cipher.getInstance(CIPHER_ALGORITHM);
            cp.init(Cipher.DECRYPT_MODE, priKey);
            return cp.doFinal(res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    /**
     * 通过模和指数生成RSA公钥，返回公钥信息的字符串文本
     * @param modulus 模
     * @param exponent 指
     * @return 公钥文本
     */
    public static String getPublicKeyStr (BigInteger modulus, BigInteger exponent, ConvertMode convertMode) {
        RSAPublicKey publicKey = getPublicKey(modulus, exponent);
        return getPublicKeyStr(convertMode,publicKey);
    }

    /**
     * 返回公钥信息的字符串文本
     * @param publicKey 公钥信息
     * @return 公钥文本
     */
    public static String getPublicKeyStr(ConvertMode convertMode, PublicKey publicKey){
        return parseByte2Str(convertMode,publicKey.getEncoded());
    }

    /**
     * 通过模和指数生成RSA公钥
     * @param modulus 模
     * @param exponent 指
     * @return RSA公钥
     */
    public static RSAPublicKey getPublicKey (BigInteger modulus, BigInteger exponent) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回私钥信息的字符串文本
     * @param privateKey 私钥信息
     * @return 私钥文本
     */
    public static String getPrivateKeyStr (ConvertMode convertMode, PrivateKey privateKey) {
        return parseByte2Str(convertMode,privateKey.getEncoded());
    }


    /**
     * 字符串转byte
     * 两种模式
     * 1.base64 -> byte[]
     * 2.16进制 -> byte[]
     * @param str
     * @return
     */
    private static byte[] parseStr2Byte(ConvertMode convertMode, String str){
        if(convertMode == ConvertMode.BASE64 ){
            return parseBase64Str2Byte(str);
        }else if(convertMode == ConvertMode.HEX){
            return parseHexStr2Byte(str);
        }else {
            throw new IllegalArgumentException("ConvertMode不合法");
        }
    }

    /**
     * byte[]转字符串
     * 两种模式
     * 1.byte[] -> base64
     * 2.byte[] -> 16进制
     * @param buf
     * @return
     */
    private static String parseByte2Str(ConvertMode convertMode, byte buf[]){
        if(convertMode == ConvertMode.BASE64 ){
            return parseByte2Base64Str(buf);
        }else if(convertMode == ConvertMode.HEX){
            return parseByte2HexStr(buf);
        }else {
            throw new IllegalArgumentException("ConvertMode不合法");
        }

    }


    /**
     * 二进制转base64
     * @param buf
     * @return
     */
    private static String parseByte2Base64Str (byte buf[]){
        return Base64.encode(buf);
    }

    /**
     * base64转二进制
     * @param base64Str
     * @return
     */
    private static byte[] parseBase64Str2Byte(String base64Str){
        return Base64.decode(base64Str);
    }



    /**
     * 将 16进制 转换为 2进制
     */
    private static byte[] parseHexStr2Byte (String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将 2进制 转换成 16进制
     */
    private static String parseByte2HexStr (byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

}