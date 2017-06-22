/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.management.menu;

import c.util.HttpUtil;
import c.wx.accesstoken.AccessTokenUtil;
import c.wx.models.menu.MenuModel;
import com.google.gson.Gson;

/**
 *
 * @author Administrator
 */
public class MenuUtil {

    public static String setMenu(MenuModel model) {
        Gson g = new Gson();
        String menu = g.toJson(model);
        String response = HttpUtil.Request_POST("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + AccessTokenUtil.getAccesstToken(), menu);
        return response;
    }
}
