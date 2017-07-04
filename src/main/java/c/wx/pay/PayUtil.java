/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.pay;

import c.util.DataTransferUtil;
import c.util.HttpUtil;
import c.util.MD5;
import c.util.SHA1;
import c.wx.config.WXConfigModel;
import c.wx.jssdk.JsApiTicketUtil;
import c.wx.models.jssdk.JsSdkPayConfigModel;
import c.wx.models.pay.UnifiedOrderCallBackModel;
import c.wx.models.pay.UnifiedOrderModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PayUtil {

    public static String getPrepaySignature(UnifiedOrderModel model) {
        try {
            List<String> list = new ArrayList<>();
            Class cls = model.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if (field.get(model) != null) {
                    list.add(field.getName() + "=" + field.get(model));
                }
            }
            Collections.sort(list);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i)).append("&");
            }
            String strA = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
            String strB = strA + "&key=" + WXConfigModel.getMch_key();
            String strC = MD5.getMD5_32(strB).toUpperCase();
            return strC;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getPrepay_id(UnifiedOrderModel model) {
        String xml = DataTransferUtil.objectToXml(model, UnifiedOrderModel.class);
        String response = HttpUtil.HttpClient_Post("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
        UnifiedOrderCallBackModel m = (UnifiedOrderCallBackModel) DataTransferUtil.xmlToObject(response, UnifiedOrderCallBackModel.class);
        return m.prepay_id;
    }

    public static String getPaySignature(JsSdkPayConfigModel model) {
        String[] strArray = new String[]{"appId=" + model.appId, "timeStamp=" + model.timeStamp, "nonceStr=" + model.nonceStr, "package=" + model.pkg, "signType=" + model.signType};
        Arrays.sort(strArray);
        String strResult = MD5.getMD5_32(strArray[0] + "&" + strArray[1] + "&" + strArray[2] + "&" + strArray[3] + "&" + strArray[4] + "&" + strArray[5] + "&key=" + WXConfigModel.getMch_key());
        return strResult;
    }
}
