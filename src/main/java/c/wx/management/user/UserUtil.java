/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.management.user;

import c.wx.models.user.UserInfoModel;
import com.google.gson.Gson;
import c.wx.accesstoken.AccessTokenUtil;
import c.util.HttpUtil;

public class UserUtil {

    public static UserInfoModel getUserInfoByOpenId(String openId) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", AccessTokenUtil.getAccesstToken(), openId);
        String rsp = HttpUtil.Request_GET(url);
        Gson gson = new Gson();
        UserInfoModel model = gson.fromJson(rsp, UserInfoModel.class);
        return model;
    }
}
