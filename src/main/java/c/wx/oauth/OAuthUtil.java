/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.oauth;

import c.wx.models.oauth.OAuthRefreshAccessTokenModel;
import c.wx.models.oauth.OAuthUserAccessTokenModel;
import c.wx.models.oauth.OAuthUserInfoModel;
import com.google.gson.Gson;
import java.util.Map;
import c.wx.config.WXConfigModel;
import c.util.DataTransferUtil;
import c.util.HttpUtil;

public class OAuthUtil {

    public static String getRedirectUrl(String encodeUrl, OAuthScopeEnum scope, String state) {
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect", WXConfigModel.getAppId(), encodeUrl, scope, state);
        return url;
    }

    public static String getOpenidBy_snsapi_base(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", WXConfigModel.getAppId(), WXConfigModel.getAppSecret(), code);
        String response = HttpUtil.Request_GET(url);
        Map<String, String> map = DataTransferUtil.jsonToMap(response);
        String openId = map.get("openid");
        return openId;
    }

    public static String getAccessTokenBy_snsapi_base(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", WXConfigModel.getAppId(), WXConfigModel.getAppSecret(), code);
        String response = HttpUtil.Request_GET(url);
        Map<String, String> map = DataTransferUtil.jsonToMap(response);
        String access_token = map.get("access_token");
        return access_token;
    }

    public static OAuthUserAccessTokenModel getResponseObjectBy_snsapi_base(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", WXConfigModel.getAppId(), WXConfigModel.getAppSecret(), code);
        String response = HttpUtil.Request_GET(url);
        Gson gson = new Gson();
        OAuthUserAccessTokenModel model = gson.fromJson(response, OAuthUserAccessTokenModel.class);
        return model;
    }

    public static OAuthUserInfoModel getUserInfoBy_snsapi_userinfo(OAuthUserAccessTokenModel model) {
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", model.access_token, model.openid);
        String rsp = HttpUtil.Request_GET(url);
        Gson gson = new Gson();
        OAuthUserInfoModel infoObj = gson.fromJson(rsp, OAuthUserInfoModel.class);
        return infoObj;
    }

    public static OAuthRefreshAccessTokenModel refreshAccessToken(String refreshToken) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s", WXConfigModel.getAppId(), refreshToken);
        String rsp = HttpUtil.Request_GET(url);
        Gson gson = new Gson();
        OAuthRefreshAccessTokenModel refreshAccessTokenObj = gson.fromJson(rsp, OAuthRefreshAccessTokenModel.class);
        return refreshAccessTokenObj;
    }
}
