/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.wx.models.menu;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class ButtonModel extends ButtonBase {

    public ButtonModel(String name, String type, String key, String url, String media_id, String appid, String pagepath) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.url = url;
        this.media_id = media_id;
        this.appid = appid;
        this.pagepath = pagepath;
    }
}
