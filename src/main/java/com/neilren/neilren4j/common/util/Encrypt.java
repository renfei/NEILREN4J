package com.neilren.neilren4j.common.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Encrypt
 * @Description 字符串不可逆哈希算法
 * @Date 2018/7/28 13:39
 */
@Component
public class Encrypt {

    /**
     * 密码加盐加密
     *
     * @param pswd 明文密码
     * @param salt 盐值
     * @return 512bit/64byte 密文
     */
    public String SHA512Paawd(String pswd, String salt) {
        return SHA512(SHA512(pswd) + salt);
    }

    /**
     * 密码加盐加密
     *
     * @param SHA512 SHA一次过的密码
     * @param salt   盐值
     * @return 512bit/64byte 密文
     */
    public String SHA512Salt(String SHA512, String salt) {
        return SHA512(SHA512 + salt);
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText 明文
     * @return
     */

    public String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText 明文
     * @return
     */
    public String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText 明文
     * @param strType 加密类型
     * @return
     */
    private String SHA(final String strText, final String strType) {
        // 返回值
        String strResult = null;
        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest
                        .getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }
}
