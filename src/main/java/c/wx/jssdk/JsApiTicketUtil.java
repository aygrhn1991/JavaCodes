/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.jssdk;

import c.util.DataTransferUtil;
import c.util.HttpUtil;
import c.util.PropertiesUtil;
import c.wx.accesstoken.AccessTokenUtil;
import c.wx.config.WXConfigModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class JsApiTicketUtil {

    private static final PropertiesUtil PROPERTIES_UTIL = new PropertiesUtil(WXConfigModel.CONFIG_FILE_NAME);

    public static String getJsApiTicket() {
        String jsapi_ticket = PROPERTIES_UTIL.getValueByKey("ticket");
        if (jsapi_ticket == null) {
            requestJsApiTicket();
            jsapi_ticket = PROPERTIES_UTIL.getValueByKey("ticket");
        } else {
            String expires_in = PROPERTIES_UTIL.getValueByKey("jsapi_ticket_expires_in");
            String jsapi_ticket_gettime = PROPERTIES_UTIL.getValueByKey("jsapi_ticket_gettime");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date gettime = df.parse(jsapi_ticket_gettime);
                Date nowtime = new Date();
                long seconds = nowtime.getTime() - gettime.getTime();
                if ((seconds / 1000) > (Integer.parseInt(expires_in) - 10)) {
                    requestJsApiTicket();
                    jsapi_ticket = PROPERTIES_UTIL.getValueByKey("ticket");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return jsapi_ticket;
    }

    private static void requestJsApiTicket() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", AccessTokenUtil.getAccesstToken());
        String response = HttpUtil.Request_GET(url);
        Map<String, String> map = DataTransferUtil.jsonToMap(response);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("jsapi_ticket_gettime", formatter.format(new Date()));
        map.put("jsapi_ticket_expires_in", map.get("expires_in"));
        map.remove("errcode");
        map.remove("errmsg");
        map.remove("expires_in");
        PROPERTIES_UTIL.setValues(map);
    }
}
