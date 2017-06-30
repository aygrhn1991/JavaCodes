/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.config;

import c.util.PropertiesUtil;

public class WXConfigModel {

    public static final String CONFIG_FILE_NAME = "wxconfig.properties";
    private static final PropertiesUtil PROPERTIES_UTIL = new PropertiesUtil(CONFIG_FILE_NAME);

    public static String getAppId() {
        return PROPERTIES_UTIL.getValueByKey("appid");
    }

    public static String getAppSecret() {
        return PROPERTIES_UTIL.getValueByKey("appsecret");
    }

    public static String getToken() {
        return PROPERTIES_UTIL.getValueByKey("token");
    }

    public static String getPlatformWechatNumber() {
        return PROPERTIES_UTIL.getValueByKey("platform_wechat_num");
    }
    public static String getMch_id() {
        return PROPERTIES_UTIL.getValueByKey("mch_id");
    }
    public static String getMch_key() {
        return PROPERTIES_UTIL.getValueByKey("mch_key");
    }

    public static String getValueByKey(String key) {
        return PROPERTIES_UTIL.getValueByKey(key);
    }
}
