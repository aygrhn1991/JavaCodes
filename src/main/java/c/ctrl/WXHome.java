/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.ctrl;

import c.util.DataTransferUtil;
import c.util.HttpUtil;
import c.util.SystemUtil;
import c.wx.config.WXConfigModel;
import c.wx.configuration.ConfigurationUtil;
import c.wx.jssdk.JsSdkUtil;
import c.wx.management.menu.MenuTypeEnum;
import c.wx.management.menu.MenuUtil;
import c.wx.management.message.MessageUtil;
import c.wx.oauth.OAuthScopeEnum;
import c.wx.oauth.OAuthUtil;
import c.wx.management.user.UserUtil;
import c.wx.models.jssdk.JsSdkConfigModel;
import c.wx.models.jssdk.JsSdkPayConfigModel;
import c.wx.models.menu.ButtonGroupModel;
import c.wx.models.menu.ButtonModel;
import c.wx.models.menu.MenuModel;
import c.wx.models.oauth.OAuthUserAccessTokenModel;
import c.wx.models.oauth.OAuthUserInfoModel;
import c.wx.models.pay.PayCallBackModel;
import c.wx.models.pay.PayResultResponseModel;
import c.wx.models.pay.UnifiedOrderModel;
import c.wx.models.user.UserInfoModel;
import c.wx.pay.PayResultEnum;
import c.wx.pay.PaySignTypeEnum;
import c.wx.pay.PayUtil;
import c.wx.pay.UnifiedOrderTradeTypeEnum;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/wxhome")
public class WXHome {

    @RequestMapping("/configuration")
    public @ResponseBody
    String configuration(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().toLowerCase().equals("get")) {
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String signature = request.getParameter("signature");
            String echostr = request.getParameter("echostr");
            return ConfigurationUtil.checkConfiguration(WXConfigModel.getToken(), timestamp, nonce, signature) ? echostr : null;
        } else {
            try {
                try (InputStream stream = request.getInputStream()) {
                    String xml = DataTransferUtil.streamToString(stream);
                    Map<String, String> xmlMap = DataTransferUtil.xmlToMap(xml, false);
                    MessageUtil.messageAndEventHandler(xmlMap, response);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(HttpServletRequest request) throws UnsupportedEncodingException {
        String currentBaseUrl = HttpUtil.getCurrentBaseUrlWithoutPort(request);
        String encodeUrl = URLEncoder.encode(currentBaseUrl + "/c/wxhome/login", "utf-8");
        String url = OAuthUtil.getRedirectUrl(encodeUrl, OAuthScopeEnum.snsapi_base, "state");
        return "redirect:" + url;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody
    String login(HttpServletRequest request) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        OAuthUserAccessTokenModel model = OAuthUtil.getResponseObjectBy_snsapi_base(code);
        OAuthUserInfoModel info1 = OAuthUtil.getUserInfoBy_snsapi_userinfo(model);
        UserInfoModel info2 = UserUtil.getUserInfoByOpenId(model.openid);
        return "result";
    }

    @RequestMapping("/jssdk")
    public String jssdk() {
        return "wx/jssdk";
    }

    @RequestMapping("/jssdkconfig")
    public @ResponseBody
    JsSdkConfigModel jssdkconfig(HttpServletRequest request) {
        String url = request.getParameter("url");
        JsSdkConfigModel model = new JsSdkConfigModel();
        model.appId = WXConfigModel.getAppId();
        model.timestamp = System.currentTimeMillis() / 1000;
        model.nonceStr = SystemUtil.getRandomString(36);
        model.signature = JsSdkUtil.getSignature(model, url);
        return model;
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public @ResponseBody
    JsSdkPayConfigModel pay(HttpServletRequest request) {
        UnifiedOrderModel model = new UnifiedOrderModel();
        model.appid = WXConfigModel.getAppId();
        model.body = "testbody";
        model.mch_id = WXConfigModel.getMch_id();
        model.nonce_str = SystemUtil.getRandomString(30);
        model.notify_url = HttpUtil.getCurrentBaseUrlWithoutPort(request) + "/c/wxhome/paycallback";
        model.openid = "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o";
        model.out_trade_no = "201706301012001";
        model.spbill_create_ip = "192.168.1.1";
        model.total_fee = 1;
        model.trade_type = UnifiedOrderTradeTypeEnum.JSAPI;
        model.sign = PayUtil.getPrepaySignature(model);
        String prepay_id = PayUtil.getPrepay_id(model);
        JsSdkPayConfigModel m = new JsSdkPayConfigModel();
        m.appId = WXConfigModel.getAppId();
        m.nonceStr = SystemUtil.getRandomString(30);
        m.pkg = "prepay_id=" + prepay_id;
        m.signType = PaySignTypeEnum.MD5;
        m.timeStamp = System.currentTimeMillis() / 1000;
        m.paySign = PayUtil.getPaySignature(m);
        return m;
    }

    @RequestMapping(value = "/paycallback", method = RequestMethod.GET)
    public @ResponseBody
    String paycallback(HttpServletRequest request) {
        try {
            String payResult = DataTransferUtil.streamToString(request.getInputStream());
            PayCallBackModel model = (PayCallBackModel) DataTransferUtil.xmlToObject(payResult, PayCallBackModel.class);
            if (model.return_code.equals(PayResultEnum.SUCCESS) && model.result_code.equals(PayResultEnum.SUCCESS)) {
                PayResultResponseModel m = new PayResultResponseModel();
                m.return_code = PayResultEnum.SUCCESS;
                return DataTransferUtil.objectToXml(m, PayResultResponseModel.class);
            } else {

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    String test(HttpServletRequest request) {
        ButtonModel b1 = new ButtonModel("aaa1", MenuTypeEnum.click, "V1001_TODAY_MUSIC", null, null, null, null);
        ButtonModel b2 = new ButtonModel("bbb1", MenuTypeEnum.view, null, "http://www.soso.com/", null, null, null);
        ButtonModel b3 = new ButtonModel("ccc1", MenuTypeEnum.scancode_push, "rselfmenu_0_1", null, null, null, null);
        ButtonModel b4 = new ButtonModel("ddd1", MenuTypeEnum.pic_weixin, "rselfmenu_1_2", null, null, null, null);
        ButtonModel b5 = new ButtonModel("eee1", MenuTypeEnum.location_select, "rselfmenu_2_0", null, null, null, null);
        ButtonGroupModel g1 = new ButtonGroupModel("g1", b1, b2);
        ButtonGroupModel g2 = new ButtonGroupModel("g2", b3, b4);
        MenuModel m = new MenuModel(g1, g2, b5);
        MenuUtil.setMenu(m);
        return "result";
    }
}
