/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.util;

import java.security.MessageDigest;

/**
 *
 * @author Administrator
 */
public class MD5 {

    public static String getMD5_32(String str) {
        try {
            MessageDigest md5;
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(val));
            }
            str = stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    public static String getMD5_16(String encryptStr) {
        return getMD5_32(encryptStr).substring(8, 24);
    }
}
