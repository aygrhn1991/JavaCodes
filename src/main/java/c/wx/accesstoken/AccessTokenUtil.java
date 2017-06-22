/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.accesstoken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import c.wx.config.WXConfigModel;
import c.util.DataTransferUtil;
import c.util.HttpUtil;
import c.util.PropertiesUtil;

public class AccessTokenUtil {

    private static final PropertiesUtil PROPERTIES_UTIL = new PropertiesUtil(WXConfigModel.CONFIG_FILE_NAME);

    public static String getAccesstToken() {
        String access_token = PROPERTIES_UTIL.getValueByKey("access_token");
        if (access_token == null) {
            requestAccesstToken();
            access_token = PROPERTIES_UTIL.getValueByKey("access_token");
        } else {
            String expires_in = PROPERTIES_UTIL.getValueByKey("access_token_expires_in");
            String access_token_gettime = PROPERTIES_UTIL.getValueByKey("access_token_gettime");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date gettime = df.parse(access_token_gettime);
                Date nowtime = new Date();
                long seconds = nowtime.getTime() - gettime.getTime();
                if ((seconds / 1000) > (Integer.parseInt(expires_in) - 10)) {
                    requestAccesstToken();
                    access_token = PROPERTIES_UTIL.getValueByKey("access_token");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return access_token;
    }

    private static void requestAccesstToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", WXConfigModel.getAppId(), WXConfigModel.getAppSecret());
        String response = HttpUtil.Request_GET(url);
        Map<String, String> map = DataTransferUtil.jsonToMap(response);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("access_token_gettime", formatter.format(new Date()));
        map.put("access_token_expires_in", map.get("expires_in"));
        map.remove("expires_in");
        PROPERTIES_UTIL.setValues(map);
    }
}
