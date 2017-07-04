/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.jssdk;

import c.util.SHA1;
import c.wx.models.jssdk.JsSdkConfigModel;
import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class JsSdkUtil {

    public static String getSignature(JsSdkConfigModel model, String url) {
        String[] strArray = new String[]{"noncestr=" + model.nonceStr, "jsapi_ticket=" + JsApiTicketUtil.getJsApiTicket(), "timestamp=" + model.timestamp, "url=" + url};
        Arrays.sort(strArray);
        String strResult = SHA1.getSha1(strArray[0] + "&" + strArray[1] + "&" + strArray[2] + "&" + strArray[3]);
        return strResult;
    }
}
