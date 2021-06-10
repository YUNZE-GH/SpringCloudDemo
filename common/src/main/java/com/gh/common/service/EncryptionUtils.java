package com.gh.common.service;

import java.security.NoSuchAlgorithmException;

public interface EncryptionUtils {

    /**
     * 使用MD5加密
     *
     * @param str 加密前数据
     * @return 加密后数据
     */
    String encryptionMD5(String str);

    /**
     * SHA加密 生成40位SHA码
     *
     * @param str 加密前数据
     * @return 加密后数据
     * @throws NoSuchAlgorithmException
     */
    String encryptionSHA(String str) throws Exception;

    /**
     * AES对称加密
     *
     * @param str 加密前数据
     * @return 加密后数据
     */
    String encryptionAES(String str);

    /**
     * AES解密
     *
     * @param str 解密前数据
     * @return 解密后数据
     */
    String decryptAES(String str);
}
