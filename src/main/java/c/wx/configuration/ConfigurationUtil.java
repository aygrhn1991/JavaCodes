/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.configuration;

import java.util.Arrays;
import c.util.SHA1;

public class ConfigurationUtil {

    public static boolean checkConfiguration(String token, String timestamp, String nonce, String signature) {
        String[] strArray = new String[]{token, timestamp, nonce};
        Arrays.sort(strArray);
        String strResult = SHA1.getSha1(strArray[0] + strArray[1] + strArray[2]);
        return strResult.equals(signature);
    }
}
