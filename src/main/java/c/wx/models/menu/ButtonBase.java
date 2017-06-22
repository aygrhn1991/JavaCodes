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
public class ButtonBase {

    public String name;
    public String type;
    public String key;
    public String url;
    public String media_id;
    public String appid;
    public String pagepath;
    public List<ButtonModel> sub_button;
}
